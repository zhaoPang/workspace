<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
</head>
<script type="text/javascript">
	function register() {
		location.href = "${pageContext.request.contextPath}/register.jsp";
	}
	function isok() {
		var arr = [1,2,3,4,5];
		return arr;
	}
</script>
<body>

<form action="${pageContext.request.contextPath}/user/login.action" style="padding-top: 100px" method="post">
	<table cellspacing="1" border="1" align="center" width="400px" style="text-align: center;">
		<tr>
			<td width="100px">登录账号</td>
			<td><input type="text" name="loginName" style="width: 99%;height: 30px;font-size: 20px" ></td>
		</tr>
		<tr>
			<td>登录密码</td>
			<td><input type="password" name="loginPswd" style="width: 99%;height: 30px;font-size: 20px"></td>
		</tr>
		<tr>
			<td colspan="2" height="30px">
				<input type="submit" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="register()" value="注册">
			</td>
		</tr>
	</table>
	
	
</form>

</body>
</html>