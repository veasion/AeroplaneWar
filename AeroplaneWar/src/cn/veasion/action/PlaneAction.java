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
import cn.veasion.util.VeaUtil;

/**
 * 飞机大战画板核心类.
 * 
 * @auto Veasion
 */
public class PlaneAction extends JPanel{
	
	private static final long serialVersionUID = 143160032243117246L;

	/**
	 * 游戏参数
	 */
	private GameBean p=new GameBean();
	private Integer historyScore;
	private static StringBuffer keys;
	
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
				// 按键监听
				PlaneAction.keyReleased(p, key, e.getKeyChar());
				p.myPlane.keyReleased(key);
			}
			public void keyPressed(KeyEvent e) {
				p.myPlane.keyPressed(e.getKeyCode());
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
		PlaneAction.keys=new StringBuffer();
		// 初始化游戏参数
		p.setStatus(GameBean.STATUS_HOME);
		// 启动制造线程
		ProducedThread pt=new ProducedThread(p);
		pt.start();
	}
	
	@Override
	public void paint(Graphics g) {
		if(p.getStatus() == GameBean.STATUS_HOME){
			super.paint(g);// 清除上一帧
			p.battleground.draw(g);// 背景
			this.paintHome(g);// 绘画首页
		}else if(p.getStatus() == GameBean.STATUS_GAME){
			super.paint(g);
			p.battleground.draw(g);
			this.paintGame(g);// 绘画游戏界面
		}else if(p.getStatus() == GameBean.STATUS_PAUSE){
			this.paintGamePause(g);// 绘画暂停，不清除上一帧
		}else if(p.getStatus() == GameBean.STATUS_OVER){
			super.paint(g);
			p.battleground.draw(g);
			this.paintGameOver(g);// 绘画结束界面
		}
		
		try {
			// 延迟10
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
		p.homeStyle += 2;
		int count=p.homeStyle/20;
		g.drawImage(Resource.IMAGE_Rocket, count*100+20, 100, 70, 40, null);
		g.drawImage(Resource.IMAGE_RocketFly01, (count-1)*100+20, 100, 50, 50, null);
		g.drawImage(Resource.IMAGE_RocketFly02, (count-2)*100+20, 100, 50, 50, null);
		g.drawImage(Resource.IMAGE_RocketFly03, (count-3)*100+20, 100, 50, 50, null);
		if((count-3)*100+20 >= p.containerWidth){
			p.homeStyle=0;
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
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("分数："+p.score, 32, 50);
		//绘制分数
		g.setColor(Constants.itselfDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("分数："+p.score, 30, 48);
		
		//绘制生命值阴影
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("生命值：", p.containerWidth-202, 50);
		//绘制生命值
		g.setColor(Constants.itselfDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 24));
		g.drawString("生命值：", p.containerWidth-204, 48);
		
		//绘制空心血条方框
		g.setColor(new Color(190, 195, 199));
		g.drawRect(p.containerWidth-109, 30, 101, 18);
		
		//绘制实心血条方框
		g.setColor(new Color(234, 75, 53));
		g.fillRect(p.containerWidth-108, 31, p.myPlane.getBlood(), 17);
		
		//绘制血条数值阴影
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 16));
		g.drawString(String.valueOf(p.myPlane.getBlood()), p.containerWidth-67, 47);
		
		//绘制血条数值
		g.setColor(Constants.itselfDefaultColor);
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
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("楷体", 1, 40));
		g.drawString("请按Enter继续游戏", (p.containerWidth-40*9)/2+2, p.containerHeight/2-20);
		g.setColor(Constants.itselfDefaultColor);
		g.setFont(new Font("楷体", 1, 40));
		g.drawString("请按Enter继续游戏", (p.containerWidth-40*9)/2, p.containerHeight/2-20);
	}
	
	
	/**
	 * 绘制游戏结束界面 
	 */
	private void paintGameOver(Graphics g){
		// 判断本句是否为第一次读取历史记录
		if(p.firstReadObjFile){
			try {
				// 读取历史最高纪录
				historyScore=VeaUtil.readHistoryRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(historyScore==null || historyScore<p.score){
				try {
					// 保存本次新纪录
					VeaUtil.saveCurrentRecord(p.score);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			p.firstReadObjFile=false;
		}
		
		if(historyScore==null){
			historyScore=0;
		}
		g.drawImage(Resource.IMAGE_GameOver, (p.containerWidth-350)/2, 40, 350, 140, null);
		if(p.score > historyScore){
			// 破历史纪录
			g.drawImage(Resource.IMAGE_breakRecord, (p.containerWidth-120)/2, 180, 120, 50, null);
		}else{
			// 历史最高纪录
			g.setColor(Constants.shadeDefaultColor);
			g.setFont(new Font("黑体", Font.BOLD, 24));
			String historyStr="历史最高纪录："+historyScore+"分";
			int x=(p.containerWidth-historyStr.length()*(24-String.valueOf(historyScore).length()/2))/2;
			g.drawString(historyStr, x+12, 220);
			g.setColor(Constants.itselfDefaultColor);
			g.drawString(historyStr, x+10, 220);
		}
		//绘制结束时分数显示阴影
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.PLAIN, 36));
		String score=p.score+"分";
		g.drawString(score, (p.containerWidth-score.length()*18)/2+2, 292);
		//绘制结束时分数显示
		g.setColor(Constants.itselfDefaultColor);
		g.drawString(score, (p.containerWidth-score.length()*18)/2, 290);
		
		// 计算评价
		String appraise="";
		int [] scores={1500, 2700, 4100, 5700, 6500, 8500, 10700, 13000, 16000, 20000};
		String [] appraises={"F", "E", "D", "C", "B", "A", "S", "SS", "SSS", "666"};
		int len=0;
		for (int i = 0; i < scores.length; i++) {
			if(p.score <= scores[i]){
				appraise=appraises[i];
				len=appraise.length();
				break;
			}
			if(i>=scores.length-1){
				len=4;
				appraise="大神";
			}
		}
		//绘制结束时评价显示阴影
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.BOLD, 40));
		g.drawString("评价：", p.containerWidth/2-40*3-len*10+20, 387);
		//绘制结束时评价显示
		g.setColor(Constants.itselfDefaultColor);
		g.drawString("评价：", p.containerWidth/2-40*3-len*10+18, 385);
		g.setColor(Constants.shadeDefaultColor);
		g.setFont(new Font("黑体", Font.BOLD, 80));
		g.drawString(appraise, (p.containerWidth-len*40)/2+60+2, 397);
		g.setColor(Constants.itselfDefaultColor);
		g.drawString(appraise, (p.containerWidth-len*40)/2+60, 395);
		g.drawImage(Resource.IMAGE_GameOver_Tips, (p.containerWidth-340)/2, p.containerHeight-100, 340, 80, null);
	}
	
	/**
	 * 键盘按键监听 
	 */
	private static void keyReleased(GameBean p, int key, char c){
		// 监听Enter
		if(key==KeyEvent.VK_ENTER){
			if(p.getStatus()==GameBean.STATUS_HOME){
				// 在首页按Enter，改变游戏状态开始游戏
				p.setStatus(GameBean.STATUS_GAME);
			}else if(p.getStatus()==GameBean.STATUS_GAME){
				// 在游戏中按Enter为暂停
				p.setStatus(GameBean.STATUS_PAUSE);
			}else if(p.getStatus()==GameBean.STATUS_PAUSE){
				// 在游戏暂停中按Enter为继续游戏
				p.setStatus(GameBean.STATUS_GAME);
			}
		}else if(key==KeyEvent.VK_ESCAPE && p.getStatus()==GameBean.STATUS_OVER){
			// 游戏结束按Esc为返回首页
			p.setStatus(GameBean.STATUS_HOME);
		}else if(key==KeyEvent.VK_0 || key==KeyEvent.VK_NUMPAD0){
			// 按0随机改变背景
			p.battleground.changeBackground(null);
		}else if(key==KeyEvent.VK_ADD && Constants.isCheats()){
			// 游戏作弊：按+加血
			p.myPlane.addBlood(10);
		}else if(key==KeyEvent.VK_SUBTRACT && Constants.isCheats()){
			// 游戏作弊：按-随机出现2到5的武器补给箱
			int s=VeaUtil.random(2, 5);
			if(s==2){
				p.createBulletSupply02Time-=Constants.CreateBulletSupply02Frequency;
			}else if(s==3){
				p.createBulletSupply03Time-=Constants.CreateBulletSupply03Frequency;
			}else if(s==4){
				p.createBulletSupply04Time-=Constants.CreateBulletSupply04Frequency;
			}else if(s==5){
				p.createBulletSupply05Time-=Constants.CreateBulletSupply05Frequency;
			}
		}
		
		// 开启和关闭作弊
		if(key>=65 && key<=90){
			keys.append(c);
			if (keys.length() >= Constants.cheatsCode.length() && keys.indexOf(Constants.cheatsCode) != -1) {
				Constants.setCheats(!Constants.isCheats());
				keys.setLength(0);
			}
			if(keys.length()>20){
				keys.setLength(0);
			}
		}
	}
}
