package com.fuy.tank.chainforesponsibility;

import com.fuy.tank.AbstractGameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    public void collide(AbstractGameObject ob1,AbstractGameObject ob2);
}
