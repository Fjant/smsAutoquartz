package cn.zhuoqin.thirdparty.smsgc.emailservice;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author
 */
public class SendMail {
	private static final Logger log = Logger.getLogger(SendMail.class.getName());

	private String host; // smtp服务器

	private String auth = "true";

	private String subjectStr;// 环境

	private String userName;// 用户名

	private String userPassword;// 密码

	private String byname;// 发件人别名

	private String encoding = "UTF-8";// 邮件编码

	private MimeMessage message;// 创建message对象

	private Session mailSession;// 创建session

	public SendMail() {
	}

	public SendMail(String host, String userName, String password) {
		this.host = host;
		this.userName = userName;
		this.userPassword = password;
	}

	private void init() {
		Properties props = new Properties();
		SmtpAuthenticator au = new SmtpAuthenticator();
		au.check(userName, userPassword); // 认证 (用户名和密码)
		props.put("mail.smtp.host", host); // 设置smtp服务器
		props.put("mail.smtp.auth", auth); // 这样认证才会起作用

		mailSession = Session.getInstance(props, au);// 创建session
		message = new MimeMessage(mailSession); // 创建message对象
	}

	// 设置发件人
	private void setFrom(String from) throws UnsupportedEncodingException, MessagingException {
		Address address = new InternetAddress(from, byname, encoding); // 发件人地址
		message.setFrom(address);// 设置发件地址
	}

	// 设置发件地址及別名
	private void setFrom(String from, String byname) throws UnsupportedEncodingException, MessagingException {
		Address address = new InternetAddress(from, byname, encoding); // 发件人别名
		message.setFrom(address);// 设置发件人地址
	}

	// 设置收件地址
	private int setRecipient(String sendTo) throws MessagingException {
		if (sendTo == null || sendTo.equals("")) {
			return 0;
		} else {
			String[] copy_to_address = sendTo.split(",");
			Address[] copy_to_address_array = new InternetAddress[copy_to_address.length];
			for (int i = 0; i < copy_to_address.length; i++) {
				copy_to_address_array[i] = new InternetAddress(copy_to_address[i]);
			}
			message.setRecipients(MimeMessage.RecipientType.TO, copy_to_address_array);// 设置收件地址
			return 1;
		}
	}

	// 设置抄送人
	private int setRecipients(String ccTo) throws MessagingException {
		if (StringUtils.isBlank(ccTo)) {
			return 0;
		}

		String[] ccToAddress = ccTo.trim().split(",");
		Address[] addressArray = new InternetAddress[ccToAddress.length];
		for (int i = 0; i < ccToAddress.length; i++) {
			addressArray[i] = new InternetAddress(ccToAddress[i]);
		}
		message.addRecipients(MimeMessage.RecipientType.CC, addressArray);
		return 1;

	}

	private void writeMail(String subject, String content) throws MessagingException {
		// 下面写邮件容
		message.setSubject(subject, encoding); // 设置主题
		message.setSentDate(new Date());// 设置日期
		message.setText(content, encoding);// 设置邮件内容
	}

	private void writeMail(String subject, String content, File file) throws Exception {

		// 下面写邮件内宄1?7
		message.setSubject(subject, encoding); // 设置主题
		message.setSentDate(new Date());// 设置日期
		message.setText(content, encoding);// 设置邮件内容

		MimeMultipart mimemultipart = new MimeMultipart();
		MimeBodyPart mimebodypart = new MimeBodyPart();
		if (content == null) {
			content = "";
		}
		content = new String(content.getBytes("GBK"));
		mimebodypart.setContent(content, "text/html;charset=GBK");
		mimemultipart.addBodyPart(mimebodypart);

		MimeBodyPart mimebodypart1 = new MimeBodyPart();
		FileDataSource filedatasource = new FileDataSource(file);
		mimebodypart1.setDataHandler(new DataHandler(filedatasource));
		sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		String filename = "=?GBK?B?" + enc.encode(filedatasource.getName().getBytes()) + "?=";
		mimebodypart1.setFileName(filename);
		mimemultipart.addBodyPart(mimebodypart1);
		message.setContent(mimemultipart);
		message.saveChanges();
	}

	// 发送
	public void send() throws MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		try {
			transport.send(message);
		} catch (MessagingException e) {
			log.info(e.getMessage());
			throw e;
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * JobCommon发送邮件
	 * 
	 * @param subject
	 *            邮件标题
	 * @param content
	 *            邮件正文
	 * @param sendTo
	 *            收件人电子邮箱地址
	 * @return
	 * @throws Exception
	 */
	public int send(String subject, String content, String from, String sendTo) throws Exception {
		this.init();
		this.setFrom(from);
		this.setRecipient(sendTo);
		log.info("邮箱收件地址是：" + sendTo);
		this.setRecipients("");
		content = content.replaceAll("#lt#", "<");
		content = content.replaceAll("#gt#", ">");
		content = content.replaceAll("&", "&amp");
		this.writeMail(subject, content);
		log.info("邮件信息设置完成!");
		try {
			this.send();

			return 1;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}

	}

	/**
	 * @param sendTo
	 *            收件人信箱,可以为多个，以逗号“,”分隔
	 * @param copy_to
	 *            抄送,可以为多个，以逗号“,”分隔
	 * @param subject
	 *            主题,邮件主题
	 * @param content
	 *            邮件内容
	 * @return 返回一个int类型的数据，当返回1?:表示邮件发送成功，0:表示发送失败
	 */
	public int send(String subject, String content, String from, String sendTo, File file) throws Exception {
		this.init();
		this.setFrom(from);
		this.setRecipient(sendTo);

		this.setRecipients("");
		this.writeMail(subject, content, file);

		try {
			this.send();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public int send(String subject, String content, String from, String sendTo, String recipients, File file) throws Exception {
		this.init();
		this.setFrom(from);
		this.setRecipient(sendTo);
		this.setRecipients(recipients);
		this.writeMail(subject, content, file);
		try {
			this.send();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}