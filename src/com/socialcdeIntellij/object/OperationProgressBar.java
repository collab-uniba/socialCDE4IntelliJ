package com.socialcdeIntellij.object;

import org.jdesktop.swingx.*;
import javax.swing.*;
import java.awt.*;


public class OperationProgressBar extends JPanel  {

    private JPanel panel2;
    private JPanel panel3;
    private JLabel lblOpBar;
    private JProgressBar progressOpBar;
    //private final Image img = new ImageIcon(this.getClass().getClassLoader().getResource("images/Wallpaper.png")).getImage();

    public OperationProgressBar() {
        initComponents();
    }

    private void initComponents() {

        panel2 = new JPanel();
        panel3 = new JPanel();
        lblOpBar = new JLabel();
        progressOpBar = new JProgressBar();

        //======== this ========
        setBackground(UIManager.getColor("Button.background"));
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setLayout(new CardLayout(0, 160));

            //======== panel3 ========
            {
                panel3.setLayout(new VerticalLayout(10));

                //---- lblOpBar ----
                lblOpBar.setText("Operation in progress");
                lblOpBar.setHorizontalAlignment(SwingConstants.CENTER);
                lblOpBar.setFont(new Font("Dialog", Font.BOLD, 20));
                panel3.add(lblOpBar);

                //---- progressOpBar ----
                progressOpBar.setBackground(Color.white);
                progressOpBar.setPreferredSize(new Dimension(146, 20));
               // progressOpBar.setStringPainted(true);
                progressOpBar.setIndeterminate(true);
                panel3.add(progressOpBar);
            }
            panel2.add(panel3, "card1");
        }
        add(panel2, BorderLayout.CENTER);

    }

    public void setLabelBar(String label){
        lblOpBar.setText(label);
    }

    /*@Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }*/


    public void startWork() {
        // Creates and starts the thread.
        Thread workThread = new Thread(new WorkRunnable());
        workThread.start();

    }

    private class WorkRunnable implements Runnable {
        public void run() {
            try {
                for (int i = 0; i <= 100; i+=5) {
                    updateProgress(i);
                    // A sleep .... just to do something.
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                }
            } finally {
                // Always executes this.
                updateProgress(0);
                setVisible(false);
                revalidate();

            }
        }
    }

    private void updateProgress(final int value) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressOpBar.setValue(value);
            }
        });
    }

}
