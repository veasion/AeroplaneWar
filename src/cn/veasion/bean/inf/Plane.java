package cn.veasion.bean.inf;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


/**
 * 飞机接口.
 * 
 * @auto Veasion
 */
public interface Plane {
	
	void create(Image image, int blood, Rectangle r);
	
	void draw(Graphics g);
	
	Rectangle area();
	
	void sendBullet();
	
	void move();
	
	void addBlood(int addBlood);
	
	boolean isLive();
	
}
