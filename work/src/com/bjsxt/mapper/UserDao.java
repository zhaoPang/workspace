package com.bjsxt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bjsxt.pojo.User;

public interface UserDao {
	/**
	 * 根据传入参数的不同，获取不同的用户
	 * key=id		根据id查询用户
	 * key=name 	根据name模糊查询用户	
	 * key=loginName,loginPswd	根据登录名和登录密码查询，即为用户登录
	 * key=start,row	分页查询用户信息，start为起始行，row为查询的条数
	 * key=birthday		根据生日查询
	 * @return 查询得到的用户列表
	 */
	List<User> selectUsers(Map<String, Object> params);

	/**
	 * 插入用户
	 * 
	 * @param user
	 *            要插入的数据
	 * @return 同步数据库自增主键后的用户对象.
	 */
	int insertUser(User user);

	/**
	 * 更新数据，根据参数更新数据库中数据. 更新条件是参数的主键属性值.
	 * 
	 * @param user
	 * @return 返回更新后的用户对象
	 */
	int updateUser(User user);

	/**
	 * 获取数据库数据总数
	 * @return
	 */
	int getCount();

}
