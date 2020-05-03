package com.fuy.tank.strategy;

import com.fuy.tank.Player;

import java.io.Serializable;

//使用策略者模式
//开火策略 使得添加功能变得简单
public interface FireStrategy extends Serializable {
    public void fire(Player player);
}
