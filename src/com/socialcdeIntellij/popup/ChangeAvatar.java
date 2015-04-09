/*
 * Created by JFormDesigner on Mon Mar 02 10:55:23 CET 2015
 */

package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.GeneralButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

import com.socialcdeIntellij.object.ImagesMod;
import org.jdesktop.swingx.*;

public class ChangeAvatar extends JDialog {

    private ArrayList<GeneralButton> allAvatar;
    private URI[] uriAvatar;
    private ImagesMod im = new ImagesMod();
    private JLabel lblNoAvatars;
    private String avatarChoosen = null;


    public ChangeAvatar(Frame owner) {
        super(owner);
        initComponents();
    }

    public ChangeAvatar(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        dialogPane = new JPanel();
        scrollPane1 = new JScrollPane();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        okButton = new JButton();
        lblNoAvatars = new JLabel("No avatars avaible");


        //======== this ========

        setName("ChangeAvatar");
        setTitle("Select avatar");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new VerticalLayout(15));

            //======== scrollPane1 ========
            {
                scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane1.setSize(new Dimension(200,200));

                uriAvatar = Controller.getProxy().GetAvailableAvatars(
                        Controller.getCurrentUser().Username,
                        Controller.getCurrentUserPassword());

                //======== contentPanel ========

                {
                    contentPanel.setBackground(Color.white);
                    contentPanel.setLayout(new FlowLayout());


                    if (uriAvatar.length > 0) {
                        lblNoAvatars.setVisible(false);

                        allAvatar = new ArrayList<GeneralButton>();
                        for (int i = 0; i < uriAvatar.length; i++) {

                            Boolean flag = true;
                            System.out.println("uri  " + i + " n. "
                                    + uriAvatar[i].toString());
                            try {

                                im.get_ImageStream(uriAvatar[i].toURL());

                            } catch (Exception e) {
                                // TODO: handle exception
                                flag = false;
                            }

                            System.out.println("flag = "+flag);
                            if (flag) {
                                GeneralButton btnAvatar = new GeneralButton();

                                if (Controller.getCurrentUser().getAvatar() != null) {
                                    btnAvatar.setText("");
                                    /*avatarImage.setData("ID_action", "btnAvatar");
                                    avatarImage.setData("URI", uriAvatar[i]);*/
                                    final int j = i;

                                    try {

                                        btnAvatar.setIcon(new ImageIcon(im.resize((BufferedImage) im.get_ImageStream(uriAvatar[i].toURL()), 70, 70)));
                                        btnAvatar.setVisible(true);
                                    } catch (MalformedURLException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    contentPanel.add(btnAvatar);
                                    allAvatar.add(btnAvatar);

                                    btnAvatar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            if (!Controller.getProxy().SaveAvatar(
                                                    Controller.getCurrentUser().Username,
                                                    Controller.getCurrentUserPassword(),
                                                    uriAvatar[j])) {
                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Something was wrong, please try again.",
                                                        "SocialCDE message", JOptionPane.WARNING_MESSAGE);
                                                dispose();
                                            }
                                            else {
                                                //avatarChoosen = String.valueOf(uriAvatar[j]);
                                                Controller.getCurrentUser().setAvatar(String.valueOf(uriAvatar[j]));
                                               /* try {
                                                    ((JLabel) Controller.temporaryInformation.get("newAvatar"))
                                                            .setIcon(new ImageIcon(im.resize((BufferedImage)
                                                                    Controller.getUsersAvatar().get(Controller.getCurrentUser().Username), 75, 75)));
                                                } catch (IOException e1) {
                                                    e1.printStackTrace();
                                                }
                                                Controller.getWindow().revalidate();*/
                                                JOptionPane.showMessageDialog(Controller.getFrame(), "Avatar set up!",
                                                        "SocialCDE message", JOptionPane.INFORMATION_MESSAGE);
                                                dispose();
                                            }

                                        }
                                    });
                                }

                            }
                        }
                    }
                    else{
                        lblNoAvatars.setVisible(true);
                        contentPanel.add(lblNoAvatars);
                    }


                }
                scrollPane1.setViewportView(contentPanel);
            }
            dialogPane.add(scrollPane1);

            //======== panel1 ========
            {
                panel1.setPreferredSize(new Dimension(100, 50));
                panel1.setLayout(new FlowLayout());

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(uriAvatar.length > 0 && avatarChoosen !=null){
                            Controller.getCurrentUser().setAvatar(avatarChoosen);
                        }
                        else
                            dispose();
                    }
                });
                panel1.add(okButton);
            }
            dialogPane.add(panel1);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
    private JPanel dialogPane;
    private JScrollPane scrollPane1;
    private JPanel contentPanel;
    private JPanel panel1;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
