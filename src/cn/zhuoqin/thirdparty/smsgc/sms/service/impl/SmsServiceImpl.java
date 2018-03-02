package cn.zhuoqin.thirdparty.smsgc.sms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zhuoqin.thirdparty.smsgc.sms.dao.SmsDao;
import cn.zhuoqin.thirdparty.smsgc.sms.model.SmsInfo;
import cn.zhuoqin.thirdparty.smsgc.sms.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private SmsDao smsDao;

	@Override
	public void insertBatch(List<SmsInfo> smsList) throws Exception {
		smsDao.insertBatch(smsList);
	}

	@Override
	public void updateBatch(String batchId, Integer statusId) {
		smsDao.updateBatch(batchId, statusId);
	}
}
