package com.gpg.util;

public class MyUtil {
	/**
	 * 判断密码
	 * 
	 * @param pwd_1
	 *            密码一
	 * @param pwd_2
	 *            密码二
	 * @return 相同返回true
	 */
	public static boolean checkPwd(String pwd_1, String pwd_2) {
		if (pwd_1.equals(pwd_2)) {
			return true;
		}
		return false;
	}

}
