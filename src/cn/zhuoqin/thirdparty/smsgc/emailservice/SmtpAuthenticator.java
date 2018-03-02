package cn.zhuoqin.thirdparty.smsgc.emailservice;

import javax.mail.Authenticator;

public class SmtpAuthenticator extends Authenticator {
	private String userName = null;
	private String password = null;

	public void check(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(this.userName, this.password);
	}
}
