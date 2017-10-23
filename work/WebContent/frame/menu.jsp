<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <ul>
		<li>
			<a href="${pageContext.request.contextPath}/listUsers.jsp"target="mainFrame">用户列表</a>
			
		</li>
	</ul> --%>
	
	<ul>
		<li>	
			<a href="${pageContext.request.contextPath}/listUsers.jsp" target="mainFrame">用户列表</a>
		</li>
	</ul>
</body>
</html>