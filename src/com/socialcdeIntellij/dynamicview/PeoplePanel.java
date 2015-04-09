package com.socialcdeIntellij.dynamicview;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.ImagesMod;
import com.socialcdeIntellij.object.LabelClicked;
import com.socialcdeIntellij.shared.library.WUser;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PeoplePanel extends JPanel {

    private JPanel pnlUser;
    private LabelClicked lblImgUser;
    private LabelClicked lblUsername;
    private JScrollPane scrollPane1;
    private JPanel panelPeople;
    private JPanel panelSuggestions;
    private JLabel lblSuggestions;
    private JLabel lblTxtSuggestions;
    private JPanel panelFollowings;
    private JLabel lblFollowings;
    private JLabel lblTxtSFollowings;
    private JPanel panelFollowers;
    private JLabel lblFollowers;
    private JLabel lblTxtFollowers;
    private JPanel panelHidden;
    private JLabel lblHidden;
    private JLabel lblTxtHidden;

    ImagesMod im = new ImagesMod();
    ActionGeneral listener = new ActionGeneral();

    public PeoplePanel() {
        initComponents();
    }

    private void initComponents() {
        scrollPane1 = new JScrollPane();
        panelPeople = new JPanel();
        panelSuggestions = new JPanel();
        lblSuggestions = new JLabel();
        lblTxtSuggestions = new JLabel();
        panelFollowings = new JPanel();
        lblFollowings = new JLabel();
        lblTxtSFollowings = new JLabel();
        panelFollowers = new JPanel();
        lblFollowers = new JLabel();
        lblTxtFollowers = new JLabel();
        panelHidden = new JPanel();
        lblHidden = new JLabel();
        lblTxtHidden = new JLabel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== scrollPane1 ========
        {
            scrollPane1.setPreferredSize(new Dimension(450, 540));

            //======== panelPeople ========
            {
                panelPeople.setLayout(new VerticalLayout(10));
                panelPeople.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
                panelPeople.setBackground(Color.WHITE);

                //======== panelSuggestions ========
                {
                    panelSuggestions.setLayout(new VerticalLayout());
                    panelSuggestions.setBackground(Color.WHITE);

                    final WUser[] suggestion = Controller.getProxy().GetSuggestedFriends(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    //---- lblSuggestions ----
                    lblSuggestions.setText("Suggestions");
                    lblSuggestions.setFont(new Font("Dialog", Font.BOLD, 18));
                    panelSuggestions.add(lblSuggestions);

                    //---- lblTxtSuggestions ----
                    if (suggestion.length == 0) {
                        lblTxtSuggestions.setText("<html>We have no suggestion for you.<br>Please try again.</html>");
                        panelSuggestions.add(lblTxtSuggestions);
                    } else {
                        for (int i = 0; i < suggestion.length; i++) {
                            final int j = i;

                            pnlUser = new JPanel(new HorizontalLayout(15));
                            pnlUser.setBackground(Color.WHITE);

                            lblImgUser = new LabelClicked();
                            lblUsername = new LabelClicked();

                            if (Controller.getUsersAvatar().get(suggestion[j].Username) == null) {
                                Controller.getUsersAvatar().put(suggestion[j].Username, im.getUserImage(suggestion[j].Avatar));
                            }

                            lblImgUser.getLabel().setIcon(new ImageIcon(Controller.getUsersAvatar().get(suggestion[j].Username)));
                            lblImgUser.setName("lblImgUser");
                            lblImgUser.setUserType("Suggested");
                            lblImgUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            lblImgUser.getLabel().setwUser(suggestion[j]);
                            pnlUser.add(lblImgUser);

                            lblUsername.getLabel().setText(suggestion[j].Username);
                            lblUsername.setUserType("Suggested");
                            lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            lblUsername.getLabel().setwUser(suggestion[j]);
                            pnlUser.add(lblUsername);

                            panelSuggestions.add(pnlUser);

                            lblImgUser.addMouseListener(listener);
                            lblUsername.addMouseListener(listener);
                        }
                    }
                }
                panelPeople.add(panelSuggestions);

                //======== panelFollowings ========
                {
                    panelFollowings.setLayout(new VerticalLayout());
                    panelFollowings.setBackground(Color.WHITE);

                    final WUser[] following = Controller.getProxy().GetFollowings(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    //---- lblFollowings ----
                    lblFollowings.setText("Followings");
                    lblFollowings.setFont(new Font("Dialog", Font.BOLD, 18));
                    panelFollowings.add(lblFollowings);

                    if (following.length == 0) {
                        //---- lblTxtSFollowings ----
                        lblTxtSFollowings.setText("You are following no one");
                        panelFollowings.add(lblTxtSFollowings);
                    } else {
                        for (int i = 0; i < following.length; i++) {
                            final int j = i;

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    pnlUser = new JPanel(new HorizontalLayout(15));
                                    pnlUser.setBackground(Color.WHITE);

                                    lblImgUser = new LabelClicked();
                                    lblUsername = new LabelClicked();

                                    if (Controller.getUsersAvatar().get(following[j].Username) == null) {
                                        Controller.getUsersAvatar().put(following[j].Username, im.getUserImage(following[j].Avatar));
                                    }

                                    lblImgUser.getLabel().setIcon(new ImageIcon(Controller.getUsersAvatar().get(following[j].Username)));
                                    lblImgUser.setName("lblImgUser");
                                    lblImgUser.setUserType("Following");
                                    lblImgUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblImgUser.getLabel().setwUser(following[j]);
                                    pnlUser.add(lblImgUser);

                                    lblUsername.getLabel().setText(following[j].Username);
                                    lblUsername.setUserType("Following");
                                    lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblUsername.getLabel().setwUser(following[j]);
                                    pnlUser.add(lblUsername);

                                    panelFollowings.add(pnlUser);

                                    lblImgUser.addMouseListener(listener);
                                    lblUsername.addMouseListener(listener);
                                }
                            });
                        }
                    }


                }
                panelPeople.add(panelFollowings);

                //======== panelFollowers ========
                {
                    panelFollowers.setLayout(new VerticalLayout());
                    panelFollowers.setBackground(Color.WHITE);

                    final WUser[] followers = Controller.getProxy().GetFollowers(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    //---- lblFollowers ----
                    lblFollowers.setText("Followers");
                    lblFollowers.setFont(new Font("Dialog", Font.BOLD, 18));
                    panelFollowers.add(lblFollowers);

                    if (followers.length == 0) {
                        //---- lblTxtFollowers ----
                        lblTxtFollowers.setText("No one is following you");
                        panelFollowers.add(lblTxtFollowers);
                    } else {
                        for (int i = 0; i < followers.length; i++) {
                            final int j = i;

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    pnlUser = new JPanel(new HorizontalLayout(15));
                                    pnlUser.setBackground(Color.WHITE);

                                    lblImgUser = new LabelClicked();
                                    lblUsername = new LabelClicked();

                                    if (Controller.getUsersAvatar().get(followers[j].Username) == null) {
                                        Controller.getUsersAvatar().put(followers[j].Username, im.getUserImage(followers[j].Avatar));
                                    }

                                    lblImgUser.getLabel().setIcon(new ImageIcon(Controller.getUsersAvatar().get(followers[j].Username)));
                                    lblImgUser.setName("lblImgUser");
                                    lblImgUser.setUserType("Followers");
                                    lblImgUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblImgUser.getLabel().setwUser(followers[j]);
                                    pnlUser.add(lblImgUser);

                                    lblUsername.getLabel().setText(followers[j].Username);
                                    lblUsername.setUserType("Followers");
                                    lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblUsername.getLabel().setwUser(followers[j]);
                                    pnlUser.add(lblUsername);

                                    panelFollowers.add(pnlUser);

                                    lblImgUser.addMouseListener(listener);
                                    lblUsername.addMouseListener(listener);
                                }
                            });
                        }
                    }


                }
                panelPeople.add(panelFollowers);

                //======== panelHidden ========
                {
                    panelHidden.setLayout(new VerticalLayout());
                    panelHidden.setBackground(Color.WHITE);

                    final WUser[] hiddenUsers = Controller.getProxy().GetHiddenUsers(
                            Controller.getCurrentUser().Username,
                            Controller.getCurrentUserPassword());

                    //---- lblHidden ----
                    lblHidden.setText("Hidden");
                    lblHidden.setFont(new Font("Dialog", Font.BOLD, 18));
                    panelHidden.add(lblHidden);

                    if (hiddenUsers.length == 0) {
                        //---- lblTxtHidden ----
                        lblTxtHidden.setText("You have hidden no one");
                        panelHidden.add(lblTxtHidden);
                    } else {
                        for (int i = 0; i < hiddenUsers.length; i++) {
                            final int j = i;

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    pnlUser = new JPanel(new HorizontalLayout(15));
                                    pnlUser.setBackground(Color.WHITE);

                                    lblImgUser = new LabelClicked();
                                    lblUsername = new LabelClicked();

                                    if (Controller.getUsersAvatar().get(hiddenUsers[j].Username) == null) {
                                        Controller.getUsersAvatar().put(hiddenUsers[j].Username, im.getUserImage(hiddenUsers[j].Avatar));
                                    }

                                    lblImgUser.getLabel().setIcon(new ImageIcon(Controller.getUsersAvatar().get(hiddenUsers[j].Username)));
                                    lblImgUser.setName("lblImgUser");
                                    lblImgUser.setUserType("Hidden");
                                    lblImgUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblImgUser.getLabel().setwUser(hiddenUsers[j]);
                                    pnlUser.add(lblImgUser);

                                    lblUsername.getLabel().setText(hiddenUsers[j].Username);
                                    lblUsername.setUserType("Hidden");
                                    lblUsername.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                    lblUsername.getLabel().setwUser(hiddenUsers[j]);
                                    pnlUser.add(lblUsername);

                                    panelHidden.add(pnlUser);

                                    lblImgUser.addMouseListener(listener);
                                    lblUsername.addMouseListener(listener);
                                }
                            });
                        }
                    }


                }
                panelPeople.add(panelHidden);
            }
            scrollPane1.setViewportView(panelPeople);
        }
        add(scrollPane1, BorderLayout.CENTER);

        Controller.getWindow().revalidate();
    }

    public HashMap<String, Object> getData() {
        // TODO Auto-generated method stub
        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("imageUser", lblImgUser);
        uiData.put("labelUser", lblUsername);
        return uiData;
    }
}
