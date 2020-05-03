package com.fuy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


//窗口类
public class TankFrame extends Frame{

    //使用单例模式使得只有一个全局TankFrame对象(可以解耦)
    public static final TankFrame INSTANCE = new TankFrame();   //唯一一个TankFrame对象
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    Image offScreenImage = null;

    private GameModel gm = new GameModel();


    public TankFrame() {
        this.setTitle("TankWar");
        this.setLocation(500,600); //位置
        this.setSize(GAME_WIDTH,GAME_HEIGHT); //设置大小
        this.addKeyListener(new TankKeyListener());  //添加键盘监听器(是Observer设计模式)
    }

    //绘画方法
    @Override
    public void paint(Graphics g) {     //画笔(系统自动初始化)
        gm.paint(g);
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

    //使用内部类
    private class TankKeyListener extends KeyAdapter {      //KeyAdapter里面已经实现了KeyListener接口的方法

        @Override
        public void keyPressed(KeyEvent e) {    //按下
            //KeyEvent e 按键事件
            //面对对象思想： 坦克自己处理自己
            gm.getMyTank().keyPressed(e);  //将键盘事件传递给他,自己移动
        }

        @Override
        public void keyReleased(KeyEvent e) {    //按键抬起
            gm.getMyTank().keyReleased(e);
        }
    }

    //获取页面中的游戏物体对象,用来调用它的add方法,提供给其他类进行往里面添加
    public GameModel getGameModel(){
        return this.gm;
    }
}
