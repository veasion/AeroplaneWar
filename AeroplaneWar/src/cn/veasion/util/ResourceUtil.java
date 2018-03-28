package cn.veasion.util;

import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.filechooser.FileSystemView;

import cn.veasion.bean.GameBean;

/**
 * 资源帮助类.
 * 
 * @author Veasion
 */
public class ResourceUtil {

	/**
	 * 桌面路径.
	 */
	public static String homePath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

	/**
	 * 根据相对路径获取图片资源
	 * 
	 * @since eg: loadImage("/images/a.png");
	 */
	public static Image loadImage(String name) {
		try {
			return ImageIO.read(ResourceUtil.class.getResourceAsStream(name));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据Container画面保存图片
	 */
	public static void saveImageByContainer(Container container, String pathname, Integer width, Integer height,
			Integer imageType) throws IOException {
		if (width == null) {
			width = container.getWidth();
		}
		if (height == null) {
			height = container.getHeight();
		}
		if (imageType == null) {
			imageType = BufferedImage.TYPE_3BYTE_BGR;
		}
		BufferedImage buff = new BufferedImage(width, height, imageType);
		container.paint(buff.getGraphics());
		ImageIO.write(buff, "jpg", new File(pathname));
	}

	/**
	 * 播放wav格式音乐
	 */
	public static void playMusic(String path, GameBean p) throws Exception {
		playWavMusic(ResourceUtil.class.getResourceAsStream(path), p);
	}

	private static void playWavMusic(InputStream in, GameBean p) throws Exception {
		byte[] audioData = new byte[1024];
		// 音源:即需要把本地或网络上的音频读进此处，
		// 注意:声音的播方是在将声加入到此处之后，然后再写向声卡
		AudioInputStream ais = null;
		SourceDataLine line = null;
		// 注意：用BufferedInputStream 它重写了父类mark和resetf方法
		ais = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
		if (ais != null) {
			AudioFormat baseFormat = ais.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, baseFormat);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(baseFormat);
		}
		if (line == null) {
			return;
		}
		// 允许开始执行数行的I/O,在此这前所做的所有操作，
		// 只是为SourceDataLint获得了一个音频输入流，但是其还未真正的开始进行流的输入。
		line.start();
		int inByte = 0;
		while (inByte != -1) {
			// 判断游戏是否暂停
			if (GameBean.STATUS_PAUSE != p.getStatus()) {
				// 将音频流读入到缓冲区中
				inByte = ais.read(audioData, 0, 1024);
				if (inByte > 0) {
					// 将缓冲区中的内容写出到音频器中，暂解。
					line.write(audioData, 0, inByte);
				}
			}
		}
		// 相当于flush 清空缓冲区
		line.drain();
		// 停止播放
		line.stop();
		// 关闭
		line.close();
	}
}
