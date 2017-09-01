package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.bean.inf.Supply;
import cn.veasion.util.Resource;

/**
 * 血量补给箱.
 * @auto Veasion
 */
public class BloodSupply implements Supply, Serializable{
	
	private static final long serialVersionUID = 552460271203967321L;
	public final static int TYPE_BloodSupply=1;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private int value;
	private int type;
	private Rectangle r;
	
	public BloodSupply(GameBean p){
		this.p=p;
	}
	
	@Override
	public void create(Image image, int value, int type, Rectangle r) {
		if(image != null){
			this.image=image;
		}else{
			this.image=Resource.IMAGE_BloodSupply;
		}
		this.r=r;
		this.value=value;
		this.isLive=true;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		this.supply();
		this.move();
	}
	
	@Override
	public Rectangle area() {
		return this.r;
	}
	
	@Override
	public void move() {
		r.y+=5;
		if(r.y>p.containerHeight){
			this.isLive=false;
		}
	}
	
	@Override
	public void supply() {
		Rectangle r=this.area();
		if(p.myPlane.isLive() && r.intersects(p.myPlane.area())){
			p.myPlane.addBlood(value);
			this.isLive=false;
			p.battleground.playMusic(Resource.MUSIC_GetItem);
		}
	}

	@Override
	public boolean isLive() {
		return this.isLive;
	}
}
