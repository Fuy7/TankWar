package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;
import com.fuy.tank.Bullet;
import com.fuy.tank.Tank;
import com.fuy.tank.Wall;

public class TankWallCollider implements Collider {

    @Override
    public void collide(AbstractGameObject ob1, AbstractGameObject ob2) {
        if(ob1 instanceof Tank && ob2 instanceof Wall){
            Tank t = (Tank) ob1;
            Wall w= (Wall) ob2;
            if(t.isLive()){
                if(t.getRectangle().intersects(w.getRectangle())){
                    t.back();
                }
            }
        }else if(ob1 instanceof Tank && ob2 instanceof Bullet){
            collide(ob2,ob1);
        }
    }
}
