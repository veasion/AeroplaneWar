package cn.veasion.util;

import java.awt.Image;

/**
 * 游戏资源.
 * 
 * @auto Veasion
 */
public class Resource {
	
	// 资源路径
	public final static String IMAGE_PATH="/images/";
	public final static String MUSIC_PATH="/music/";
	
	// 其他图片
	public final static Image IMAGE_Icon=ResourceUtil.loadImage(IMAGE_PATH+"Veasion.png");
	public final static Image IMAGE_GameOver=ResourceUtil.loadImage(IMAGE_PATH+"GameOver.png");
	public final static Image IMAGE_Title=ResourceUtil.loadImage(IMAGE_PATH+"Title.png");
	public final static Image IMAGE_Title_Tips=ResourceUtil.loadImage(IMAGE_PATH+"Title_Tips.png");
	public final static Image IMAGE_GameOver_Tips=ResourceUtil.loadImage(IMAGE_PATH+"GameOver_Tips.png");
	public final static Image IMAGE_Rocket=ResourceUtil.loadImage(IMAGE_PATH+"Rocket.png");
	public final static Image IMAGE_RocketFly01=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly01.png");
	public final static Image IMAGE_RocketFly02=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly02.png");
	public final static Image IMAGE_RocketFly03=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly03.png");
	
	// 背景图片资源
	public final static Image IMAGE_Background1=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background1.png");
	public final static Image IMAGE_Background2=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background2.jpg");
	public final static Image IMAGE_Background3=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background3.jpg");
	public final static Image IMAGE_Background4=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background4.jpg");
	public final static Image IMAGE_Background5=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background5.jpg");
	public final static Image IMAGE_Background6=ResourceUtil.loadImage(IMAGE_PATH+"Background/Background6.jpg");
	//public final static Image [] IMAGE_Backgrounds=new Image[]{IMAGE_Background1, IMAGE_Background2, IMAGE_Background3, IMAGE_Background4, IMAGE_Background5, IMAGE_Background6};	
	
	// 我方图片资源
	public final static Image IMAGE_Plane=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane.png");
	public final static Image IMAGE_Plane_Down=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_Down.png");
	public final static Image IMAGE_Plane_Left=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_Left.png");
	public final static Image IMAGE_Plane_LeftDown=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_LeftDown.png");
	public final static Image IMAGE_Plane_LeftUp=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_LeftUp.png");
	public final static Image IMAGE_Plane_Right=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_Right.png");
	public final static Image IMAGE_Plane_RightDown=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_RightDown.png");
	public final static Image IMAGE_Plane_RightUp=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_RightUp.png");
	public final static Image IMAGE_Plane_Up=ResourceUtil.loadImage(IMAGE_PATH+"Plane/Plane_Up.png");
	// 我方子弹资源
	public final static Image IMAGE_MyBullet01=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet01.png");
	public final static Image IMAGE_MyBullet02=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet02.png");
	public final static Image IMAGE_MyBullet03=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet03.png");
	public final static Image IMAGE_MyBullet04=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet04.png");
	private final static Image IMAGE_MyBullet05_1=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet05_1.png");
	private final static Image IMAGE_MyBullet05_2=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet05_1.png");
	private final static Image IMAGE_MyBullet05_3=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet05_1.png");
	private final static Image IMAGE_MyBullet05_4=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/MyBullet05_1.png");
	public final static Image [] IMAGE_MyBullet05=new Image[]{IMAGE_MyBullet05_1, IMAGE_MyBullet05_2, IMAGE_MyBullet05_3, IMAGE_MyBullet05_4};
	
