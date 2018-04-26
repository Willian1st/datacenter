<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<jsp:include page="../common/header.jsp"></jsp:include>
<script type="text/javascript" charset="utf-8" src="/cms/js/jquery.form.js"></script>
<style type="text/css">
.logo-img {
	height: 150px !important;
	width: auto;
	display: block;
}
</style>
<title>设置</title>
</head>
<body ng-app="settingApp" ng-controller="setting">
  <jsp:include page="../common/nav.jsp"></jsp:include>
  <div class="row">
    <div class="col-md-offset-2 col-md-8">
      <form id="form" class="form-horizontal col-md-10" enctype="multipart/form-data">
        <div class="form-group">
          <label class="col-sm-2 control-label">网站图片</label>
          <div class="col-sm-10">
            <div class="col-md-offset-4 col-md-4 thumbnail">
              <img class="logo-img" alt="暂无图片" id="imgSrc" src="${setting.picture }">
            </div>
            <input type="file" name="file" class="form-control" id="imageInput" placeholder="网站图片">
          </div>
        </div>
      </form>
      <form id="setting" class="form-horizontal col-md-10" ng-submit="setting.manage()">
        <input type="hidden" class="form-control" name="id" value="${setting.id }">
        <div class="form-group ">
          <label class="col-sm-2 control-label">网站名称</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="name" placeholder="网站名称" value="${setting.name }">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">网站描述</label>
          <div class="col-sm-10">
            <!-- <script id="description" name="description" type="text/plain"></script> -->
            <textarea class="form-control" name="description" rows="5" cols="">${setting.description }</textarea>
          </div>
        </div>
        <div class="form-group">
          <div class="col-md-offset-4 col-md-4">
            <input type="hidden" class="form-control" name="picture" value="${setting.picture }">
            <button class="btn  btn-primary" type="submit" id="save">保存</button>
            <button class="btn btn-default pull-right" type="reset" ng-click="setting.reset()">清空</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <script src="/cms/js/app.js"></script>
  <script src="/cms/js/setting/index.js"></script>
</body>

</html>