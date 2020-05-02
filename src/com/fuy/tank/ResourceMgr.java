package com.fuy.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/*
 *管理图片加载
 *@return
 */
public class ResourceMgr {
	public static BufferedImage goodTankL, goodTankU,goodTankR, goodTankD;  //自己的坦克
	public static BufferedImage badTankL, badTankU, bodTankR, badTankD ;    //敌人坦克
	public static BufferedImage bulletL, bulletU,bulletR, bulletD;  //子弹
	public static BufferedImage[] explodes = new BufferedImage[16];

	//静态代码块只加载一次
	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage (goodTankU,-90);
			goodTankR = ImageUtil.rotateImage (goodTankU,90);
			goodTankD = ImageUtil.rotateImage (goodTankU, 180) ;

			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader ().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil. rotateImage (badTankU, -90);
			bodTankR = ImageUtil .rotateImage (badTankU, 90);
			badTankD = ImageUtil .rotateImage (badTankU,180) ;

			bulletU = ImageIO.read(ResourceMgr.class . getClassLoader() . getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage (bulletU,-90);
			bulletR = ImageUtil.rotateImage (bulletU,90);
			bulletD = ImageUtil.rotateImage(bulletU,180);
			for(int i=0; i<16; i++)
				explodes[i] = ImageIO.read(ResourceMgr.class. getClassLoader () . getResourceAsStream("images/e" + (i+1) + ".gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}