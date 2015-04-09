/*
 * Created by JFormDesigner on Mon Mar 02 12:00:16 CET 2015
 */

package com.socialcdeIntellij.popup;

import com.socialcdeIntellij.action.ActionGeneral;
import com.socialcdeIntellij.controller.Controller;
import com.socialcdeIntellij.shared.library.WFeature;
import com.socialcdeIntellij.shared.library.WService;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Davide Rossi
 */
public class PopupServiceRegistered extends JDialog {
    private WService service;
    private ArrayList<JCheckBox> checkboxCreated;
    private ActionGeneral listener;
    private HashMap<String, Object> uiData = new HashMap<String, Object>();
    boolean selectAllItems;

    public boolean isSelectAllItems() {
        return selectAllItems;
    }

    public void setSelectAllItems(boolean selectAllItems) {
        this.selectAllItems = selectAllItems;
    }

    public PopupServiceRegistered(Frame owner) {
        super(owner);
        initComponents();

    }

    public PopupServiceRegistered(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        lblServiceName = new JLabel();
        panel2 = new JPanel();
        lblServiceImage = new JLabel();
        panel3 = new JPanel();
        ckboxAvatar = new JCheckBox();
        ckboxTimeline = new JCheckBox();
        ckboxFollowing = new JCheckBox();
        ckboxFollowers = new JCheckBox();
        buttonBar = new JPanel();
        btnUnsubscrive = new JButton();
        btnSave = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new VerticalLayout(25));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

                    //---- label1 ----
                    label1.setText("Registration of service:");
                    panel1.add(label1);

                    //---- lblServiceName ----
                    lblServiceName.setText("text");
                    lblServiceName.setName("lblServiceName");
                    panel1.add(lblServiceName);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new HorizontalLayout(20));

                    //---- lblServiceImage ----
                    lblServiceImage.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
                    lblServiceImage.setName("lblServiceImage");
                    panel2.add(lblServiceImage);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new VerticalLayout(10));

                        service = (WService) Controller.temporaryInformation.get("service");

                        System.out.println("forse qui**************** "+service);
                        WFeature[] featuresService = Controller.getProxy().GetChosenFeatures(
                                Controller.getCurrentUser().Username,
                                Controller.getCurrentUserPassword(), service.Id);

                        checkboxCreated = new ArrayList<JCheckBox>();

                        for (int i = 0; i < featuresService.length; i++) {

                            JCheckBox featureService = new JCheckBox();
                            featureService.setText(featuresService[i].Description);
                            setData("FeatureName", featuresService[i].Name);
                            featureService.setActionCommand(featuresService[i].Name);
                            featureService.setName(featuresService[i].Name);
                            if (featuresService[i].IsChosen) {
                                featureService.setSelected(true);

                            }
                            checkboxCreated.add(featureService);
                            panel3.add(featureService);

                        }
                    }
                    panel2.add(panel3);
                }
                contentPanel.add(panel2);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- btnUnsubscrive ----
                btnUnsubscrive.setText("Unsubscrive");
                btnUnsubscrive.setActionCommand("btnUnsubscrive");
                buttonBar.add(btnUnsubscrive, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- btnSave ----
                btnSave.setText("Save");
                btnSave.setPreferredSize(new Dimension(105, 26));
                btnSave.setActionCommand("btnSave");
                buttonBar.add(btnSave, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    // Generated using JFormDesigner Evaluation license - Pablo Rossi
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JLabel lblServiceName;
    private JPanel panel2;
    private JLabel lblServiceImage;
    private JPanel panel3;
    private JCheckBox ckboxAvatar;
    private JCheckBox ckboxTimeline;
    private JCheckBox ckboxFollowing;
    private JCheckBox ckboxFollowers;
    private JPanel buttonBar;
    private JButton btnUnsubscrive;
    private JButton btnSave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public ArrayList<JCheckBox> getCheckboxCreated() {
        return checkboxCreated;
    }

    public void setCheckboxCreated(ArrayList<JCheckBox> checkboxCreated) {
        this.checkboxCreated = checkboxCreated;
    }

    public WService getService() {
        return service;
    }

    public void setService(WService service) {
        this.service = service;
    }

    public JButton getSaveButton(){return btnSave;}

    public JButton getUnsubscriveButton(){return btnUnsubscrive;}

    public void setImage(Image img){
        lblServiceImage.setIcon(new ImageIcon(img));
    }

    public void setServiceName(String name){
        lblServiceName.setText(name);
    }



    public HashMap<String, Object> getData() {

        return uiData;
    }

    public void setData(String name, Object object){
        getData().put(name, object);
    }
}
