package cn.zhuoqin.platform.restful.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

@XmlRootElement
public class BaseModel<T> implements Serializable {
	public BaseModel() {
	}

	public BaseModel(Integer code) {
		super();
		this.code = code;
	}

	public BaseModel(Integer code, Map<String, T> data) {
		super();
		this.code = code;
		this.data = data;
	}

	public BaseModel(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseModel(Integer code, Map<String, T> data, String message) {
		super();
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public BaseModel(ServiceCode serviceCode) {
		super();
		this.code = serviceCode.getCode();
		this.message = serviceCode.getComment();
	}

	public BaseModel(ServiceCode serviceCode, String message) {
		super();
		this.code = serviceCode.getCode();
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer code = ServiceCode.Normal.getCode();
	private Long serial;
	private Map<String, T> data;
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return StringUtils.isBlank(message) ? "" : (this.code == ServiceCode.Normal.getCode() ? ServiceCode.Normal.getComment() : message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public Long getSerial() {
		if (serial == null || serial == 0) {
			serial = (new Date()).getTime();
		}
		return serial;
	}

	public Map<String, T> getData() {
		return data == null ? new HashMap<String, T>() : data;
	}

	public void setData(Map<String, T> data) {
		this.data = data;
	}

	public void setServiceCode(ServiceCode serviceCode) {
		this.code = serviceCode.getCode();
		this.message = serviceCode.getComment();
	}

	public void setServiceCode(ServiceCode serviceCode, String message) {
		this.code = serviceCode.getCode();
		this.message = StringUtils.isNotBlank(message) ? message : serviceCode.getComment();
	}
}
