package cn.veasion.action;

import javax.swing.JFrame;

import cn.veasion.util.ResourceUtil;

/**
 * 开始游戏.
 * @auto Veasion
 */
public class StartRun {
	
	public static void main(String[] args) throws Exception {
		
		JFrame frame=new JFrame();
		frame.setTitle("飞机大战 --Veasion");
		frame.setSize(400, 600);
		frame.setResizable(false);
		frame.setIconImage(ResourceUtil.loadImage("/images/Veasion.png"));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlaneAction p=new PlaneAction();
		frame.add(p);
		frame.setVisible(true);
		Thread.sleep(10);
		p.requestFocus();
		
	}
	
}
