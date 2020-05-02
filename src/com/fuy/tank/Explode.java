package com.fuy.tank;

import java.awt.*;
//爆炸对象
public class Explode {

    private int x, y,width,height;
    private int step;
    private Boolean isLive = true;;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();

        //添加声音
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
    }

    //绘画方法
    public void paint(Graphics g) {     //画笔(系统自动初始化)
        if(!isLive) return;
        //加载图片
        g.drawImage(ResourceMgr.explodes[step],x,y,null);
        step ++;
        if(step >= ResourceMgr.explodes.length) {
            die();
            step = 0;
        }
    }

    private void die() {
        this.isLive = false;
    }

}
