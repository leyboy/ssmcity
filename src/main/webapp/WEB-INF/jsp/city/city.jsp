<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>city jsp</title>
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
</head>
<body>
	<!-- 搜索 -->
	<div align="center">
		<h2>搜索城市</h2>
	</div>
	<div class="container">
		<form action="${ctx}/city/selectCity.do" method="get">
			<div class="row">
				<div class="col-xs-2">
					<strong>城市名:</strong><input type="text" name="name"
						class="form-control">
				</div>
				<div class="col-xs-2">
					<strong>区域码:</strong><input type="text" name="countryCode"
						class="form-control">
				</div>
				<div class="col-xs-2">
					<strong>人口数:</strong><input type="text" name="population"
						class="form-control">
				</div>
				<div class="col-xs-2">
					<strong>搜索:</strong> <input type="submit" value="搜索"
						class="form-control btn btn-success">
				</div>
			</div>
		</form>
		<div class="row">
			<!-- 添加城市 -->
			<div class="col-xs-2">
				<strong>添加城市:</strong> <input type="button"
					class="form-control btn btn-success" value="添加城市"
					onclick="javascrtpt:window.location.href='${ctx }/city/addCity.do?flag=1'">
			</div>
		</div>
	</div>

	<!-- 数据展示区 -->
	<div class="container">
		<table class="table table-bordered table-hover">
			<tr>
				<th>城市名</th>
				<th>城市区号</th>
				<th>城市所在地</th>
				<th>人口数</th>
				<th>图片名</th>
				<th>修改|删除</th>
			</tr>
			<tbody>
				<c:forEach items="${requestScope.cities}" var="city" step="1">
					<tr class="info">
						<td>${city.name}</td>
						<td>${city.countryCode}</td>
						<td>${city.district}</td>
						<td>${city.population}</td>
						<td><img src="${ctx}/upload/${city.imagePath}" width="40"
							height="30" alt="#"></td>
						<td>
							<button type="button" class="btn btn-success"
								onclick="
									javascrtpt:window.location.href='${ctx }/city/updateCity.do?flag=1&cityId=${city.id}'">修改</button>
							<button type="button" class="btn btn-danger"
								onclick="
									javascrtpt:window.location.href='${ctx }/city/removeCity.do?cityId=${city.id}'">删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<div align="center">
			<!-- 分页链接 -->
			<!-- 不要使用 city/selectCity.do?pageNum=xxx,应该使用全路径-->
			<a
				href='${ctx }/city/selectCity.do?pageNum=1&name=${requestScope.target.name}
						&countryCode=${requestScope.target.countryCode}&population=${requestScope.target.population}'><strong>首页</strong></a>
			<c:if test="${requestScope.page.hasPreviousPage}">
				<a
					href='${ctx }/city/selectCity.do?pageNum=${requestScope.page.prePage}&name=${requestScope.target.name}
						&countryCode=${requestScope.target.countryCode}&population=${requestScope.target.population}'><strong>上一页</strong></a>
			</c:if>
			<c:if test="${requestScope.page.hasNextPage}">
				<a
					href='${ctx }/city/selectCity.do?pageNum=${requestScope.page.nextPage}&name=${requestScope.target.name}
						&countryCode=${requestScope.target.countryCode}&population=${requestScope.target.population}'><strong>下一页</strong></a>
			</c:if>
			<a
				href='${ctx }/city/selectCity.do?pageNum=${requestScope.page.pages}&name=${requestScope.target.name}
						&countryCode=${requestScope.target.countryCode}&population=${requestScope.target.population}'><strong>尾页</strong></a>
		</div>
	</div>
	<br>
	<div align="center" class="container">
		<a href="${ctx}/city/exportExcel.do"
			class="btn btn-primary btn-lg active" role="button">城市EXCEL</a>
	</div>
	<div align="center">
		<!-- 分页链接 -->
		<!-- 不要使用 city/selectCity.do?pageNum=xxx,应该使用全路径-->
		<a href='${ctx }/city/cityJson.do'><strong>首页</strong></a>
	</div>
</body>
</html>