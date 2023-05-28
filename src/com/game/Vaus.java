package com.game;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Vaus extends ImgSetup{

    private int dx;

    public Vaus(){
        initVaus();
    }

    private void initVaus(){
        loadImage();
        getImgDimensions();
        resetState();
    }

    private void loadImage(){
        var i = new ImageIcon("Vaus.png");
        image = i.getImage();
    }

    void move(){
        x += dx;

        if (x <= 0){
            x = 0;
        }

        if (x >= Arkanoid.WIDTH - imgWidth){
            x = Arkanoid.WIDTH - imgWidth;
        }
    }

    void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A){
            dx = -1;
        }else if
        (key == KeyEvent.VK_LEFT){
            dx = -1;
        }
        if (key == KeyEvent.VK_D){
            dx = 1;
        }else if
        (key == KeyEvent.VK_RIGHT){
            dx = 1;
        }
    }

    void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A){
            dx = 0;
        }else if
        (key == KeyEvent.VK_LEFT){
            dx = 0;
        }
        if (key == KeyEvent.VK_D){
            dx = 0;
        }else if
        (key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    private void resetState(){
        x = Arkanoid.INIT_VAUS_X;
        y = Arkanoid.INIT_VAUS_Y;
    }
}
