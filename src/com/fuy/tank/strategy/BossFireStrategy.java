package com.fuy.tank.strategy;

import com.fuy.tank.Dir;
import com.fuy.tank.Player;
import com.fuy.tank.ResourceMgr;
import com.fuy.tank.TankFrame;

//四方向开火方法
public class BossFireStrategy implements FireStrategy{

    @Override
    public void fire(Player player) {
        int bx = player.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int by = player.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            TankFrame.INSTANCE.getGameModel().add(new com.fuy.tank.Bullet(bx,by,dir,player.getGroup()));
        }
    }
}
