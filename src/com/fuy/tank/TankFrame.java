package com.fuy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//窗口类
public class TankFrame extends Frame{

    //使用单例模式使得只有一个全局TankFrame对象(可以解耦)
    public static final TankFrame INSTANCE = new TankFrame();   //唯一一个TankFrame对象
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Image offScreenImage = null;
    private Player myTank;
    private List<Tank> enemyTanks;
    private List<Bullet> bullets;
    private List<Explode> explodes;

    private TankFrame() {
        this.setTitle("TankWar");
        this.setLocation(500,600); //位置
        this.setSize(GAME_WIDTH,GAME_HEIGHT); //设置大小
        this.addKeyListener(new TankKeyListener());  //添加键盘监听器(是Observer设计模式)
        initGameObject();
    }

    //初始化游戏事务
    private void initGameObject() {
        myTank = new Player(100,100,Dir.L,Group.GOOD);
        enemyTanks = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();
        //读取配置文件
        int tankCount = Integer.parseInt(PropertiesMgr.getTankCount("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            enemyTanks.add(new Tank(100+i*50,200,Dir.D,Group.BAD));
        }
    }

    //绘画方法
    @Override
    public void paint(Graphics g) {     //画笔(系统自动初始化)
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("bullet: "+bullets.size(),10,50);
        g.drawString("enemyTank: "+enemyTanks.size(),10,70);
        g.setColor(c);
        //面对对象思想： 坦克自己处理自己
        myTank.paint(g);    //将画笔传递给坦克,自己画自己

        //画敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            if(!enemyTanks.get(i).isLive()){
                enemyTanks.remove(i);
            }
            else {
                enemyTanks.get(i).paint(g);
            }
        }

        //子弹与坦克碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                //先进行碰撞检测
                bullets.get(i).collidesWithTank(enemyTanks.get(j));
            }
            //与自己检测碰撞
            //bullets.get(i).collidesWithMe(myTank);
            if(!bullets.get(i).isLive()){
                bullets.remove(i);  //根据子弹状态值判断是否删除
            } else {
                bullets.get(i).paint(g);
            }
        }

        //画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }

    //向frame中添加子弹
    public void add(Bullet bullet){
        this.bullets.add(bullet);
    }

    //向frame中添加爆炸对象
    public void add(Explode explode) {
        this.explodes.add(explode);
    }

    //使用内部类
    private class TankKeyListener extends KeyAdapter {      //KeyAdapter里面已经实现了KeyListener接口的方法

        @Override
        public void keyPressed(KeyEvent e) {    //按下
            //KeyEvent e 按键事件
            //面对对象思想： 坦克自己处理自己
            myTank.keyPressed(e);  //将键盘事件传递给他,自己移动
        }

        @Override
        public void keyReleased(KeyEvent e) {    //按键抬起
            myTank.keyReleased(e);
        }
    }

    /**
     * 解决闪烁 双缓冲问题
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

    }

}
