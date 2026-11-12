package com.deltamini;
// #5 11:22
/* TODO:
  - make it not bad
 */
import javax.swing.JFrame;

import com.deltamini.main.GamePanel;

import java.awt.*;

public class Main {
    public static void main() {
        //Initialize everything
        JFrame window = new JFrame("MANTLE");
        GamePanel panel = new GamePanel();
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
