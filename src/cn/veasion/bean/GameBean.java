package cn.veasion.bean;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.veasion.util.Constants;
import cn.veasion.util.Resource;
import cn.veasion.util.VeaUtil;

/**
 * 游戏参数.
 * 
 * @author Veasion
 */
public class GameBean implements Serializable{
	
	private static final long serialVersionUID = -7495751682407109254L;
	
	public final static int STATUS_HOME = 0; // 首页
	public final static int STATUS_GAME = 1; // 游戏中
	public final static int STATUS_OVER = 2; // 游戏结束
	public final static int STATUS_PAUSE = -1; // 暂停
	
	/**
	 * 容器宽度 
	 */
	public int containerWidth;
	
	/**
	 * 容器高度
	 */
	public int containerHeight;
	
	/**
	 * 游戏状态
	 */
	public int status;
	
	/**
	 * 得分 
	 */
	public int score;
	
	/**
	 * 我方飞机 
	 */
	public MyPlane myPlane;
	
	/**
	 * 敌方飞机 
	 */
	public List<EnemyPlane> enemyPlanes;
	
	/**
	 * 大Boss 
	 */
	public EnemyBoos enemyBoss;
	
	/**
	 * 我方子弹
	 */
	public List<MyBullet> myBullets;
	
	/**
	 * 敌方子弹 
	 */
	public List<EnemyBullet> enemyBullets;
	
	/**
	 * 爆炸 
	 */
	public List<Explosion> explosions;
	
	/**
	 * 武器补给箱 
	 */
	public List<WeaponsSupply> weaponsSupplys;
	
	/**
	 * 加血补给箱 
	 */
	public BloodSupply bloodSupply;
	
	/**
	 * 战场
	 */
	public Battleground battleground;
	
	/**
	 * 游戏开始时间
	 */
	public long gameStartTime;
	
	/**
	 * 创建敌方飞机时间
	 */
	public long createEnemyTime;
	
	/**
	 * 创建boss时间
	 */
	public long createBossTime;
	
	/**
	 * 创建子弹02补给时间
	 */
	public long createBulletSupply02Time;
	
	/**
	 * 创建子弹03补给时间
	 */
	public long createBulletSupply03Time;
	
	/**
	 * 创建子弹04补给时间
	 */
	public long createBulletSupply04Time;
	
	/**
	 * 创建子弹05补给时间
	 */
	public long createBulletSupply05Time;
	
	/**
	 * 创建加血补给时间
	 */
	public long createBloodSupplyTime;
	
	/**
	 * 临时变量
	 */
	public int temp=0;
	
	/**
	 * 游戏初始化 
	 */
	public void init(){
		this.myPlane=new MyPlane(this);
		this.myPlane.create(null, Constants.MyPlaneBlood, new Rectangle(160, 450, 80, 70));
		this.enemyPlanes=new ArrayList<>();
		EnemyPlane enemyPlane=new EnemyPlane(this);
		enemyPlane.create(null, Constants.EnemyBlood, new Rectangle(VeaUtil.random(0, 350), -80, 50, 50));
		this.enemyPlanes.add(enemyPlane);
		this.explosions=new ArrayList<>();
		this.myBullets=new ArrayList<>();
		this.enemyBullets=new ArrayList<>();
		this.bloodSupply=new BloodSupply(this);
		this.weaponsSupplys=new ArrayList<>();
		this.battleground=new Battleground(this, null);
		this.battleground.playBackgroundMusic(Resource.MUSIC_bgsound);
		
		long systemTime=System.currentTimeMillis();
		
		this.gameStartTime=systemTime;
		this.createEnemyTime=systemTime;
		this.createBossTime=systemTime;
		this.createBulletSupply02Time=systemTime;
		this.createBulletSupply03Time=systemTime;
		this.createBulletSupply04Time=systemTime;
		this.createBulletSupply05Time=systemTime;
		this.createBloodSupplyTime=systemTime;
		this.score=0;
		this.status=GameBean.STATUS_HOME;
	}
	
	/**
	 * 是否运行物体移动 
	 */
	public boolean allowMove(){
		return STATUS_GAME==status;
	}
	
	/**
	 * 获取敌方特殊战机数量 
	 */
	public int enemyEspecialCount(){
		int count=0;
		for (int i=enemyPlanes.size()-1; i>=0 ; i--) {
			EnemyPlane ep=enemyPlanes.get(i);
			if(ep.isEspecial()){
				count++;
			}
		}
		return count;
	}
	
}
