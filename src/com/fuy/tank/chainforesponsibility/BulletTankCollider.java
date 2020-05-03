package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;
import com.fuy.tank.Bullet;
import com.fuy.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public void collide(AbstractGameObject ob1, AbstractGameObject ob2) {
        if(ob1 instanceof Bullet && ob2 instanceof Tank){
            Bullet b = (Bullet) ob1;
            Tank t= (Tank)ob2;
            b.collidesWithTank(t);
        }else if(ob1 instanceof Tank && ob2 instanceof Bullet){
            collide(ob2,ob1);
        }
    }
}
