package com.fuy.tank;

import java.util.Random;

//使用枚举类型存储坦克的方法 因为枚举类型不可改变,防止修改出错
public enum Dir {
    L,R,U,D;

    private static Random random = new Random();
    public Dir randomDir(){
        return values()[random.nextInt(Dir.values().length)];
    }
}
