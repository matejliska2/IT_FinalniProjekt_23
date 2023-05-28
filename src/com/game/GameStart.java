package com.game;

import javax.swing.JFrame;
import java.awt.EventQueue;

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
    }
    public static void main(String[] args) {
        EventQueue.invokeLater (() -> {
            var game = new GameStart();
            game.setVisible(true);
        });
    }
}