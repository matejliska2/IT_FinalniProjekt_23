package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel{

    private Ball ball;
    private Bricks[] bricks;
    private Vaus vaus;
    private String endMessage = "Game over";
    private Timer timer;
    private boolean inGame = true;

    public Board(){
        initBoard();
    }
    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Arkanoid.WIDTH, Arkanoid.HEIGHT));
        gameInit();
    }
    private void gameInit(){
        bricks = new Bricks[Arkanoid.N_OF_BRICKS];
        ball = new Ball();
        vaus = new Vaus();
        int k = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j<6; j++){
                bricks[k] = new Bricks(j * 40 + 30, i * 10 + 50);
                k++;
            }
        }
        timer = new Timer(Arkanoid.SPEED, new GameCycle());
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        var g2d = (Graphics2D)g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame){
            drawObjects(g2d);
        }
        else {
            gameFinished(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawObjects (Graphics2D g2d) {
        g2d.drawImage(ball.getImage(), ball.getX(),ball.getY(), ball.getImgWidth(), ball.getImgHeight(), this);
        g2d.drawImage(vaus.getImage(), vaus.getX(),vaus.getY(), vaus.getImgWidth(), vaus.getImgHeight(), this);
        for (int i = 0; i< Arkanoid.N_OF_BRICKS; i++) {
            if(!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks [i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImgWidth(), bricks [i].getImgHeight(), this);
            }
        }
    }
    private void gameFinished (Graphics2D g2d) {
        var font = new Font("Verdana", Font.BOLD,18);
        FontMetrics fontMetrics = this.getFontMetrics(font);
        g2d.setColor(Color.black);
        g2d.setFont(font);
        g2d.drawString(endMessage, (Arkanoid.WIDTH - fontMetrics.stringWidth(endMessage)) / 2, Arkanoid.WIDTH / 2);
    }
    private class TAdapter extends KeyAdapter {
        public void keyReleased (KeyEvent e) {
            vaus.keyReleased (e);
        }
        public void keyPressed (KeyEvent e) {
            vaus.keyPressed(e);
        }
    }
    private class GameCycle implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            doGameCycle();
        }
    }
    private void doGameCycle() {
        ball.movement();
        vaus.move();
        checkCollision();
        repaint();
    }
    public void stopGame() {
        inGame = false;
        timer.stop();
    }
    private void checkCollision() {
        if (ball.getRct().getMaxY() > Arkanoid.BOTTOM_EDGE) {
            stopGame();
        }
        for (int i = 0, j=0; i< Arkanoid.N_OF_BRICKS; i++) {
            if (bricks[i].isDestroyed()) {
                j++;
            }
            if (j == Arkanoid.N_OF_BRICKS) {
                endMessage="You won Arkanoid";
                stopGame();
            }
        }

       if ((ball.getRct().intersects(vaus.getRct()))) {
        int vausLPos = (int) vaus.getRct().getMinX();
        int ballLPos = (int) ball.getRct().getMinX();

        int first = vausLPos + 8;
        int second = vausLPos + 16;
        int third = vausLPos + 24;
        int fourth = vausLPos + 32;

        if (ballLPos< first) {
            ball.setDrcx(-1);
            ball.setDrcy(-1);
        }
        if (ballLPos >= first && ballLPos <second) {
            ball.setDrcx(-1);
        }
        if (ballLPos >= second && ballLPos < third){
            ball.setDrcx(0);
            ball.setDrcy(-1);
        }
        if (ballLPos >= third && ballLPos < fourth){
            ball.setDrcx(-1);
            ball.setDrcy(-1 * ball.getDrcy());
        }
        if  (ballLPos > fourth){
            ball.setDrcx(1);
            ball.setDrcy(-1);
        }
    }
    for (int i = 0; i < Arkanoid.N_OF_BRICKS; i++){
        if ((ball.getRct().intersects(bricks[i].getRct()))){
            int ballLeft = (int) ball.getRct().getMinX();
            int ballHeight = (int) ball.getRct().getHeight();
            int ballWidth = (int) ball.getRct().getWidth();
            int ballTop = (int) ball.getRct().getMinY();

            var pointRight = new Point (ballLeft + ballWidth + 1, ballTop);
            var pointLeft = new Point (ballLeft -1, ballTop);
            var pointTop = new Point (ballLeft, ballTop-1);
            var pointBottom = new Point (ballLeft, ballTop + ballHeight +1);
            if (!bricks[i].isDestroyed()) {
                if (bricks[i].getRct().contains(pointRight)) {
                    ball.setDrcx(-1);
                }else if
                (bricks[i].getRct().contains(pointLeft)){
                    ball.setDrcx(1);
                }
                if (bricks[i].getRct().contains(pointTop)){
                    ball.setDrcy(1);
                }else if
                (bricks[i].getRct().contains(pointBottom)){
                    ball.setDrcy(-1);
                }
                bricks[i].setDestroyed(true);
            }
        }
    }
}
}