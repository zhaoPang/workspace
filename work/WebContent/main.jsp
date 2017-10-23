<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<frameset rows="59,*" cols="*" frameborder="yes" border="1" framespacing="1">
		<frame src="${pageContext.request.contextPath}/frame/header.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="213,*" frameborder="yes" border="1" framespacing="1">
			<frame src="${pageContext.request.contextPath}/frame/menu.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="${pageContext.request.contextPath}/frame/center.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
		</frameset>
	</frameset>
</html>