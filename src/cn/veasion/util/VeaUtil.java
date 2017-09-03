package cn.veasion.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * 帮助类.
 * 
 * @auto Veasion
 */
public class VeaUtil {

	private static Random random=new Random();
	private static final String objFilePath="C:\\AeroplaneWarHistory.obj";
	
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
		ObjectInputStream input=null;
		try{
			input = new ObjectInputStream(new FileInputStream(objFilePath));
			return (Integer) input.readObject();
		}catch(Exception e){
			throw e;
		}finally{
			if(input!=null){
				input.close();
			}
		}
	}
	
	/**
	 * 保存当前纪录为最高纪录 
	 */
	public static void saveCurrentRecord(Integer score) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(objFilePath));
		out.writeObject(score);
		out.flush();
		out.close();
	}
}
