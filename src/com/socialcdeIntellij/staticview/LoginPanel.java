/*
 * Created by JFormDesigner on Thu Mar 05 12:24:25 CET 2015
 */

package com.socialcdeIntellij.staticview;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.controller.Controller;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * @author Davide Rossi
 */
public class LoginPanel extends JPanel {
    private ActionGeneral listener;

    public LoginPanel() {
        initComponents();
    }

    private void initComponents() {

        listener = new ActionGeneral();

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        lblLogin = new JLabel();
        lblAlert = new JLabel();
        panelDati = new JPanel();
        panelLabel = new JPanel();
        lblProxy = new JLabel();
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        panelTxt = new JPanel();
        txtProxy = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        panelSign = new JPanel();
        signProxy = new JLabel();
        signUsername = new JLabel();
        signPassword = new JLabel();
        panelButton = new JPanel();
        btnLogin = new JButton();
        panelCheckBtn = new JPanel();
        ckBxAuto_login = new JCheckBox();
        ckBxSave_psw = new JCheckBox();
        lblChange = new JLabel();

        //======== this ========
        setName("Login");

        setLayout(new VerticalLayout(10));

        //---- lblLogin ----
        lblLogin.setText("LOGIN");
        lblLogin.setFont(new Font("Calibri", Font.BOLD, 16));
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblLogin);

        //---- lblAlert ----
        lblAlert.setText("text prova");
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

                //---- txtProxy ----
                txtProxy.setText("");
                txtProxy.setMinimumSize(new Dimension(130, 22));
                txtProxy.setPreferredSize(new Dimension(130, 22));
                txtProxy.setAutoscrolls(false);
                txtProxy.setMargin(new Insets(20, 20, 20, 20));
                txtProxy.setAlignmentX(28.5F);
                txtProxy.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                txtProxy.setHorizontalAlignment(SwingConstants.LEFT);
                txtProxy.setName("txtProxy");
                if(!Controller.getPreferences("proxyHost").isEmpty()
                        && (Controller.getPreferences("autoLogin").equals("True")
                            || Controller.getPreferences("savePassword").equals("True"))){
                    txtProxy.setText(Controller.getPreferences("proxyHost"));
                }
                panelTxt.add(txtProxy);

                //---- txtUsername ----
                txtUsername.setText("");
                txtUsername.setMinimumSize(new Dimension(130, 22));
                txtUsername.setPreferredSize(new Dimension(130, 22));
                txtUsername.setAutoscrolls(false);
                txtUsername.setMargin(new Insets(20, 10, 20, 59));
                txtUsername.setAlignmentX(28.5F);
                txtUsername.setName("txtUsername");
                if(!Controller.getPreferences("username").isEmpty()
                        && (Controller.getPreferences("autoLogin").equals("True")
                        || Controller.getPreferences("savePassword").equals("True"))){
                    txtUsername.setText(Controller.getPreferences("username"));
                }
                panelTxt.add(txtUsername);

