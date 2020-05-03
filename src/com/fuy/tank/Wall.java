package com.fuy.tank;

import java.awt.*;

public class Wall extends AbstractGameObject {

    private int x,y,w,h;
    private Rectangle rectangle;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x,y,w,h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(c);
    }

    @Override
    public boolean isLive() {
        return true;
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }
}
