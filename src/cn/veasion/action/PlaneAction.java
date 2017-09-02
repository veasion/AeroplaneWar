package cn.veasion.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import cn.veasion.bean.BloodSupply;
import cn.veasion.bean.EnemyBullet;
import cn.veasion.bean.EnemyPlane;
import cn.veasion.bean.Explosion;
import cn.veasion.bean.GameBean;
import cn.veasion.bean.MyBullet;
import cn.veasion.bean.WeaponsSupply;
import cn.veasion.util.Constants;
import cn.veasion.util.Resource;

/**
 * 飞机大战画板核心类.
 * 
 * @auto Veasion
 */
public class PlaneAction extends JPanel{
	
	private static final long serialVersionUID = 143160032243117246L;

	private GameBean p=new GameBean();
	
	public PlaneAction() throws Exception{
		this.init();
	}
	
	/**
	 * 初始化
	 */
	void init() throws Exception{
		// 添加按键监听
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					if(p.getStatus()==GameBean.STATUS_HOME){
						p.setStatus(GameBean.STATUS_GAME);
					}else if(p.getStatus()==GameBean.STATUS_GAME){
						p.setStatus(GameBean.STATUS_PAUSE);
					}else if(p.getStatus()==GameBean.STATUS_PAUSE){
						p.setStatus(GameBean.STATUS_GAME);
					}
				}else if(key==KeyEvent.VK_ESCAPE && p.getStatus()==GameBean.STATUS_OVER){
					p.setStatus(GameBean.STATUS_HOME);
				}
				p.myPlane.keyReleased(e);
			}
			public void keyPressed(KeyEvent e) {
				p.myPlane.keyPressed(e);
			}
		});
		// 窗体大小监听
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				p.containerWidth=e.getComponent().getWidth();
				p.containerHeight=e.getComponent().getHeight();
				if(p.getStatus()==GameBean.STATUS_HOME){
					p.myPlane.create(null, Constants.MyPlaneBlood, 
							new Rectangle((p.containerWidth-80)/2, p.containerHeight-70-30, 80, 70));
				}else if(p.getStatus()==GameBean.STATUS_PAUSE){
					p.setStatus(GameBean.STATUS_GAME);
				}
			}
		});
		// 初始化游戏参数
		p.setStatus(GameBean.STATUS_HOME);
		// 启动制造线程
		ProducedThread pt=new ProducedThread(p);
		pt.start();
	}
	
	@Override
	public void paint(Graphics g) {
		if(p.getStatus() == GameBean.STATUS_HOME){
			// 清除上一帧
			super.paint(g);
			// 背景
			p.battleground.draw(g);
			this.paintHome(g);
		}else if(p.getStatus() == GameBean.STATUS_GAME){
			// 清除上一帧
			super.paint(g);
			// 背景
			p.battleground.draw(g);
			this.paintGame(g);
		}else if(p.getStatus() == GameBean.STATUS_OVER){
			// 清除上一帧
			super.paint(g);
			// 背景
			p.battleground.draw(g);
			this.paintGameOver(g);
		}else if(p.getStatus() == GameBean.STATUS_PAUSE){
			this.paintGamePause(g);
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 重绘
		super.repaint();
	}
	
	/**
	 * 绘制开始画面 
	 */
	private void paintHome(Graphics g){
		g.setColor(Color.ORANGE);
		g.setFont(new Font("楷体", Font.BOLD, 30));
		g.drawString("伟神", (int)(getWidth()/2-30), 30+25);
		g.drawImage(Resource.IMAGE_Title, (p.containerWidth-350)/2, (p.containerHeight-140)/2, 350, 140, null);
		g.drawImage(Resource.IMAGE_Title_Tips, (p.containerWidth-350)/2, p.containerHeight-120, 340, 80, null);
		// 绘制移动小飞机
		p.temp += 2;
		int count=p.temp/20;
		g.drawImage(Resource.IMAGE_Rocket, count*100+20, 100, 70, 40, null);
		g.drawImage(Resource.IMAGE_RocketFly01, (count-1)*100+20, 100, 50, 50, null);
		g.drawImage(Resource.IMAGE_RocketFly02, (count-2)*100+20, 100, 50, 50, null);
		g.drawImage(Resource.IMAGE_RocketFly03, (count-3)*100+20, 100, 50, 50, null);
		if((count-3)*100+20 >= p.containerWidth){
			p.temp=0;
		}
	}
	
	/**
	 * 绘制游戏界面 
	 */
	private void paintGame(Graphics g){
		// 我方飞机
		p.myPlane.draw(g);
		// 我方子弹
		for (int i=p.myBullets.size()-1; i>=0 ; i--) {
			MyBullet mb=p.myBullets.get(i);
			if(mb.isLive()){
				mb.draw(g);
			}
			if(!mb.isLive()){
				p.myBullets.remove(i);
			}
		}
		// 敌方飞机
		for (int i=p.enemyPlanes.size()-1; i>=0 ; i--) {
			EnemyPlane ep=p.enemyPlanes.get(i);
			if(ep.isLive()){
				ep.draw(g);
			}
			if(!ep.isLive()){
				p.enemyPlanes.remove(i);
			}
		}
		// 大Boss
		if(p.enemyBoss!=null){
			if(p.enemyBoss.isLive()){
				p.enemyBoss.draw(g);
			}
			if(!p.enemyBoss.isLive()){
				p.enemyBoss=null;
			}
		}
		// 敌方子弹
		for (int i=p.enemyBullets.size()-1; i>=0 ; i--) {
			EnemyBullet eb=p.enemyBullets.get(i);
			if(eb.isLive()){
				eb.draw(g);
			}
			if(!eb.isLive()){
				p.enemyBullets.remove(i);
			}
		}
		// 爆炸
		for (int i=p.explosions.size()-1; i>=0 ; i--) {
			Explosion e=p.explosions.get(i);
			if(e.isLive()){
				e.draw(g);
			}
			if(!e.isLive()){
				p.explosions.remove(i);
			}
		}
		// 加血补给箱
		for (int i =p.bloodSupplys.size()-1; i>=0 ; i--) {
			BloodSupply bs=p.bloodSupplys.get(i);
			if(bs.isLive()){
				bs.draw(g);
			}
			if(!bs.isLive()){
				p.bloodSupplys.remove(i);
			}
		}
		// 武器补给箱
		for (int i =p.weaponsSupplys.size()-1; i>=0 ; i--) {
			WeaponsSupply ws=p.weaponsSupplys.get(i);
			if(ws.isLive()){
				ws.draw(g);
			}
			if(!ws.isLive()){
				p.weaponsSupplys.remove(i);
			}
		}
		
		//绘制分数阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("分数："+p.score, 32, 50);
		//绘制分数
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("分数："+p.score, 30, 48);
		
		//绘制生命值阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("生命值：", p.containerWidth-202, 50);
		//绘制生命值
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("生命值：", p.containerWidth-204, 48);
		
		//绘制空心血条方框
		g.setColor(new Color(190,195,199));
		g.drawRect(p.containerWidth-109, 30, 101, 18);
		
		//绘制实心血条方框
		g.setColor(new Color(234, 75, 53));
		g.fillRect(p.containerWidth-108, 31, p.myPlane.getBlood(), 17);
		
		//绘制血条数值阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 16));
		g.drawString(String.valueOf(p.myPlane.getBlood()), p.containerWidth-67, 47);
		
		//绘制血条数值
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 16));
		g.drawString(String.valueOf(p.myPlane.getBlood()), p.containerWidth-69, 45);
		
		//绘制时间
		g.setFont(new Font("黑体", 0, 16));
		String time=String.valueOf(System.currentTimeMillis()-p.gameStartTime);
		g.drawString(time, p.containerWidth-8-time.length()*8, p.containerHeight-8);
	}
	
	/**
	 * 暂停界面 
	 */
	private void paintGamePause(Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("楷体", 1, 40));
		g.drawString("请按Enter继续游戏", (p.containerWidth-40*9)/2+2, p.containerHeight/2-20);
		g.setColor(Color.white);
		g.setFont(new Font("楷体", 1, 40));
		g.drawString("请按Enter继续游戏", (p.containerWidth-40*9)/2, p.containerHeight/2-20);
	}
	
	/**
	 * 绘制游戏结束界面 
	 */
	private void paintGameOver(Graphics g){
		//----------
		g.drawImage(Resource.IMAGE_GameOver, (p.containerWidth-350)/2, 60, 350, 140, null);
		//绘制结束时分数显示阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 36));
		String score=p.score+"分";
		g.drawString(score, (p.containerWidth-score.length()*18)/2+2, 292);
		//绘制结束时分数显示
		g.setColor(Color.white);
		g.drawString(score, (p.containerWidth-score.length()*18)/2, 290);
		//绘制结束时评价显示阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.drawString("评价：", p.containerWidth/2-40*3+10, 387);
		//绘制结束时评价显示
		g.setColor(Color.white);
		g.drawString("评价：", p.containerWidth/2-40*3+8, 385);
		
		String appraise="";
		int [] scores={ 500, 1200, 3000, 4200, 5500, 6800, 8000, 9500, 16000};
		String [] appraises={ "F", "E", "D", "C", "B", "A", "S", "SS", "SSS"};
		for (int i = 0; i < scores.length; i++) {
			if(p.score<scores[i]){
				appraise=appraises[i];
				break;
			}
			if(i>=scores.length-1){
				appraise="666";
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.BOLD, 80));
		g.drawString(appraise, p.containerWidth/2+60+2, 397);
		g.setColor(Color.white);
		g.drawString(appraise, p.containerWidth/2+60, 395);
		g.drawImage(Resource.IMAGE_GameOver_Tips, (p.containerWidth-340)/2, p.containerHeight-100, 340, 80, null);
	}
	
}
