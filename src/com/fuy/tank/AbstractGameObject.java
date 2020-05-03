package com.fuy.tank;

import java.awt.*;
import java.io.Serializable;

public abstract class AbstractGameObject implements Serializable {

    public int x, y,width,height;
    public abstract void paint(Graphics g);
    public abstract boolean isLive();
}
