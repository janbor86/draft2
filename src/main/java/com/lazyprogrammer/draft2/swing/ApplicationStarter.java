package com.lazyprogrammer.draft2.swing;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ApplicationStarter extends JFrame {

    public ApplicationStarter() {
        setTitle("Project Grand Rouge Strategy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Get the default screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = ge.getScreenDevices();
        final var secondMonitor = devices[1];
        setBounds(secondMonitor.getDefaultConfiguration().getBounds());
        // borderless
        setUndecorated(true);
        // fullscreen
        setPreferredSize(secondMonitor.getDefaultConfiguration().getBounds().getSize());

        JPanel panel = new MainPanel();
        setContentPane(panel);
        pack();
//        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicationStarter::new);
    }
}
