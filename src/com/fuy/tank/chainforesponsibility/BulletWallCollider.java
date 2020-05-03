package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;
import com.fuy.tank.Bullet;
import com.fuy.tank.Tank;
import com.fuy.tank.Wall;

import java.awt.*;

public class BulletWallCollider implements Collider {

    @Override
    public void collide(AbstractGameObject ob1, AbstractGameObject ob2) {
        if(ob1 instanceof Bullet && ob2 instanceof Wall){
            Bullet b = (Bullet) ob1;
            Wall w= (Wall) ob2;
            if(b.isLive()){
                if(b.getRectangle().intersects(w.getRectangle())){
                    b.die();
                }
            }
        }else if(ob1 instanceof Tank && ob2 instanceof Bullet){
            collide(ob2,ob1);
        }
    }
}
