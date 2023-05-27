package com.game;

import java.awt.*;

public class ImgSetup {

    int x;
    int y;

    int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    int imgHeight;
    int imgWidth;

    int getImgHeight() {
        return imgHeight;
    }

    private void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    int getImgWidth() {
        return imgWidth;
    }

    private void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    Image image;

    Image getImage() {
        return image;
    }

    Rectangle getRct(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    void getImgDimensions(){
        imgWidth = image.getWidth(null);
        imgHeight = image.getHeight(null);
    }
}
