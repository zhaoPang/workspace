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
	
	function deleteAddress(id) {
		alert(id);
	}
	function AddAddress(userId) {
		alert(userId);
	}
	
</script>
</head>
<body>
	<form action="">
		<table border="solid 1px black" width="500px">
			<thead>
				<tr>添加地址</tr>
			</thead>
			<tr>
				<td width="100px" height="30px">省</td>
				<td height="30px"><input type="text" name="province"
					id="provinceId" style="width: 99%; height: 25px" /></td>
			</tr>
			<tr>
				<td>市</td>
				<td><input type="text" name="city" id="cityId"
					style="width: 99%; height: 25px" /></td>
			</tr>
			<tr>
				<td>街道</td>
				<td><input type="text" name="subdistrict" id="subdistrictId"
					style="width: 99%; height: 25px" /></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="button" value="添加" onclick="AddAddress(${param.userId})" /> </td>
			</tr>
		</table>
	</form>

	<table border="solid 1px black" width="500px">
		<tr>
			<th>省</th>
			<th>市</th>
			<th>街道</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${addressList}" var="address">
			<tr>
				<td>${address.province}</td>
				<td>${address.city}</td>
				<td>${address.subdistrict}</td>
				<td><input type="button" value="删除"
					onclick="deleteAddress(${address.id})" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>