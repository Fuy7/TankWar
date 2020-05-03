package com.fuy.tank;

import com.fuy.tank.strategy.FireStrategy;
import java.awt.*;
import java.awt.event.KeyEvent;

//坦克实体类
public class Player extends AbstractGameObject{

    public static final int SPEED = 5;
    private int x, y;
    private boolean bL,bR,bU,bD;    //用来记录哪个键被按下
    private boolean moving;  //坦克运动状态
    private boolean isLive = true;
    private Dir dir;    //坦克方向
    private Group group;

    public Dir getDir() {
        return dir;
    }

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        initStrategy(); //创建类的时候加载一次
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
                    g.drawImage(ResourceMgr.goodTankL,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD,x,y,null);
                    break;
            }

        move(); //每次画的时候就更新下坐标
    }

    public Group getGroup() {
        return group;
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();   //获取按的是哪个键
        switch (keyCode){
            case KeyEvent.VK_LEFT : bL = false; break;
            case KeyEvent.VK_RIGHT : bR = false; break;
            case KeyEvent.VK_UP : bU = false; break;
            case KeyEvent.VK_DOWN : bD = false; break;
            case KeyEvent.VK_CONTROL :  fire(); break;
        }
        setMainDir();   //设置方向
    }

    private FireStrategy strategy = null;

    private void initStrategy(){
        ClassLoader loader = Player.class.getClassLoader();//获取该类的ClassLoader
        String strategyName = PropertiesMgr.get("FireStrategyType");//读取配置文件中的配置
        try {
            //Class clazz = loader.loadClass("com.fuy.tank.strategy." + strategyName); //将对应的strategy加载到内存中
            Class clazz = Class.forName("com.fuy.tank.strategy." + strategyName); //将对应的strategy加载到内存中
            strategy =  (FireStrategy) (clazz.getDeclaredConstructor().newInstance());   //得到类的无参构造函数进行实例化对象
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //开火
    private void fire() {
        strategy.fire(this);
    }

    public void keyPressed(KeyEvent e) {     //KeyEvent e 按键事件
        int keyCode = e.getKeyCode();   //获取按的是哪个键
        switch (keyCode){
            case KeyEvent.VK_LEFT : bL = true; break;
            case KeyEvent.VK_RIGHT : bR = true; break;
            case KeyEvent.VK_UP : bU = true; break;
            case KeyEvent.VK_DOWN : bD = true; break;
        }
        setMainDir();   //设置方向
    }

    private void setMainDir() {
        if(!bL && !bD && !bR && !bU) moving = false;
        else {
            moving = true;
            if(bL && !bD && !bR && !bU) dir = Dir.L;
            if(!bL && bD && !bR && !bU) dir = Dir.D;
            if(!bL && !bD && bR && !bU) dir = Dir.R;
            if(!bL && !bD && !bR && bU) dir = Dir.U;
        }
    }
    //坦克移动方法
    private void move() {
        if(moving == false) return;
        switch (dir){
            case L : x-=SPEED; break;
            case R : x+=SPEED; break;
            case U : y-=SPEED; break;
            case D : y+=SPEED; break;
        }
    }

    public void die() {
        this.setLive(false);
    }
}
