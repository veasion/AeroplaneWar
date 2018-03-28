package cn.veasion.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * 帮助类.
 * 
 * @author Veasion
 */
public class VeaUtil {

	private static Random random = new Random();
	private static final String objFilePath = "C:\\AeroplaneWarHistory.obj";

	/**
	 * 随机数字
	 * 
	 * @since 随机start-end中的任意数字，包含start和end
	 */
	public static int random(int start, int end) {
		return random.nextInt(end - start + 1) + start;
	}

	/**
	 * 随机元素
	 */
	public static <T> T random(T[] item) {
		return item[random(0, item.length - 1)];
	}

	/**
	 * 随机元素
	 */
	public static int random(int[] item) {
		return item[random(0, item.length - 1)];
	}

	/**
	 * 读取最高历史纪录
	 */
	public static Integer readHistoryRecord() throws Exception {
		ObjectInputStream input = null;
		if (!new File(objFilePath).exists())
			return null;
		try {
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(objFilePath)));
			Object obj = input.readObject();
			if (obj == null)
				return null;
			String content = String.valueOf(obj);
			int index = -1;
			if ((index = content.indexOf("|")) == -1) {
				return Integer.valueOf(content);
			} else {
				return Integer.valueOf(content.substring(0, index));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * 保存当前纪录为最高纪录
	 */
	public static void saveCurrentRecord(Integer score) throws Exception {
		if (score == null)
			return;
		int cheatsCount = Constants.getCheatsCount();
		String content = score + (cheatsCount > 0 ? "|" + cheatsCount : "");
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(objFilePath)));
		out.writeObject(content);
		out.flush();
		out.close();
	}

}
