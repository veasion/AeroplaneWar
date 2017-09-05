package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import cn.veasion.util.Constants;
import cn.veasion.util.Resource;
import cn.veasion.util.ResourceUtil;
import cn.veasion.util.VeaUtil;

/**
 * 战场.
 * 
 * @auto Veasion
 */
public class Battleground implements Serializable{

	private static final long serialVersionUID = -8582691227231848856L;
	
	/**蓝色星河*/
	public static final int TYPE_BLUE_STARS=1;// 蓝色星河
	/**火焰山*/
	public static final int TYPE_FIRE_MOUNT=2;// 火焰山
	/**青山绿水*/
	public static final int TYPE_GREEN_MOUNT=3;// 青山绿水
	/**沙漠风暴*/
	public static final int TYPE_DESERT_STORM=4;//沙漠风暴
	/**空中天柱*/
	public static final int TYPE_SKY_PILLAR=5;//空中天柱
	/**深渊桥*/
	public static final int TYPE_ABYSS_BRIDGE=6;//深渊桥
	/**漫天云层*/
	public static final int TYPE_CLOUD_LAYER=7;//漫天云层
	/**星际天空*/
	public static final int TYPE_STAR_SKY=8;//星际天空
	/**路边小村*/
	public static final int TYPE_ROAD_VILLAGE=9;//路边小村
	
	/**
	 * 战场类型数组
	 */
	public static final int [] TYPES={
			TYPE_BLUE_STARS, TYPE_FIRE_MOUNT,
			TYPE_GREEN_MOUNT, TYPE_DESERT_STORM,
			TYPE_SKY_PILLAR, TYPE_ABYSS_BRIDGE,
			TYPE_CLOUD_LAYER, TYPE_STAR_SKY, TYPE_ROAD_VILLAGE};
	
	private GameBean p;
	private Image bgImage;
	private Image [] bgImages;
	private int index;
	private String bgMusicPath;
	private boolean isUp;
	private int y=0;
	
	public Battleground(GameBean p, Integer type){
		this.changeBackground(type);
		this.p=p;
	}
	
	public Battleground(GameBean p, boolean isUp, Image bgImage){
		this.init();
		this.p=p;
		this.bgImage=bgImage;
		this.isUp=isUp;
	}
	
	public Battleground(GameBean p, boolean isUp, Image [] bgImages){
		this.init();
		this.p=p;
		this.bgImages=bgImages;
		this.isUp=isUp;
	}
	
	private void init(){
		this.y=0;
		this.index=0;
		this.bgImage=null;
		this.bgImages=null;
		this.isUp=false;
		// 随机背景音乐
		this.bgMusicPath=VeaUtil.random(Resource.MUSIC_bgsounds);
	}
	
	public void draw(Graphics g){
		
		if(this.bgImages==null){
			// 单背景双循环算法
			g.drawImage(bgImage, 0, y, p.containerWidth, p.containerHeight, null);
			g.drawImage(bgImage, 0, y>=0 ? y-p.containerHeight : p.containerHeight-Math.abs(y), p.containerWidth, p.containerHeight, null);
		}else{
			// 背景数组循环算法
			g.drawImage(bgImages[index], 0, y, p.containerWidth, p.containerHeight, null);
			g.drawImage(bgImages[index < bgImages.length-1 ? index+1 : 0], 0, isUp ? p.containerHeight-Math.abs(y) : y-p.containerHeight, p.containerWidth, p.containerHeight, null);
		}
		
		this.move();
	}
	
	public void move(){
		if(p.getStatus() != GameBean.STATUS_PAUSE){
			if(isUp){
				y-=Constants.backgroundMoveVelocity;
				if(y<=-p.containerHeight){
					//y=p.containerHeight;
					y=0;
					if(bgImages!=null){
						index++;
						if(index>bgImages.length-1){
							index=0;
						}
					}
				}
			}else{
				y+=Constants.backgroundMoveVelocity;
				if(y>=p.containerHeight){
					//y=-p.containerHeight;
					y=0;
					if(bgImages!=null){
						index++;
						if(index>bgImages.length-1){
							index=0;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 改变背景战场 
	 */
	public void changeBackground(Integer type){
		this.init();
		if(type==null){
			// 随机战场
			type=VeaUtil.random(TYPES);
		}
		switch (type) {
			default:
			case TYPE_BLUE_STARS:
				this.isUp=false;
				this.bgImage=Resource.IMAGE_Background1;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_FIRE_MOUNT:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background2;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_GREEN_MOUNT:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background3;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_DESERT_STORM:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background4;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_SKY_PILLAR:
				this.isUp=false;
				this.bgImage=Resource.IMAGE_Background5;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_ABYSS_BRIDGE:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background6;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_CLOUD_LAYER:
				this.isUp=false;
				this.bgImages=Resource.IMAGE_Background7;
				GameBean.changeItselfToBlack();
				break;
			case TYPE_STAR_SKY:
				this.isUp=false;
				this.bgImages=Resource.IMAGE_Background8;
				GameBean.changeItselfToWhite();
				break;
			case TYPE_ROAD_VILLAGE:
				this.isUp=false;
				this.bgImages=Resource.IMAGE_Background9;
				GameBean.changeItselfToBlack();
				break;
		}
	}
	
	public void playBackgroundMusic(String bgMusicPath){
		this.bgMusicPath=bgMusicPath;
		// 开启线程循环播放背景音乐
		BgMusicThread thread=new BgMusicThread();
		thread.start();
	}
	
	public void playMusic(final String musicPath){
		// 开启线程播放音乐
		new Thread(new Runnable() {
			public void run() {
				try {
					ResourceUtil.playMusic(musicPath, p);
					Thread.sleep(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	class BgMusicThread extends Thread{
		public void run() {
			// 判断是否为暂停
			while(p.getStatus() != GameBean.STATUS_PAUSE){
				try {
					ResourceUtil.playMusic(bgMusicPath, p);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
