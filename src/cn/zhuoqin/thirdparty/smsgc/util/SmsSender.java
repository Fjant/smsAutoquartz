package cn.zhuoqin.thirdparty.smsgc.util;

import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.citic.cpiep.util.CpiepClient;

import cn.zhuoqin.platform.config.SystemConfig;
import cn.zhuoqin.platform.util.ParseXML;

public class SmsSender {
	private static final Logger logger = Logger.getLogger(SmsSender.class);

	public static boolean send(String mobile, String message) {
		try {
			Calendar calendar = Calendar.getInstance();
			String currentDate = DateFormatUtils.format(calendar, "yyyyMMdd");
			// 拼装报文
			StringBuilder builder = new StringBuilder();
			builder.append("<?xml version='1.0' encoding='UTF-8'?>");
			builder.append("<ReqInfo>");
			builder.append("	<BaseInfo>");
			builder.append("		<phonecode>" + mobile + "</phonecode>");
			builder.append("		<sendtext>" + message + "</sendtext>");
			builder.append("		<sender>" + SystemConfig.getPara("SmsSender") + "</sender>");
			builder.append("		<flagno>" + SystemConfig.getPara("SmsFlagNo") + "</flagno>");
			builder.append("		<gwid>" + currentDate + SystemConfig.getPara("SmsGwid") + "</gwid>");
			builder.append("		<autoread>" + SystemConfig.getPara("SmsAutoRead") + "</autoread>");
			builder.append("		<smstype>" + SystemConfig.getPara("SmsType") + "</smstype>");
			builder.append("		<chdrnum>" + SystemConfig.getPara("SmsChdrnum") + "</chdrnum> ");
			builder.append("		<back_no>" + SystemConfig.getPara("SmsBackNo") + "</back_no>");
			builder.append("		<ind04>" + SystemConfig.getPara("SmsInd04") + "</ind04>");
			builder.append("		<ind05>" + SystemConfig.getPara("SmsInd05") + "</ind05>");
			builder.append("		<company>" + SystemConfig.getPara("SmsCompany") + "</company>");
			builder.append("		<department>" + SystemConfig.getPara("SmsDepartment") + "</department>");
			builder.append("		<check_hmd>" + SystemConfig.getPara("SmsCheckHmd") + "</check_hmd>");
			builder.append("	</BaseInfo>");
			builder.append("</ReqInfo>");

			HashMap<String, String> oMap = new HashMap<String, String>();
			oMap.put("sysId", SystemConfig.getPara("CpiepSysId"));
			oMap.put("roleId", SystemConfig.getPara("CpiepRoleId"));
			oMap.put("intfId", SystemConfig.getPara("SmsIntfId"));
			oMap.put("enCode", SystemConfig.getPara("CpiepEnCode"));
			oMap.put("userType", SystemConfig.getPara("SmsUserType"));
			oMap.put("userId", SystemConfig.getPara("SmsUserId"));
			oMap.put("returnType", SystemConfig.getPara("SmsReturnType"));
			oMap.put("methodType", SystemConfig.getPara("SmsMethodType"));
			oMap.put("sessionKey", SystemConfig.getPara("SmsSessionKey"));
			oMap.put("encrypt", SystemConfig.getPara("CpiepEncrypt"));
			oMap.put("xmlcontent", builder.toString());

			logger.info("xmlcontent=" + builder.toString());
			String returnXml = CpiepClient.postAndResponse(oMap, null, SystemConfig.getPara("CpiepContext"));
			logger.info("returnXml= " + returnXml);

			ParseXML p = new ParseXML();
			p.initXmlByText(returnXml.trim());
			if (StringUtils.isNotBlank(returnXml)) {
				if (returnXml.indexOf("<state>1</state>") >= 0) {
					return true;
				} else {
					return false;
				}
			} else {
				logger.error("发送短信至[" + mobile + "]失败");
				return false;
			}

		} catch (Exception ex) {
			return false;
		}
	}
}
