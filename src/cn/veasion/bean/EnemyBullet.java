package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.bean.inf.Bullet;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;

/**
 * 敌方子弹.
 * @auto Veasion
 */
public class EnemyBullet implements Bullet, Serializable{
	
	private static final long serialVersionUID = -3765899649571787469L;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private int power;// 威力
	private Rectangle r;
	
	public EnemyBullet(GameBean p) {
		this.p=p;
	}
	
	@Override
	public void create(Image image, int power, Rectangle r) {
		if(image !=null){
			this.image=image;
		}else{
			this.image=Resource.IMAGE_Bullet05;
		}
		this.r=r;
		this.power=power;
		this.isLive=true;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		this.kill();
		this.move();
	}

	@Override
	public Rectangle area() {
		return this.r;
	}

	@Override
	public void move() {
		if(GameBean.STATUS_GAME == p.status){
			r.y+=Constants.EnemyBulletVelocity;
			if(r.y>=p.containerHeight){
				this.isLive=false;
			}
		}
	}
	
	@Override
	public void kill() {
		Rectangle r=this.area();
		// 杀我方飞机
		if(p.myPlane.isLive() && r.intersects(p.myPlane.area())){
			p.myPlane.addBlood(-power);
			this.isLive=false;
		}
	}

	@Override
	public boolean isLive() {
		return this.isLive;
	}

	public void destroy(){
		this.isLive=false;
	}
}
