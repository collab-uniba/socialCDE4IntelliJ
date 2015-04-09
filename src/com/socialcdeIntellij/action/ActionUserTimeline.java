package com.socialcdeIntellij.action;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.object.LabelClicked;
import com.socialcdeIntellij.popup.HidePanel;
import com.socialcdeIntellij.popup.PopupSkill;
import com.socialcdeIntellij.shared.library.WPost;
import com.socialcdeIntellij.shared.library.WUser;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Teo on 20/03/2015.
 */
public class ActionUserTimeline {
    private static long lastId;

    public ActionUserTimeline(final HashMap<String, Object> uiData) {

        String widgetName = uiData.get("ID_action").toString();

        int type = (int) uiData.get("Event_type");
        final WUser user_selected = ((WUser) uiData.get("userSelected"));
        final ActionGeneral listener = new ActionGeneral();
        ImagesMod im = new ImagesMod();


        switch (widgetName) {

            case "lblReturn":

                if(Controller.getProxy().IsWebServiceRunning()) {
                    String str = (String) Controller.temporaryInformation.get("PrePanel");

                    switch (str){
                        case "HomeTimeline":
                            Controller.selectDynamicWindow(3);
                            Controller.getWindow().revalidate();
                            break;

                        case "InteractiveTimeline":
                            Controller.selectDynamicWindow(5);
                            Controller.getWindow().revalidate();
                            break;

                        case "IterationTimeline":
                            Controller.selectDynamicWindow(4);
                            Controller.getWindow().revalidate();
                            break;

                        case "PeoplePanel":
                            Controller.selectDynamicWindow(2);
                            Controller.getWindow().revalidate();

                    }

                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            case "lblFollow":
                if(Controller.getProxy().IsWebServiceRunning()) {
                    if (Controller.getProxy().Follow(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword(), user_selected.Id)) {
                        ((JLabel) uiData.get("labelFollow")).setVisible(false);
                        ((JLabel) uiData.get("labelUnfollow")).setVisible(true);

                    }
                    else{
                        JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            case "lblUnfollow":
                if(Controller.getProxy().IsWebServiceRunning()) {

                    if (Controller.getProxy().UnFollow(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword(), user_selected.Id)) {

                        ((JLabel) uiData.get("labelUnfollow")).setVisible(false);
                        ((JLabel) uiData.get("labelFollow")).setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                "SocialCDE message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            case "lblSkill":
                if(Controller.getProxy().IsWebServiceRunning()) {
                    PopupSkill skillPanel = new PopupSkill(Controller.getFrame());
                    skillPanel.setUser(user_selected);
                    skillPanel.setVisible(true);
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            case "lblHide":
                if(Controller.getProxy().IsWebServiceRunning()) {
                    Controller.temporaryInformation.put("CurrentUserView", user_selected);
                    HidePanel hd = new HidePanel(Controller.getFrame());
                    hd.setVisible(true);
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;

            case "otherPostAvailable":
                if(Controller.getProxy().IsWebServiceRunning()) {

                    final WPost[] posts = Controller.getProxy().GetUserTimeline(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword(),
                            ((WUser) uiData.get("userSelected")).Username, 0,
                            getLastId());

                    for (int i = 0; i < posts.length; i++) {
                        final int j = i;


                        JPanel pnl = new JPanel(new HorizontalLayout(3));
                       // pnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
                        pnl.setBackground(Color.WHITE);
                        JPanel pnl2 = new JPanel(new VerticalLayout(2));
                        pnl2.setBackground(Color.WHITE);

                        //userPostComposite.setData("IdPost", posts[j].Id);
                        LabelClicked lblImgAvatar = new LabelClicked();
                        //lblImgAvatar.setName("lblUser");

                        if (Controller.getUsersAvatar().get(posts[j].getUser().Username) == null) {
                            Controller.getUsersAvatar().put(posts[j].getUser().Username, im.getUserImage(posts[j].getUser().Avatar));
                        }
                        //lblImgAvatar.setIcon(new ImageIcon(Controller.getUsersAvatar().get(posts[j].getUser().Username)));
                        try {
                            lblImgAvatar.getLabel().setIcon(new ImageIcon(im.resize((BufferedImage) Controller.getUsersAvatar().get(posts[j].getUser().Username), 50, 50)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (!posts[j].getUser().Username.equals(Controller
                                .getCurrentUser().Username)) {
                            //lblImgAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            lblImgAvatar.setToolTipText("View "
                                    + posts[j].getUser().Username + " Timeline");
                            // Controller.temporaryInformation.put("User_selected", posts[j].getUser());

                            lblImgAvatar.getLabel().setwUser(posts[j].getUser());
                            //lblImgAvatar.setUserType("");
                            lblImgAvatar.addMouseListener(listener);

                        }
                        pnl.add(lblImgAvatar);

                        LabelClicked lblUsername = new LabelClicked();
                        //lblUsername.setName("lblUsername");

                        lblUsername.getLabel().setText(posts[j].getUser().Username);
                        if (!posts[j].getUser().Username.equals(Controller
                                .getCurrentUser().Username)) {
                            lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            lblUsername.setToolTipText("View " + posts[j].getUser().Username + " Timeline");
                            lblUsername.getLabel().setFont(new Font("Calibri", Font.BOLD, 15));
                            lblUsername.getLabel().setForeground(Color.BLUE);
                            //Controller.temporaryInformation.put("User_selected", posts[j].getUser());

                            lblUsername.getLabel().setwUser(posts[j].getUser());
                            lblUsername.addMouseListener(listener);
                        } else {
                            lblUsername.getLabel().setFont(new Font("Calibri", Font.BOLD, 15));
                            lblUsername.getLabel().setForeground(Color.BLACK);
                        }

                        pnl2.add(lblUsername);


                        JTextArea message = new JTextArea();
                        message.setPreferredSize(new Dimension(160,60));
                        message.setFont(new Font("Calibri", Font.ITALIC, 11));
                        message.setLineWrap(true);
                        message.setWrapStyleWord(true);
                        message.setEditable(false);
                        message.setBackground(Color.WHITE);
                        message.setText(posts[j].getMessage());
                        pnl2.add(message);


                        Calendar nowDate = Calendar.getInstance();
                        Calendar dateSelected = posts[j].getCreateAt();
                        long millisDiff = nowDate.getTime().getTime()
                                - dateSelected.getTime().getTime();

                        int seconds = (int) (millisDiff / 1000 % 60);
                        int minutes = (int) (millisDiff / 60000 % 60);
                        int hours = (int) (millisDiff / 3600000 % 24);
                        int days = (int) (millisDiff / 86400000);

                        JLabel messageDate = new JLabel();

                        if (days > 1 && days < 30) {
                            messageDate.setText("About " + days + " days ago from "
                                    + posts[j].getService().getName());
                        } else if (days > 30) {
                            messageDate.setText("More than one month ago from "
                                    + posts[j].getService().getName());
                        } else if (days == 1) {
                            messageDate.setText("About " + days + " day ago from "
                                    + posts[j].getService().getName());
                        } else {
                            if (hours > 1) {
                                messageDate.setText("About " + hours
                                        + " hours ago from "
                                        + posts[j].getService().getName());
                            } else if (hours == 1) {
                                messageDate.setText("About " + hours
                                        + " hour ago from "
                                        + posts[j].getService().getName());
                            } else {

                                if (minutes > 1) {
                                    messageDate.setText("About " + minutes
                                            + " minutes ago from "
                                            + posts[j].getService().getName());
                                } else if (minutes == 1) {
                                    messageDate.setText("About " + minutes
                                            + " minute ago from "
                                            + posts[j].getService().getName());
                                } else {

                                    if (seconds > 1) {
                                        messageDate.setText("About " + seconds
                                                + " seconds ago from "
                                                + posts[j].getService().getName());
                                    } else if (seconds == 1) {
                                        messageDate.setText("About " + seconds
                                                + " second ago from "
                                                + posts[j].getService().getName());
                                    } else {
                                        messageDate.setText("Few seconds ago from "
                                                + posts[j].getService().getName());

                                    }
                                }
                            }
                        }
                        messageDate.setFont(new Font("Calibri", Font.ITALIC, 8));
                        messageDate.setForeground(Color.LIGHT_GRAY);
                        pnl2.add(messageDate);
                        pnl.add(pnl2);
                        // panel.add(pnl);




                        ((JPanel) uiData.get("panelDynamic")).remove(((JPanel) uiData.get("PanelOtherPost")));

                        ((JPanel) uiData.get("panelDynamic")).add(pnl);

                        ((JPanel) uiData.get("panelDynamic")).revalidate();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                ((JScrollPane)uiData.get("scroll")).getVerticalScrollBar().setValue(0);
                            }
                        });

                        setLastId(posts[i].Id);
                    }


                    WPost[] newPosts = Controller.getProxy().GetUserTimeline(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword(),
                            ((WUser) uiData.get("userSelected")).Username, 0,
                            getLastId());

                    if (newPosts == null || newPosts.length == 2) {
                        newPosts = new WPost[0];
                    }

                    if (newPosts.length > 0) {

                        ((JPanel) uiData.get("panelDynamic")).add(((JPanel) uiData.get("PanelOtherPost")));
                        ((JPanel) uiData.get("PanelOtherPost")).setVisible(true);
                        ((JPanel)uiData.get("PanelNoPost")).setVisible(false);


                    } else {

                        ((JPanel) uiData.get("panelDynamic")).add( ((JPanel)uiData.get("PanelNoPost")));
                        ((JPanel)uiData.get("PanelOtherPost")).setVisible(false);
                        ((JPanel)uiData.get("PanelNoPost")).setVisible(true);

                    }
                }
                else
                {
                    Controller.openConnectionLostPanel();
                }
                break;


            default:
                break;


        }
    }



    public static long getLastId() {
        return lastId;
    }

    public static void setLastId(long lastId) {
        ActionUserTimeline.lastId = lastId;
    }

    private String findLink(String message){
        String[] subsequences = message.split(" ");
        String result = "";
        for(int i=0;i<subsequences.length; i++)
        {
            if(result.equals(""))
            {
                if(subsequences[i].contains("http"))
                {
                    result = "<a href=\" " + subsequences[i] + "\" > " + subsequences[i] + "</a> ";
                }
                else
                {
                    result = subsequences[i] + " ";
                }
            }
            else
            {
                if(subsequences[i].contains("http"))
                {
                    result += "<a href=\" " + subsequences[i] + "\" > " + subsequences[i] + "</a> ";
                }
                else
                {
                    result += subsequences[i] + " ";
                }
            }
        }

        return result;
    }
}
