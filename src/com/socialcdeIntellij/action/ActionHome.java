package com.socialcdeIntellij.action;

import chrriis.dj.nativeswing.swtimpl.components.*;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
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
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.List;

/**
 * Created by Teo on 13/03/2015.
 */
public class ActionHome {

    public ActionHome(HashMap<String, Object> uiData) {

        String widgetName = uiData.get("ID_action").toString();

        switch (widgetName) {

            case "lblAvatar":
                ChangeAvatar changeAvatar = new ChangeAvatar(Controller.getFrame());
                //Controller.temporaryInformation.put("newAvatar",uiData.get("LabelAvatar"));
                changeAvatar.setSize(new Dimension(300, 300));
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

                    if (service.isRegistered()) {
                        Controller.temporaryInformation.put("service",service);

                        final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());

                        popupServiceRegistered.setServiceName(service.Name);
                        popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));
                        popupServiceRegistered.setVisible(true);

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

                            System.out.println("versione del SERVIZIO ***** "+service.getOAuthVersion());

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



                            final Browser browser = new Browser();

                            browser.startBrowser(oauthData.AuthorizationLink);

                            Controller.setCounter(Controller.getCounter()+1);
                            ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(Controller.getProject());
                            ToolWindow toolWindow = toolWindowManager.registerToolWindow("Browser"+Controller.getCounter(),true, ToolWindowAnchor.LEFT);
                            Content content= ContentFactory.SERVICE.getInstance().createContent(browser,"",true);
                            toolWindow.getContentManager().addContent(content);

                            toolWindow.show( new Runnable() {
                                @Override
                                public void run() {
                                    browser.setVisible(true);
                                }
                            });

                            if(service.OAuthVersion == 2){
                                browser.getWb().addWebBrowserListener(new WebBrowserListener() {

                                    @Override
                                    public void loadingProgressChanged(WebBrowserEvent wbe) {
                                        JWebBrowser wb = wbe.getWebBrowser();
                                        String pageUrl = wb.getResourceLocation();

                                        System.out.println("prova url:-> "+pageUrl);

                                        int progress = wb.getLoadingProgress();
                                        if (progress == 100) {
                                            System.out.println("nome servizio: "+service.Name);
                                            getAccessToken(pageUrl,service);
                                            System.out.println("C'è IL TOKEN??*** "+(String) Controller.temporaryInformation.get("AccessToken"));
                                        }

                                       /* try {

                                            int progress = wb.getLoadingProgress();
                                            if (progress == 100) {
                                                URL strUrl = new URL(pageUrl);
                                                if (pageUrl.contains("access_token=")){
                                                    System.out.println("QUIIIIIIIII :  "+splitQuery(strUrl).get("access_token"));

                                                    for(int i=0;i<splitQuery(strUrl).get("access_token").size();i++){
                                                        System.out.println("*** "+splitQuery(strUrl).get("access_token").get(i));
                                                    }
                                                    oauthData.setAccessToken(splitQuery(strUrl).get("access_token").get(0));
                                                }
                                                else if(pageUrl.contains("code=")) {
                                                    oauthData.setAccessToken(splitQuery(strUrl).get("code").get(0));
                                                }
                                                *//*String jsCommand = "";
                                                wb.executeJavascript(jsCommand);
                                                HttpUtility.ParseQueryString(pageUrl).Get(0)*//*
                                            }

                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }*/


                                    }

                                    @Override
                                    public void windowWillOpen(WebBrowserWindowWillOpenEvent webBrowserWindowWillOpenEvent) {

                                    }

                                    @Override
                                    public void windowOpening(WebBrowserWindowOpeningEvent webBrowserWindowOpeningEvent) {

                                    }

                                    @Override
                                    public void windowClosing(WebBrowserEvent webBrowserEvent) {

                                    }

                                    @Override
                                    public void locationChanging(WebBrowserNavigationEvent webBrowserNavigationEvent) {

                                    }

                                    @Override
                                    public void locationChanged(WebBrowserNavigationEvent webBrowserNavigationEvent) {

                                    }

                                    @Override
                                    public void locationChangeCanceled(WebBrowserNavigationEvent webBrowserNavigationEvent) {

                                    }


                                    @Override
                                    public void titleChanged(WebBrowserEvent webBrowserEvent) {

                                    }

                                    @Override
                                    public void statusChanged(WebBrowserEvent webBrowserEvent) {

                                    }

                                    @Override
                                    public void commandReceived(WebBrowserCommandEvent webBrowserCommandEvent) {

                                    }

                                });
                            }


