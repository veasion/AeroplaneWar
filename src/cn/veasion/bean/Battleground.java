package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import cn.veasion.util.Resource;
import cn.veasion.util.VeaUtil;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * 战场.
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
	
	public static final int [] TYPES={TYPE_BLUE_STARS, TYPE_FIRE_MOUNT, TYPE_GREEN_MOUNT, TYPE_DESERT_STORM};
	
	private BgMusicThread thread;
	private GameBean p;
	private Image bgImage;
	private String bgMusicPath;
	private boolean isUp;
	private int y=0;
	
	public Battleground(GameBean p, Integer type){
		if(type==null){
			type=VeaUtil.random(TYPES);
		}
		switch (type) {
			default:
			case TYPE_BLUE_STARS:
				this.isUp=false;
				this.bgImage=Resource.IMAGE_Background1;
				break;
			case TYPE_FIRE_MOUNT:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background2;
				break;
			case TYPE_GREEN_MOUNT:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background3;
				break;
			case TYPE_DESERT_STORM:
				this.isUp=true;
				this.bgImage=Resource.IMAGE_Background4;
				break;
		}
		this.p=p;
	}
	
	public Battleground(GameBean p, boolean isUp, Image bgImage){
		this.p=p;
		this.bgImage=bgImage;
		this.isUp=isUp;
	}
	
	public void draw(Graphics g){
		// 双背景循环
		g.drawImage(bgImage, 0, y, null);
		g.drawImage(bgImage, 0, y>=0 ? y-p.containerHeight : p.containerHeight-Math.abs(y), null);
		this.move();
	}
	
	public void move(){
		if(isUp){
			y-=2;
			if(y<=-p.containerHeight){
				//y=p.containerHeight;
				y=0;
			}
		}else{
			y+=2;
			if(y>=p.containerHeight){
				y=-p.containerHeight;
			}
		}
	}
	
	public void changeBgImage(Image bgImage){
		this.bgImage=bgImage;
	}
	
	public void playBackgroundMusic(String bgMusicPath){
		this.bgMusicPath=bgMusicPath;
		thread=new BgMusicThread();
		thread.start();
	}
	
	public void playMusic(final String musicPath){
		new Thread(()->{
			try {
				new AdvancedPlayer(getClass()
						.getResourceAsStream(musicPath)).play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();;
	}
	
	class BgMusicThread extends Thread{
		AdvancedPlayer music;
		@Override
		public void run() {
			while(true){
				try {
					music = new AdvancedPlayer(getClass().getResourceAsStream(bgMusicPath));
					music.play();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	}
}
