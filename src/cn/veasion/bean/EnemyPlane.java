package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import cn.veasion.bean.inf.Kill;
import cn.veasion.bean.inf.Plane;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;
import cn.veasion.util.VeaUtil;

/**
 * 敌方飞机.
 * @auto Veasion
 */
public class EnemyPlane implements Plane, Kill, Serializable{

	private static final long serialVersionUID = -470314945420725311L;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private int blood;
	private int power;
	private Rectangle r;
	private boolean flag;
	
	private long sendBulletTime;
	
	public EnemyPlane(GameBean p) {
		this.p=p;
	}
	
	@Override
	public void create(Image image, int blood, Rectangle r) {
		if(image!=null){
			this.image=image;
		}else{
			this.image=VeaUtil.random(Resource.IMAGE_Enemy);
			// 特殊战机
			if(this.image==Resource.IMAGE_Enemy01){
				this.blood+=20;
			}
		}
		this.r=r;
		this.blood=blood;
		this.power=Constants.EnemyPower;
		this.isLive=true;
		this.flag=VeaUtil.random(1, 2)==2;
		sendBulletTime=System.currentTimeMillis();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		this.kill();
		this.sendBullet();
		this.move();
	}

	@Override
	public Rectangle area() {
		return this.r;
	}

	@Override
	public void sendBullet() {
		if(System.currentTimeMillis()-sendBulletTime >= VeaUtil.random(Constants.EnemyBulletFrequency, Constants.EnemyBulletFrequency+360)){
			EnemyBullet eb=new EnemyBullet(p);
			eb.create(Resource.IMAGE_Bullet05, Constants.EnemyPower, new Rectangle(r.x+30, r.y+60, 15, 15));
			p.enemyBullets.add(eb);
			sendBulletTime=System.currentTimeMillis();
		}
	}

	@Override
	public void move() {
		if(GameBean.STATUS_GAME == p.status){
			if(this.image!=Resource.IMAGE_Enemy01){
				r.y+=Constants.EnemyPlaneVelocity;
				if(r.y>=p.containerHeight){
					this.isLive=false;
				}
			}else{
				if(r.y>=120){
					if(flag){
						r.x+=Constants.EnemyPlaneVelocity;
						if(r.x>=p.containerWidth-10){
							flag=false;
						}
					}else{
						r.x-=Constants.EnemyPlaneVelocity;
						if(r.x<=-r.width/2){
							flag=true;
						}
					}
				}else{
					r.y+=Constants.EnemyPlaneVelocity;
				}
			}
		}
	}
	
	@Override
	public void kill(){
		Rectangle r=this.area();
		// 杀放我飞机
		if(r.intersects(p.myPlane.area())){
			p.myPlane.addBlood(-power);
			this.isLive=false;
		}
	}
	
	@Override
	public void addBlood(int addBlood) {
		this.blood=this.blood+addBlood;
		if(this.blood<=0){
			this.blood=0;
			this.isLive=false;
		}
	}

	@Override
	public boolean isLive() {
		return this.isLive;
	}
	
}
