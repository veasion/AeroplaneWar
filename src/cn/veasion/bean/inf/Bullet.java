package cn.veasion.bean.inf;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 子弹接口.
 * 
 * @auto Veasion
 */
public interface Bullet extends Kill{
	
	void create(Image image, int power, Rectangle r);
	
	void draw(Graphics g);
	
	Rectangle area();
	
	void move();
	
	boolean isLive();
	
}
