package cn.veasion.action;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import cn.veasion.util.Resource;

/**
 * 开始游戏.
 * 
 * @auto Veasion
 * @since www.luozhuowei.cn
 */
public class StartRun {
	
	/**
	 * 启动游戏
	 */
	public static void main(String[] args) {
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 构造窗体 
	 */
	private static void start() throws Exception{
		JFrame frame=new JFrame();
		frame.setTitle("飞机大战 --Veasion");
		frame.setSize(400, 600);
		frame.setIconImage(Resource.IMAGE_Icon);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlaneAction p=new PlaneAction();
		frame.add(p);
		frame.setResizable(true);
		frame.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			public void componentResized(ComponentEvent e) {
				int w=e.getComponent().getWidth();
				int h=e.getComponent().getHeight();
				if(w>750 || h>600){
					frame.setSize(750, 600);
					frame.setLocationRelativeTo(null);
				}else if(w<400 || h<600){
					frame.setSize(400, 600);
					frame.setLocationRelativeTo(null);
				}
			}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
		frame.setVisible(true);
		Thread.sleep(10);
		p.requestFocus();
	}
	
}
