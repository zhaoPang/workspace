package com.bjsxt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
	public Map<String, Object> listUsersByPage(int page, int size) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.openSession();
			this.userDao = sqlSession.getMapper(UserDao.class);
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
			sqlSession.commit();
			return params;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}

	}

	@Override
	public User register(User user) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.openSession();
			this.userDao = sqlSession.getMapper(UserDao.class);
			int rows = this.userDao.insertUser(user);
			if (rows <= 0) {
				throw new RuntimeException("注册失败");
			}
			sqlSession.commit();
			return user;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	@Override
	public User login(String loginName, String loginPswd) {
		SqlSession sqlSession = null;
		try {
			sqlSession = MyBatisUtil.openSession();
			this.userDao = sqlSession.getMapper(UserDao.class);
			Map<String, Object> params = new HashMap<>();
			params.put("loginName", loginName);
			params.put("loginPswd", loginPswd);
			List<User> userList = this.userDao.selectUsers(params);
			if (userList.size() > 0) {
				User user = userList.get(0);
				user.setLastLoginTime(new Date());
				user.setLoginHits(user.getLoginHits() + 1);
				int rows = this.userDao.updateUser(user);
				if (rows <= 0) {
					throw new RuntimeException("登陆失败");
				} else {
					sqlSession.commit();
					return user;
				}
			} else {
				throw new RuntimeException("登陆失败");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlSession) {
				sqlSession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlSession) {
				sqlSession.close();
			}
		}
	}

	@Override
	public User updateUser(User user) {
		SqlSession sqlsession = null;
		try {
			sqlsession = MyBatisUtil.openSession();
			this.userDao = sqlsession.getMapper(UserDao.class);
			int rows = this.userDao.updateUser(user);
			if (rows <= 0) {
				throw new RuntimeException("更新失败");
			}
			sqlsession.commit();
			return user;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlsession) {
				sqlsession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlsession) {
				sqlsession.close();
			}
		}
	}

	@Override
	public List<Address> listUserAddressesById(String userId) {
		SqlSession sqlsession = null;
		try {
			sqlsession = MyBatisUtil.openSession();
			this.addressDao = sqlsession.getMapper(AddressDao.class);
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			List<Address> listUserAddresses = this.addressDao.listUserAddresses(params);
			return listUserAddresses;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlsession) {
				sqlsession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlsession) {
				sqlsession.close();
			}
		}
	}

	@Override
	public int addUserAddress(String userId, String province, String city, String subdistrict) {
		SqlSession sqlsession = null;
		try {
			sqlsession = MyBatisUtil.openSession();
			this.addressDao = sqlsession.getMapper(AddressDao.class);
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("province", province);
			params.put("city", city);
			params.put("subdistrict", subdistrict);
			int rows = this.addressDao.insertAddress(params);
			if(rows!=1){
				throw new RuntimeException("插入失败");
			}
			sqlsession.commit();
			return rows;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlsession) {
				sqlsession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlsession) {
				sqlsession.close();
			}
		}
	}

	@Override
	public int deleteAddressById(Integer id) {
		SqlSession sqlsession = null;
		try {
			sqlsession = MyBatisUtil.openSession();
			this.addressDao = sqlsession.getMapper(AddressDao.class);
			Map<String, Object> params = new HashMap<>();
			int rows = this.addressDao.deleteAddressById(id);
			if(rows!=1){
				throw new RuntimeException("删除失败");
			}
			sqlsession.commit();
			return rows;
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (null != sqlsession) {
				sqlsession.rollback();
			}
			throw e;
		} finally {
			if (null != sqlsession) {
				sqlsession.close();
			}
		}
	}

}
