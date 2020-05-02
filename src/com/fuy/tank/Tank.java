package com.fuy.tank;


import java.awt.*;
import java.util.Random;

//坦克实体类
public class Tank {

    private int x, y,oldX,oldY;
    private boolean moving = true;  //坦克运动状态
    private boolean isLive = true;
    private Dir dir;    //坦克方向
    public static final int SPEED = 5;
    private Group group;
    private int tankWidth;
    private int tankHeight;

    public Group getGroup() {
        return group;
    }


    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
        this.tankWidth = ResourceMgr.badTankU.getWidth();
        this.tankHeight = ResourceMgr.badTankU.getHeight();
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {

        if(!this.isLive) return;    //判断坦克是否死亡
            //加载图片
            switch (dir){
                case L:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.bodTankR,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;
        }

        move(); //每次画的时候就更新下坐标
    }

    //开火
    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int by = y + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullet(bx,by,dir,group));
    }

    private Random random = new Random();
    //坦克移动方法
    private void move() {
        if(moving == false) return; //停止状态
        oldX = x;
        oldY = y;
        boundCheck();   //碰撞检测
        switch (dir){
            case L : x-=SPEED; break;
            case R : x+=SPEED; break;
            case U : y-=SPEED; break;
            case D : y+=SPEED; break;
        }
        //生成随机方向
        randomDir();
        if((random.nextInt(100)) > 95) fire();
    }


    private void randomDir() {
        if((random.nextInt(100)) < 90) return;
        this.dir = dir.randomDir();
    }

    //边界检测
    private void boundCheck() {
        if(x < (0+tankWidth) || y  < (50+tankHeight) || x + tankWidth > TankFrame.GAME_WIDTH || y + tankHeight> TankFrame.GAME_HEIGHT){
            back();
        }
    }

    //回退方法
    private void back() {
        this.x = oldX;
        this.y  =oldY;
        randomDir();
    }

    public void die() {
        this.setLive(false);
        //发送爆炸
        TankFrame.INSTANCE.add(new Explode(x,y));
    }
}
