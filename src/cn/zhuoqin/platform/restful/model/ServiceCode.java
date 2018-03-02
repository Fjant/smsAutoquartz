package cn.zhuoqin.platform.restful.model;

public enum ServiceCode {
	Normal("正常", 0), IllegalAccess("非法请求", 1000), InvalidToken("token无效", 1001), AccessOverLimit("访问次数超过限制", 1002), ParamIsNull("参数未提供", 1003), ValidationParamFailed("参数验证不通过", 1004), UnknowError("未知错误", 1005), UnfoundData("未找到数据或数据不存在", 1006), OrderCannotBeActivated("当前订单所处状态不允许激活", 2001), DuplicateRefund("不能重复申请退款", 2002), CannotRefundBecauseUnpaid("尚未付款不能申请退款", 2003),OrderStatusHadChanged("订单状态已发生变化",2004),RefundAmountOverOrderAmount("申请退款金额不能大于订单金额",2005),SmsHadSent("短信已发送",2006),HAD_KA_STS("STS已写账",2007),HAD_KA_APS("APS已写账",2008),PAYMENT_HANDLING("支付结果正在处理中",2009);

	private ServiceCode(String comment, int code) {
		this.code = code;
		this.comment = comment;
	}

	private int code;
	private String comment;

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
