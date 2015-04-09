/*
 * Created by JFormDesigner on Mon Mar 02 11:52:15 CET 2015
 */

package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Davide Rossi
 */
public class TFSLogin extends JDialog {
    private WService service;

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public WService getService() {
        return service;
    }

    public void setService(WService service) {
        this.service = service;
    }

    public TFSLogin(Frame owner) {
        super(owner);
        initComponents();
    }

    public TFSLogin(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - David Rossi
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panelDati = new JPanel();
        panelLabel = new JPanel();
        lblDomain = new JLabel();
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        panelTxt = new JPanel();
        txtDomain = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(25));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

                    //---- label1 ----
                    label1.setText("Registration");
                    label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
                    panel1.add(label1);
                }
                contentPanel.add(panel1);

                //======== panelDati ========
                {
                    panelDati.setLayout(new FlowLayout());

                    //======== panelLabel ========
                    {
                        panelLabel.setLayout(new VerticalLayout(4));

                        //---- lblDomain ----
                        lblDomain.setText("Domain");
                        lblDomain.setAlignmentX(10.0F);
                        lblDomain.setHorizontalAlignment(SwingConstants.LEFT);
                        lblDomain.setPreferredSize(new Dimension(89, 24));
                        panelLabel.add(lblDomain);

                        //---- lblUsername ----
                        lblUsername.setText("Username");
                        lblUsername.setPreferredSize(new Dimension(89, 24));
                        panelLabel.add(lblUsername);

                        //---- lblPassword ----
                        lblPassword.setText("Password");
                        lblPassword.setPreferredSize(new Dimension(89, 24));
                        panelLabel.add(lblPassword);
                    }
                    panelDati.add(panelLabel);

                    //======== panelTxt ========
                    {
                        panelTxt.setLayout(new VerticalLayout(4));

                        //---- txtDomain ----
                        txtDomain.setMinimumSize(new Dimension(100, 24));
                        txtDomain.setPreferredSize(new Dimension(250, 24));
                        txtDomain.setAutoscrolls(false);
                        txtDomain.setMargin(new Insets(20, 20, 20, 20));
                        txtDomain.setAlignmentX(28.5F);
                        txtDomain.setMaximumSize(new Dimension(300, 24));
                        txtDomain.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                        txtDomain.setHorizontalAlignment(SwingConstants.LEFT);
                        txtDomain.setName("txtDomain");
                        panelTxt.add(txtDomain);

                        //---- txtUsername ----
                        txtUsername.setMinimumSize(new Dimension(76, 24));
                        txtUsername.setPreferredSize(new Dimension(250, 24));
                        txtUsername.setAutoscrolls(false);
                        txtUsername.setMargin(new Insets(20, 10, 20, 59));
                        txtUsername.setAlignmentX(28.5F);
                        txtUsername.setMaximumSize(new Dimension(1000, 24));
                        txtUsername.setName("txtUsername");
                        panelTxt.add(txtUsername);

                        //---- txtPassword ----
                        txtPassword.setName("txtPassword");
                        panelTxt.add(txtPassword);
                    }
                    panelDati.add(panelTxt);
                }
                contentPanel.add(panelDati);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - David Rossi
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panelDati;
    private JPanel panelLabel;
    private JLabel lblDomain;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel panelTxt;
    private JTextField txtDomain;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public HashMap<String, Object> getData() {
        // TODO Auto-generated method stub

        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("textUsername", txtUsername);
        uiData.put("textPassword", txtPassword);

        if (service.RequireTFSDomain) {
            uiData.put("textDomain", txtDomain.getText());
        } else {
            uiData.put("textDomain", "");
        }

        return uiData;
    }
}
