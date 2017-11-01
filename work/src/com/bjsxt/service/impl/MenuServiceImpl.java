package com.bjsxt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bjsxt.mapper.MenuDao;
import com.bjsxt.pojo.Menu;
import com.bjsxt.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	@Qualifier("menuDao")
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findMenu() {
		try{
			List<Menu> MenuList = menuDao.findMenu();
			return MenuList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

}
