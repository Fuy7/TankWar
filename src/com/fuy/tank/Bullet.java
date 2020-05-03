package com.fuy.tank;

import java.awt.*;
//子弹类
public class Bullet extends AbstractGameObject{

    public static final int SPEED = 10;
    private int x, y;
    private int w = ResourceMgr.bulletU.getWidth();
    private int h = ResourceMgr.bulletU.getHeight();
    private Dir dir;
    private Group group;
    private boolean isLive = true;
    private Rectangle rectangle;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rectangle = new Rectangle(x,y,w,h);
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    //绘画方法
    public void paint(Graphics g) {     //画笔(系统自动初始化)
        //if(!this.isLive) return;
        //加载图片
            switch (dir) {
                case L:
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
            }
            move(); //每次画的时候就更新下坐标
            //需要更新子弹的坐标
            rectangle.x = x;
            rectangle.y = y;
    }

    private void move() {
        switch (dir){
            case L : x-=SPEED; break;
            case R : x+=SPEED; break;
            case U : y-=SPEED; break;
            case D : y+=SPEED; break;
        }


        boundCheck();   //边界检测
    }

    //碰撞检测
    public void collidesWithTank(Tank tank){
        if(!this.isLive || !tank.isLive()) return;//死亡后不再碰撞
        if(this.group == tank.getGroup()) return;//己方子弹不碰撞
        //获取子弹的方块
        //获取到坦克方块
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),ResourceMgr.badTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        //检测相交 intersects方法
        if(rectangle.intersects(rectTank)){
            this.die();
            tank.die();
        }
    }
    //碰撞检测
    public void collidesWithMe(Player me){
        if(!this.isLive || !me.isLive()) return;//死亡后不再碰撞
        if(this.group == me.getGroup()) return;//己方子弹不碰撞
        //获取子弹的方块
        Rectangle rect = new Rectangle(x,y,ResourceMgr.bulletU.getWidth(),ResourceMgr.bulletU.getHeight());
        //获取到坦克方块
        Rectangle rectTank = new Rectangle(me.getX(),me.getY(),ResourceMgr.goodTankU.getWidth(),ResourceMgr.goodTankU.getHeight());
        //检测相交 intersects方法
        if(rect.intersects(rectTank)){
            this.die();
            me.die();
        }
    }

    //子弹消失
    public void die() {
        this.setLive(false);
    }

    //边界检测
    private void boundCheck() {
        if(x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            isLive = false;
        }
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
