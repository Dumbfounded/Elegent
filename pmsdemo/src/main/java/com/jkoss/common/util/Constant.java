package com.jkoss.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类
 * 
 * @Author Chair
 * @Version 1.0, 2018年9月23日
 * @See
 * @Since com.jkoss.common.util
 * @Description: TODO
 */
@Component
public class Constant {
 
	public static final String YAN_KEY = "pms";
	
	public static final String SESSION_USER_KEY = "pmsuser";
	
	public static final String SESSION_MENU_KEY = "pmsmenu";
	
	public static final String SESSION_BUTTON_KEY = "pmsbutton";

	public static final String DWZRESULT_STATUSCODE_SUCCESS = "200";

	public static final String DWZRESULT_STATUSCODE_ERROR = "300";

	public static final String DWZRESULT_STATUSCODE_TIMEOUT = "301";

	public static final String DWZRESULT_MESSAGE_SUCCESS = "操作成功";

	public static final String DWZRESULT_MESSAGE_ERROR = "操作失败";

	public static final String DWZRESULT_MESSAGE_TIMEOUT = "超时";

	public static final String DWZRESULT_CALLBACKTYPE_CLOSECURRENT = "closeCurrent";

	public static final String DWZRESULT_CALLBACKTYPE_FORWARD = "forward";
	/**
	 * token请求token
	 */
	public static final String REQUEST_TOKEN_HEADER = "userid";
	/**
	 * 成功返回码
	 */
	public static final int RESULT_CODE_SUCCESS = 1000;
	/**
	 * 请求失败返回码
	 */
	public static final int RESULT_CODE_FAIL = 1001;
	/**
	 * 请求抛出异常返回码
	 */
	public static final int RESULT_CODE_EXCEPTION = 1002;
	/**
	 * 未登陆状态返回码
	 */
	public static final int RESULT_CODE_NOLOGIN = 1003;
	/**
	 * 无操作权限返回码
	 */
	public static final int RESULT_CODE_NOAUTH = 1004;
	/**
	 * userid可能在别的地方登录
	 */
	public static final int RESULT_CODE_USERID = 1005;
	/**
	 * 成功返回信息
	 */
	public static final String RESULT_MSG_SUCCESS = "操作成功";
	/**
	 * 成功返回信息
	 */
	public static final String RESULT_MSG_FAIL = "操作失败";
	/**
	 * 请求抛出异常返回信息
	 */
	public static final String RESULT_MSG_EXCEPTION = "请求报错！";
	/**
	 * 未登陆状态返回信息
	 */
	public static final String RESULT_MSG_NOLOGIN = "no login!";
	/**
	 * 无操作权限返回信息
	 */
	public static final String RESULT_MSG_NOAUTH = "no auth!";

	/**
	 * Token可能在别的地方登录
	 */
	public static final String RESULT_MSG_USERID = "Userid可能在别的地方登录!";

	

}
