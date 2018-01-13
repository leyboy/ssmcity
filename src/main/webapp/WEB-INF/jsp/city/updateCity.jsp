<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
<title>update city</title>
</head>
<body>
	<h1>update city</h1>
	<div class="container">
		<div class="row">
			<form action="${ctx}/city/updateCity.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="flag" value="2">
				<input type="hidden" name="id" value="${requestScope.city.id}">
				<input type="text" name="name" value="${requestScope.city.name }">
				<input type="text" name="countryCode" value="${requestScope.city.countryCode}">
				<input type="text" name="district" value="${requestScope.city.district}">
				<input type="text" name="population" value="${requestScope.city.population}">
				<input type="file" name="imageFile">
				<button class="btn btn-success">更新</button>
			</form>
		</div>
	</div>
</body>
</html>