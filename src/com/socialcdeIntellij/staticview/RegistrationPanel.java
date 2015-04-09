/*
 * Created by JFormDesigner on Thu Mar 05 12:07:17 CET 2015
 */

package com.socialcdeIntellij.staticview;

import com.socialcdeIntellij.action.ActionGeneral;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Davide Rossi
 */
public class RegistrationPanel extends JPanel {
    private ActionGeneral listener;

    public RegistrationPanel() {
        initComponents();
    }

    private void initComponents() {

        listener = new ActionGeneral();

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        lblRegistration = new JLabel();
        lblAlert = new JLabel();
        panelDati = new JPanel();
        panelLabel = new JPanel();
        lblProxy = new JLabel();
        lblEmail = new JLabel();
        lblCode = new JLabel();
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        lblConfirmPsw = new JLabel();
        panelTxt = new JPanel();
        txtProxy = new JTextField();
        txtEmail = new JTextField();
        txtCode = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        panel3 = new JPanel();
        signProxy = new JLabel();
        signEmail = new JLabel();
        signCode = new JLabel();
        signUsername = new JLabel();
        signPassword = new JLabel();
        signConfirmPassword = new JLabel();
        panelButton = new JPanel();
        btnRegister = new JButton();
        lblChange = new JLabel();

        //======== this ========
        setName("Registration");

        setLayout(new VerticalLayout(10));

        //---- lblRegistration ----
        lblRegistration.setText("REGISTRATION");
        lblRegistration.setFont(new Font("Calibri", Font.BOLD, 16));
        lblRegistration.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblRegistration);

        //---- lblAlert ----
        lblAlert.setText("text");
        lblAlert.setForeground(Color.red);
        lblAlert.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlert.setVisible(false);
        add(lblAlert);

        //======== panelDati ========
        {
            panelDati.setLayout(new FlowLayout());

            //======== panelLabel ========
            {
                panelLabel.setLayout(new VerticalLayout(4));

                //---- lblProxy ----
                lblProxy.setText("Proxy server host");
                lblProxy.setAlignmentX(10.0F);
                lblProxy.setHorizontalAlignment(SwingConstants.LEFT);
                lblProxy.setPreferredSize(new Dimension(89, 24));
                panelLabel.add(lblProxy);

                //---- lblEmail ----
                lblEmail.setText("Email");
                lblEmail.setPreferredSize(new Dimension(89, 24));
                panelLabel.add(lblEmail);

                //---- lblCode ----
                lblCode.setText("Invitation code");
                lblCode.setPreferredSize(new Dimension(89, 24));
                panelLabel.add(lblCode);

                //---- lblUsername ----
                lblUsername.setText("Username");
                lblUsername.setPreferredSize(new Dimension(89, 24));
                panelLabel.add(lblUsername);

                //---- lblPassword ----
                lblPassword.setText("Password");
                lblPassword.setPreferredSize(new Dimension(89, 24));
                panelLabel.add(lblPassword);

                //---- lblConfirmPsw ----
                lblConfirmPsw.setText("Confirm password");
                lblConfirmPsw.setPreferredSize(new Dimension(97, 24));
                panelLabel.add(lblConfirmPsw);
            }
            panelDati.add(panelLabel);

            //======== panelTxt ========
            {
                panelTxt.setLayout(new VerticalLayout(4));

                //---- txtProxy ----
                txtProxy.setMinimumSize(new Dimension(130, 22));
                txtProxy.setPreferredSize(new Dimension(130, 22));
                txtProxy.setAutoscrolls(false);
                txtProxy.setMargin(new Insets(20, 20, 20, 20));
                txtProxy.setAlignmentX(28.5F);
                txtProxy.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                txtProxy.setHorizontalAlignment(SwingConstants.LEFT);
                txtProxy.setName("txtProxy");
                panelTxt.add(txtProxy);

                //---- txtEmail ----
                txtEmail.setMinimumSize(new Dimension(130, 22));
                txtEmail.setPreferredSize(new Dimension(130, 22));
                txtEmail.setAutoscrolls(false);
                txtEmail.setMargin(new Insets(20, 10, 20, 59));
                txtEmail.setAlignmentX(28.5F);
                txtEmail.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                txtEmail.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                txtEmail.setName("txtEmail");
                panelTxt.add(txtEmail);

                //---- txtCode ----
                txtCode.setMinimumSize(new Dimension(130, 22));
                txtCode.setPreferredSize(new Dimension(130, 22));
                txtCode.setAutoscrolls(false);
                txtCode.setMargin(new Insets(20, 10, 20, 59));
                txtCode.setAlignmentX(28.5F);
                txtCode.setName("txtCode");
                panelTxt.add(txtCode);

                //---- txtUsername ----
                txtUsername.setMinimumSize(new Dimension(130, 22));
                txtUsername.setPreferredSize(new Dimension(130, 22));
                txtUsername.setAutoscrolls(false);
                txtUsername.setMargin(new Insets(20, 10, 20, 59));
                txtUsername.setAlignmentX(28.5F);
                txtUsername.setName("txtUsername");
                panelTxt.add(txtUsername);

                //---- txtPassword ----
                txtPassword.setName("txtPassword");
                txtPassword.setMinimumSize(new Dimension(130, 22));
                txtPassword.setPreferredSize(new Dimension(130, 22));
                panelTxt.add(txtPassword);

                //---- txtConfirmPassword ----
                txtConfirmPassword.setName("txtConfirmPassword");
                txtConfirmPassword.setMinimumSize(new Dimension(130, 22));
                txtConfirmPassword.setPreferredSize(new Dimension(130, 22));
                panelTxt.add(txtConfirmPassword);
            }
            panelDati.add(panelTxt);

            //======== panel3 ========
            {
                panel3.setLayout(new VerticalLayout(4));

                //---- signProxy ----
                signProxy.setIcon(null);
                signProxy.setHorizontalAlignment(SwingConstants.LEFT);
                signProxy.setPreferredSize(new Dimension(28, 22));
                signProxy.setFocusable(false);
                panel3.add(signProxy);

                //---- signEmail ----
                signEmail.setIcon(null);
                signEmail.setHorizontalAlignment(SwingConstants.LEFT);
                signEmail.setPreferredSize(new Dimension(28, 22));
                signEmail.setFocusable(false);
                panel3.add(signEmail);

                //---- signCode ----
                signCode.setIcon(null);
                signCode.setHorizontalAlignment(SwingConstants.LEFT);
                signCode.setPreferredSize(new Dimension(28, 22));
                signCode.setFocusable(false);
                panel3.add(signCode);

                //---- signUsername ----
                signUsername.setIcon(null);
                signUsername.setHorizontalAlignment(SwingConstants.LEFT);
                signUsername.setPreferredSize(new Dimension(28, 22));
                signUsername.setFocusable(false);
                panel3.add(signUsername);

                //---- signPassword ----
                signPassword.setIcon(null);
                signPassword.setHorizontalAlignment(SwingConstants.LEFT);
                signPassword.setPreferredSize(new Dimension(28, 22));
                signPassword.setFocusable(false);
                panel3.add(signPassword);

                //---- signConfirmPassword ----
                signConfirmPassword.setIcon(null);
                signConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
                signConfirmPassword.setPreferredSize(new Dimension(28, 22));
                signConfirmPassword.setFocusable(false);
                panel3.add(signConfirmPassword);
            }
            panelDati.add(panel3);
        }
        add(panelDati);

