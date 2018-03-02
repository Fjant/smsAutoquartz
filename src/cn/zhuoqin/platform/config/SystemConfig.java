package cn.zhuoqin.platform.config;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import cn.zhuoqin.platform.util.DataUtil;
import cn.zhuoqin.platform.util.ParseXML;

/**
 * 数据字典
 * 
 */

public class SystemConfig {

	private static Logger logger = Logger.getLogger(SystemConfig.class);// 记录日志
	private static Element ele = null;

	static {
		init();
	}

	/**
	 * 读取配置文件信息，保存到内存中
	 */
	private static void init() {
		try {
			ParseXML px = new ParseXML();
			String localPath = DataUtil.getProjectLocalPath();
			String configFilePath = localPath + "/WEB-INF/classes/systemConfig.xml";
			File file = new File(configFilePath);
			if (file.exists()) {
				px.initXmlByFile(configFilePath);
			} else {
				configFilePath = localPath + "/classes/systemConfig.xml";
				file = new File(configFilePath);
				if (file.exists()) {
					px.initXmlByFile(configFilePath);
				}
			}
			ele = px.getElement();
		} catch (Exception e) {
			logger.error("系统配置文件systemConfig.xml读取出错", e);
		}
	}

	/**
	 * 获取配置信息
	 * 
	 * @param src
	 *            参数名称
	 * @return 参数对应值
	 */
	public static String getPara(String src) {
		return ele.elementText(src).trim();
	}
}