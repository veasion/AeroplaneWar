package cn.veasion.bean;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * 战场.
 * @auto Veasion
 */
public class Battleground implements Serializable{

	private static final long serialVersionUID = -8582691227231848856L;
	
	BgMusicThread thread;
	GameBean p;
	Image bgImage;
	String bgMusicPath;
	int y=0;
	
	public Battleground(GameBean p, Image bgImage, String bgMusicPath){
		this.p=p;
		this.bgImage=bgImage;
		this.bgMusicPath=bgMusicPath;
	}
	
	public void draw(Graphics g){
		// 双背景循环
		g.drawImage(bgImage, 0, y, null);
		g.drawImage(bgImage, 0, y >= 0 ? y-p.containerHeight : y+p.containerHeight, null);
		this.move();
	}
	
	public void move(){
		y+=2;
		if(y>=p.containerHeight){
			y=-p.containerHeight;
		}
	}
	
	public void changeBgImage(Image bgImage){
		this.bgImage=bgImage;
	}
	
	public void playBackgroundMusic(String bgMusicPath){
		if(bgMusicPath != null){
			this.bgMusicPath=bgMusicPath;
		}
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
