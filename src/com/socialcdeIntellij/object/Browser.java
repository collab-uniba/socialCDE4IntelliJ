package com.socialcdeIntellij.object;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Teo on 28/03/2015.
 */
public class Browser extends JPanel{
    final JWebBrowser wb;
    JPanel panel;
    String initialPage = "http://www.google.it";
    JFrame f = new JFrame();

    public Browser() {
        setLayout(new CardLayout());
        panel = new JPanel(new CardLayout());
        wb = new JWebBrowser();

        //wb.navigate(initialPage);
        wb.setMenuBarVisible(false);
        panel.add(wb);
        add(panel);
    }

    public JWebBrowser getWb() {
        return wb;
    }

    public String getInitialPage() {
        return initialPage;
    }

    public void setInitialPage(String initialPage) {
        this.initialPage = initialPage;
    }

    public String getUrlString(){
        return wb.getResourceLocation();
    }

    public void startBrowser(final String url){
        NativeInterface.open();

        //f.setLayout(new BorderLayout());
        //f.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        wb.navigate(url);

        setVisible(true);
        //f.add(this);
        //f.setVisible(true);
        //f.pack();
       // f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }




}