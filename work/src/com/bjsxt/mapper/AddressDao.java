package com.bjsxt.mapper;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.Address;

public interface AddressDao {
	/**
	 * 根据不同的条件查询某个用户的地址
	 * @param params
	 * @return
	 */
	List<Address> listUserAddresses(Map<String, Object> params);

	/**
	 * 插入用户的地址
	 * @param params
	 * 		userId:用户id
	 * 		province:省
	 * 		city:城市
	 * 		subdistrict:街道
	 * @return	插入数据库的条数
	 */
	int insertAddress(Map<String, Object> params);
	
	/**
	 * 根据地址id删除地址
	 * @param id
	 * @return	删除数据库数据的条数
	 */
	int deleteAddressById(Integer id);

	/**
	 * 批量增加用户地址
	 * @param addsMap 	value里是用户提交的地址list集合，key值为addsMap
	 * @return
	 */
	int insertAddressList(Map<String, List<Address>> addsMap);
}
