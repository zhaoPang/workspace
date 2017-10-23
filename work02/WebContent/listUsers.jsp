<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		myAjax("${pageContext.request.contextPath}/user/listUsers.action", {});

	});
	//根据条件查询
	function searchCounter() {
		var size = $("#size").val();
		myAjax("${pageContext.request.contextPath}/user/listUsers.action", {
			"size" : size
		});

	}

	//AJAX获取数据
	function myAjax(_url, _date) {
		$
				.ajax({
					type : "post",
					url : _url,
					data : _date,
					async : true,
					dataType : "text",
					success : function(result) {
						if (result == "error") {
							location.href = "${pageContext.request.contextPath}/404.jsp";
							return;
						}
						eval("var userPage = " + result);
						showTableView(userPage);
					}
				});
	}

	//翻页
	function paging(index) {
		var totalPageCount = $("#totalPageCount").text();
		if (index<=0 || index>totalPageCount) {
			return;
		}
		var size = $("#size").val();
		myAjax("${pageContext.request.contextPath}/user/listUsers.action", {
			"size" : size,
			"index" : index
		});
	}
	function showTableView(userPage) {
		if (userPage.numbers == undefined) {
			alert("没有查询结果");
			return;
		}

		$("#table").html("");
		$("#paginList").html("");
		//设置总记录数
		var totalCount = $("#totalCount");
		totalCount.html(userPage.totalCount);

		//记录当前页数
		var index = $("#index");
		index.html(userPage.index);

		//设置总页数
		var totalPageCount = $("#totalPageCount");
		totalPageCount.html(userPage.totalPageCount);

		//设置页数表
		var ul = $("#paginList");
		var li = $("<li class='paginItem'><a href='javascript:paging("
				+ (userPage.index - 1)
				+ ");'><span class='pagepre'>上一页</span></a></li>");
		ul.append(li);
		for (var i = 0; i < userPage.numbers.length; i++) {
			var li = $("<li class='paginItem' id=page_"+userPage.numbers[i]+"><a href='javascript:paging("
					+ userPage.numbers[i]
					+ ");' >"
					+ userPage.numbers[i]
					+ "</a></li>");
			ul.append(li);
		}
		var li = $("<li class='paginItem'><a href='javascript:paging("
				+ (userPage.index + 1)
				+ ");'><span class='pagenxt'>下一页</span></a></li>");
		ul.append(li);

		//给当前页加上标注
		$("#page_" + userPage.index).css("background-color", "gainsboro");

		//显示数据
		var table = $("#table");
		for (var i = 0; i < userPage.list.length; i++) {

			var user = userPage.list[i];
			var tr = $("<tr id=tr_"+user.id+" style='text-align: center; border-bottom: solid 1px gray'></tr>");
			table.append(tr);

			var id = $("<td  align='center'  >" + (user.id) + "</td>");
			tr.append(id);

			var name = $("<td  align='center'  >" + user.name + "</td>");
			tr.append(name);

			var loginName = $("<td  align='center'  >" + user.loginName
					+ "</td>");
			tr.append(loginName);

			var birthday = $("<td  align='center'  >" + user.birthday + "</td>");
			tr.append(birthday);
			console.log(new Date(user.birthday).toLocaleDateString);
			var last_login_time = $("<td  align='center'  >"
					+ user.lastLoginTime + "</td>");
			tr.append(last_login_time);

			var login_hits = $("<td  align='center'  >" + user.loginHits
					+ "</td>");
			tr.append(login_hits);
		}

	}
</script>

</head>
<body>

	<div class="rightinfo">
		<ul class="prosearch">

			<li><a> <i>每页</i> <select name="size" id="size"
					style="border: 1px solid black; height: 32px; width: 50px"
					onchange="searchCounter()">
						<option value="5" id="size_5" selected="selected">5</option>
						<option value="10" id="size_10">10</option>
						<option value="15" id="size_15">15</option>
						<option value="20" id="size_20">20</option>
				</select>
			</a> <input name="" type="button" class="sure" value="查询"
				onclick="searchCounter()" /></li>

		</ul>


		<table class="tablelist" border="solid 1px black" bgcolor="#F8F8FF">
			<tr>
				<th>id</th>
				<th>name</th>
				<th>login_name</th>
				<th>birthday</th>
				<th>last_login_time</th>
				<th>login_hits</th>
			</tr>
			<tbody id="table">

			</tbody>

		</table>


		<div class="pagin">
			<div class="message">
				共<i class="blue" id="totalCount"></i>条记录，总<i class="blue"
					id="totalPageCount"></i>页，当前显示第&nbsp;<i class="blue" id="index">2&nbsp;</i>页
			</div>

			<ul class="paginList" id="paginList">


			</ul>
		</div>

		<div id="div1">
		</div>
</body>
</html>