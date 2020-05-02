package com.fuy.tank;

//主运行类
public class Main {
    public static void main(String[] args) throws InterruptedException {

        TankFrame.INSTANCE.setVisible(true);   //设置显示

        while(true){
            Thread.sleep(25);
            TankFrame.INSTANCE.repaint();    //自动调用paint方法
        }
    }

}
