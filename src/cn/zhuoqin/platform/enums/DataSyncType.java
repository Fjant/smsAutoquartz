package cn.zhuoqin.platform.enums;

public enum DataSyncType {
	BirthdayMessage("客户生日提醒", 1), BirthdayMessageForPreCustomer("准客户生日提醒", 2), RenewalFeeMessage("续期缴费消息提醒", 3), RenewwalSync("续期缴费保单同步", 4), StaffSync("营销人员信息同步", 5), PreCustomerSync("准客户信息同步", 6), NewBillCustomerSync("新增保单客户信息同步", 7), UpdateBillCustomerSync("更新保单客户信息同步", 8), SendWxMessage("发送微信信息", 9), BillCustomerAlteration("投保人变更同步", 10), StaffSyncToWX("营销人员信息同步到微信通讯录", 11), WorkplaceSync("同步职场信息", 20), BuildAMDemand("生成考勤需求", 21), UploadDemandData("上传考勤需求数据", 22), DownloadSignInSignOutData("下载签到签退数据", 23), BuildAMResult("生成考勤结果", 24), StatAMResult("生成考勤统计数据", 25), RestatlastMonthAMResult("重新统计上个月份的考勤数据", 26), SyncAMResultToAMS("同步营销员月度出勤率到AMS系统", 27), DataCleaning("系统数据清理", 100);

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private int value;

	private DataSyncType(String name, int value) {
		this.name = name;
		this.value = value;
	}
}
