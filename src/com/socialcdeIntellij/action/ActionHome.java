package com.socialcdeIntellij.action;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.Browser;
import com.socialcdeIntellij.object.GeneralButton;
import com.socialcdeIntellij.popup.*;
import com.socialcdeIntellij.shared.library.WOAuthData;
import com.socialcdeIntellij.shared.library.WService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Teo on 13/03/2015.
 */
public class ActionHome {
    HttpURLConnection conn = null;


    public ActionHome(HashMap<String, Object> uiData) {

        String widgetName = uiData.get("ID_action").toString();

        switch (widgetName) {

            case "lblAvatar":
                ChangeAvatar changeAvatar = new ChangeAvatar(Controller.getFrame());
                changeAvatar.setVisible(true);

                break;

            case "lblSkills":
                PopupSkill popupSkill = new PopupSkill(Controller.getFrame());
                popupSkill.setVisible(true);
                break;

            case "lblSettings":
                Controller.selectDynamicWindow(1);
                Controller.getWindow().revalidate();
                break;

            case "btnService":
                if (Controller.getProxy().IsWebServiceRunning()) {

                    GeneralButton gb = (GeneralButton) uiData.get("Object");
                    final WService service = gb.getService();


                    if (service.Registered) {

                        final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());
                        popupServiceRegistered.setVisible(true);

                        popupServiceRegistered.setService(service);
                        popupServiceRegistered.setServiceName(service.Name);
                        popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));

                        popupServiceRegistered.getUnsubscriveButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                int response = JOptionPane.showConfirmDialog(Controller.getFrame(), "Are you sure you want to unsubscribe?",
                                        "SocialCDE message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                                if(response == JOptionPane.OK_OPTION){
                                    if (!Controller.getProxy().DeleteRegistredService(Controller.getCurrentUser().Username,
                                            Controller.getCurrentUserPassword(),
                                            popupServiceRegistered.getService().Id)){
                                        JOptionPane.showMessageDialog(Controller.getFrame(),"Something was wrong, please try again.",
                                                "SocialCDE message",JOptionPane.ERROR_MESSAGE);
                                        popupServiceRegistered.dispose();
                                    }
                                    else {
                                        popupServiceRegistered.dispose();
                                        Controller.selectDynamicWindow(0);
                                        Controller.getWindow().revalidate();
                                    }
                                }


                            }
                        });

                        popupServiceRegistered.getSaveButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ArrayList<JCheckBox> btnCheckbox = popupServiceRegistered.getCheckboxCreated();

                                int counter = 0;
                                for (int i = 0; i < btnCheckbox.size(); i++) {
                                    if (btnCheckbox.get(i).isSelected()) {
                                        counter += 1;
                                    }
                                }

                                String[] strFeature = new String[counter];
                                counter = 0;
                                for (int i = 0; i < btnCheckbox.size(); i++) {
                                    if (btnCheckbox.get(i).isSelected()) {
                                        strFeature[counter] = btnCheckbox.get(i).getName();
                                        counter += 1;
                                    }
                                }

                                if (Controller.getProxy().UpdateChosenFeatures(
                                        Controller.getCurrentUser().Username,
                                        Controller.getCurrentUserPassword(),
                                        popupServiceRegistered.getService().Id, strFeature)) {
                                    popupServiceRegistered.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                            "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                                    popupServiceRegistered.dispose();

                                }
                            }
                        });
                    }
                    else {
                        if (service.RequireOAuth) {

                            final WOAuthData oauthData = Controller.getProxy()
                                    .GetOAuthData(
                                            Controller.getCurrentUser().Username,
                                            Controller.getCurrentUserPassword(),
                                            service.Id);

                            System.out.println("OauthData access secret "
                                    + oauthData.AccessSecret + "\n token "
                                    + oauthData.AccessToken + "\n oauth link "
                                    + oauthData.AuthorizationLink + "\n id "
                                    + service.Id);

                            final PinWindow pinWindow = new PinWindow(Controller.getFrame(), service);
                            pinWindow.setService(service);

                            if(service.OAuthVersion == 1){
                                pinWindow.setVisible(true);
                            }
                            else{
                                pinWindow.getLblPin().setText("Please authorize the application");
                                pinWindow.getTxtPin().setVisible(false);
                                pinWindow.setVisible(true);
                            }

                            Browser browser = new Browser();
                            browser.getWb().navigate(oauthData.AuthorizationLink);
                            browser.startBrowser();

                           /* final URL url;
                            final URI uri;

                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop desktop = Desktop.getDesktop();
                                    uri = new URI(oauthData.AuthorizationLink);
                                    desktop.browse(uri);
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } catch (URISyntaxException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }*/

                           // pinWindow.setService(service);
                            //pinWindow.setOauthData(oauthData);
                            System.out.println("service *** "+service);
                            System.out.println("oauthdata *** "+oauthData);


                            pinWindow.getBtnOk().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                   /* WOAuthData oauthData2 = Controller.getProxy()
                                            .GetOAuthData(
                                                    Controller.getCurrentUser().Username,
                                                    Controller.getCurrentUserPassword(),
                                                    service.Id);

                                    System.out.println("****PROVA****** \naccess secret "
                                            + oauthData2.AccessSecret + "\n token "
                                            + oauthData2.AccessToken + "\n oauth link "
                                            + oauthData2.AuthorizationLink + "\n id "
                                            + service.Id);*/



                                    switch (service.getOAuthVersion()) {

                                        case 1:
                                            System.out.println("******: "+pinWindow.getTxtPin().getText());
                                            if (!Controller.getProxy().Authorize(
                                                    Controller.getCurrentUser().Username,
                                                    Controller.getCurrentUserPassword(),
                                                    service.getId(),
                                                    //pinWindow.getService().Id,
                                                    pinWindow.getTxtPin().getText(),
                                                    oauthData.getAccessToken(),
                                                    oauthData.getAccessSecret())
                                                    /*pinWindow.getOauthData().AccessToken,
                                                    pinWindow.getOauthData().AccessSecret)*/) {
                                                pinWindow.dispose();
                                                //pinWindow.getService().Registered = true;
                                                service.Registered = true;
                                                pinWindow.setOauthData(null);

                                                {//inizio pannello interno service registered
                                                    final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());
                                                    popupServiceRegistered.setVisible(true);

                                                    popupServiceRegistered.setService(service);
                                                    popupServiceRegistered.setServiceName(service.Name);
                                                    popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));

                                                    popupServiceRegistered.getUnsubscriveButton().addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {

                                                            int response = JOptionPane.showConfirmDialog(Controller.getFrame(), "Are you sure you want to unsubscribe?",
                                                                    "SocialCDE message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                                                            if(response == JOptionPane.OK_OPTION){
                                                                if (!Controller.getProxy().DeleteRegistredService(Controller.getCurrentUser().Username,
                                                                        Controller.getCurrentUserPassword(),
                                                                        popupServiceRegistered.getService().Id)){
                                                                    JOptionPane.showMessageDialog(Controller.getFrame(),"Something was wrong, please try again.",
                                                                            "SocialCDE message",JOptionPane.ERROR_MESSAGE);
                                                                    popupServiceRegistered.dispose();
                                                                }
                                                                else {
                                                                    popupServiceRegistered.dispose();
                                                                    Controller.selectDynamicWindow(0);
                                                                    Controller.getWindow().revalidate();
                                                                }
                                                            }


                                                        }
                                                    });

                                                    popupServiceRegistered.getSaveButton().addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            ArrayList<JCheckBox> btnCheckbox = popupServiceRegistered.getCheckboxCreated();

                                                            int counter = 0;
                                                            for (int i = 0; i < btnCheckbox.size(); i++) {
                                                                if (btnCheckbox.get(i).isSelected()) {
                                                                    counter += 1;
                                                                }
                                                            }

                                                            String[] strFeature = new String[counter];
                                                            counter = 0;
                                                            for (int i = 0; i < btnCheckbox.size(); i++) {
                                                                if (btnCheckbox.get(i).isSelected()) {
                                                                    strFeature[counter] = btnCheckbox.get(i).getName();
                                                                    counter += 1;
                                                                }
                                                            }

                                                            if (Controller.getProxy().UpdateChosenFeatures(
                                                                    Controller.getCurrentUser().Username,
                                                                    Controller.getCurrentUserPassword(),
                                                                    popupServiceRegistered.getService().Id, strFeature)) {
                                                                popupServiceRegistered.dispose();
                                                            } else {
                                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                                        "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                                                                popupServiceRegistered.dispose();

                                                            }
                                                        }
                                                    });
                                                }//fine pannello interno service registered

                                                Controller.selectDynamicWindow(0);
                                                Controller.getWindow().revalidate();
                                            } else {
                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                        "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                                                pinWindow.dispose();
                                            }
                                            break;

                                        case 2:
                                            System.out.println("qui **** "+oauthData.getAccessToken());
                                           // System.out.println("speriamo *** "+ conn[0].getURL());
                                            if (Controller.getProxy().Authorize(
                                                    Controller.getCurrentUser().Username,
                                                    Controller.getCurrentUserPassword(),
                                                    service.getId(),
                                                    null,
                                                    oauthData.getAccessToken(),
                                                    null)) {
                                                pinWindow.dispose();
                                                pinWindow.getService().Registered = true;
                                                service.Registered = true;
                                                pinWindow.setOauthData(null);

                                                {//inizio pannello interno service registered
                                                    final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());
                                                    popupServiceRegistered.setVisible(true);

                                                    popupServiceRegistered.setService(service);
                                                    popupServiceRegistered.setServiceName(service.Name);
                                                    popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));

                                                    popupServiceRegistered.getUnsubscriveButton().addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {

                                                            int response = JOptionPane.showConfirmDialog(Controller.getFrame(), "Are you sure you want to unsubscribe?",
                                                                    "SocialCDE message", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                                                            if(response == JOptionPane.OK_OPTION){
                                                                if (!Controller.getProxy().DeleteRegistredService(Controller.getCurrentUser().Username,
                                                                        Controller.getCurrentUserPassword(),
                                                                        popupServiceRegistered.getService().Id)){
                                                                    JOptionPane.showMessageDialog(Controller.getFrame(),"Something was wrong, please try again.",
                                                                            "SocialCDE message",JOptionPane.ERROR_MESSAGE);
                                                                    popupServiceRegistered.dispose();
                                                                }
                                                                else {
                                                                    popupServiceRegistered.dispose();
                                                                    Controller.selectDynamicWindow(0);
                                                                    Controller.getWindow().revalidate();
                                                                }
                                                            }


                                                        }
                                                    });

                                                    popupServiceRegistered.getSaveButton().addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            ArrayList<JCheckBox> btnCheckbox = popupServiceRegistered.getCheckboxCreated();

                                                            int counter = 0;
                                                            for (int i = 0; i < btnCheckbox.size(); i++) {
                                                                if (btnCheckbox.get(i).isSelected()) {
                                                                    counter += 1;
                                                                }
                                                            }

                                                            String[] strFeature = new String[counter];
                                                            counter = 0;
                                                            for (int i = 0; i < btnCheckbox.size(); i++) {
                                                                if (btnCheckbox.get(i).isSelected()) {
                                                                    strFeature[counter] = btnCheckbox.get(i).getName();
                                                                    counter += 1;
                                                                }
                                                            }

                                                            if (Controller.getProxy().UpdateChosenFeatures(
                                                                    Controller.getCurrentUser().Username,
                                                                    Controller.getCurrentUserPassword(),
                                                                    popupServiceRegistered.getService().Id, strFeature)) {
                                                                popupServiceRegistered.dispose();
                                                            } else {
                                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                                        "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                                                                popupServiceRegistered.dispose();

                                                            }
                                                        }
                                                    });
                                                }//fine pannello interno service registered


                                                Controller.selectDynamicWindow(0);
                                                Controller.getWindow().revalidate();
                                            } else {
                                                pinWindow.dispose();
                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                        "SocialCDE message", JOptionPane.ERROR_MESSAGE);


                                                System.out.println("Autorizzazione non confermata");
                                            }

                                            break;

                                    }
                                }
                            });

                                pinWindow.getBtnCancel().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    pinWindow.dispose();
                                }
                            });

                            /*if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().browse(new URI(oauthData.AuthorizationLink));
                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } catch (URISyntaxException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }*/

                        }
                        else if(service.RequireTFSAuthentication) {

                            final TFSLogin tfsPanel = new TFSLogin(Controller.getFrame());
                            tfsPanel.setVisible(true);
                            tfsPanel.setService(service);

                            tfsPanel.getOkButton().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    HashMap<String, Object> tfsData = tfsPanel.getData();

                                    if (Controller.getProxy().RecordService(
                                            Controller.getCurrentUser().Username,
                                            Controller.getCurrentUserPassword(),
                                            tfsPanel.getService().Id,
                                            ((JTextField) tfsData.get("textUsername"))
                                                    .getText(),
                                            ((JTextField) tfsData.get("textPassword"))
                                                    .getText(),
                                            tfsData.get("textDomain").toString())) {
                                        tfsPanel.dispose();
                                        Controller.selectDynamicWindow(0);
                                        Controller.getWindow().revalidate();

                                    } else {
                                        JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                                        tfsPanel.dispose();

                                    }
                                }
                            });

                            tfsPanel.getCancelButton().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    tfsPanel.dispose();
                                }
                            });

                        }


                    }
                }
                else {
                    Controller.openConnectionLostPanel();
                }

                break;
            default:
                break;
        }
    }




}
