package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;

public interface Collider {
    public void collide(AbstractGameObject ob1,AbstractGameObject ob2);
}
