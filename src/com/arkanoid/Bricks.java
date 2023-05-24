package com.arkanoid;

import javax.swing.*;

public class Bricks extends ImgSetup{

    private boolean destroyed;

    private void initBricks(int x, int y){
        this.x = x;
        this.y = y;

        destroyed = false;
        loadImage();
        getImgDimensions();
    }

    public Bricks (int x, int y){
        initBricks(x, y);
    }

    private void loadImage(){
        var i = new ImageIcon("Brick.png)");
        image = i.getImage();
    }

    boolean isDestroyed(){
        return destroyed;
    }

    void setDestroyed(boolean val){
        destroyed = val;
    }

}
