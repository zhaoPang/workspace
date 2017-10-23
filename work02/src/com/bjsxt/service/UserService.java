package com.bjsxt.service;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.User;

public interface UserService {
	/**
	 * 分页查询
	 * @return
	 * @throws RuntimeException
	 */
	List<User> listUsersByPage(Map<String, Object> params);
	
	/**
	 * 注册
	 * @param user
	 * @return	返回注册成功的用户的对象
	 */
	User register(User user);
	
	/**
	 * 登录方法
	 * @param loginName	登录名
	 * @param loginPwsd	登录密码
	 * @return	登陆成功后用户的信息
	 */
	User login(String loginName,String loginPswd);
	
	/**
	 * 修改用户
	 * @param user
	 * @return	返回修改后的用户对象
	 */
	User updateUser(User user);

	/**
	 * 获取总个数
	 * @return
	 */
	int getCount();
}
