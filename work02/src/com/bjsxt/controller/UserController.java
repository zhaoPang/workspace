package com.bjsxt.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;
import com.bjsxt.util.PageBean;
import com.bjsxt.util.ServletUtil;
import com.google.gson.Gson;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String methodName = ServletUtil.getMethodName(url);
		if (null == methodName) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return;
		}
		try {
			Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		try {
			String indexStr = request.getParameter("index");
			String sizeStr = request.getParameter("size");
			PageBean<User> userPage = new PageBean<>();
			try{
				int index = Integer.parseInt(indexStr);
				userPage.setIndex(index);
			}catch(NumberFormatException e){
				userPage.setIndex(0);
			}
			try{
				int size = Integer.parseInt(sizeStr);
				userPage.setSize(size);
			}catch(NumberFormatException e){
				userPage.setSize(0);
			}
			
			Map<String,Object> params = new HashMap<>();
			params.put("start", userPage.getStartRow());
			params.put("rows", userPage.getSize());
			List<User> users = userService.listUsersByPage(params);
			userPage.setList(users);
			
			int count = userService.getCount();
			userPage.setTotalCount(count);
			Gson gson = new Gson();
			String json = gson.toJson(userPage);
			response.setContentType("application/Json;charSet=utf-8");
			response.getWriter().print(json);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error");
		}
	}

	private void toMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/main.jsp");
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		try {
			String name = request.getParameter("name");
			String loginName = request.getParameter("loginName");
			String loginPswd = request.getParameter("loginPswd");
			String birthdayStr = request.getParameter("birthday");
			User user = new User();
			user.setName(name);
			user.setLoginName(loginName);
			user.setLoginPswd(loginPswd);
			Date birthday = null;
			try{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				birthday = simpleDateFormat.parse(birthdayStr);
			}catch(ParseException e){
				birthday = null;
			}
			user.setBirthday(birthday);
			User register = userService.register(user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} catch (Exception e) {
			request.setAttribute("error", "注册失败");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		try {
			String loginName = request.getParameter("loginName");
			String LoginPswd = request.getParameter("loginPswd");
			User loginUser = userService.login(loginName, LoginPswd);
			response.sendRedirect(request.getContextPath() + "/user/toMain.action");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
	
}