                //---- txtPassword ----
                txtPassword.setText("");
                txtPassword.setName("txtPassword");
                txtPassword.setMinimumSize(new Dimension(130, 22));
                txtPassword.setPreferredSize(new Dimension(130, 22));
                if(!Controller.getPreferences("password").isEmpty()
                        && (Controller.getPreferences("autoLogin").equals("True")
                        || Controller.getPreferences("savePassword").equals("True"))){
                    txtPassword.setText(Controller.getPreferences("password"));
                }
                panelTxt.add(txtPassword);
            }
            panelDati.add(panelTxt);

            //======== panelSign ========
            {
                panelSign.setLayout(new VerticalLayout(4));

                //---- signProxy ----
                signProxy.setIcon(null);
                signProxy.setHorizontalAlignment(SwingConstants.LEFT);
                signProxy.setPreferredSize(new Dimension(28, 22));
                signProxy.setFocusable(false);
                panelSign.add(signProxy);

                //---- signUsername ----
                signUsername.setIcon(null);
                signUsername.setHorizontalAlignment(SwingConstants.LEFT);
                signUsername.setPreferredSize(new Dimension(28, 22));
                signUsername.setFocusable(false);
                panelSign.add(signUsername);

                //---- signPassword ----
                signPassword.setIcon(null);
                signPassword.setHorizontalAlignment(SwingConstants.LEFT);
                signPassword.setPreferredSize(new Dimension(28, 22));
                signPassword.setFocusable(false);
                panelSign.add(signPassword);
            }
            panelDati.add(panelSign);
        }
        add(panelDati);

        //======== panelButton ========
        {
            panelButton.setLayout(new FlowLayout());

            //---- btnLogin ----
            btnLogin.setText("Login");
            btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
            btnLogin.setPreferredSize(new Dimension(100, 40));
            btnLogin.setName("btnLogin");
            btnLogin.setActionCommand("btnLogin");

            panelButton.add(btnLogin);
        }
        add(panelButton);

        //======== panelCheckBtn ========
        {
            panelCheckBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 5));

            //---- ckBxAuto_login ----
            ckBxAuto_login.setText("Auto login");
            ckBxAuto_login.setName("ckBxAuto_login");
            ckBxAuto_login.setActionCommand("ckBxAuto_login");
            //ckBxAuto_login.setSelected(false);
            if(Controller.getPreferences("autoLogin").equals("True")){
                ckBxAuto_login.setSelected(true);

            }
            panelCheckBtn.add(ckBxAuto_login);

            //---- ckBxSave_psw ----
            ckBxSave_psw.setText("Save password");
            ckBxSave_psw.setMargin(new Insets(2, 12, 2, 2));
            ckBxSave_psw.setActionCommand("ckBxSave_psw");
            ckBxSave_psw.setName("ckBxSave_psw");
            //ckBxSave_psw.setSelected(false);
            if(Controller.getPreferences("savePassword").equals("True")){
                ckBxSave_psw.setSelected(true);
            }
            panelCheckBtn.add(ckBxSave_psw);
        }
        add(panelCheckBtn);

        //---- lblChange ----
        lblChange.setText("<html><u>I have no account, I want to register</u></html> ");
        lblChange.setHorizontalAlignment(SwingConstants.CENTER);
        lblChange.setForeground(Color.blue);
        lblChange.setName("lblChange");
        add(lblChange);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        txtProxy.addFocusListener(listener);
        txtUsername.addFocusListener(listener);
        txtPassword.addFocusListener(listener);
        btnLogin.addActionListener(listener);
        lblChange.addMouseListener(listener);



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(ckBxAuto_login.isSelected()){
                    btnLogin.doClick();
                }
            }
        });

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
    private JLabel lblLogin;
    private JLabel lblAlert;
    private JPanel panelDati;
    private JPanel panelLabel;
    private JLabel lblProxy;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel panelTxt;
    private JTextField txtProxy;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel panelSign;
    private JLabel signProxy;
    private JLabel signUsername;
    private JLabel signPassword;
    private JPanel panelButton;
    private JButton btnLogin;
    private JPanel panelCheckBtn;
    private JCheckBox ckBxAuto_login;
    private JCheckBox ckBxSave_psw;
    private JLabel lblChange;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public HashMap<String, Object> getData() {
        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("LabelAlert", lblAlert);
        uiData.put("ProxyHost", txtProxy);
        uiData.put("LabelImageProxy",signProxy);
        uiData.put("Username", txtUsername);
        uiData.put("LabelImageUsername",signUsername);
        uiData.put("Password", txtPassword);
        uiData.put("LabelImagePassword",signPassword);
        uiData.put("Autologin", ckBxAuto_login);
        uiData.put("SavePassword", ckBxSave_psw);
        uiData.put("ButtonLogin",btnLogin);

        return uiData;
    }
}
