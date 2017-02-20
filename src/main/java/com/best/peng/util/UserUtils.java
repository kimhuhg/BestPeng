package com.best.peng.util;

import com.best.peng.global.BestConstant;
/**
 * 用户操作工具
 * @author zhoupeng
 *
 */
public class UserUtils {
	/**
	 * 密码加密
	 * @param password
	 * @return
	 */
	public static String passwordEncrypt(String password){
		return MD5Utils.encrypt(MD5Utils.encrypt(BestConstant.PWD_TOKEN_START+password+BestConstant.PWD_TOKEN_END, MD5Utils.MD5_KEY),MD5Utils.MD5_KEY);
	}
}
