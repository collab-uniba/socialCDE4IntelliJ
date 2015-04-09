package com.socialcdeIntellij.object;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Teo on 31/03/2015.
 */
public class PanelArea extends JPanel {
    public PanelArea(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public PanelArea(LayoutManager layout) {
        super(layout);
    }

    public PanelArea(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public PanelArea() {
    }

    private final Image img = new ImageIcon(this.getClass().getClassLoader().getResource("images/Baloon.png")).getImage();

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,1,4,null);
        super.paintComponent(g);
    }
}
