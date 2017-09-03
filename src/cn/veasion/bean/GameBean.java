package cn.veasion.bean;

import java.awt.Color;
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
	 * 默认容器宽度 
	 */
	public int containerWidth=384;
	
	/**
	 * 默认容器高度
	 */
	public int containerHeight=562;
	
	/**
	 * 游戏状态
	 */
	private int status=STATUS_HOME;
	
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
	public List<BloodSupply> bloodSupplys;
	
	/**
	 * 战场
	 */
	public Battleground battleground;
	
	/**
	 * 游戏开始时间
	 */
	public long gameStartTime;
	
	/**
	 * 暂停时间
	 */
	public long pauseTime;
	
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
	 * 创建加血01补给时间
	 */
	public long createBloodSupply01Time;
	
	/**
	 * 创建加血02补给时间
	 */
	public long createBloodSupply02Time;
	
	/**Home变量*/
	public int homeStyle=0;
	
	/**本局第一次读取和保存文件*/
	public boolean firstReadObjFile;
	
	/**
	 * 游戏初始化 
	 */
	private void init(){
		this.enemyPlanes=new ArrayList<>();
		this.explosions=new ArrayList<>();
		this.myBullets=new ArrayList<>();
		this.enemyBullets=new ArrayList<>();
		this.bloodSupplys=new ArrayList<>();
		this.weaponsSupplys=new ArrayList<>();
		this.battleground=new Battleground(this, null);
		this.battleground.playBackgroundMusic(VeaUtil.random(Resource.MUSIC_bgsounds));
		this.initProducedTime();
		this.score=0;
		this.myPlane=new MyPlane(this);
		this.firstReadObjFile=true;
		this.myPlane.create(null, Constants.MyPlaneBlood, 
				new Rectangle((containerWidth-80)/2, containerHeight-70-30, 80, 70));
	}
	
	/**
	 * 制造时间初始化 
	 */
	private void initProducedTime(){
		long systemTime=System.currentTimeMillis();
		this.gameStartTime=systemTime;
		this.createEnemyTime=systemTime;
		this.createBossTime=systemTime;
		this.createBulletSupply02Time=systemTime;
		this.createBulletSupply03Time=systemTime;
		this.createBulletSupply04Time=systemTime;
		this.createBulletSupply05Time=systemTime;
		this.createBloodSupply01Time=systemTime;
		this.createBloodSupply02Time=systemTime;
	}
	
	/**
	 * 暂停恢复，制造时间恢复
	 */
	private void recoverProducedTime(){
		long pause=System.currentTimeMillis()-this.pauseTime;
		this.gameStartTime+=pause;
		this.createEnemyTime+=pause;
		this.createBossTime+=pause;
		this.createBulletSupply02Time+=pause;
		this.createBulletSupply03Time+=pause;
		this.createBulletSupply04Time+=pause;
		this.createBulletSupply05Time+=pause;
		this.createBloodSupply01Time+=pause;
		this.createBloodSupply02Time+=pause;
	}
	
	/**
	 * 是否允许移动 
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
	
	/**
	 * 游戏状态
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 游戏状态
	 */
	public void setStatus(int status) {
		if(status==STATUS_HOME){
			// 初始化游戏
			this.init();
		}else if(status==STATUS_GAME){
			if(this.status==STATUS_PAUSE){
				// 继续游戏
				this.recoverProducedTime();
			}
		}else if(status==STATUS_OVER){
			// 游戏结束
			this.battleground.playMusic(Resource.MUSIC_gameover);
		}else if(status==STATUS_PAUSE){
			// 暂停
			this.pauseTime=System.currentTimeMillis();
		}
		this.status = status;
	}
	
	/**
	 * 改变绘画主体颜色为白色 
	 */
	public static void changeItselfToWhite(){
		Constants.shadeDefaultColor=Color.black;
		Constants.itselfDefaultColor=Color.white;
	}
	
	/**
	 * 改变绘画主体颜色为黑色
	 */
	public static void changeItselfToBlack(){
		Constants.shadeDefaultColor=Color.lightGray;
		Constants.itselfDefaultColor=Color.black;
	}
	
}
