package cn.zhuoqin.thirdparty.smsgc.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import cn.zhuoqin.thirdparty.smsgc.sms.model.SmsInfo;

public class SmsInspector {
	public static boolean check(SmsInfo smsInfo) {
		// =2表示短信无效
		if (smsInfo.getStatusId() == 2)
			return false;

		// 手机号为空
		if (StringUtils.isBlank(smsInfo.getMobile())) {
			return false;
		}

		// 手机号长度不足
		if (!Pattern.matches("^\\d{11}$", smsInfo.getMobile().trim())) {
			return false;
		}

		return true;
	}
}
