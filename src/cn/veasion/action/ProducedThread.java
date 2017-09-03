package cn.veasion.action;

import java.awt.Rectangle;

import cn.veasion.bean.BloodSupply;
import cn.veasion.bean.EnemyBoos;
import cn.veasion.bean.EnemyPlane;
import cn.veasion.bean.GameBean;
import cn.veasion.bean.WeaponsSupply;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;
import cn.veasion.util.VeaUtil;

/**
 * 定时制造敌机丶补给线程
 * 
 * @author Veasion
 */
public class ProducedThread extends Thread {

	private GameBean p;

	public ProducedThread(GameBean p) {
		this.p = p;
	}

	@Override
	public void run() {
		while (true) {
			if (GameBean.STATUS_GAME == p.getStatus()) {
				// 创建敌机
				if ((System.currentTimeMillis() - p.createEnemyTime >= Constants.CreateEnemyFrequency
						|| p.enemyPlanes.size() < 3) && p.enemyPlanes.size()<=Constants.EnemyMaxCount) {
					EnemyPlane ep = new EnemyPlane(p);
					ep.create(null, Constants.EnemyBlood, new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 50));
					p.enemyPlanes.add(ep);
					p.createEnemyTime = System.currentTimeMillis();
				}
				// 创建Boss
				if (System.currentTimeMillis() - p.createBossTime >= VeaUtil
						.random(Constants.CreateEnemyBossFrequency - 200, Constants.CreateEnemyBossFrequency + 500)) {
					EnemyBoos boos = new EnemyBoos(p);
					int index=VeaUtil.random(0, Resource.IMAGE_EnemyBosss.length-1);
					boos.create(Resource.IMAGE_EnemyBosss[index], Resource.IMAGE_BossBullets[index], Constants.EnemyBlood * 10, new Rectangle((p.containerWidth-80)/2, 0, 80, 80));
					p.enemyBoss = boos;
					p.createBossTime = System.currentTimeMillis();
				}
				// 创建血量01补给
				if (System.currentTimeMillis() - p.createBloodSupply01Time >= VeaUtil
						.random(Constants.CreateBloodSupplyFrequency01, Constants.CreateBloodSupplyFrequency01 + 200)) {
					BloodSupply bs = new BloodSupply(p);
					bs.create(Resource.IMAGE_BloodSupply01, VeaUtil.random(18, 35), BloodSupply.TYPE_BloodSupply,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 60));
					p.bloodSupplys.add(bs);
					p.createBloodSupply01Time = System.currentTimeMillis();
				}
				// 创建血量02补给
				if (System.currentTimeMillis() - p.createBloodSupply02Time >= VeaUtil
						.random(Constants.CreateBloodSupplyFrequency02, Constants.CreateBloodSupplyFrequency02 + 200)) {
					BloodSupply bs = new BloodSupply(p);
					bs.create(Resource.IMAGE_BloodSupply02, 100, BloodSupply.TYPE_BloodSupply,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 40, 35));
					p.bloodSupplys.add(bs);
					p.createBloodSupply02Time = System.currentTimeMillis();
				}
				// 创建武器02
				if (System.currentTimeMillis() - p.createBulletSupply02Time >= VeaUtil.random(
						Constants.CreateBulletSupply02Frequency - 200, Constants.CreateBulletSupply02Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply02, VeaUtil.random(80, 120), WeaponsSupply.TYPE_BulletSupply02,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply02Time = System.currentTimeMillis();
				}
				// 创建武器03
				if (System.currentTimeMillis() - p.createBulletSupply03Time >= VeaUtil.random(
						Constants.CreateBulletSupply03Frequency - 200, Constants.CreateBulletSupply03Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply03, VeaUtil.random(70, 95), WeaponsSupply.TYPE_BulletSupply03,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply03Time = System.currentTimeMillis();
				}
				// 创建武器04(超级武器)
				if (System.currentTimeMillis() - p.createBulletSupply04Time >= VeaUtil.random(
						Constants.CreateBulletSupply04Frequency - 200, Constants.CreateBulletSupply04Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply04, VeaUtil.random(1, 4), WeaponsSupply.TYPE_BulletSupply04,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply04Time = System.currentTimeMillis();
				}
				// 创建武器05
				if (System.currentTimeMillis() - p.createBulletSupply05Time >= VeaUtil.random(
						Constants.CreateBulletSupply05Frequency - 200, Constants.CreateBulletSupply05Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply05, VeaUtil.random(50, 65), WeaponsSupply.TYPE_BulletSupply05,
							new Rectangle(VeaUtil.random(0, p.containerWidth-30), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply05Time = System.currentTimeMillis();
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
