package cn.zhuoqin.platform.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

public class Struts2Util {
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public static HttpSession getSession(boolean bool) {
		return ServletActionContext.getRequest().getSession(bool);
	}

	public static Object getSessionAttribute(String key) {
		HttpSession localHttpSession = getSession(false);
		return localHttpSession != null ? localHttpSession.getAttribute(key) : null;
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static String getParameter(String key) {
		return getRequest().getParameter(key);
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static String getIpAddr() {
		return getIpAddr(getRequest());
	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
			ip = request.getHeader("X-Forwarded-For");
			if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
				// 多次反向代理后会有多个IP值，第一个为真实IP。
				int index = ip.indexOf(',');
				if (index != -1) {
					return ip.substring(0, index);
				} else {
					return ip;
				}
			} else {
				return request.getRemoteAddr();
			}
		} catch (Exception ex) {
			return "";
		}
	}
	
	public static void setSessionAttribute(String key, Object value) {
		HttpSession localHttpSession = getSession(false);
		if (localHttpSession != null) {
			localHttpSession.setAttribute(key, value);
		}
	}
}
