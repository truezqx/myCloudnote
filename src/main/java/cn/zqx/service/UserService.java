package cn.zqx.service;

import cn.zqx.entity.User;

public interface UserService {
	/*
	 * 登录功能方法
	 * @param name用户名
	 * @param password密码
	 */
	User Login(String name,String password)throws NameException,PasswordException;
	User regist(String name,String password,String nick)throws NameException,PasswordException;
	User checkName(String name);
}
