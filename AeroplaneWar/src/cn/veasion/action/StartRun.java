package cn.veasion.action;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cn.veasion.util.Constants;
import cn.veasion.util.Resource;

/**
 * 开始游戏. <br>
 * <br>
 * <a href="https://github.com/veasion">Github</a> &nbsp;&nbsp;
 * <a href="http://www.luozhuowei.cn">Website</a> &nbsp;&nbsp;
 * <a href="mailto:luozhuowei.kong@qq.com">Email</a>
 * 
 * @author Veasion
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
	private static void start() throws Exception {
		JFrame frame = new JFrame();
		frame.setTitle(Constants.title);
		frame.setSize(400, 600);
		frame.setIconImage(Resource.IMAGE_Icon);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		PlaneAction p = new PlaneAction();
		frame.add(p);
		frame.setResizable(true);
		frame.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {
			}

			public void componentResized(ComponentEvent e) {
				int w = e.getComponent().getWidth();
				int h = e.getComponent().getHeight();
				if (w > 750 || h > 600) {
					// 最小界面
					frame.setSize(750, 600);
					frame.setLocationRelativeTo(null);
				} else if (w < 400 || h < 600) {
					// 最大界面
					frame.setSize(400, 600);
					frame.setLocationRelativeTo(null);
				}
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentHidden(ComponentEvent e) {
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(frame, "确认退出吗？", "温馨提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		frame.setVisible(true);
		Thread.sleep(10);
		// 延迟10s申请键盘监听
		p.requestFocus();
	}

}
