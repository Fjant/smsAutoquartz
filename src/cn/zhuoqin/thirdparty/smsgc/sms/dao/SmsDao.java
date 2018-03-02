package cn.zhuoqin.thirdparty.smsgc.sms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.zhuoqin.platform.ibatis.MyBatisEntityDao;
import cn.zhuoqin.thirdparty.smsgc.sms.model.SmsInfo;

@Repository
public class SmsDao extends MyBatisEntityDao<SmsInfo, Long> {

	public void updateBatch(String batchId, Integer statusId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("batchId", batchId);
		params.put("statusId", statusId);

		this.getSqlSessionTemplate().update("SmsInfo.updateBatchStatus", params);
	}

	public void insertBatch(List<SmsInfo> smsList) {
		this.getSqlSessionTemplate().insertBatch("SmsInfo.insert", smsList);
	}
}
