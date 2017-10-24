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

import com.bjsxt.pojo.Address;
import com.bjsxt.pojo.User;
import com.bjsxt.service.UserService;
import com.bjsxt.service.impl.UserServiceImpl;
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
			System.out.println(url);
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
			int page = 1;
			try{
				page = Integer.parseInt(indexStr);
			}catch(NumberFormatException e){
			}
			int size = 5;
			try{
				size = Integer.parseInt(sizeStr);
			}catch(NumberFormatException e){
			}
			
			Map<String,Object> params  = userService.listUsersByPage(page,size);
			Gson gson = new Gson();
			String json = gson.toJson(params);
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
	
	private void listUserAddresses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		try {
			String userId = request.getParameter("userId");
			 List<Address> listUserAddresses = userService.listUserAddressesById(userId);
			 request.setAttribute("addressList", listUserAddresses);
//			 request.setAttribute("userId", userId);
			 request.getRequestDispatcher("/listUsersAddress.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
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
