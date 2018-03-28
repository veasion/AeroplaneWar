package cn.veasion.bean.inf;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 补给接口.
 * 
 * @author Veasion
 */
public interface Supply {

	void create(Image image, int value, int type, Rectangle r);

	void draw(Graphics g);

	Rectangle area();

	void move();

	void supply();

	boolean isLive();

}
