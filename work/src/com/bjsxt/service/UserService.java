package com.bjsxt.service;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.Address;
import com.bjsxt.pojo.User;

public interface UserService {
	/**
	 * 分页查询
	 * @return
	 * @throws RuntimeException
	 */
	Map<String,Object> listUsersByPage(int page,int size);
	
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
	 * 根据用户id查询用户的地址
	 * @param userId
	 * @return
	 */
	List<Address> listUserAddressesById(String userId);

	/**
	 * 给用户添加地址
	 * @param userId	用户id
	 * @param province	省
	 * @param city		市
	 * @param subdistrict	街道
	 * @return
	 */
	int addUserAddress(String userId, String province, String city, String subdistrict);
	
	/**
	 * 根据地址id删除地址
	 * @param 地址id
	 * @return
	 */
	int deleteAddressById(Integer id);

	/**
	 * 批量添加地址
	 * @param adds
	 */
	int addUserAddressList(List<Address> adds);
	
}