                            System.out.println("INIZIO **** "+browser.getUrlString());

                            pinWindow.getBtnOk().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    switch (service.getOAuthVersion()) {

                                        case 1:
                                            System.out.println("******: "+pinWindow.getTxtPin().getText());
                                            if (Controller.getProxy().Authorize(
                                                    Controller.getCurrentUser().Username,
                                                    Controller.getCurrentUserPassword(),
                                                    service.getId(),
                                                    //pinWindow.getService().Id,
                                                    pinWindow.getTxtPin().getText(),
                                                    oauthData.getAccessToken(),
                                                    oauthData.getAccessSecret()))
                                                   /* pinWindow.getOauthData().AccessToken,
                                                    pinWindow.getOauthData().AccessSecret))*/ {
                                                pinWindow.dispose();
                                                //pinWindow.getService().Registered = true;
                                                service.Registered = true;
                                                Controller.getWindow().revalidate();
                                                pinWindow.setOauthData(null);
                                                Controller.temporaryInformation.put("service", service);

                                                {//inizio pannello interno service registered
                                                    final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());

                                                    popupServiceRegistered.setServiceName(service.Name);
                                                    popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));
                                                    popupServiceRegistered.setVisible(true);

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

                                        case 2://facebook
                                                System.out.println("QUA **** "+browser.getWb().getResourceLocation());

                                            if(!(((String) Controller.temporaryInformation.get("AccessToken")).isEmpty())){
                                                if (Controller.getProxy().Authorize(
                                                        Controller.getCurrentUser().Username,
                                                        Controller.getCurrentUserPassword(),
                                                        service.getId(),
                                                        null,
                                                        (String) Controller.temporaryInformation.get("AccessToken"),
                                                        null)) {
                                                    pinWindow.dispose();
                                                    service.Registered = true;
                                                    Controller.getWindow().revalidate();
                                                    pinWindow.setOauthData(null);
                                                    Controller.temporaryInformation.put("service", service);

                                                    {//inizio pannello interno service registered
                                                        final PopupServiceRegistered popupServiceRegistered = new PopupServiceRegistered(Controller.getFrame());

                                                        popupServiceRegistered.setServiceName(service.Name);
                                                        popupServiceRegistered.setImage(Controller.getServicesImage().get(service.Name));
                                                        popupServiceRegistered.setVisible(true);

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
                                                }
                                                else {
                                                    pinWindow.dispose();
                                                    JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                            "SocialCDE message", JOptionPane.ERROR_MESSAGE);


                                                    System.out.println("Autorizzazione non confermata");
                                                }
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
                                            Controller.getProxy().getHost())) {
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


    public void getAccessToken(String event, WService service){
        if (service != null && service.Name.equals("Facebook") && event.contains("#")) {
            Controller.temporaryInformation
                    .put("AccessToken",
                            event.split("#")[1].toString().split("&")[0].toString().split("=")[1].toString());

        } else if (service != null && service.Name.equals("LinkedIn") && event.contains("?")) {

            Controller.temporaryInformation.put("AccessToken", event.split("=")[1].toString());

        } else if ( service != null && event.contains("?") && service.Name.equals("GitHub")) {
            Controller.temporaryInformation.put("AccessToken", event.split("=")[1].toString());
        }
        else if (service != null &&
                service.Name.equals("Yammer")
                && event.contains("?")
                && !event.contains("&")) {
            Controller.temporaryInformation.put("AccessToken", event.split("=")[1].toString());
        }

    }

    public static Map<String, List<String>> splitQuery(URL url) throws UnsupportedEncodingException {
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }
}
