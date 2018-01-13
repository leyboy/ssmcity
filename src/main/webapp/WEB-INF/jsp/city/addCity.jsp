<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add city jsp</title>
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
</head>
<body>
	<h1>add city</h1>
	<div class="container">
		<div class="row">
			<form action="${ctx}/city/addCity.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="flag" value="2">
				<table class="table" align="center">
					<tr>
						<td><label>城市名</label><strong>:</strong> <input type="text"
							name="name" ></td>
					</tr>
					<tr>
						<td><label>区域码</label><strong>:</strong> <input type="text"
							name="countryCode"></td>
					</tr>
					<tr>
						<td><label>所在地</label><strong>:</strong> <input type="text"
							name="district"></td>
					</tr>
					<tr>
						<td><label>人口数</label><strong>:</strong> <input type="text"
							name="population"></td>
					</tr>
					<tr>
						<td><label>图片</label><strong>:</strong><input type="file" name="imageFile">
					</tr>
				</table>
				<div align="left">
					<button class="btn btn-success">添加</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>