package com.game;

import javax.swing.ImageIcon;

public class Ball extends ImgSetup{

    private int drcx;
    private int drcy;

    public Ball(){
        initBall();
    }
    private void initBall(){

        drcx = 1;
        drcy = -1;
        loadImage();
        getImgDimensions();
        resetState();
    }
    private void loadImage(){
        var i = new ImageIcon("src/Ball.png)");
        image = i.getImage();
    }

    void movement(){

        x += drcx;
        y += drcy;

        if (x == 0) {
            setDrcx(1);
        }
        if (x == Arkanoid.WIDTH - imgWidth){
            setDrcx(-1);
        }
        if (y == 0) {
            setDrcy(1);
        }
    }
    private void resetState() {
        x = Arkanoid.INIT_BALL_X;
        y = Arkanoid.INIT_BALL_Y;
    }
    void setDrcx(int x) {
        drcx = x;
    }
    void setDrcy(int y){
        drcy = y;
    }

    int getDrcy() {
        return drcy;
    }
}