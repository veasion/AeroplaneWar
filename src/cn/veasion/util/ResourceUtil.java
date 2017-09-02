package cn.veasion.util;

import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * 资源帮助类.
 * 
 * @author Veasion
 */
public class ResourceUtil {
	
	/**
	 * 桌面路径. 
	 */
	public static String homePath=FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
	
	/**
	 * 根据相对路径获取图片资源
	 * 
	 * @since eg: loadImage("/images/a.png");
	 */
	public static Image loadImage(String name) {
		try{
			return ImageIO.read(ResourceUtil.class.getResourceAsStream(name));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 根据Container画面保存图片 
	 */
	public static void saveImageByContainer(Container container,String pathname, Integer width, Integer height, Integer imageType) throws IOException{
		if(width==null){
			width=container.getWidth();
		}
		if(height==null){
			height=container.getHeight();
		}
		if(imageType==null){
			imageType=BufferedImage.TYPE_3BYTE_BGR;
		}
		BufferedImage buff=new BufferedImage(width, height, imageType);
		container.paint(buff.getGraphics());
		ImageIO.write(buff, "jpg", new File(pathname));
	}
	
}
