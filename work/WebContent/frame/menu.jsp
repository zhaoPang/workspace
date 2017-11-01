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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$.post("${pageContext.request.contextPath}/showMenu1.do",{},function(data){
			if("error" == data){
				alert(data)
			}else{
				var ul = $("#menuId");
				var str = "";
				console.log(data);
				for(var i=0;i<data.length;i++){
					var menu = data[i];
					str+="<li><a href='${pageContext.request.contextPath}/"+menu.location+"' target='mainFrame'>"+menu.showText+"</a>";
					if(menu.childMenu.length>0){
						for(var j=0;j<menu.childMenu.length;j++){
							var menu1 = menu.childMenu[j];
							str+=showMenu(menu1);
						}
					}
					str+="</li>";
				}
				ul.html(str);
			}
		});
	});
	
	function showMenu(menu){
		var str = "<ul>";
		str+="<li><a href='${pageContext.request.contextPath}/"+menu.location+"' target='mainFrame'>"+menu.showText+"</a>";
		if(menu.childMenu.length>0){
			for(var i=0;i<menu.childMenu.length;i++){
				var menu1 = menu.childMenu[i];
				str+=showMenu(menu1);
			}
		}
		str+="</li></ul>";
		return str;
	}	
	function creatChild(data,id){
		var ulStr = "<ul>"
		for(var i = 0;i<data.length;i++){
			var menu = data[i];
			if(menu.parentMenuId == id){
				ulStr+="<li><a href='${pageContext.request.contextPath}/"+menu.location+"' target='mainFrame'>"+menu.showText+"</a></li>";
				ulStr+=creatChild(data, menu.menuId);
			}
		}
		ulStr += "</ul>"
		return ulStr;
	}
	
</script>

</head>
<body>
	<ul id="menuId">
		
	</ul>
</body>
</html>