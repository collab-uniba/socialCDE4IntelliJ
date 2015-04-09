package com.socialcdeIntellij.object;

import org.jdesktop.swingx.HorizontalLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Teo on 29/03/2015.
 */
public class LabelClicked extends JPanel {
    private JPanel panel1;
    private GeneralLabel label;
    private String userType;


    public LabelClicked() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Pablo Rossi
        panel1 = new JPanel();
        label = new GeneralLabel();

        //======== this ========
        setBackground(Color.white);
        setName("lblUser");

        setLayout(new HorizontalLayout(0));

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(new FlowLayout());

            //---- btnService ----
           // btnService.setPreferredSize(new Dimension(70, 70));
            label.setName("label");
           // btnService.setIcon(new ImageIcon(getClass().getResource("/images/Hide.png")));
            label.setBackground(Color.white);
            panel1.add(label);
        }
        add(panel1);
    }

    public void setImage(Image img){
        label.setIcon(new ImageIcon(img));
    }

    public GeneralLabel getLabel(){return label;}

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
