package cn.veasion.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import cn.veasion.bean.inf.Plane;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;

/**
 * 我方飞机.
 * @auto Veasion
 */
public class MyPlane implements Plane, Serializable{

	private static final long serialVersionUID = 7668421606657770594L;
	
	private GameBean p;
	private boolean isLive;
	private Image image;
	private int blood; // 血量
	private Rectangle r;
	
	private boolean isUp;
	private boolean isLeft;
	private boolean isDown;
	private boolean isRight;
	private boolean isEnter;
	private boolean isEsc;
	
	private long sendBulletTime;
	public int useBullet02;
	public int useBullet03;
	public int useBullet04;
	
	public MyPlane(GameBean p) {
		this.p=p;
	}
	
	@Override
	public void create(Image image, int blood, Rectangle r) {
		if(image != null){
			this.image=image;
		}else{
			this.image=Resource.IMAGE_Plane;
		}
		this.r=r;
		this.blood=blood;
		this.isLive=true;
		sendBulletTime=System.currentTimeMillis();
		useBullet02=0;
		useBullet03=0;
		useBullet04=0;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		g.setColor(Color.red);
		g.setFont(new Font("楷体", Font.BOLD, 16));
		g.drawString("伟", r.x+(r.width/2-8), r.y+(r.height/2+4));
		this.sendBullet();
		int bx=2;
		int size=25;
		int by=p.containerHeight-size-2;
		if(useBullet02>0){
			g.drawImage(Resource.IMAGE_BulletSupply02, bx, by, size, size, null);
			g.setColor(Color.white);
			g.setFont(new Font("黑体", Font.BOLD, size/2));
			g.drawString(String.valueOf(useBullet02), bx+size+10, by+size/2+5);
			by-=size+5;
		}
		if(useBullet03>0){
			g.drawImage(Resource.IMAGE_BulletSupply03, bx, by, size, size, null);
			g.setColor(Color.white);
			g.setFont(new Font("黑体", Font.BOLD, size/2));
			g.drawString(String.valueOf(useBullet03), bx+size+10, by+size/2+5);
			by-=size+5;
		}
		if(useBullet04>0){
			g.drawImage(Resource.IMAGE_BulletSupply04, bx, by, size, size, null);
			g.setColor(Color.white);
			g.setFont(new Font("黑体", Font.BOLD, size/2));
			g.drawString(String.valueOf(useBullet04), bx+size+10, by+size/2+5);
		}
		if(this.blood<=28){
			g.setColor(Color.red);
			g.setFont(new Font("黑体", 0, 18));
			g.drawString("注意：血量过低！", 250, 75);
		}
		this.move();
	}

	@Override
	public Rectangle area() {
		return this.r;
	}

	@Override
	public void sendBullet() {
		if(isLive && System.currentTimeMillis()-sendBulletTime > Constants.MyBulletFrequency){
			int power=Constants.MyBulletPower;
			if(useBullet04 > 0){
				MyBullet myBullet=new MyBullet(p);
				myBullet.create(Resource.IMAGE_Bullet04, power*2, new Rectangle(0, 500, 400, 50));
				p.myBullets.add(myBullet);
				p.battleground.playMusic(Resource.MUSIC_Bullet04);
				useBullet04--;
			}else if(useBullet03 >0){
				MyBullet myBullet1=new MyBullet(p);
				MyBullet myBullet2=new MyBullet(p);
				MyBullet myBullet3=new MyBullet(p);
				myBullet1.create(Resource.IMAGE_Bullet03, power, new Rectangle(r.x-15, r.y-40, 25, 25));
				myBullet2.create(Resource.IMAGE_Bullet03, power, new Rectangle(r.x+70, r.y-40, 25, 25));
				myBullet3.create(Resource.IMAGE_Bullet03, power, new Rectangle(r.x+30, r.y-40, 25, 25));
				p.myBullets.add(myBullet1);
				p.myBullets.add(myBullet2);
				p.myBullets.add(myBullet3);
				p.battleground.playMusic(Resource.MUSIC_Bullet03);
				useBullet03-=3;
			}else if(useBullet02 >0){
				MyBullet myBullet1=new MyBullet(p);
				MyBullet myBullet2=new MyBullet(p);
				myBullet1.create(Resource.IMAGE_Bullet02, power, new Rectangle(r.x+10, r.y-40, 15, 25));
				myBullet2.create(Resource.IMAGE_Bullet02, power, new Rectangle(r.x+60, r.y-40, 15, 25));
				p.myBullets.add(myBullet1);
				p.myBullets.add(myBullet2);
				p.battleground.playMusic(Resource.MUSIC_Bullet02);
				useBullet02-=2;
			}else{
				MyBullet myBullet=new MyBullet(p);
				myBullet.create(Resource.IMAGE_Bullet01, power, new Rectangle(r.x+35, r.y-40, 15, 25));
				p.myBullets.add(myBullet);
				p.battleground.playMusic(Resource.MUSIC_Bullet01);
			}
			sendBulletTime=System.currentTimeMillis();
		}
	}

