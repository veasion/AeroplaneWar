package cn.veasion.util;

import java.util.Random;

/**
 * 帮助类.
 * @auto Veasion
 */
public class VeaUtil {

	static Random random=new Random();
	
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
	
}
