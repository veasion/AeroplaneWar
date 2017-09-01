package cn.veasion.util;

import java.awt.Image;

/**
 * 资源.
 * @auto Veasion
 */
public class Resource {
	
	// 资源路径
	public final static String IMAGE_PATH="/images/";
	public final static String MUSIC_PATH="/music/";
	
	// 其他图片
	public final static Image IMAGE_Background=ResourceUtil.loadImage(IMAGE_PATH+"Background.png");
	public final static Image IMAGE_GameOver=ResourceUtil.loadImage(IMAGE_PATH+"GameOver.png");
	public final static Image IMAGE_Title=ResourceUtil.loadImage(IMAGE_PATH+"Title.png");
	public final static Image IMAGE_Title_Tips=ResourceUtil.loadImage(IMAGE_PATH+"Title_Tips.png");
	public final static Image IMAGE_GameOver_Tips=ResourceUtil.loadImage(IMAGE_PATH+"GameOver_Tips.png");
	public final static Image IMAGE_Rocket=ResourceUtil.loadImage(IMAGE_PATH+"Rocket.png");
	public final static Image IMAGE_RocketFly01=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly01.png");
	public final static Image IMAGE_RocketFly02=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly02.png");
	public final static Image IMAGE_RocketFly03=ResourceUtil.loadImage(IMAGE_PATH+"RocketFly03.png");
	public final static Image [] IMAGE_Rockets=new Image[]{IMAGE_Rocket, IMAGE_RocketFly01, IMAGE_RocketFly02, IMAGE_RocketFly03};
	
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
	
	public final static Image IMAGE_Bullet01=ResourceUtil.loadImage(IMAGE_PATH+"Bullet01.png");
	public final static Image IMAGE_Bullet02=ResourceUtil.loadImage(IMAGE_PATH+"Bullet02.png");
	public final static Image IMAGE_Bullet03=ResourceUtil.loadImage(IMAGE_PATH+"Bullet03.png");
	public final static Image IMAGE_Bullet04=ResourceUtil.loadImage(IMAGE_PATH+"Bullet04.png");
	
	// 敌方图片资源
	public final static Image IMAGE_Enemy01=ResourceUtil.loadImage(IMAGE_PATH+"Enemy01.png");
	public final static Image IMAGE_Enemy02=ResourceUtil.loadImage(IMAGE_PATH+"Enemy02.png");
	public final static Image IMAGE_Enemy03=ResourceUtil.loadImage(IMAGE_PATH+"Enemy03.png");
	public final static Image IMAGE_Enemy04=ResourceUtil.loadImage(IMAGE_PATH+"Enemy04.png");
	public final static Image IMAGE_Enemy05=ResourceUtil.loadImage(IMAGE_PATH+"Enemy05.png");
	public final static Image [] IMAGE_Enemy={IMAGE_Enemy01, IMAGE_Enemy02, IMAGE_Enemy03, IMAGE_Enemy04, IMAGE_Enemy05};
	
	public final static Image IMAGE_EnemyBoss01=ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss01.png");
	public final static Image IMAGE_EnemyBoss02=ResourceUtil.loadImage(IMAGE_PATH+"EnemyBoss02.png");
	
	public final static Image IMAGE_Bullet05=ResourceUtil.loadImage(IMAGE_PATH+"Bullet05.png");
	public final static Image IMAGE_Bullet06=ResourceUtil.loadImage(IMAGE_PATH+"Bullet06.png");
	
	// 补给
	public final static Image IMAGE_BulletSupply02=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply02.png");
	public final static Image IMAGE_BulletSupply03=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply03.png");
	public final static Image IMAGE_BulletSupply04=ResourceUtil.loadImage(IMAGE_PATH+"BulletSupply04.png");
	public final static Image IMAGE_BloodSupply=ResourceUtil.loadImage(IMAGE_PATH+"BloodSupply.png");
	
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
