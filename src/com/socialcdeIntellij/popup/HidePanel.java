/*
 * Created by JFormDesigner on Mon Mar 30 18:58:08 CEST 2015
 */

package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.object.GeneralButton;
import com.socialcdeIntellij.shared.library.WHidden;
import com.socialcdeIntellij.shared.library.WUser;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * @author David Rossi
 */
public class HidePanel extends JDialog {

    private WUser user_selected;
    private HashMap<String, Button> checkboxCreated;

    public HidePanel(Frame owner) {
        super(owner);
        initComponents();
    }

    public HidePanel(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - David Rossi
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        lblTitle = new JLabel();
        panel1 = new JPanel();
        ckBoxSuggestions = new JCheckBox();
        ckBoxIteration = new JCheckBox();
        ckBoxInteractive = new JCheckBox();
        buttonBar = new JPanel();
        okButton = new GeneralButton();
        cancelButton = new GeneralButton();

        user_selected = (WUser) Controller.temporaryInformation.get("CurrentUserView");

        final WHidden hiddenSetting = Controller.getProxy().GetUserHideSettings(
                Controller.getCurrentUser().Username,
                Controller.getCurrentUserPassword(), user_selected.Id);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(4));

                //---- lblTitle ----
                lblTitle.setText("Hide user from:");
                lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
                contentPanel.add(lblTitle);

                //======== panel1 ========
                {
                    panel1.setLayout(new VerticalLayout(2));

                    //---- ckBoxSuggestions ----
                    ckBoxSuggestions.setText("Suggestions");
                    ckBoxSuggestions.setName("ckBoxSuggestions");
                    if(hiddenSetting.isSuggestions())
                        ckBoxSuggestions.setSelected(true);
                    panel1.add(ckBoxSuggestions);

                    //---- ckBoxIteration ----
                    ckBoxIteration.setText("Iteration timeline");
                    ckBoxIteration.setName("ckBoxIteration");
                    if(hiddenSetting.isDynamic())
                        ckBoxIteration.setSelected(true);
                    panel1.add(ckBoxIteration);

                    //---- ckBoxInteractive ----
                    ckBoxInteractive.setText("Interactive timeline");
                    ckBoxInteractive.setName("ckBoxInteractive");
                    if(hiddenSetting.isInteractive())
                        ckBoxInteractive.setSelected(true);
                    panel1.add(ckBoxInteractive);
                }
                contentPanel.add(panel1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Save");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (!Controller.getProxy().UpdateHiddenUser(
                                Controller.getCurrentUser().Username,
                                Controller.getCurrentUserPassword(),
                                user_selected.Id, ckBoxSuggestions.isSelected(),
                                ckBoxIteration.isSelected(),
                                ckBoxInteractive.isSelected() )) {

                            JOptionPane.showMessageDialog(Controller.getFrame(),
                                    "Something was wrong, please try again!",
                                    "SocialCDE message", JOptionPane.WARNING_MESSAGE);
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(Controller.getFrame(), "Saved",
                                    "SocialCDE message", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                });

                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - David Rossi
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel lblTitle;
    private JPanel panel1;
    private JCheckBox ckBoxSuggestions;
    private JCheckBox ckBoxIteration;
    private JCheckBox ckBoxInteractive;
    private JPanel buttonBar;
    private GeneralButton okButton;
    private GeneralButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public HashMap<String, Object> getData() {
        // TODO Auto-generated method stub
        HashMap<String, Object> uiData = new HashMap<String, Object>();
        uiData.put("hideSuggestions", ckBoxSuggestions);
        uiData.put("hideInteractive", ckBoxInteractive);
        uiData.put("hideIteration", ckBoxIteration);
        return uiData;
    }
}
