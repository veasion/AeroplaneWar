package cn.veasion.bean;

import java.awt.Color;
import java.awt.Font;
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
 * 大Boos.
 * 
 * @author Veasion
 */
public class EnemyBoos implements Plane, Kill, Serializable {

	private static final long serialVersionUID = 1756817926647598848L;

	private GameBean p;
	private boolean isLive;
	private Image image;
	private Image[] images;
	private Rectangle r;
	private Image bullet;
	private int power;
	private int blood;
	private boolean flag;
	private int velocity;
	private boolean first = true;
	private boolean changeImage;

	private long sendBulletTime;

	public EnemyBoos(GameBean p) {
		this.p = p;
	}

	@Override
	public void create(Image image, int blood, Rectangle r) {
		this.image = image;
		this.r = r;
		this.blood = blood;
		this.isLive = true;
		this.power = Constants.EnemyPower * 2;
		this.flag = false;
		this.velocity = VeaUtil.random(1, 3);
		sendBulletTime = System.currentTimeMillis();
	}

	public void create(Image[] images, Image bullet, int blood, Rectangle r) {
		this.r = r;
		this.blood = blood;
		this.isLive = true;
		this.power = Constants.EnemyPower * 2;
		this.flag = false;
		this.images = images;
		this.bullet = bullet;
		this.velocity = VeaUtil.random(1, 3);
		sendBulletTime = System.currentTimeMillis();
	}

	@Override
	public void draw(Graphics g) {
		if (images != null && changeImage && images.length > 1) {
			// 被子弹击中时状态
			image = images[1];
			changeImage = false;
		} else if (images != null) {
			// 正常状态
			image = images[0];
		}
		g.drawImage(image, r.x, r.y, r.width, r.height, null);
		this.kill();
		this.sendBullet();
		this.move();
		if (first) {
			// 第一次出现播放音乐
			p.battleground.playMusic(Resource.MUSIC_Boss_Comming);
			first = false;
		}
		// 空心血框
		g.setColor(new Color(190, 195, 199));
		g.drawRect(30, 55, 101, 10);
		// 实心血
		g.setColor(new Color(234, 75, 53));
		g.fillRect(31, 55, (int) (((double) this.blood / Constants.EnemyBossBlood) * 100), 11);
		// 血量阴影
		g.setFont(new Font("黑体", 0, 10));
		g.setColor(Constants.shadeDefaultColor);
		g.drawString(String.valueOf(this.blood), 80 - (String.valueOf(this.blood).length() * 5) / 2 + 2, 64);
		// 血量
		g.setFont(new Font("黑体", 0, 10));
		g.setColor(Constants.itselfDefaultColor);
		g.drawString(String.valueOf(this.blood), 80 - (String.valueOf(this.blood).length() * 5) / 2, 64);
	}

	@Override
	public Rectangle area() {
		return this.r;
	}

	@Override
	public void sendBullet() {
		// 发射子弹
		if (System.currentTimeMillis() - sendBulletTime >= Constants.EnemyBossBulletFrequency) {
			EnemyBullet eb = new EnemyBullet(p);
			int w = 30, h = 40;
			if (bullet == Resource.IMAGE_BossBullet02) {
				w = 15;
				h = 28;
			}
			eb.create(bullet, Constants.EnemyPower * 2, new Rectangle(r.x + 30, r.y + 60, w, h));
			p.enemyBullets.add(eb);
			p.battleground.playMusic(Resource.MUSIC_Bullet06);
			sendBulletTime = System.currentTimeMillis();
		}
	}

	@Override
	public void move() {
		// 大Boss移动算法
		if (isLive && p.allowMove()) {
			if (r.y >= 120) {
				if (flag) {
					r.x -= velocity;
					if (r.x % 5 == 0) {
						r.y -= velocity;
					}
					if (r.x <= -r.width / 2) {
						flag = false;
						velocity = VeaUtil.random(1, 6);
					}
				} else {
					r.x += velocity;
					if (r.x % 7 == 0) {
						r.y -= velocity;
					}
					if (r.x >= p.containerWidth - 10) {
						flag = true;
						velocity = VeaUtil.random(1, 6);
					}
				}
			} else {
				r.y += velocity;
			}
		}
	}

	@Override
	public void kill() {
		Rectangle r = this.area();
		// 杀放我飞机
		if (p.myPlane.isLive() && r.intersects(p.myPlane.area())) {
			p.myPlane.addBlood(-power);
		}
	}

	@Override
	public void addBlood(int addBlood) {
		this.blood = this.blood + addBlood;
		if (this.blood <= 0) {
			this.blood = 0;
			this.isLive = false;
		}
	}

	@Override
	public boolean isLive() {
		return this.isLive;
	}

	public void changeImage() {
		this.changeImage = true;
	}

}
