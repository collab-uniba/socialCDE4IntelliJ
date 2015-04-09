/*
 * Created by JFormDesigner on Tue Mar 17 18:27:11 CET 2015
 */

package com.socialcdeIntellij.popup;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.shared.library.WOAuthData;
import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.*;

/**
 * @author Pablo Rossi
 */
public class PinWindow extends JDialog {

    private WService service;
    private WOAuthData oauthData;



    public PinWindow(Frame owner, WService service) {
        super(owner);
        initComponents();
        this.service = service;
    }

    public PinWindow(Dialog owner, WService service) {
        super(owner);
        initComponents();
        this.service = service;
    }

    public JLabel getLblPin() {
        return lblPin;
    }

    public void setLblPin(JLabel lblPin) {
        this.lblPin = lblPin;
    }

    public WService getService() {
        return service;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void setService(WService service) {
        this.service = service;
    }

    public WOAuthData getOauthData() {
        return oauthData;
    }

    public void setOauthData(WOAuthData oauthData) {
        this.oauthData = oauthData;
    }

    public JTextField getTxtPin() {
        return txtPin;
    }

    public void setTxtPin(JTextField txtPin) {
        this.txtPin = txtPin;
    }

    public PinWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        lblPin = new JLabel();
        panel2 = new JPanel();
        txtPin = new JTextField();
        panel1 = new JPanel();
        btnOk = new JButton();
        btnCancel = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new VerticalLayout(10));

                //---- lblPin ----
                lblPin.setText("Inserisci il PIN:");
                lblPin.setName("lblPin");
                contentPane.add(lblPin);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- txtPin ----
                    txtPin.setName("txtPin");
                    txtPin.setPreferredSize(new Dimension(200, 24));
                    panel2.add(txtPin);
                }
                contentPane.add(panel2);


        //======== panel1 ========
        {
            panel1.setLayout(new FlowLayout());

            //---- btnOk ----
            btnOk.setText("Ok");
            btnOk.setActionCommand("btnOk");
            btnOk.setPreferredSize(new Dimension(66, 32));
            btnOk.setName("btnOk");
            panel1.add(btnOk);

            //---- btnCancel ----
            btnCancel.setText("Cancel");
            btnCancel.setActionCommand("btnCancel");
            btnCancel.setPreferredSize(new Dimension(66, 32));
            btnCancel.setName("btnCancel");
            panel1.add(btnCancel);
        }
        contentPane.add(panel1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
    private JLabel lblPin;
    private JPanel panel2;
    private JTextField txtPin;
    private JPanel panel1;
    private JButton btnOk;
    private JButton btnCancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
