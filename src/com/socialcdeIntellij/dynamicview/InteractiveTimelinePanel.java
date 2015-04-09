/*
 * Created by JFormDesigner on Mon Mar 02 12:18:31 CET 2015
 */

package com.socialcdeIntellij.dynamicview;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.action.ActionInteractiveTimeline;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.object.LabelClicked;
import com.socialcdeIntellij.object.PanelArea;
import com.socialcdeIntellij.shared.library.WPost;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * @author Davide Rossi
 */
public class InteractiveTimelinePanel extends JPanel {

    private WPost[] posts;
    private static int totalField = 0;
    private static long timerUpdate = 0;
    ImagesMod im = new ImagesMod();
    private JLabel otherPostAvailable;
    private JLabel noPostAvailable;
    private JLabel labelDownloadPost;
    private JPanel DownloadOlderPosts;
    private JPanel controlToPost;
    private JPanel userPanel;
    private LabelClicked lblImgAvatar;
    private LabelClicked lblUsername;
    private JPanel pnlUser;
    private JPanel pnl;
    private JPanel subPanel;
    private final JScrollPane scrollPane1 = new JScrollPane();
    private JPanel panelDynamic;
    private JTextArea textArea1;
    private JPanel panelMsg;
    private JLabel lblEnter;
    private ActionGeneral listener = new ActionGeneral();
    private static String fileSelected;
    private String tempFileSelected;
    JPanel jp1;
    JPanel jp2;
    private JTextArea mytextArea;
    private JScrollPane scrollPaneArea;


    public InteractiveTimelinePanel() {
        initComponents();

    }

    private void initComponents() {

        Editor editor = null;
        tempFileSelected = "";
        try {
            editor = FileEditorManager.getInstance(Controller.getProject()).getSelectedTextEditor();

                   /* PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();*/

            if (editor != null) {
                tempFileSelected = editor.getContentComponent().getToolTipText();
            }
        } catch (Exception e) {
            fileSelected = "";
        }


        fileSelected = tempFileSelected;

        timerUpdate = 0;

        jp1 = new JPanel(new FlowLayout());
        jp2 = new JPanel(new FlowLayout());
        panelDynamic = new JPanel();
        panelMsg = new JPanel();
        lblEnter = new JLabel();
        subPanel = new JPanel(new VerticalLayout(10));
        subPanel.setBackground(Color.WHITE);

        setLayout(new VerticalLayout(15));

        //========= scrollPane ==========
        {
            //======== panelDynamic ========
            {
                panelDynamic.setBackground(Color.white);
                panelDynamic.setBorder(new BevelBorder(BevelBorder.LOWERED));
                panelDynamic.setLayout(new VerticalLayout(15));

                insertTimeline(subPanel);

                panelDynamic.add(subPanel);

                long secondCallpostStartTime = System.currentTimeMillis();

                WPost[] newPost = Controller.getProxy().GetInteractiveTimeline(Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword(), "", fileSelected, "File", ActionInteractiveTimeline.getLastId(), 0);

                if (newPost == null || newPost.length == 2) {
                    newPost = new WPost[0];
                }

                otherPostAvailable = new JLabel("<html><a>Click to view older posts</a></html>");
                otherPostAvailable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                otherPostAvailable.setName("otherPostAvailable");

                jp1.add(otherPostAvailable);
                panelDynamic.add(jp1);

                noPostAvailable = new JLabel("<html>There are no post in the cache.<br> Please try again in two minutes.</html>");

                jp2.add(noPostAvailable);
                panelDynamic.add(jp2);


                if (newPost.length != 2 && newPost.length >0) {
                    jp1.setVisible(true);
                    jp2.setVisible(false);

                }
                else {
                    jp1.setVisible(false);
                    jp2.setVisible(true);
                }


            }

            scrollPane1.setPreferredSize(new Dimension(0, 450));
            scrollPane1.setViewportView(panelDynamic);
            add(scrollPane1);

        }

        //======== panelMsg ========
        {
            panelMsg.setLayout(new FlowLayout());
            //JPanel panelMsg2 = new JPanel(new HorizontalLayout(15));
            JPanel panelMsg2 = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));

            //---- customTextArea1 ----
            mytextArea = new JTextArea();
            mytextArea.setLineWrap(true);
            mytextArea.setWrapStyleWord(true);
            mytextArea.setBorder(null);

            scrollPaneArea = new JScrollPane();

            scrollPaneArea.setViewportView(mytextArea);
            scrollPaneArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPaneArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPaneArea.setPreferredSize(new Dimension(218, 42));

