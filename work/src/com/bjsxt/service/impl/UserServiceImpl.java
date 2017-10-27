package com.bjsxt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjsxt.mapper.AddressDao;
import com.bjsxt.mapper.UserDao;
import com.bjsxt.pojo.Address;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.util.MyBatisUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private AddressDao addressDao;

	@Override
	public String toString() {
		return "UserServiceImpl [userDao=" + userDao + ", addressDao=" + addressDao + "]";
	}

	@Override
	public Map<String, Object> listUsersByPage(int page, int size) {
		try {
			int start = (page - 1) * size;
			Map<String, Object> params = new HashMap<>();
			params.put("start", start);
			params.put("rows", size);
			List<User> selectUsers = this.userDao.selectUsers(params);
			int count = this.userDao.getCount();
			if (count <= 0) {
				throw new RuntimeException("获取数据总数失败");
			}

			params.put("items", selectUsers);
			params.put("count", count);
			int pages = count % size == 0 ? count / size : count / size + 1;
			params.put("pages", pages);
			params.put("page", page);
			return params;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User register(User user) {
		try {
			int rows = this.userDao.insertUser(user);
			if (rows <= 0) {
				throw new RuntimeException("注册失败");
			}
			return user;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User login(String loginName, String loginPswd) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("loginName", loginName);
			params.put("loginPswd", loginPswd);
			System.out.println(userDao+"----");
			List<User> userList = this.userDao.selectUsers(params);
			if (userList.size() > 0) {
				User user = userList.get(0);
				user.setLastLoginTime(new Date());
				user.setLoginHits(user.getLoginHits() + 1);
				int rows = this.userDao.updateUser(user);
				if (rows <= 0) {
					throw new RuntimeException("登陆失败");
				} else {
					return user;
				}
			} else {
				throw new RuntimeException("登陆失败");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User updateUser(User user) {
		try {

			int rows = this.userDao.updateUser(user);
			if (rows <= 0) {
				throw new RuntimeException("更新失败");
			}
			return user;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Address> listUserAddressesById(String userId) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			List<Address> listUserAddresses = this.addressDao.listUserAddresses(params);
			return listUserAddresses;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int addUserAddress(String userId, String province, String city, String subdistrict) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("province", province);
			params.put("city", city);
			params.put("subdistrict", subdistrict);
			int rows = this.addressDao.insertAddress(params);
			if (rows != 1) {
				throw new RuntimeException("插入失败");
			}
			return rows;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int deleteAddressById(Integer id) {
		try {
			Map<String, Object> params = new HashMap<>();
			int rows = this.addressDao.deleteAddressById(id);
			if (rows != 1) {
				throw new RuntimeException("删除失败");
			}
			return rows;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	
	
}
