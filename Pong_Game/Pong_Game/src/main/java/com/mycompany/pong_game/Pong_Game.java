
package com.mycompany.pong_game;

/**
 *
 * @author Rizwan
 */
import javax.swing.*;
import java.awt.*;
    
public class Pong_Game extends JFrame {
    Pong_Game(){
        GamePanel panel=new GamePanel();
        add(panel);
        setTitle("Pong Game");
        getContentPane().setBackground(Color.darkGray);
        pack();
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Pong_Game g=new Pong_Game();
    }
}

