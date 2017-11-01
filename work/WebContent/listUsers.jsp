<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		myAjax("${pageContext.request.contextPath}/listUsers.do", {});

	});
	//根据条件查询
	function searchCounter() {
		var size = $("#size").val();
		myAjax("${pageContext.request.contextPath}/listUsers.do", {
			"size" : size
		});

	}
	Date.prototype.formatDate = function(type){
		var year = this.getFullYear();
		var month = this.getMonth();
		var day = this.getDay();
		month = ""+month;
		if(month.length == 1){
			month = "0"+month;
		}
		day = ""+day;
		if(day.length==1){
			day = "0"+day;
		}
		var time = ""+year+"/"+month+"/"+day;
		if(type == "date"){
			return time;
		}else if(type == "timestamp"){
			var hour = this.getHours();
			hour = ""+hour;
			if(hour.length == 1){
				hour = "0"+hour;
			}
			var minter = this.getMinutes();
			minter = ""+minter;
			if(minter.length == 1){
				minter = "0"+minter;
			}
			var second = this.getSeconds();
			second = ""+second
			if(second.length == 1){
				second = "0"+second;
			}
			time = time+" "+hour+":"+minter+":"+second;
			return time;
		}else{
			return "";
		}		
	}

	//AJAX获取数据
	function myAjax(_url, _date) {
		$.ajax({
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
				eval("result="+result);
				console.log(result);
				showTableView(result);
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
		myAjax("${pageContext.request.contextPath}/listUsers.do", {
			"size" : size,
			"index" : index
		});
	}
	function showTableView(result) {

		$("#table").html("");
		$("#paginList").html("");
		//设置总记录数
		var totalCount = $("#totalCount");
		totalCount.html(result.count);

		//记录当前页数
		var index = $("#index");
		index.html(result.page);

		//设置总页数
		var totalPageCount = $("#totalPageCount");
		totalPageCount.html(result.pages);
		//设置页数表
		$("#pageup").attr("href","javascript:paging("+ (result.page-1)+ ");"); 
		$("#pagedown").attr("href","javascript:paging("+ (result.page+1)+ ");"); 
		//给当前页加上标注
		$("#page_" + result.page).css("background-color", "gainsboro");

		//显示数据
		
		
		var table = $("#table");
		for (var i = 0; i < result.items.length; i++) {

			var user = result.items[i];
			var tr = $("<tr id=tr_"+user.id+" style='text-align: center; border-bottom: solid 1px gray'></tr>");
			table.append(tr);

			var id = $("<td  align='center'  >" + (user.id) + "</td>");
			tr.append(id);

			var name = $("<td  align='center'  >"+
			"<a href='${pageContext.request.contextPath}/listUserAddresses.do?userId="+(user.id)+"'>" + user.name + "</a>"+
			"</td>");
			tr.append(name);

			var loginName = $("<td  align='center'  >" + user.loginName
					+ "</td>");
			tr.append(loginName);

			var birthday = $("<td  align='center'  >" + new Date(user.birthday).formatDate("date") + "</td>");
			tr.append(birthday);
			var last_login_time = $("<td  align='center'  >"
					+ new Date(user.lastLoginTime).formatDate("timestamp")  + "</td>");
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
					<a id="pageup">上一页</a><a id="pagedown">下一页</a>
			</div>
		</div>
		<div id="div1">
		</div>
</body>
</html>