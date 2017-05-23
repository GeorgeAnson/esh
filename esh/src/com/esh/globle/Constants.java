package com.esh.globle;

import java.awt.Color;
import java.awt.Font;

/**
 * 常量类，用于管理常量参数
 */
public class Constants {
	/**
	 * 验证码长度
	 */
	public static final Integer IMAGE_WIDTH=90;
	
	/**
	 * 验证码宽度
	 */
	public static final Integer IMAGE_HEIGHT=30;
	
	/**
	 * 验证码字库
	 */
	public static Font[] codeFont={new Font("Times New Roman",Font.PLAIN,20),
			new Font("Times New Roman",Font.PLAIN,20),new Font("Times New Roman",Font.PLAIN,20),
			new Font("Times New Roman",Font.PLAIN,20),new Font("Times New Roman",Font.PLAIN,20)};
	/**
	 * 验证码每个字符颜色
	 */
	public static Color[] color={Color.BLACK,Color.BLUE,Color.RED,Color.DARK_GRAY,Color.GREEN};
	
	/**
	 * 验证码字库
	 */
	public static final String IMAGE_CHAR="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * session中的用户ID
	 */
	public static String USER_ID = "user";
	/**
	 * 验证码
	 */
	public static String CHECK_NUMBER_NAME = "identify_code";

	/**
	 * 保存在ServletContext中的用户列表
	 */
	public static String ONLINE_USERS = "userMap";
	
	/**
	 * 软件拥有方
	 */
	public static final String SOFTWARE_OWNER = "宁工信息安全实验室";
	
	/**
	 * 初始化
	 */
	public static final String INIT = "init";
	
	/**
	 * 增加
	 */
	public static final String ADD = "add";
	
	/**
	 * 删除
	 */
	public static final String DELETE = "delete";
	
	/**
	 * 搜索
	 */
	public static final String SEARCH = "search";
	
	/**
	 * 更新
	 */
	public static final String UPDATE = "update";
	
	/**
	 * 详情
	 */
	public static final String DETAIL = "detail";
	
	/**
	 * 用户状态活跃
	 */
	public static final int STATIC_ACTIVE=1;
	
	/**
	 * 用户状态非法
	 */
	public static final int STATIC_INVALID=0;
	
	/**
	 * 用户记录不存在
	 */
	public static final int STATIC_DELETE=-1;
	
	
	
	/**
	 * 不存在任何错误，指向null
	 */
	public static final int NO_ERROR_EXIST=-1;
	
	/**
	 * 该用户名已经被注册
	 */
	public static final int USER_ACCOUNT_EXIST = 1;
	/**
	 * 该用户名包含非法字符
	 */
	public static final int USER_ACCOUNT_CONTAIN_INVALIDE_WORD=2;
	
	/**
	 * 注册成功
	 */
	public static final int REGISTER_SUCCESS=3;
	
	/**
	 * 由于网络等未知因素，造成注册失败
	 */
	public static final int UNKNOWN_REGISTER_ERROR=4;
	
	/**
	 * 用户账户不存在
	 */
	public static final int USER_ACCPUNT_NOT_EXIST=5;
	
	/**
	 * 登录密码错误
	 */
	public static final int USER_LOGIN_PWD_ERROR=6;
	
	/**
	 * 非法访问
	 */
	public static final int INVALID_REQUEST=7;
}