	@Override
	public void move() {
		int distance=Constants.MyPlaneVelocity;
		if(isLive && GameBean.STATUS_GAME == p.status){
			if(isUp){
				r.y-=distance;
				if(isLeft){
					r.x-=distance;
					image=Resource.IMAGE_Plane_LeftUp;
				}else if(isRight){
					r.x+=distance;
					image=Resource.IMAGE_Plane_RightUp;
				}else{
					image=Resource.IMAGE_Plane_Up;
				}
			}else if(isDown){
				r.y+=distance;
				if(isLeft){
					r.x-=distance;
					image=Resource.IMAGE_Plane_LeftDown;
				}else if(isRight){
					r.x+=distance;
				}else{
					image=Resource.IMAGE_Plane_RightDown;
				}
			}else if(isLeft){
				r.x-=distance;
				image=Resource.IMAGE_Plane_Left;
			}else if(isRight){
				r.x+=distance;
				image=Resource.IMAGE_Plane_Right;
			}else{
				image=Resource.IMAGE_Plane;
			}
			
			if(r.x > p.containerWidth-r.width/2){
				r.x=p.containerWidth-r.width/2;
			}else if(r.x < -r.width/2){
				r.x=-r.width/2;
			}
			if(r.y > p.containerHeight-r.height/2){
				r.y=p.containerHeight-r.height/2;
			}else if(r.y < -r.height/2){
				r.y=-r.height/2;
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		
		int key=updateStatusByEvent(e, true);
		if(KeyEvent.VK_ENTER==key && GameBean.STATUS_HOME==p.status){
			p.status=GameBean.STATUS_GAME;
		}else if(KeyEvent.VK_ESCAPE==key && GameBean.STATUS_OVER==p.status){
			p.init();
			p.status=GameBean.STATUS_HOME;
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		updateStatusByEvent(e, false);
	}
	
	private int updateStatusByEvent(KeyEvent e, boolean flag){
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				this.isUp=flag;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				this.isLeft=flag;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				this.isDown=flag;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				this.isRight=flag;
				break;
			case KeyEvent.VK_ENTER:
				this.isEnter=flag;
				break;
			case KeyEvent.VK_ESCAPE:
				this.isEsc=flag;
				break;
			default:
				break;
		}
		return e.getKeyCode();
	}
	
	@Override
	public boolean isLive() {
		return this.isLive;
	}
	
	@Override
	public void addBlood(int addBlood){
		this.blood=this.blood+addBlood;
		if(this.blood<=0){
			this.blood=0;
			this.isLive=false;
			p.status=GameBean.STATUS_OVER;
			p.battleground.playMusic(Resource.MUSIC_gameover);;
		}else if(this.blood<=28){
			p.battleground.playMusic(Resource.MUSIC_Health_Low);
		}else if(this.blood>Constants.MyPlaneBlood){
			this.blood=Constants.MyPlaneBlood;
		}
	}
	
	public int getBlood() {
		return blood;
	}
	
}