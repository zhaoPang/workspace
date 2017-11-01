package com.bjsxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.pojo.Menu;
import com.bjsxt.service.MenuService;
import com.google.gson.Gson;

@Controller
public class MenuController {
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;
	@RequestMapping(value="/showMenu1.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String showMenu(){
		Gson gson = new Gson();
		System.out.println("MenuController.showMenu()");
		try{
			List<Menu> menuList = menuService.findMenu();
			String json = gson.toJson(menuList);
			System.out.println(json);
			return json;
		}catch(RuntimeException re){
			return "error";
		}
	}
}
