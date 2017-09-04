package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.bean.inf.Bullet;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;

/**
 * 我方子弹.
 * 
 * @auto Veasion
 */
public class MyBullet implements Bullet, Serializable{

	private static final long serialVersionUID = -4753038093507559536L;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private Image [] images;
	private int power; // 威力
	private Rectangle r;
	private boolean isSuperWeapon;
	private int index;
	
	public MyBullet(GameBean p) {
		this.p=p;
		this.isSuperWeapon=false;
	}
	
	public MyBullet(GameBean p, boolean isSuperWeapon) {
		this.p=p;
		this.isSuperWeapon=isSuperWeapon;
	}
	
	@Override
	public void create(Image image, int power, Rectangle r) {
		this.image=image;
		this.power=power;
		this.r=r;
		this.isLive=true;
	}
	
	public void create(Image [] images, int power, Rectangle r) {
		this.images=images;
		this.image=null;
		this.power=power;
		this.r=r;
		this.isLive=true;
	}

	@Override
	public void draw(Graphics g) {
		if(images !=null){
			// 图组超炫子弹
			g.drawImage(images[index++], r.x, r.y, r.width, r.height, null);
			if(index>=images.length){
				index=0;
			}
		}else{
			// 普通子弹
			g.drawImage(image, r.x, r.y, r.width, r.height, null);
		}
		this.move();
	}

	@Override
	public Rectangle area() {
		return this.r;
	}

	@Override
	public void move() {
		// 移动
		if(isLive && p.allowMove()){
			r.y-=Constants.MyBulletVelocity;
			if(r.y<0){
				this.isLive=false;
			}else{
				this.kill();
			}
		}
	}
	
	@Override
	public void kill() {
		Rectangle r=this.area();
		// 杀敌方飞机
		for (int i = 0, len=p.enemyPlanes.size(); i < len; i++) {
			EnemyPlane ep=p.enemyPlanes.get(i);
			if(ep.isLive() && r.intersects(ep.area())){
				ep.addBlood(-power);
				if(!ep.isLive()){
					p.score+=Constants.DefaultScore;
					Explosion e=new Explosion();
					e.create(ep.area());
					p.explosions.add(e);
					p.battleground.playMusic(Resource.MUSIC_Enemy_Boom);
				}
				if(!isSuperWeapon){
					this.isLive=false;
				}
				break;
			}
		}
		// 杀Boss
		if(p.enemyBoss!=null && p.enemyBoss.isLive() && r.intersects(p.enemyBoss.area())){
			p.enemyBoss.addBlood(-power);
			p.enemyBoss.changeImage();
			this.isLive=false;
			if(!p.enemyBoss.isLive()){
				p.score+=Constants.DefaultScore*10;
				Explosion e=new Explosion();
				e.create(p.enemyBoss.area());
				p.explosions.add(e);
				p.battleground.playMusic(Resource.MUSIC_Enemy_Boom);
			}
		}
		// 超级子弹抵消飞机敌方子弹
		if(isSuperWeapon){
			for (int i = 0, len=p.enemyBullets.size(); i < len; i++) {
				EnemyBullet eb=p.enemyBullets.get(i);
				if(eb.isLive() && r.intersects(eb.area())){
					eb.destroy();
				}
			}
		}
	}
	
	@Override
	public boolean isLive() {
		return this.isLive;
	}
	
}
