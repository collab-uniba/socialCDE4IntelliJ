package com.socialcdeIntellij.dynamicview;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.action.ActionUserTimeline;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.shared.library.WPost;
import com.socialcdeIntellij.shared.library.WUser;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;


public class UserTimelinePanel extends JPanel {
    ImagesMod im = new ImagesMod();
    WUser userSelected;
    private String userType;
    private WPost[] posts;
    private static int totalField;
    private JPanel pnl;
    private JLabel otherPostAvailable;
    private JLabel noPostAvailable;
    ActionGeneral listener = new ActionGeneral();
    private JLabel lblUnfollow;
    private JPanel panelReturn;
    private JLabel lblUsername;
    private JLabel lblImgAvatar;
    private JPanel panelProfile;
    private JLabel lblReturn;
    private JLabel lblAvatar;
    private JPanel panelInfo;
    private JPanel panelSettings;
    private JLabel lblNickname;
    private JPanel panelInfo2;
    private JPanel panelPost;
    private JLabel lblPosts;
    private JLabel lblNumPost;
    private JPanel panelfollowing;
    private JLabel lblFollowing;
    private JLabel lblNumFollowing;
    private JPanel panelFollowers;
    private JLabel lblFollowers;
    private JLabel lblNumFollowers;
    private JPanel panelIcon;
    private JLabel lblFollow;
    private JLabel lblSkill;
    private JLabel lblHide;
    private JScrollPane scrollPane1;
    private JPanel panelDynamic;


    public UserTimelinePanel() {
        initComponents();
    }

