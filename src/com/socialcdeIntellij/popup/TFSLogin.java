package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

public class TFSLogin extends JDialog {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panelDati;
    private JPanel panelLabel;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel panelTxt;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
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

        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panelDati = new JPanel();
        panelLabel = new JPanel();
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        panelTxt = new JPanel();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(250,200));

        {
            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(10));

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

                        //---- txtUsername ----

                        txtUsername.setPreferredSize(new Dimension(120, 24));
                        txtUsername.setAutoscrolls(false);
                        txtUsername.setMargin(new Insets(20, 10, 20, 59));
                        txtUsername.setAlignmentX(28.5F);
                        txtUsername.setName("txtUsername");
                        panelTxt.add(txtUsername);

                        //---- txtPassword ----
                        txtPassword.setName("txtPassword");
                        txtPassword.setPreferredSize(new Dimension(120, 24));
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

    }

    public HashMap<String, Object> getData() {

        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("textUsername", txtUsername);
        uiData.put("textPassword", txtPassword);

        return uiData;
    }
}
