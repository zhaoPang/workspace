package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.pojo.Menu;

public interface MenuDao {
	/**
	 * 以树的形式返回所有的菜单
	 * 菜单的根放在list集合中
	 * 每个menu上都有一个childMenu属性，存放着孩子菜单
	 * @return 返回菜单的根菜单列表
	 */
	public List<Menu> findMenu();
	
}
