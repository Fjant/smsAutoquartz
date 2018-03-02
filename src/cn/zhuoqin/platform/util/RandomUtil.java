package cn.zhuoqin.platform.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * 随机生成指定长度数字字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String generateNumber(int length) {
		String randomString = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			randomString += random.nextInt(10);
		}
		return randomString;
	}
}
