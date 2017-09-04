package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.bean.inf.Supply;
import cn.veasion.util.Resource;

/**
 * 武器补给箱.
 * 
 * @auto Veasion
 */
public class WeaponsSupply implements Supply, Serializable{

	private static final long serialVersionUID = 2861714120545845974L;
	// 武器类型
	public final static int TYPE_BulletSupply02=2;
	public final static int TYPE_BulletSupply03=3;
	public final static int TYPE_BulletSupply04=4;
	public final static int TYPE_BulletSupply05=5;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private int value;
	private int type;
	private Rectangle r;
	
	public WeaponsSupply(GameBean p) {
		this.p=p;
	}
	
	@Override
	public void create(Image image, int value, int type, Rectangle r) {
		this.image=image;
		this.type=type;
		this.value=value;
		this.r=r;
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
		if(isLive && p.allowMove()){
			r.y+=5;
			if(r.y>p.containerHeight){
				this.isLive=false;
			}
		}
	}

	@Override
	public void supply() {
		Rectangle r=this.area();
		if(p.myPlane.isLive() && r.intersects(p.myPlane.area())){
			switch (type) {
				case TYPE_BulletSupply02:
					p.myPlane.useBullet02+=this.value;
					break;
				case TYPE_BulletSupply03:
					p.myPlane.useBullet03+=this.value;
					break;
				case TYPE_BulletSupply04:
					p.myPlane.useBullet04+=this.value;
					break;
				case TYPE_BulletSupply05:
					p.myPlane.useBullet05+=this.value;
					break;
				default:
					break;
			}
			this.isLive=false;
			p.battleground.playMusic(Resource.MUSIC_GetItem);
		}
	}
	
	@Override
	public boolean isLive() {
		return this.isLive;
	}
}