	// 敌方图片资源
	public final static Image IMAGE_Enemy01=ResourceUtil.loadImage(IMAGE_PATH+"Enemy01.png");
	public final static Image IMAGE_Enemy02=ResourceUtil.loadImage(IMAGE_PATH+"Enemy02.png");
	public final static Image IMAGE_Enemy03=ResourceUtil.loadImage(IMAGE_PATH+"Enemy03.png");
	public final static Image IMAGE_Enemy04=ResourceUtil.loadImage(IMAGE_PATH+"Enemy04.png");
	public final static Image IMAGE_Enemy05=ResourceUtil.loadImage(IMAGE_PATH+"Enemy05.png");
	public final static Image IMAGE_Enemy06=ResourceUtil.loadImage(IMAGE_PATH+"Enemy06.png");
	public final static Image IMAGE_Enemy07=ResourceUtil.loadImage(IMAGE_PATH+"Enemy07.png");
	public final static Image IMAGE_Enemy08=ResourceUtil.loadImage(IMAGE_PATH+"Enemy08.png");
	public final static Image [] IMAGE_Enemy={IMAGE_Enemy01, IMAGE_Enemy02, IMAGE_Enemy03, IMAGE_Enemy04, IMAGE_Enemy05, IMAGE_Enemy06, IMAGE_Enemy07, IMAGE_Enemy08};
	// 敌方子弹
	public final static Image IMAGE_EnemyBullet01=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/EnemyBullet01.png");
	public final static Image IMAGE_EnemyBullet02=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/EnemyBullet02.png");
	public final static Image IMAGE_EnemyBullet03=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/EnemyBullet03.png");
	public final static Image IMAGE_EnemyBullet04=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/EnemyBullet04.png");
	public final static Image IMAGE_EnemyBullet05=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/EnemyBullet05.png");
	public final static Image [] IMAGE_EnemyBullets=new Image[]{IMAGE_EnemyBullet01, IMAGE_EnemyBullet02, IMAGE_EnemyBullet03, IMAGE_EnemyBullet04, IMAGE_EnemyBullet05};
	// 敌方大Boss
	public final static Image [] IMAGE_EnemyBoss01=new Image[]{ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss01_1.png"), ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss01_2.png")};
	public final static Image [] IMAGE_EnemyBoss02=new Image[]{ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss02_1.png"), ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss02_2.png")};
	public final static Image [][] IMAGE_EnemyBosss=new Image[][]{IMAGE_EnemyBoss01, IMAGE_EnemyBoss02};
	public final static Image IMAGE_BossBullet01=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/BossBullet01.png");
	public final static Image IMAGE_BossBullet02=ResourceUtil.loadImage(IMAGE_PATH+"Bullet/BossBullet02.png");
	public final static Image [] IMAGE_BossBullets=new Image[]{IMAGE_BossBullet01, IMAGE_BossBullet02};
	
	// 补给
	public final static Image IMAGE_BulletSupply02=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply02.png");
	public final static Image IMAGE_BulletSupply03=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply03.png");
	public final static Image IMAGE_BulletSupply04=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply04.png");
	public final static Image IMAGE_BulletSupply05=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply05.png");
	public final static Image IMAGE_BloodSupply01=ResourceUtil.loadImage(IMAGE_PATH+"BloodSupply01.png");
	public final static Image IMAGE_BloodSupply02=ResourceUtil.loadImage(IMAGE_PATH+"BloodSupply02.png");
	
	// 爆炸
	public final static Image IMAGE_Explosion01=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion01.png");
	public final static Image IMAGE_Explosion02=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion02.png");
	public final static Image IMAGE_Explosion03=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion03.png");
	public final static Image IMAGE_Explosion04=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion04.png");
	public final static Image IMAGE_Explosion05=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion05.png");
	public final static Image IMAGE_Explosion06=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion06.png");
	public final static Image IMAGE_Explosion07=ResourceUtil.loadImage(IMAGE_PATH+"Explosion/Explosion07.png");
	public final static Image [] IMAGE_Explosion={IMAGE_Explosion01, IMAGE_Explosion02, IMAGE_Explosion03, IMAGE_Explosion04, IMAGE_Explosion05, IMAGE_Explosion06, IMAGE_Explosion07};
	
	// 音乐
	public final static String MUSIC_bgsound=MUSIC_PATH+"bgsound.mp3";
	public final static String MUSIC_Boss_Comming=MUSIC_PATH+"Boss_Comming.mp3";
	public final static String MUSIC_Bullet01=MUSIC_PATH+"Bullet01.mp3";
	public final static String MUSIC_Bullet02=MUSIC_PATH+"Bullet02.mp3";
	public final static String MUSIC_Bullet03=MUSIC_PATH+"Bullet03.mp3";
	public final static String MUSIC_Bullet04=MUSIC_PATH+"Bullet04.mp3";
	public final static String MUSIC_Bullet06=MUSIC_PATH+"Bullet06.mp3";
	public final static String MUSIC_gameover=MUSIC_PATH+"gameover.mp3";
	public final static String MUSIC_Enemy_Boom=MUSIC_PATH+"Enemy_Boom.mp3";
	public final static String MUSIC_GetItem=MUSIC_PATH+"GetItem.mp3";
	public final static String MUSIC_Health_Low=MUSIC_PATH+"Health_Low.mp3";
	
}
