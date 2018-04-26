<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body ng-app="goodsApp" ng-controller="goodsAdd">
  <jsp:include page="../common/nav.jsp"></jsp:include>
  <div class="row">
    <div class="col-md-offset-2 col-md-8">
      <form id="form" class="form-horizontal col-md-10" enctype="multipart/form-data">
        <div class="form-group">
          <label class="col-sm-2 control-label">商品图片</label>
          <div class="col-sm-10">
            <div class="col-md-offset-4 col-md-4 thumbnail">
              <img class="goods-img" alt="暂无图片" id="imgSrc" src="${good.picture }">
            </div>
            <input type="file" name="file" class="form-control" id="image_input" placeholder="商品图片">
          </div>
        </div>
      </form>
      <form id="goods" class="form-horizontal col-md-10" ng-submit="goodsAdd.add()">
        <input type="hidden" class="form-control" name="id" value="${good.id }">
        <div class="form-group">
          <!-- <label class="col-sm-2 control-label">商品图片</label>
          <div class="col-sm-10">
            <div class="col-md-offset-4 col-md-4 thumbnail">
              <img class="goods-img" src="" alt="暂无图片" id="imgSrc">
            </div>
            <input type="file" class="form-control" id="picture" placeholder="商品图片">
          </div>
          <input type="hidden" class="form-control" name="picture" id="base64"> -->
          <input type="hidden" class="form-control" name="picture" value="${good.picture }">
        </div>
        <div class="form-group hidden">
          <label class="col-sm-2 control-label">商品名称</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" name="name" placeholder="商品名称" value="${good.name }">
          </div>
        </div>
        <div class="form-group hidden">
          <label class="col-sm-2  control-label">商品原价</label>
          <div class="col-sm-4 ">
            <input type="number" class="form-control" name="originalprice" placeholder="元"
              value="${good.originalprice }"
            >
          </div>
          <label class="col-sm-2  control-label">优惠券</label>
          <div class="col-sm-4 ">
            <input type="number" class="form-control" name="coupon" placeholder="元" value="${good.coupon }">
          </div>
        </div>
        <div class="form-group hidden">
          <label class="col-sm-2 control-label">商品价格</label>
          <div class="col-sm-4">
            <input type="number" class="form-control" name="price" placeholder="元" value="${good.price }">
          </div>
          <label class="col-sm-2 control-label">商品状态</label>
          <div class="col-sm-4">
            <select name="flag" class="form-control">
              <option value="01">有效</option>
              <option value="02">无效</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">商品描述</label>
          <div class="col-sm-10">
            <!-- <script id="description" name="description" type="text/plain"></script> -->
            <textarea class="form-control" name="description" rows="5" cols="">${good.description }</textarea>
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label">商品分类</label>
          <div class="col-sm-10">
            <select name="type" class="form-control">
              <option value="">请选择</option>
              <c:forEach items="${goodsType }" var="item">
                <option value="${item.key}" <c:if test="${item.key eq good.type }">selected</c:if>>${item.value}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="form-group ">
          <label class="col-sm-2  control-label">销量</label>
          <div class="col-sm-4 ">
            <input type="number" class="form-control" name="salenum" placeholder="销量" value="${good.salenum }">
          </div>
          <label class="col-sm-2  control-label">领券量</label>
          <div class="col-sm-4 ">
            <input type="number" class="form-control" name="getnum" placeholder="领券量" value="${good.getnum }">
          </div>
        </div>
        <!-- <div class="form-group">
        <label class="col-sm-2 control-label">优惠开始</label>
        <div class="col-sm-4">
          <input type="date" class="form-control" name="discountStartTime" placeholder="商品优惠开始时间">
        </div>
        <label class="col-sm-2 control-label">优惠结束</label>
        <div class="col-sm-4">
          <input type="date" class="form-control" name="discountEndTime" placeholder="商品优惠结束时间">
        </div>
      </div> -->
        <div class="form-group">
          <div class="col-md-offset-4 col-md-4">
            <button class="btn  btn-primary" type="submit" id="save">保存</button>
            <button class="btn btn-default pull-right" type="reset" ng-click="goodsAdd.reset()">清空</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <script src="/cms/js/goods/manage.js"></script>
</body>
