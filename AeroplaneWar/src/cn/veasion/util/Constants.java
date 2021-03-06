package cn.veasion.util;

import java.awt.Color;

import cn.veasion.bean.Battleground;

/**
 * 常量.
 * 
 * @author Veasion
 */
public class Constants {

	// =============== 我方战机默认变量 =============== \\

	/**
	 * 不死之身 （开发专用）
	 */
	public static boolean isUndead = false;

	/**
	 * 背景默认移动速度
	 */
	public static int backgroundMoveVelocity = 1;

	/**
	 * 我方子飞机默认血量
	 */
	public final static int MyPlaneBlood = 100;

	/**
	 * 我方飞机默认移动速度
	 */
	public static int MyPlaneVelocity = 5;

	/**
	 * 我方飞机子弹默认速度
	 */
	public static int MyBulletVelocity = 6;

	/**
	 * 我方子弹默认威力
	 */
	public static int MyBulletPower = 10;

	/**
	 * 我方子弹默认发射频率
	 */
	public static int MyBulletFrequency = 200;

	/**
	 * 普通得分
	 */
	public static int DefaultScore = 10;

	// =============== 敌方战机默认变量 =============== \\

	/**
	 * 敌方飞机默认血量
	 */
	public static int EnemyBlood = 10;

	/**
	 * 敌方飞机最大数量
	 */
	public static int EnemyMaxCount = 10;

	/**
	 * 敌方飞机默认移动速度
	 */
	public static int EnemyPlaneVelocity = 2;

	/**
	 * 敌方飞机子弹默认速度
	 */
	public static int EnemyBulletVelocity = 3;

	/**
	 * 敌方飞机和子弹默认威力
	 */
	public static int EnemyPower = 10;

	/**
	 * 敌方子弹默认最快发射频率
	 */
	public static int EnemyBulletFrequency = 1200;

	/**
	 * 敌方Boss子弹默认最快发射频率
	 */
	public static int EnemyBossBulletFrequency = 800;

	/**
	 * 敌方Boss默认血量
	 */
	public static int EnemyBossBlood = 350;

	// =============== 制造默认变量 =============== \\

	/**
	 * 生产敌方飞机默认频率
	 */
	public static int CreateEnemyFrequency = 600;

	/**
	 * 生产大Boss默认频率
	 */
	public static int CreateEnemyBossFrequency = 72000;

	/**
	 * 生产加血01补给默认频率
	 */
	public static int CreateBloodSupplyFrequency01 = 18000;

	/**
	 * 生产加血02补给默认频率
	 */
	public static int CreateBloodSupplyFrequency02 = 82000;

	/**
	 * 生产武器02补给默认频率
	 */
	public static int CreateBulletSupply02Frequency = 21000;

	/**
	 * 生产武器03补给默认频率
	 */
	public static int CreateBulletSupply03Frequency = 32000;

	/**
	 * 生产超级武器04补给默认频率
	 */
	public static int CreateBulletSupply04Frequency = 48000;

	/**
	 * 生产超级武器05补给默认频率
	 */
	public static int CreateBulletSupply05Frequency = 59000;

	// =============== 其它默认变量和方法 =============== \\

	/**
	 * 游戏标题
	 */
	public static String title = "飞机大战 --Veasion";

	/**
	 * 游戏默认场景 ，随机为 null
	 */
	public static Integer battlegroundType = Battleground.TYPE_FIRE_MOUNT;

	/**
	 * 当前为第几局
	 */
	public static int gameCount = 1;

	/**
	 * 绘画阴影默认颜色
	 */
	public static Color shadeDefaultColor = Color.black;

	/**
	 * 绘画正体默认颜色
	 */
	public static Color itselfDefaultColor = Color.white;

	/**
	 * 作弊代码
	 */
	public static String cheatsCode = "Veasion";

	// 作弊是否开启
	private static boolean isCheats = false;
	// 作弊记录
	private static int cheatsCount;

	/**
	 * 获取作弊是否开启
	 */
	public static boolean isCheats() {
		return isCheats;
	}

	/**
	 * 作弊是否开启
	 */
	public static void setCheats(boolean isCheats) {
		Constants.isCheats = isCheats;
		if (isCheats) {
			cheatsCount++;
		}
	}

	/**
	 * 获取作弊次数
	 */
	public static int getCheatsCount() {
		return cheatsCount;
	}

	/**
	 * 清空作弊次数
	 */
	public static void cheatsCountClear() {
		setCheats(false);
		Constants.cheatsCount = 0;
	}

}
