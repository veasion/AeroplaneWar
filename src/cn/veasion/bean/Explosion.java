package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.util.Resource;

/**
 * 爆炸效果.
 * 
 * @auto Veasion
 */
public class Explosion implements Serializable{

	private static final long serialVersionUID = -1575270680852250940L;
	
	private boolean isLive;
	private Image image;
	private Rectangle r;
	private int index;
	
	public Explosion() { }
	
	public void create(Rectangle r) {
		this.r=r;
		this.isLive=true;
	}
	
	public void draw(Graphics g) {
		this.image=Resource.IMAGE_Explosion[index];
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		if(++index >= Resource.IMAGE_Explosion.length){
			this.isLive=false;
		}
	}
	
	public boolean isLive() {
		return this.isLive;
	}
	
}
