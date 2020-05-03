package com.fuy.tank;

import com.fuy.tank.chainforesponsibility.Collider;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//封装对象体
public class GameModel implements Serializable {

    private Player myTank;
    private Wall wall;
    private List<AbstractGameObject> gameObjects;
    private List<Collider> colliders;

    public GameModel() {
        initGameObject();
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("gameObjects: "+gameObjects.size(),10,50);
        //g.drawString("enemyTank: "+enemyTanks.size(),10,70);
        g.setColor(c);

        for (int i = 0; i < gameObjects.size(); i++) {
            if (!gameObjects.get(i).isLive()) {
                gameObjects.remove(i);     //对象消亡并从容器中清除
                break; //结束本次循环
            }
        }
        //使用游戏物体的绘画
        for (int i = 0; i < gameObjects.size(); i++) {

            for (int j = 0; j < gameObjects.size(); j++) {
                for (Collider collider : colliders) {
                    collider.collide(gameObjects.get(i),gameObjects.get(j));
                }
            }
            if(gameObjects.get(i).isLive()){    //只有物体存在才绘画
                gameObjects.get(i).paint(g);
            }
        }

    }

        //初始化游戏事务
    private void initGameObject() {
        myTank = new Player(100,100,Dir.L,Group.GOOD);
        wall = new Wall(300,400,200,50);
        gameObjects = new ArrayList<>();
        gameObjects.add(myTank);
        gameObjects.add(wall);
        //读取配置文件
        int tankCount = Integer.parseInt(PropertiesMgr.get("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            gameObjects.add(new Tank(100+i*200,200+i*50,Dir.D,Group.BAD));
        }
        //初始化碰撞检测
        initCollide();
    }

    private void initCollide() {
        colliders = new ArrayList<>();
        String[] colliderNames = PropertiesMgr.get("ColliderType").split(",");
        for (String colliderName : colliderNames) {
            try {
                //将类加载到内存
                Class clazz = Class.forName("com.fuy.tank.chainforesponsibility." + colliderName);
                //得到构造函数进行创建
                Collider c = (Collider) clazz.getDeclaredConstructor().newInstance();
                colliders.add(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void add(AbstractGameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public Player getMyTank(){
        return myTank;
    }

}
