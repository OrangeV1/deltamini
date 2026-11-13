package com.deltamini;
// #5 11:22
/* TODO:
  - make it not bad
 */
import javax.swing.*;

import com.deltamini.main.GamePanel;

import java.awt.*;
import java.net.URL;

public class Main {
    public static void main() {
        //Initialize everything
        JFrame window = new JFrame("MANTLE");
        GamePanel panel = new GamePanel();
        URL iconURL = Main.class.getResource("/assets/icon.png");
        if (iconURL != null) {
            window.setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("Could not find icon.png on classpath");
        }
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocation(0, 0);
        window.setResizable(false);
        window.add(panel);
        panel.setBackground(Color.BLACK);

        window.pack();

        panel.startGameThread();
    }
}
