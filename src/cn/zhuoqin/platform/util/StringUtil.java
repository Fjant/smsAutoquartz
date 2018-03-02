package cn.zhuoqin.platform.util;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.zhuoqin.platform.config.SystemConfig;

/**
 */
public class StringUtil {
	public static String removeInvalidUnicodeChar(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < value.length(); i++) {
			if (chars[i] > 0xFFFD) {
				chars[i] = ' ';
			} else if (chars[i] < 0x20 && chars[i] != '\t' & chars[i] != '\n' & chars[i] != '\r') {
				chars[i] = ' ';
			}
		}
		return new String(chars);
	}

	public static String parse(String source, String patternString) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			// 用了非贪婪模式匹配,并用元字符进行了转义
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(source);
			String key = null;
			String value = null;
			while (matcher.find()) {
				key = matcher.group(1);
				value = SystemConfig.getPara(key);
				if (StringUtils.isNoneBlank(value)) {
					matcher.appendReplacement(sb, value);
				}
			}
			matcher.appendTail(sb);
		} catch (Exception e) {
			return source;
		}
		return sb.toString();
	}
}
