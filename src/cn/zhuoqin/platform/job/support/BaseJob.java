package cn.zhuoqin.platform.job.support;

import cn.zhuoqin.platform.job.model.JobEntity;

public interface BaseJob {
	public void execute(JobEntity jobEntity);
}
