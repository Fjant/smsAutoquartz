package cn.zhuoqin.platform.util;

import java.util.regex.Pattern;

public class LongUtil {
	public static boolean isLong(String input) {
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(input).matches()) {
			return true;
		} else {
			return false;
		}
	}
}
