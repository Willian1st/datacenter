<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<jsp:include page="../common/header.jsp"></jsp:include>
<title>${setting.name}-${setting.description}</title>
<style>
.goods-height {
	/* 	height: 405px !important;
	max-height: 405px !important; */
	
}

.goods_item {
	padding: 0 5px;
}

.goods-img {
	height: 150px !important;
	max-width: 100% !important;
	display: block;
}

.goods-description {
	height: 330px ! important;
	overflow-y: hidden;
	background-color: #fff ! important;
}

.thumbnail {
	margin-bottom: 0px !important;
	padding: 0px !important;
}

#goodsType  span {
	cursor: pointer;
}
</style>
</head>
<jsp:include page="list.jsp"></jsp:include>
</html>