    private void initComponents() {
        panelProfile = new JPanel();
        lblReturn = new JLabel();
        lblAvatar = new JLabel();
        panelInfo = new JPanel();
        panelSettings = new JPanel();
        lblNickname = new JLabel();
        panelInfo2 = new JPanel();
        panelPost = new JPanel();
        lblPosts = new JLabel();
        lblNumPost = new JLabel();
        panelfollowing = new JPanel();
        lblFollowing = new JLabel();
        lblNumFollowing = new JLabel();
        panelFollowers = new JPanel();
        lblFollowers = new JLabel();
        lblNumFollowers = new JLabel();
        panelIcon = new JPanel();
        lblFollow = new JLabel();
        lblUnfollow = new JLabel();
        lblSkill = new JLabel();
        lblHide = new JLabel();
        scrollPane1 = new JScrollPane();
        panelDynamic = new JPanel();
        panelReturn = new JPanel();
        lblUsername = new JLabel();
        lblImgAvatar = new JLabel();

        //======== this ========

        setLayout(new VerticalLayout());

        userSelected = Controller.getProxy()
                .GetColleagueProfile(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword(),
                        ((WUser) Controller.temporaryInformation.get("User_selected")).Id);

        userType = ((String) Controller.temporaryInformation.get("UserType"));

        //======== panelProfile ========
        {
            panelProfile.setBackground(Color.white);
            panelProfile.setLayout(new HorizontalLayout(5));

            //---- lblReturn ----
            panelReturn.setLayout(new FlowLayout(FlowLayout.LEFT));
            lblReturn.setIcon(new ImageIcon(getClass().getResource("/images/Toolbar/Back.png")));
            lblReturn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblReturn.setBackground(new Color(204, 204, 204));
            panelReturn.add(lblReturn);
            add(panelReturn);


            //---- lblImgAvatar ----

            if(Controller.getUsersAvatar().get(userSelected.Username) == null)
            {
                Controller.getUsersAvatar().put(userSelected.Username, im.getUserImage(userSelected.Avatar));
            }

            try {

                lblImgAvatar.setIcon(new ImageIcon(im.resize((BufferedImage) Controller.getUsersAvatar().get(userSelected.Username), 75, 75)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblImgAvatar.setName("lblImgAvatar");
            lblImgAvatar.setVisible(true);
            panelProfile.add(lblImgAvatar);

            //======== panelInfo ========
            {
                panelInfo.setBackground(Color.white);
               // panelInfo.setLayout(new GridLayout(2, 0, 0, 10));
                panelInfo.setLayout(new VerticalLayout(5));

                //======== panelSettings ========
                {
                    panelSettings.setBackground(Color.white);
                    //panelSettings.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
                    panelSettings.setLayout(new GridLayout(1, 2, 50, 0));

                    //---- lblUsername----
                    lblUsername.setText(userSelected.Username);
                    lblUsername.setHorizontalAlignment(SwingConstants.CENTER);

                    panelSettings.add(lblUsername);
                }
                panelInfo.add(panelSettings);

                //======== panelInfo2 ========
                {
                    panelInfo2.setBackground(Color.white);
                    panelInfo2.setLayout(new GridLayout(1, 3, 10, 0));
                   // panelInfo2.setLayout(new FlowLayout());

                    //======== panelPost ========
                    {
                        panelPost.setBackground(Color.white);
                        panelPost.setBorder(new MatteBorder(0, 0, 0, 1, Color.black));
                        //panelPost.setLayout(new GridLayout(2, 1));
                        panelPost.setLayout(new VerticalLayout(2));
                        panelPost.setSize(new Dimension(20,10));

                        //---- lblPosts ----
                        lblPosts.setText("Posts");
                        lblPosts.setHorizontalAlignment(SwingConstants.CENTER);
                        panelPost.add(lblPosts);

                        //---- lblNumPost ----
                        lblNumPost.setText(String.valueOf(userSelected.getStatuses()));
                        lblNumPost.setHorizontalAlignment(SwingConstants.CENTER);
                        panelPost.add(lblNumPost);
                    }
                    panelInfo2.add(panelPost);

                    //======== panelfollowing ========
                    {
                        panelfollowing.setBackground(Color.white);
                        //panelfollowing.setLayout(new GridLayout(2, 1));
                        panelfollowing.setLayout(new VerticalLayout(2));
                        panelfollowing.setSize(new Dimension(20, 10));
                       // panelfollowing.setBorder(new MatteBorder(0, 1, 0, 0, Color.black));

                        //---- lblFollowing ----
                        lblFollowing.setText("Following");
                        lblFollowing.setHorizontalAlignment(SwingConstants.CENTER);
                        panelfollowing.add(lblFollowing);

                        //---- lblNumFollowing ----
                        lblNumFollowing.setText(String.valueOf(userSelected.getFollowings()));
                        lblNumFollowing.setHorizontalAlignment(SwingConstants.CENTER);
                        panelfollowing.add(lblNumFollowing);
                    }
                    panelInfo2.add(panelfollowing);

                    //======== panelFollowers ========
                    {
                        panelFollowers.setBackground(Color.white);
                        panelFollowers.setBorder(new MatteBorder(0, 1, 0, 0, Color.black));
                        panelFollowers.setPreferredSize(new Dimension(70, 32));
                        //panelFollowers.setLayout(new GridLayout(2, 1));
                        panelFollowers.setLayout(new VerticalLayout(2));
                        panelFollowers.setSize(new Dimension(20,10));

                        //---- lblFollowers ----

                        lblFollowers.setText("Followers");
                        lblFollowers.setHorizontalAlignment(SwingConstants.CENTER);
                        lblFollowers.setHorizontalTextPosition(SwingConstants.CENTER);
                        panelFollowers.add(lblFollowers);

                        //---- lblNumFollowers ----
                        lblNumFollowers.setText(String.valueOf(userSelected.getFollowers()));
                        lblNumFollowers.setHorizontalAlignment(SwingConstants.CENTER);
                        panelFollowers.add(lblNumFollowers);
                    }
                    panelInfo2.add(panelFollowers);
                }
                panelInfo.add(panelInfo2);
            }
            panelProfile.add(panelInfo);
        }
        add(panelProfile);

        //======== panelIcon ========
        {
            panelIcon.setBackground(Color.white);
            panelIcon.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

           if(userType == "Suggested"){
                //---- lblFollow ----
                lblFollow.setIcon(new ImageIcon(getClass().getResource("/images/Follow.png")));
                lblFollow.setToolTipText("Follow this user in SocialCDE for Intellij");
                lblFollow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblFollow.setVisible(true);
                lblUnfollow.setVisible(false);
                panelIcon.add(lblFollow);

                //---- lblSkill ----
                lblSkill.setIcon(new ImageIcon(getClass().getResource("/images/skills.png")));
                lblSkill.setToolTipText("View his/her skills");
                lblSkill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblSkill.setVisible(true);
                panelIcon.add(lblSkill);

                //---- lblHide ----
                lblHide.setIcon(new ImageIcon(getClass().getResource("/images/Hide.png")));
                lblHide.setToolTipText("Hide this user from SocialCDE for Intellij");
                lblHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblHide.setVisible(true);
                panelIcon.add(lblHide);

            }
            else if (userType == "Following"){
                //---- lblUnFollow ----
                lblUnfollow.setIcon(new ImageIcon(getClass().getResource("/images/Unfollow.png")));
                lblUnfollow.setToolTipText("Unfollow this user in SocialCDE for Intellij");
                lblUnfollow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblFollow.setVisible(false);
                lblUnfollow.setVisible(true);
                panelIcon.add(lblUnfollow);

                //---- lblSkill ----
                lblSkill.setIcon(new ImageIcon(getClass().getResource("/images/skills.png")));
                lblSkill.setToolTipText("View his/her skills");
                lblSkill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblSkill.setVisible(true);
                panelIcon.add(lblSkill);

                //---- lblHide ----
                lblHide.setIcon(new ImageIcon(getClass().getResource("/images/Hide.png")));
                lblHide.setToolTipText("Hide this user from SocialCDE for Intellij");
                lblHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblHide.setVisible(true);
                panelIcon.add(lblHide);
            }
            else if (userType == "Followers" || userType == "Hidden"){
                //---- lblFollow ----
                lblFollow.setIcon(new ImageIcon(getClass().getResource("/images/Follow.png")));
                lblFollow.setToolTipText("Follow this user in SocialCDE for Intellij");
                lblFollow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblFollow.setVisible(true);
                lblUnfollow.setVisible(false);
                panelIcon.add(lblFollow);

                //---- lblSkill ----
                lblSkill.setIcon(new ImageIcon(getClass().getResource("/images/skills.png")));
                lblSkill.setToolTipText("View his/her skills");
                lblSkill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblSkill.setVisible(true);
                panelIcon.add(lblSkill);

                //---- lblHide ----
                lblHide.setIcon(new ImageIcon(getClass().getResource("/images/Hide.png")));
                lblHide.setToolTipText("Hide this user from SocialCDE for Intellij");
                lblHide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                lblHide.setVisible(true);
                panelIcon.add(lblHide);
            }

        }
        panelSettings.add(panelIcon);
        //add(panelIcon);

        posts = Controller.getProxy().GetUserTimeline(
                Controller.getCurrentUser().Username,
                Controller.getCurrentUserPassword(), userSelected.Username);

        //======== scrollPane1 ========
        {

            //======== panelDynamic ========
            {
                panelDynamic.setBackground(Color.white);
                panelDynamic.setBorder(new BevelBorder(BevelBorder.LOWERED));
                panelDynamic.setLayout(new VerticalLayout(15));

                ActionUserTimeline.setLastId(0);

                for (int i = 0; i < posts.length; i++) {
                    final int j = i;

                    pnl = new JPanel(new HorizontalLayout(10));
                    pnl.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
                    pnl.setBackground(Color.WHITE);
                    JPanel pnl2 = new JPanel(new VerticalLayout(10));
                    pnl2.setBackground(Color.WHITE);

                    //userPostComposite.setData("IdPost", posts[j].Id);
                    lblAvatar = new JLabel();
                    lblAvatar.setName("lblAvatar");

                    if (Controller.getUsersAvatar().get(posts[j].getUser().Username) == null) {
                        Controller.getUsersAvatar().put(posts[j].getUser().Username, im.getUserImage(posts[j].getUser().Avatar));
                    }

                    try {
                        lblAvatar.setIcon(new ImageIcon(im.resize((BufferedImage) Controller.getUsersAvatar().get(posts[j].getUser().Username), 75, 75)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //lblAvatar.setIcon(new ImageIcon(Controller.getUsersAvatar().get(posts[j].getUser().Username)));

                    pnl.add(lblAvatar);

                    lblNickname = new JLabel();
                    lblNickname.setName("lblNickname");

                    lblNickname.setText(posts[j].getUser().Username);
                    lblNickname.setFont(new Font("Calibri", Font.BOLD, 15));
                    lblNickname.setForeground(Color.BLACK);

                    pnl2.add(lblNickname);

                    JTextPane message = new JTextPane();
                    message.setContentType("text/html");
                    message.setEditable(false);
                    message.setBackground(Color.WHITE);
                    message.setText(findLink(posts[j].getMessage()));
                    message.addHyperlinkListener(new HyperlinkListener() {
                        @Override
                        public void hyperlinkUpdate(HyperlinkEvent e) {
                            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                                if (Desktop.isDesktopSupported()) {
                                    try {
                                        Desktop.getDesktop().browse(e.getURL().toURI());
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    } catch (URISyntaxException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
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
                    panelDynamic.add(pnl);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            scrollPane1.getVerticalScrollBar().setValue(0);
                        }
                    });

                    ActionUserTimeline.setLastId(posts[i].Id);

                }
                WPost[] newPost = Controller.getProxy().GetUserTimeline(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword(), userSelected.Username,
                        ActionUserTimeline.getLastId(), 0);

                if (newPost == null) {
                    newPost = new WPost[0];
                }
                if (newPost.length > 0) {
                    otherPostAvailable = new JLabel("<html><a>Click to view older posts</a></html>");
                    otherPostAvailable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    otherPostAvailable.setName("otherPostAvaible");
                    JPanel jp = new JPanel(new FlowLayout());
                    jp.add(otherPostAvailable);
                    panelDynamic.add(jp);

                    noPostAvailable = new JLabel("There are no post in the cache.\n Please try again in two minutes.");


                } else {
                    noPostAvailable = new JLabel("There are no post in the cache.\n Please try again in two minutes.");
                    JPanel jp = new JPanel(new FlowLayout());
                    jp.add(noPostAvailable);
                    panelDynamic.add(jp);

                    otherPostAvailable = new JLabel("<html><a>Click to view older posts</a></html>");
                    otherPostAvailable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }
            }

            scrollPane1.setPreferredSize(new Dimension(0, 400));
            scrollPane1.setViewportView(panelDynamic);
            add(scrollPane1);
        }
        add(scrollPane1);

        lblReturn.addMouseListener(listener);
        lblFollow.addMouseListener(listener);
        lblUnfollow.addMouseListener(listener);
        lblSkill.addMouseListener(listener);
        lblHide.addMouseListener(listener);
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

    public WUser getUser() {
        return userSelected;
    }

    public void setUser(WUser user) {
        this.userSelected = user;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public HashMap<String, Object> getData() {
        // TODO Auto-generated method stub
        HashMap<String, Object> uiData = new HashMap<String, Object>();

        uiData.put("userType", userType);
        uiData.put("labelFollow", lblFollow);
        uiData.put("labelSkills", lblSkill);
        uiData.put("labelHide", lblHide);
        uiData.put("userSelected", userSelected);
        uiData.put("LabelOtherPost", otherPostAvailable);
        uiData.put("LabelNoPost", noPostAvailable);
        uiData.put("labelReturn", lblReturn);

        return uiData;
    }
}
