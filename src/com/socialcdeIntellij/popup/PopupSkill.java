/*
 * Created by JFormDesigner on Mon Mar 02 11:30:44 CET 2015
 */

package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.shared.library.WUser;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Davide Rossi
 */
public class PopupSkill extends JDialog {
    private WUser user;
    private JLabel lblSkill;
    private JLabel noSkill;

    public WUser getUser() {
        return user;
    }

    public void setUser(WUser user) {
        this.user = user;
    }

    public PopupSkill(Frame owner) {
        super(owner);
        initComponents();
    }

    public PopupSkill(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        dialogPane = new JPanel();
        scrollPane2 = new JScrollPane();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        btnReturn = new JButton();
        lblSkill = new JLabel();
        noSkill = new JLabel();

        //======== this ========
        setResizable(false);
        setTitle("Skills");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(0, 10));

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new VerticalLayout(10));

            //======== scrollPane2 ========
            {
                scrollPane2.setPreferredSize(new Dimension(300, 136));

                //======== contentPanel ========
                {
                    contentPanel.setBackground(Color.white);
                    contentPanel.setLayout(new FlowLayout());
                    String[] skills = null;

                    if(user != null){
                        skills = Controller.getProxy().GetSkills(
                                Controller.getCurrentUser().Username,
                                Controller.getCurrentUserPassword(),
                                user.Username);
                    }
                    else{
                        skills = Controller.getProxy().GetSkills(
                                Controller.getCurrentUser().Username,
                                Controller.getCurrentUserPassword(),
                                Controller.getCurrentUser().Username);
                    }


                    if (skills.length > 0) {
                        String stringSkills = "";

                        for (int i = 0; i < skills.length; i++) {
                            if (i == skills.length - 1) {
                                stringSkills += skills[i];
                            } else {
                                stringSkills += skills[i] + ", ";
                            }
                        }

                        lblSkill.setText(stringSkills);
                        lblSkill.setVisible(true);
                        noSkill.setVisible(false);
                        contentPanel.add(lblSkill);
                    } else {
                        noSkill.setText("<html>There are no skills or there are no \"Get your skills\" <br> feature selected.<br> Try again later.</html>");
                        lblSkill.setVisible(false);
                        noSkill.setVisible(true);
                        contentPanel.add(noSkill);
                    }
                }
                scrollPane2.setViewportView(contentPanel);
            }
            dialogPane.add(scrollPane2);

            //======== panel1 ========
            {
                panel1.setPreferredSize(new Dimension(63, 50));
                panel1.setLayout(new GridBagLayout());
                ((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {133, 0};
                ((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

                //---- btnReturn ----
                btnReturn.setText("Back");
                btnReturn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                panel1.add(btnReturn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.NORTH, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 0, 0));
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
    private JScrollPane scrollPane2;
    private JPanel contentPanel;
    private JPanel panel1;
    private JButton btnReturn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
