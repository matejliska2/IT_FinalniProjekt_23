package com.game;

import javax.swing.*;
import java.awt.*;

public class GameStart extends JFrame {

    public GameStart() {
        initUI();
    }

    public void initUI() {
        add(new Board());
        setTitle("Arkanoid");
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        getContentPane().setBackground(Color.BLUE);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater (() -> {
            var game = new GameStart();
            game.setVisible(true);
        });
    }
}