        //======== panelButton ========
        {
            panelButton.setLayout(new FlowLayout());

            //---- btnRegister ----
            btnRegister.setText("Register");
            btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
            btnRegister.setPreferredSize(new Dimension(100, 40));
            btnRegister.setName("btnRegister");
            btnRegister.setActionCommand("btnRegister");
            panelButton.add(btnRegister);
        }
        add(panelButton);

        //---- lblChange ----
        lblChange.setText("<html><u>I already have an account, I want to sign in</u></html> ");
        lblChange.setHorizontalAlignment(SwingConstants.CENTER);
        lblChange.setForeground(Color.blue);
        lblChange.setName("lblChange");
        add(lblChange);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        txtProxy.addFocusListener(listener);
        txtEmail.addFocusListener(listener);
        txtCode.addFocusListener(listener);
        txtUsername.addFocusListener(listener);
        txtPassword.addFocusListener(listener);
        txtConfirmPassword.addFocusListener(listener);
        btnRegister.addActionListener(listener);
        lblChange.addMouseListener(listener);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
    private JLabel lblRegistration;
    private JLabel lblAlert;
    private JPanel panelDati;
    private JPanel panelLabel;
    private JLabel lblProxy;
    private JLabel lblEmail;
    private JLabel lblCode;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblConfirmPsw;
    private JPanel panelTxt;
    private JTextField txtProxy;
    private JTextField txtEmail;
    private JTextField txtCode;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JPanel panel3;
    private JLabel signProxy;
    private JLabel signEmail;
    private JLabel signCode;
    private JLabel signUsername;
    private JLabel signPassword;
    private JLabel signConfirmPassword;
    private JPanel panelButton;
    private JButton btnRegister;
    private JLabel lblChange;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public HashMap<String, Object> getData() {
        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("LabelAlert", lblAlert);
        uiData.put("ProxyHost", txtProxy);
        uiData.put("LabelImageProxy",signProxy);
        uiData.put("Email", txtEmail);
        uiData.put("LabelImageEmail",signEmail);
        uiData.put("InvitationCode", txtCode);
        uiData.put("LabelImageInvitationCode",signCode);
        uiData.put("Username", txtUsername);
        uiData.put("LabelImageUsername",signUsername);
        uiData.put("Password", txtPassword);
        uiData.put("LabelImagePassword",signPassword);
        uiData.put("ConfirmPassword", txtConfirmPassword);
        uiData.put("LabelImageConfirmPassword",signConfirmPassword);
        uiData.put("ButtonRegister",btnRegister);
        uiData.put("LabelChange", lblChange);

        return uiData;
    }
}
