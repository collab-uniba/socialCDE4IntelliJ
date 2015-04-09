/*
 * Created by JFormDesigner on Thu Feb 26 17:53:14 CET 2015
 */

package com.socialcdeIntellij.dynamicview;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ButtonService;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class HomePanel extends JPanel {
    private JPanel panelInfoUser;
    private JLabel lblAvatar;
    private JPanel panelInfo;
    private JPanel panelSettings;
    private JLabel lblNickname;
    private JPanel panelSubSettings;
    private JLabel lblSkills;
    private JLabel lblSettings;
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
    private JScrollPane scrollPane1;
    private JPanel panelService;
    private JPanel panelserviceDemo;
    private ImagesMod im = new ImagesMod();
    private ActionGeneral listener = new ActionGeneral();;
    private ButtonService services;
    private WService[] wService;
    private HashMap<String, Object> uiData= new HashMap<String, Object>();

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {

        panelInfoUser = new JPanel();
        lblAvatar = new JLabel();
        panelInfo = new JPanel();
        panelSettings = new JPanel();
        lblNickname = new JLabel();
        panelSubSettings = new JPanel();
        lblSkills = new JLabel();
        lblSettings = new JLabel();
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
        scrollPane1 = new JScrollPane();
        panelService = new JPanel();
        panelserviceDemo = new JPanel();

        Controller.setCounter(0);
        //======== this ========
        setBackground(Color.white);
        //setPreferredSize(new Dimension(448, 610));
        setName("Home");


        setLayout(new VerticalLayout(10));

        //======== panelInfoUser ========
        {
            panelInfoUser.setBackground(Color.white);
            //panelInfoUser.setPreferredSize(new Dimension(446, 115));
            //panelInfoUser.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
            panelInfoUser.setLayout(new HorizontalLayout(5));

            //---- lblAvatar ----

            Controller.setCurrentUser(Controller.getProxy().GetUser(
                    Controller.getCurrentUser().Username,
                    Controller.getCurrentUserPassword()));

            if (Controller.getUsersAvatar().get(Controller.getCurrentUser().Username) == null)
            {
                Controller.getUsersAvatar().put(Controller.getCurrentUser().Username,
                        getUserImage(Controller.getCurrentUser().Avatar));
            }
            try {
                lblAvatar.setIcon(new ImageIcon(im.resize((BufferedImage)
                        Controller.getUsersAvatar().get(Controller.getCurrentUser().Username), 75, 75)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            lblAvatar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblAvatar.setName("lblAvatar");
            panelInfoUser.add(lblAvatar);

            //======== panelInfo ========
            {
                panelInfo.setBackground(Color.white);
               // panelInfo.setLayout(new GridLayout(2, 0, 0, 10));
                panelInfo.setLayout(new VerticalLayout(5));

                //======== panelSettings ========
                {
                    panelSettings.setBackground(Color.white);
                    //panelSettings.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
                    panelSettings.setLayout(new GridLayout(1, 2, 50, 0));

                    //---- lblNickname ----
                    lblNickname.setText(Controller.getCurrentUser().Username);
                    lblNickname.setHorizontalAlignment(SwingConstants.CENTER);
                    lblNickname.setName("lblNickname");
                    panelSettings.add(lblNickname);

                    //======== panelSubSettings ========
                    {
                        panelSubSettings.setBackground(Color.white);
                        panelSubSettings.setLayout(new FlowLayout());

                        //---- lblSkills ----
                        lblSkills.setIcon(new ImageIcon(getClass().getResource
                                ("/images/skills.png")));
                        lblSkills.setName("lblSkills");
                        lblSkills.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        panelSubSettings.add(lblSkills);

                        //---- lblSettings ----
                        lblSettings.setIcon(new ImageIcon(getClass().getResource
                                ("/images/settings.png")));
                        lblSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        lblSettings.setName("lblSettings");
                        panelSubSettings.add(lblSettings);
                    }
                    panelSettings.add(panelSubSettings);
                }
                panelInfo.add(panelSettings);

                //======== panelInfo2 ========
                {
                    panelInfo2.setBackground(Color.white);
                    panelInfo2.setLayout(new FlowLayout(FlowLayout.CENTER, 5,0));

                    JSeparator sep1 = new JSeparator();
                    sep1.setOrientation(SwingConstants.VERTICAL);
                    sep1.setPreferredSize(new Dimension(3,30));
                    sep1.setVisible(true);

                    JSeparator sep2 = new JSeparator();
                    sep2.setPreferredSize(new Dimension(3,30));
                    sep2.setVisible(true);
                    sep2.setOrientation(SwingConstants.VERTICAL);

                    //======== panelPost ========
                    {
                        panelPost.setBackground(Color.white);
                        panelPost.setLayout(new VerticalLayout(2));

                        //---- lblPosts ----
                        lblPosts.setText("Posts");
                        lblPosts.setHorizontalAlignment(SwingConstants.CENTER);
                        panelPost.add(lblPosts);

                        //---- lblNumPost ----
                        lblNumPost.setText(String.valueOf(Controller.getCurrentUser
                                ().getStatuses()));
                        lblNumPost.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNumPost.setName("lblNumPost");
                        panelPost.add(lblNumPost);
                    }
                    panelInfo2.add(panelPost);
                    panelInfo2.add(sep1);

                    //======== panelfollowing ========
                    {
                        panelfollowing.setBackground(Color.white);
                        panelfollowing.setLayout(new VerticalLayout(2));

                        //---- lblFollowing ----
                        lblFollowing.setText("Following");
                        lblFollowing.setHorizontalAlignment(SwingConstants.CENTER);
                        panelfollowing.add(lblFollowing);

                        //---- lblNumFollowing ----
                        lblNumFollowing.setText(String.valueOf(Controller.getCurrentUser
                                ().getFollowings()));
                        lblNumFollowing.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNumFollowing.setName("lblNumFollowing");
                        panelfollowing.add(lblNumFollowing);
                    }
                    panelInfo2.add(panelfollowing);
                    panelInfo2.add(sep2);

                    //======== panelFollowers ========
                    {
                        panelFollowers.setBackground(Color.white);
                        panelFollowers.setLayout(new VerticalLayout(2));

                        //---- lblFollowers ----
                        lblFollowers.setText("Followers");
                        lblFollowers.setHorizontalAlignment(SwingConstants.CENTER);
                        lblFollowers.setHorizontalTextPosition(SwingConstants.CENTER);
                        panelFollowers.add(lblFollowers);

                        //---- lblNumFollowers ----
                        lblNumFollowers.setText(String.valueOf(Controller.getCurrentUser
                                ().getFollowers()));
                        lblNumFollowers.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNumFollowers.setName("lblNumFollowers");
                        panelFollowers.add(lblNumFollowers);
                    }
                    panelInfo2.add(panelFollowers);
                }
                panelInfo.add(panelInfo2);
            }
            panelInfoUser.add(panelInfo);
        }
        add(panelInfoUser);

        //======== scrollPane1 ========
        {
            scrollPane1.setVerticalScrollBarPolicy
                    (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane1.setHorizontalScrollBarPolicy
                    (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane1.setPreferredSize(new Dimension(200, 460));

            //======== panelService ========
            {
                panelService.setBackground(Color.white);
                panelService.setLayout(new VerticalLayout(5));

                wService = Controller.getProxy().GetServices(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword());

                if (wService.length > 0) {
                    for (int i = 0; i < wService.length; i++) {
                        final int j = i;

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                services = new ButtonService();

                                services.setwService(wService[j]);

                                services.getButton().setService(wService[j]);
                                services.getButton().addActionListener(listener);

                                if(Controller.getServicesImage().get(wService[j].Name) == null)
                                {
                                    Controller.getServicesImage().put(wService[j].Name,
                                            getServiceImage(wService[j].Image));
                                }

                                services.setImage(Controller.getServicesImage().get(wService
                                        [j].Name));

                                services.setServiceName(wService[j].Name);

                                if (wService[j].Registered)
                                    services.setServiceStatus(true);
                                else
                                    services.setServiceStatus(false);

                                panelService.add(services);
                            }
                        });
                    }

                } else {
                    JLabel lblNothing = new JLabel("There are no services available yet.\nPlease try again soon or contact your admin.");
                    lblNothing.setVisible(true);
                    panelService.add(lblNothing);
                }

                panelService.add(panelserviceDemo);
            }
            scrollPane1.setViewportView(panelService);
        }
        add(scrollPane1);

        lblAvatar.addMouseListener(listener);
        lblSkills.addMouseListener(listener);
        lblSettings.addMouseListener(listener);
    }

    private Image getServiceImage(String link){
        try {

            return im.get_ImageStream(new URL(
                    Controller.getPreferences("proxyRoot")
                            + link));

        } catch (MalformedURLException e) {

            e.printStackTrace();
            return null;
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    private Image getUserImage(String link) {
        try {
            return im.resize((BufferedImage) im.get_ImageStream(new URL(Controller
                    .getCurrentUser().Avatar)), 48, 48);
        } catch (IOException e) {
            try {
                return im.getDEFAULT_AVATAR(48, 48);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }


    public HashMap<String, Object> getData() {

        uiData.put("LabelAvatar", lblAvatar);
        uiData.put("LabelNickname", lblNickname);
        uiData.put("LabelImageSkills",lblSkills);
        uiData.put("LabelImageSettings", lblSettings);
        uiData.put("LabelNumPost",lblNumPost);
        uiData.put("LabelNumFollowing", lblNumFollowing);
        uiData.put("LabelNumFollowers",lblNumFollowers);

        return uiData;
    }
}
