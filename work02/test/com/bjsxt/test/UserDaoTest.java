package com.bjsxt.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.bjsxt.mapper.UserDao;
import com.bjsxt.pojo.User;
import com.bjsxt.util.MyBatisUtil;

public class UserDaoTest {
	@Test
	public void selectUsersTest(){
		SqlSession sqlSession = null;
		try{
			sqlSession = MyBatisUtil.openSession();
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			Map<String, Object> params = new HashMap<>();
			params.put("start", 0);
			params.put("rows", 5);
			List<User> selectUsers = userDao.selectUsers(params);
			sqlSession.commit();
			for (User user : selectUsers) {
				System.out.println(user);
			}
		}catch(RuntimeException e){
			e.printStackTrace();
			if(null!=sqlSession){
				sqlSession.rollback();
			}
			throw e;
		}finally {
			if(null!=sqlSession){
				sqlSession.close();
			}
		}
	}
}
