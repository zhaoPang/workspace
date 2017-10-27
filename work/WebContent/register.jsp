<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
	<span style="font-size: 20px;color: red">${error }</span>
	<form action="${pageContext.request.contextPath}/register"
		style="padding-top: 100px" method="post">
		<table cellspacing="1" border="1" align="center" width="400px"
			style="text-align: center;">
			<tr>
				<td width="100px">姓名</td>
				<td><input type="text" name="name"
					style="width: 99%; height: 30px; font-size: 20px"></td>
			</tr>
			<tr>
				<td width="100px">登录账号</td>
				<td><input type="text" name="loginName"
					style="width: 99%; height: 30px; font-size: 20px"></td>
			</tr>
			<tr>
				<td>登录密码</td>
				<td><input type="password" name="loginPswd"
					style="width: 99%; height: 30px; font-size: 20px"></td>
			</tr>
			<tr>
				<td>生日</td>
				<td><input type="text" name="birthday"
					style="width: 99%; height: 30px; font-size: 20px"></td>
			</tr>
			<tr>
				<td colspan="2" height="30px"><input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="reset" value="重置"></td>
			</tr>
		</table>


	</form>
</body>
</html>