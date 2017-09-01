package cn.veasion.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import cn.veasion.bean.EnemyBullet;
import cn.veasion.bean.EnemyPlane;
import cn.veasion.bean.Explosion;
import cn.veasion.bean.GameBean;
import cn.veasion.bean.MyBullet;
import cn.veasion.bean.WeaponsSupply;
import cn.veasion.util.Resource;

/**
 * 飞机大战画板核心类.
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
		// 初始化游戏参数
		p.init();
		// 添加按键监听
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
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
			}
		});
		ProducedThread pt=new ProducedThread(p);
		pt.start();
	}
	
	@Override
	public void paint(Graphics g) {
		// 清除上一帧
		super.paint(g);
		// 背景
		p.battleground.draw(g);
		if(p.status == GameBean.STATUS_HOME){
			this.paintHome(g);
		}else if(p.status == GameBean.STATUS_GAME){
			this.paintGame(g);
		}else if(p.status == GameBean.STATUS_OVER){
			this.paintGameOver(g);
		}else if(p.status == GameBean.STATUS_PAUSE){
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
		g.drawImage(Resource.IMAGE_Title, 30, 220, 350, 140, null);
		g.drawImage(Resource.IMAGE_Title_Tips, 30, 450, 340, 80, null);
		
		Image [] rocketImages=Resource.IMAGE_Rockets;
		p.temp += 2;
		int count=p.temp/20;
		if(count<=rocketImages.length){
			for (int i = 0; i < count; i++) {
				int index=count-i-1;
				int width= index==0 ? 70 : 50;
				int height= index==0 ? 40 : 50;
				g.drawImage(rocketImages[index], i*100+20, 100, width, height, null);
			}
		}else{
			for (int i = count-rocketImages.length; i < rocketImages.length; i++) {
				int index=count-i-1;
				int width= index==0 ? 70 : 50;
				int height= index==0 ? 40 : 50;
				g.drawImage(rocketImages[index], i*100+20, 100, width, height, null);
			}
		}
		if(p.temp >= p.containerWidth){
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
		if(p.bloodSupply!=null){
			if(p.bloodSupply.isLive()){
				p.bloodSupply.draw(g);
			}
			if(!p.bloodSupply.isLive()){
				p.bloodSupply=null;
			}
		}
		// 武器补给箱
		for (int i = 0, len=p.weaponsSupplys.size(); i < len; i++) {
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
		g.drawString("生命值：", 182, 50);
		
		//绘制分数
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("分数："+p.score, 30, 48);
		g.drawString("生命值：", 180, 48);
		
		//绘制空心血条方框
		g.setColor(new Color(190,195,199));
		g.drawRect(275, 30, 101, 18);
		
		//绘制实心血条方框
		g.setColor(new Color(234, 75, 53));
		g.fillRect(276, 31, p.myPlane.getBlood(), 17);
		
		//绘制血条数值阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 16));
		g.drawString(String.valueOf(p.myPlane.getBlood()), 317, 47);
		
		//绘制血条数值
		g.setColor(Color.white);
		g.setFont(new Font("黑体", Font.PLAIN, 16));
		g.drawString(String.valueOf(p.myPlane.getBlood()), 315, 45);
	}
	
	/**
	 * 暂停界面 
	 */
	private void paintGamePause(Graphics g){
		// TODO 游戏暂停
	}
	
	/**
	 * 绘制游戏结束界面 
	 */
	private void paintGameOver(Graphics g){
		g.drawImage(Resource.IMAGE_GameOver, 30, 60, 350, 140, null);
		//绘制结束时分数显示阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.PLAIN, 36));
		g.drawString(p.score+"分", 147, 292);
		//绘制结束时分数显示
		g.setColor(Color.white);
		g.drawString(p.score+"分", 145, 290);
		//绘制结束时评价显示阴影
		g.setColor(Color.black);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.drawString("评价：", 77, 387);
		//绘制结束时评价显示
		g.setColor(Color.white);
		g.drawString("评价：", 75, 385);
		
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
		g.drawString(appraise, 238, 397);
		g.setColor(Color.white);
		g.drawString(appraise, 236, 395);
		g.drawImage(Resource.IMAGE_GameOver_Tips, 30, 470, 340, 80, null);
	}
	
}
