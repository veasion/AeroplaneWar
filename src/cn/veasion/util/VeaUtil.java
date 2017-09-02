package cn.veasion.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import cn.veasion.bean.GameBean;

/**
 * 帮助类.
 * 
 * @auto Veasion
 */
public class VeaUtil {

	private static Random random=new Random();
	private static final String objFilePath=VeaUtil.class.getResource("/").getPath()+"history.obj";
	
	/**
	 * 随机数字
	 * 
	 * @since 随机start-end中的任意数字，包含start和end
	 */
	public static int random(int start, int end){
		return random.nextInt(end-start+1)+start;
	}
	
	/**
	 * 随机元素
	 */
	public static <T> T random(T [] item){
		return item[random(0, item.length-1)];
	}
	
	/**
	 * 随机元素
	 */
	public static int random(int [] item){
		return item[random(0, item.length-1)];
	}
	
	/**
	 * 读取最高历史纪录 
	 */
	public static Integer readHistoryRecord() throws Exception{
		File objFile=new File(objFilePath);
		if(objFile.exists()){
			@SuppressWarnings("resource")
			ObjectInputStream input=new ObjectInputStream(new FileInputStream(objFile));
			return (Integer)input.readObject();
		}else{
			return null;
		}
	}
	
	/**
	 * 保存当前纪录为最高纪录 
	 */
	public static void saveCurrentRecord(Integer score) throws Exception{
		BufferedOutputStream buff = new BufferedOutputStream(
				new FileOutputStream(objFilePath));
		ObjectOutputStream out = new ObjectOutputStream(buff);
		out.writeObject(score);
		out.flush();
		out.close();
	}
	
}
