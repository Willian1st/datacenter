<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="cn">
<head>
<meta charset="UTF-8">
<title>500</title>
</head>
<body>
	<div class="content">
		<div class="iconfont error-code">&#xe655;</div>
		<div class="error-text">对不起,服务器暂时无法访问请稍后再试</div>
		<div class="row">
			<h3 class="col-md-12">
				<div>信息描述如下：</div>
			</h3>
			<br>
			<div>错误状态代码是：${pageContext.errorData.statusCode}</div>
			<div>错误发生页面是：${pageContext.errorData.requestURI}</div>
			<div>错误信息：${pageContext.exception}</div>
			<div>
				错误堆栈信息：<br />
				<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
					<p>${trace}</p>
				</c:forEach>
			</div>
		</div>
		<a href="#" class="back-home">返回首页</a>
	</div>
</body>
</html>