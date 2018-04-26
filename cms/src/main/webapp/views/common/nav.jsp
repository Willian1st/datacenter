<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
body {
	padding-top: 70px;
}
</style>
<nav class="navbar  navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
        aria-expanded="false"
      >
        <span class="sr-only">导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"
        ></span>
      </button>
      <a class="navbar-brand" href="#"> <img alt="wg" class="img-responsive img-circle"
        style="width: 30px; position: relative; top: -5px;" src="/cms/img/logo.png"
      >
      </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/cms">首页</a></li>
        <c:if test="${manage eq true }">
          <li><a href="/cms/goods/manage">商品管理</a></li>
        </c:if>
        <!-- <li><a href="/cms/goods/manage">商品管理</a></li> -->
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
          aria-expanded="false"
        >管理中心 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/cms/signin">管理员登录</a></li>
            <c:if test="${manage eq true }">
              <li role="separator" class="divider"></li>
              <li><a href="/cms/goods/manage">商品管理</a></li>
              <li><a href="/cms/goods/add">添加</a></li>
              <li><a href="/cms/setting">配置</a></li>
            </c:if>
          </ul></li>
        <c:if test="${manage eq true }">
          <li><a href="/cms/signout">退出</a></li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
