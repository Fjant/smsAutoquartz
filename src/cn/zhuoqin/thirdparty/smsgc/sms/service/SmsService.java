package cn.zhuoqin.thirdparty.smsgc.sms.service;

import java.util.List;

import cn.zhuoqin.thirdparty.smsgc.sms.model.SmsInfo;

public interface SmsService {

	public void insertBatch(List<SmsInfo> smsList) throws Exception;

	/**
	 * 批量更新指定批次短信状态
	 * 
	 * @param batchId
	 * @param statusId
	 */
	public void updateBatch(String batchId, Integer statusId);

}