            PanelArea pa = new PanelArea(new FlowLayout());
            pa.setOpaque(false);
            pa.setPreferredSize(new Dimension(218,60));

            pa.setVisible(true);
            pa.add(scrollPaneArea);
            panelMsg2.add(pa);

            //---- lblEnter ----
            JPanel jp2 = new JPanel(new FlowLayout());
            try {
                lblEnter.setIcon(new ImageIcon(im.getSEND_MESSAGE(32, 32)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblEnter.setToolTipText("Send message");
            lblEnter.setName("btnSendMessage");
            lblEnter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblEnter.setHorizontalAlignment(SwingConstants.LEFT);
            lblEnter.setVerticalAlignment(SwingConstants.TOP);
            lblEnter.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            lblEnter.setVerticalTextPosition(SwingConstants.TOP);
            lblEnter.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblEnter.setAlignmentY(Component.CENTER_ALIGNMENT);
            jp2.add(lblEnter);
            panelMsg2.add(jp2);
            panelMsg.add(panelMsg2);

            lblEnter.addMouseListener(listener);
            otherPostAvailable.addMouseListener(listener);

        }

        add(panelMsg);


        final int time = 10000;
        final Runnable timer = new Runnable() {
            public void run() {

                if (timerUpdate == 0) {
                    System.out.println("Chiamata eseguita " + Calendar.getInstance().getTime().toString());
                    //updateTimeline();
                    panelDynamic.revalidate();
                    timerUpdate = Calendar.getInstance().getTimeInMillis();
                    System.out.println("Acquisito " + Calendar.getInstance().getTime().toString());
                } else {
                    long tempTimer = Calendar.getInstance().getTimeInMillis();
                    System.out.println("Acquisito 2" + Calendar.getInstance().getTime().toString());
                    System.out.println("Confronto " + (tempTimer - timerUpdate));
                    if ((tempTimer - timerUpdate) < 10010 && (tempTimer - timerUpdate) > 9990) {
                        System.out.println("Chiamata eseguita parte 2 " + Calendar.getInstance().getTime().toString());
                        updateTimeline(subPanel);

                        timerUpdate = System.currentTimeMillis();
                    }
                    timerUpdate = tempTimer;
                }
            }

        };

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scrollPane1.getVerticalScrollBar().setValue(0);
            }
        });

    }

    public static String getFileSelected() {
        return fileSelected;
    }

    public static void setFileSelected(String fileSelected) {
        InteractiveTimelinePanel.fileSelected = fileSelected;
    }

    public HashMap<String, Object> getData() {

        HashMap<String, Object> uiData = new HashMap<String, Object>();

        uiData.put("LabelSendMessage", lblEnter);
        uiData.put("TextMessage", mytextArea);
        uiData.put("Panel", this);
        uiData.put("panelDynamic", panelDynamic);
        uiData.put("scroll", scrollPane1);
        uiData.put("PanelOtherPost", jp1);
        uiData.put("PanelNoPost", jp2);
        uiData.put("PanelSubDynamic", subPanel);

        return uiData;
    }

    private String findLink(String message) {
        String[] subsequences = message.split(" ");

        String result = "";
        for(int i=0;i<subsequences.length; i++)
        {
            if(result.equals(""))
            {
                if(subsequences[i].contains("http"))
                {
                    result = "<html><a href=\" " + subsequences[i] + "\" > " + subsequences[i] + "</a></html> ";
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
                    result += " <html><a href=\" " + subsequences[i] + "\" > " + subsequences[i] + "</a></html> ";
                }
                else
                {
                    result += subsequences[i] + " ";
                }
            }
        }

        return result;
    }

    public void insertTimeline(final JPanel panel) {


        long postStartTime = System.currentTimeMillis();

        if(fileSelected == null || fileSelected.equals(""))
        {
            System.out.println("File selected nullo o vuoto");
            posts = new WPost[0];
        }
        else
        {
            System.out.println("Richiesta post inviata ");
            posts = Controller.getProxy().GetInteractiveTimeline(Controller.getCurrentUser().Username,Controller.getCurrentUserPassword(),"",fileSelected,"File");
            System.out.println("Numero post ottenuti " + posts.length);

            posts = (posts.length == 2 ? new WPost[0] : posts);

        }

        long postEndTime = System.currentTimeMillis();

        ActionInteractiveTimeline.setLastId(0);

        for (int i = 0; i < posts.length; i++) {


            final int j = i;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    pnl = new JPanel(new HorizontalLayout(3));
                    //pnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
                    pnl.setBackground(Color.WHITE);
                    JPanel pnl2 = new JPanel(new VerticalLayout(2));
                    pnl2.setBackground(Color.WHITE);

                    //userPostComposite.setData("IdPost", posts[j].Id);
                    lblImgAvatar = new LabelClicked();
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
                        lblImgAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        lblImgAvatar.setToolTipText("View "
                                + posts[j].getUser().Username + " Timeline");
                        // Controller.temporaryInformation.put("User_selected", posts[j].getUser());

                        lblImgAvatar.getLabel().setwUser(posts[j].getUser());
                        //lblImgAvatar.setUserType("");
                        lblImgAvatar.addMouseListener(listener);

                    }
                    pnl.add(lblImgAvatar);

                    lblUsername = new LabelClicked();
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
                    panel.add(pnl);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            scrollPane1.getVerticalScrollBar().setValue(0);
                        }
                    });
                }

            });


            ActionInteractiveTimeline.setLastId(posts[i].Id);

        }
    }

    public void updateTimeline(final JPanel panel) {


        Editor editor = null;
        tempFileSelected = "";
        try {
            editor = FileEditorManager.getInstance(Controller.getProject()).getSelectedTextEditor();

                   /* PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();*/

            if (editor != null) {
                tempFileSelected = editor.getContentComponent().getToolTipText();
            }
        } catch (Exception e) {
            fileSelected = "";
        }

        System.out.println("temp " + tempFileSelected + " presente " + fileSelected);

        if (!tempFileSelected.equals(fileSelected)) {
            System.out.println(" Aggiornamento concesso");
            Controller.selectDynamicWindow(5);
        } else {
            System.out.println(" Aggiornamento non concesso");
            if (fileSelected.equals("")) {
                posts = new WPost[0];
            } else if (panelDynamic.getComponentCount() == 1) {
                posts = Controller.getProxy().GetInteractiveTimeline(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword(), "", fileSelected, "File");

                if (posts == null || posts.length == 2) {
                    posts = new WPost[0];
                }

            } else {
                posts = Controller.getProxy().GetInteractiveTimeline(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword(), "", fileSelected,
                        "File", panelDynamic.getComponentCount(), 0);
            }

            //****************************************
            for (WPost element : posts) {
                System.out.println("Elemento con id " + element.Id + " inviato da " + element.User.Username + " il " + element.CreateAt.getTime().toString());
            }

            //Controller.temporaryInformation.put("CurrentComposite", panelDynamic.getComponentCount()userPostMaster.getChildren()[0]);

            if (posts.length > 0 && posts[0].Id != panelDynamic.getComponentCount()) {
                boolean flag = true;

                for (int i = 0; i < posts.length; i++) {

                    if (posts[i].Id == panelDynamic.getComponentCount()) {
                        flag = false;
                    }

                    if (flag) {

                        final int j = i;

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                pnl = new JPanel(new HorizontalLayout(3));
                                //pnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
                                pnl.setBackground(Color.WHITE);
                                JPanel pnl2 = new JPanel(new VerticalLayout(2));
                                pnl2.setBackground(Color.WHITE);

                                //userPostComposite.setData("IdPost", posts[j].Id);
                                lblImgAvatar = new LabelClicked();
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
                                    lblImgAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblImgAvatar.setToolTipText("View "
                                            + posts[j].getUser().Username + " Timeline");
                                    // Controller.temporaryInformation.put("User_selected", posts[j].getUser());

                                    lblImgAvatar.getLabel().setwUser(posts[j].getUser());
                                    //lblImgAvatar.setUserType("");
                                    lblImgAvatar.addMouseListener(listener);

                                }
                                pnl.add(lblImgAvatar);

                                lblUsername = new LabelClicked();
                                //lblUsername.setName("lblUsername");

                                lblUsername.getLabel().setText(posts[j].getUser().Username);
                                if (!posts[j].getUser().Username.equals(Controller
                                        .getCurrentUser().Username)) {
                                    lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblUsername.setToolTipText("View " + posts[j].getUser().Username + " Timeline");
                                    lblUsername.getLabel().setFont(new Font("Calibri", Font.BOLD, 15));
                                    lblUsername.getLabel().setForeground(Color.BLUE);

                                    lblUsername.getLabel().setwUser(posts[j].getUser());
                                    lblUsername.addMouseListener(listener);
                                } else {
                                    lblUsername.getLabel().setFont(new Font("Calibri", Font.BOLD, 15));
                                    lblUsername.getLabel().setForeground(Color.BLACK);
                                }

                                pnl2.add(lblUsername);


                                JTextArea message = new JTextArea();
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
                                panel.add(pnl);

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollPane1.getVerticalScrollBar().setValue(0);
                                    }
                                });
                            }

                        });


                    }
                }

            }

        }
    }
}
