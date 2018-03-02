package cn.zhuoqin.thirdparty.smsgc.sms.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbchelper.StatementMapper;

public class Sendrop {
	private int id;
	private String userid;
	private Date sendtime;
	private String phonecode;
	private String sendtext;
	private String company;
	private String departmet;
	private String smstype;
	private String cotmername;
	private String autoread;
	private String timerflag;
	private String gwid;
	private String smscomcode;
	private String flagno;
	private String back_no;
	private String status;
	private String flag;
	private String qfflag;
	private String chdrnum;
	private String ind01;
	private String ind02;
	private String ind03;
	private String ind04;
	private String ind05;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public String getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}

	public String getSendtext() {
		return sendtext;
	}

	public void setSendtext(String sendtext) {
		this.sendtext = sendtext;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartmet() {
		return departmet;
	}

	public void setDepartmet(String departmet) {
		this.departmet = departmet;
	}

	public String getSmstype() {
		return smstype;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	public String getCotmername() {
		return cotmername;
	}

	public void setCotmername(String cotmername) {
		this.cotmername = cotmername;
	}

	public String getAutoread() {
		return autoread;
	}

	public void setAutoread(String autoread) {
		this.autoread = autoread;
	}

	public String getTimerflag() {
		return timerflag;
	}

	public void setTimerflag(String timerflag) {
		this.timerflag = timerflag;
	}

	public String getGwid() {
		return gwid;
	}

	public void setGwid(String gwid) {
		this.gwid = gwid;
	}

	public String getSmscomcode() {
		return smscomcode;
	}

	public void setSmscomcode(String smscomcode) {
		this.smscomcode = smscomcode;
	}

	public String getFlagno() {
		return flagno;
	}

	public void setFlagno(String flagno) {
		this.flagno = flagno;
	}

	public String getBack_no() {
		return back_no;
	}

	public void setBack_no(String back_no) {
		this.back_no = back_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQfflag() {
		return qfflag;
	}

	public void setQfflag(String qfflag) {
		this.qfflag = qfflag;
	}

	public String getChdrnum() {
		return chdrnum;
	}

	public void setChdrnum(String chdrnum) {
		this.chdrnum = chdrnum;
	}

	public String getInd01() {
		return ind01;
	}

	public void setInd01(String ind01) {
		this.ind01 = ind01;
	}

	public String getInd02() {
		return ind02;
	}

	public void setInd02(String ind02) {
		this.ind02 = ind02;
	}

	public String getInd03() {
		return ind03;
	}

	public void setInd03(String ind03) {
		this.ind03 = ind03;
	}

	public String getInd04() {
		return ind04;
	}

	public void setInd04(String ind04) {
		this.ind04 = ind04;
	}

	public String getInd05() {
		return ind05;
	}

	public void setInd05(String ind05) {
		this.ind05 = ind05;
	}

	public static StatementMapper<SmsInfo> getMapper() {
		return new StatementMapper<SmsInfo>() {
			public void mapStatement(PreparedStatement stmt, SmsInfo info) throws SQLException {
				stmt.setString(1, info.getUserId());
				stmt.setTimestamp(2, new Timestamp(new Date().getTime()));
				stmt.setString(3, info.getMobile());
				stmt.setString(4, info.getContent());
				stmt.setString(5, info.getCompany());
				stmt.setString(6, info.getDepartment());
				stmt.setString(7, info.getSmsType());
				stmt.setString(8, info.getAutoread());
				stmt.setString(9, info.getGwid());
				stmt.setString(10, info.getDepartment());
				stmt.setString(11, info.getFlagNo());
				stmt.setString(12, info.getBatchId());
				stmt.setString(13, "0");
				stmt.setString(14, "0");
				stmt.setString(15, info.getChdrnum());
				stmt.setString(16, info.getOrderNo().toString());
				stmt.setString(17, info.getShortNum());
				stmt.setTimestamp(18, new Timestamp(new Date().getTime()));
			}
		};
	}
}
