package com.fuy.tank;

import java.awt.*;

public abstract class AbstractGameObject {

    public int x, y,width,height;
    public abstract void paint(Graphics g);
    public abstract boolean isLive();
}
