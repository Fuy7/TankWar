package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;
import com.fuy.tank.Bullet;
import com.fuy.tank.Tank;

public class TankCollider implements Collider {
    @Override
    public void collide(AbstractGameObject ob1, AbstractGameObject ob2) {
        if(ob1 instanceof Tank && ob2 instanceof Tank && ob1 != ob2){
            Tank t1 = (Tank) ob1;
            Tank t2= (Tank)ob2;
            if(t1.isLive() && t2.isLive()){
                if(t1.getRectangle().intersects(t2.getRectangle())){
                    t1.back();
                    t2.back();
                }
            }
        }else if(ob1 instanceof Tank && ob2 instanceof Bullet){
            collide(ob2,ob1);
        }
    }
}
