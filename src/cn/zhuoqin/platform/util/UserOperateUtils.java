package cn.zhuoqin.platform.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import cn.zhuoqin.platform.login.model.UserSession;



public class UserOperateUtils {
	private static final Logger logger = LoggerFactory.getLogger(UserOperateUtils.class);

	public static void removeUserFromSession() {
		Struts2Util.getSession().removeAttribute("UserSession");
	}

	public static UserSession getUser() {
		Object session = Struts2Util.getSession().getAttribute("UserSession");
		return session == null ? null : (UserSession) session;
	}

	public static String getUserId() {
		UserSession user = getUser();
		if (user != null) {
			return user.getAgntNum();
		}
		return null;
	}
}
