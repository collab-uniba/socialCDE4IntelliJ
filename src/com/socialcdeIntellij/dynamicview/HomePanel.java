/*
 * Created by JFormDesigner on Thu Feb 26 17:53:14 CET 2015
 */

package com.socialcdeIntellij.dynamicview;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ButtonService;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.*;

/**
 * @author Davide Rossi
 */
public class HomePanel extends JPanel {
    private ImagesMod im = new ImagesMod();
    private ActionGeneral listener = new ActionGeneral();
    private ButtonService services;

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-

                // Generated using JFormDesigner Evaluation license - Pablo Rossi
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
        lblImageService = new JLabel();
        panel2 = new JPanel();
        lblService = new JLabel();
        lblStatus = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setPreferredSize(new Dimension(448, 610));
        setName("Home");

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0,
                        0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog",
                        java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder())); addPropertyChangeListener(new
                                                                                                      java.beans.PropertyChangeListener() {
                                                                                                          public void propertyChange(java.beans.PropertyChangeEvent
                                                                                                                                             e) {
                                                                                                              if ("border".equals(e.getPropertyName()))
                                                                                                                  throw new RuntimeException();
                                                                                                          }
                                                                                                      });

        setLayout(new VerticalLayout(10));

        //======== panelInfoUser ========
        {
            panelInfoUser.setBackground(Color.white);
            panelInfoUser.setPreferredSize(new Dimension(446, 115));
            panelInfoUser.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

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


            lblAvatar.setName("lblAvatar");
            panelInfoUser.add(lblAvatar);

            //======== panelInfo ========
            {
                panelInfo.setBackground(Color.white);
                panelInfo.setLayout(new GridLayout(2, 0, 0, 10));

                //======== panelSettings ========
                {
                    panelSettings.setBackground(Color.white);
                    panelSettings.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

                    //---- lblNickname ----
                    lblNickname.setText(Controller.getCurrentUser().Username);
                    lblNickname.setHorizontalAlignment(SwingConstants.LEFT);
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
                    panelInfo2.setLayout(new GridLayout(1, 3, 10, 0));

                    //======== panelPost ========
                    {
                        panelPost.setBackground(Color.white);
                        panelPost.setBorder(new MatteBorder(0, 0, 0, 1, Color.black));
                        panelPost.setLayout(new GridLayout(2, 1));

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

                    //======== panelfollowing ========
                    {
                        panelfollowing.setBackground(Color.white);
                        panelfollowing.setLayout(new GridLayout(2, 1));

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

                    //======== panelFollowers ========
                    {
                        panelFollowers.setBackground(Color.white);
                        panelFollowers.setBorder(new MatteBorder(0, 1, 0, 0, Color.black));
                        panelFollowers.setPreferredSize(new Dimension(86, 32));
                        panelFollowers.setLayout(new GridLayout(2, 1));

                        //---- lblFollowers ----
                        lblFollowers.setText("Followers");
                        lblFollowers.setHorizontalAlignment(SwingConstants.CENTER);
                        lblFollowers.setAlignmentX(0.5F);
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
            scrollPane1.setPreferredSize(new Dimension(200, 400));

            //======== panelService ========
            {
                panelService.setBackground(Color.white);
                panelService.setLayout(new VerticalLayout(5));

                final WService[] wService = Controller.getProxy().GetServices(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword());

                if (wService.length > 0) {
                    for (int i = 0; i < wService.length; i++) {
                        final int j = i;
                        services = new ButtonService();

                        services.put("ID_action", "btnService");
                        services.setData("service", wService[j]);
                        services.getButton().addActionListener(listener);


                        if(Controller.getServicesImage().get(wService[j].Name) == null)
                        {
                            Controller.getServicesImage().put(wService[j].Name,
                                    getServiceImage(wService[j].Image));
                        }

                        services.setImage(Controller.getServicesImage().get(wService
                                [j].Name));

                        services.setServiceName(wService[i].Name);

                        if (wService[i].Registered)
                            services.setServiceStatus(true);
                        else
                            services.setServiceStatus(false);

                        panelService.add(services);

                    }
                } else {
                    JLabel lblNothing = new JLabel("There are no services available yet.\t\nPlease try again soon or contact your admin.");
                    lblNothing.setVisible(true);
                    panelService.add(lblNothing);
                }

                panelService.add(panelserviceDemo);
            }
            scrollPane1.setViewportView(panelService);
        }
        add(scrollPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        lblAvatar.addMouseListener(listener);
        lblSkills.addMouseListener(listener);
        lblSettings.addMouseListener(listener);
        lblImageService.addMouseListener(listener);

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
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
    private JLabel lblImageService;
    private JPanel panel2;
    private JLabel lblService;
    private JLabel lblStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

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
        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("LabelAvatar", lblAvatar);
        uiData.put("LabelNickname", lblNickname);
        uiData.put("LabelImageSkills",lblSkills);
        uiData.put("LabelImageSettings", lblSettings);
        uiData.put("LabelNumPost",lblNumPost);
        uiData.put("LabelNumFollowing", lblNumFollowing);
        uiData.put("LabelNumFollowers",lblNumFollowers);

        //dinamico
        uiData.put("ButtonService", services);

        return uiData;
    }
}