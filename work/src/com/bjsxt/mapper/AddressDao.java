package com.bjsxt.mapper;

import java.util.List;
import java.util.Map;

import com.bjsxt.pojo.Address;

public interface AddressDao {
	List<Address> listUserAddresses(Map<String, Object> params);
}
