package com.bjsxt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bjsxt.pojo.Address;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;
import com.bjsxt.util.ServletUtil;
import com.google.gson.Gson;

import oracle.net.aso.a;

@SuppressWarnings("unused")

@Controller
public class UserController {
	private static final long serialVersionUID = 1L;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(value = "/listUsers.do", produces = "application/json;charset=UTF-8")
	@ResponseBody
	private String listUsers(@RequestParam(value = "index", defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size) throws IOException {
		try {
			Map<String, Object> params = userService.listUsersByPage(page, size);
			Gson gson = new Gson();
			String json = gson.toJson(params);
			System.out.println(json);
			return json;
		} catch (Exception e) {
			return "error";
		}
	}

	@RequestMapping("/toMain.do")
	private String toMain() {
		return "/main.jsp";
	}

	@RequestMapping("/register.do")
	private ModelAndView register(User user) {
		ModelAndView mv = new ModelAndView();
		try {
			if (null == user.getLoginName() || null == user.getLoginPswd() || user.getLoginName().trim().equals("")
					|| user.getLoginPswd().trim().equals("")) {
				throw new RuntimeException();
			}
			User register = userService.register(user);
			mv.setViewName("/index.jsp");
		} catch (Exception e) {
			mv.addObject("error", "注册失败");
			mv.setViewName("/register.jsp");
		}
		return mv;

	}

	@RequestMapping("/listUserAddresses.do")
	private ModelAndView listUserAddresses(String userId) {
		ModelAndView mv = new ModelAndView();
		try {
			List<Address> listUserAddresses = userService.listUserAddressesById(userId);
			mv.addObject("addressList", listUserAddresses);
			mv.setViewName("/listUsersAddress.jsp");
			System.out.println(listUserAddresses);
		} catch (Exception e) {
			mv.setViewName("/index.jsp");
		}
		return mv;
	}

	@RequestMapping("/addUsersAddress.do")
	public ModelAndView addUsersAddress(String userId, String province, String city, String subdistrict) {
		ModelAndView mv = new ModelAndView();
		try {
			if (null == province || null == city || null == subdistrict || province.trim().equals("")
					|| city.trim().equals("") || subdistrict.trim().equals("")) {
				throw new RuntimeException();
			}
			this.userService.addUserAddress(userId, province, city, subdistrict);
			mv.setViewName("/listUserAddresses.do");
			mv.addObject("userId", userId);
		} catch (Exception e) {
			mv.setViewName("/404.jsp");
		}
		return mv;
	}

	@RequestMapping("/deleteAddressById.do")
	@ResponseBody
	public String deleteAddressById(int id) {
		try {
			int rows = this.userService.deleteAddressById(id);
			if (rows == 1) {
				return "success";
			} else {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@RequestMapping("/login.do")
	public String login(String loginName, String loginPswd) {
		try {
			System.out.println(loginName);
			System.out.println(loginPswd);
			User loginUser = userService.login(loginName, loginPswd);
			return "toMain.do";
		} catch (Exception e) {
			return "/index.jsp";
		}
	}

	@RequestMapping("/addAddressList")
	public ModelAndView addAddressList(Integer userId, MultipartFile addressList) {
		ModelAndView mv = new ModelAndView();
		if (null == addressList) {
			mv.setViewName("/index.jsp");
			return mv;
		}
		try {
			InputStream input = addressList.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String str = null;
			List<Address> adds = new ArrayList<>();
			while ((str = br.readLine()) != null) {
				String[] split = str.split(";");
				if (split.length == 3) {

					Address address = new Address();
					address.setId(userId);
					address.setProvince(split[0]);
					address.setCity(split[1]);
					address.setSubdistrict(split[2]);
					adds.add(address);
				}
			}
			userService.addUserAddressList(adds);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mv.setViewName("/listUserAddresses.do");
		mv.addObject("userId", userId);
		return mv;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
