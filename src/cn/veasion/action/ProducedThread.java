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
 * 生产敌机丶补给等线程
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
			if (GameBean.STATUS_GAME == p.status) {
				// 创建敌机
				if (System.currentTimeMillis() - p.createEnemyTime >= Constants.CreateEnemyFrequency
						|| p.enemyPlanes.size() < 3) {
					EnemyPlane ep = new EnemyPlane(p);
					ep.create(null, Constants.EnemyBlood, new Rectangle(VeaUtil.random(0, 350), -80, 50, 50));
					p.enemyPlanes.add(ep);
					p.createEnemyTime = System.currentTimeMillis();
				}
				// 创建血量补给
				if (System.currentTimeMillis() - p.createBloodSupplyTime >= VeaUtil
						.random(Constants.CreateBloodSupplyFrequency, Constants.CreateBloodSupplyFrequency + 200)) {
					BloodSupply bs = new BloodSupply(p);
					bs.create(null, VeaUtil.random(18, 35), BloodSupply.TYPE_BloodSupply,
							new Rectangle(VeaUtil.random(0, 350), -80, 50, 60));
					p.bloodSupply = bs;
					p.createBloodSupplyTime = System.currentTimeMillis();
				}
				// 创建武器02
				if (System.currentTimeMillis() - p.createBulletSupply02Time >= VeaUtil.random(
						Constants.CreateBulletSupply02Frequency - 200, Constants.CreateBulletSupply02Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply02, VeaUtil.random(80, 120), WeaponsSupply.TYPE_BulletSupply02,
							new Rectangle(VeaUtil.random(0, 350), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply02Time = System.currentTimeMillis();
				}
				// 创建武器03
				if (System.currentTimeMillis() - p.createBulletSupply03Time >= VeaUtil.random(
						Constants.CreateBulletSupply03Frequency - 200, Constants.CreateBulletSupply03Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply03, VeaUtil.random(70, 95), WeaponsSupply.TYPE_BulletSupply03,
							new Rectangle(VeaUtil.random(0, 350), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply03Time = System.currentTimeMillis();
				}
				// 创建武器04(超级武器)
				if (System.currentTimeMillis() - p.createBulletSupply04Time >= VeaUtil.random(
						Constants.CreateBulletSupply04Frequency - 200, Constants.CreateBulletSupply04Frequency + 200)) {
					WeaponsSupply ws = new WeaponsSupply(p);
					ws.create(Resource.IMAGE_BulletSupply04, VeaUtil.random(1, 4), WeaponsSupply.TYPE_BulletSupply04,
							new Rectangle(VeaUtil.random(0, 350), -80, 50, 50));
					p.weaponsSupplys.add(ws);
					p.createBulletSupply04Time = System.currentTimeMillis();
				}
				// 创建Boss
				if (System.currentTimeMillis() - p.createBossTime >= VeaUtil
						.random(Constants.CreateEnemyBossFrequency - 200, Constants.CreateEnemyBossFrequency + 500)) {
					EnemyBoos boos = new EnemyBoos(p);
					boos.create(null, Constants.EnemyBlood * 10, new Rectangle(120, 0, 80, 80));
					p.enemyBoss = boos;
					p.createBossTime = System.currentTimeMillis();
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
