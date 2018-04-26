<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body ng-app="goodsApp" ng-controller="goodsIndex">
  <c:if test="${manage eq true }">
    <style>
.thumbnail  img {
	cursor: pointer;
}

.good-remove {
	z-index: 999;
	padding: 5px;
	margin-bottom: -28px;
	cursor: pointer;
}
</style>
  </c:if>
  </style>
  <jsp:include page="../common/nav.jsp"></jsp:include>
  <div class="container-fluid">
    <div class="row">
      <form class="form-horizontal col-md-12" ng-submit="goodsIndex.getList(1)">
        <div class="form-group">
          <div class="col-md-2"></div>
          <div class="col-md-6 col-xs-9">
            <div class="input-group">
              <span class="input-group-addon"><span class="glyphicon glyphicon-gift"></span></span> <input type="text"
                name="search" na-model="search" class="form-control" placeholder="商品名称"
              >
            </div>
          </div>
          <input type="hidden" name="order"><input type="hidden" name="type">
          <div class="col-md-2 col-xs-3">
            <button class="btn btn-default pull-right" type="submit">
              <span class="glyphicon glyphicon-search"></span> 搜索
            </button>
          </div>
          <div class="col-md-1">
            <c:if test="${manage eq true }">
              <%-- <a class="btn btn-default pull-right" href="/cms/goods/add"><span class="glyphicon glyphicon-plus">
              </span> 添加</a>--%>
              <input type="hidden" name="manage" value="${manage }">
            </c:if>
          </div>
        </div>
      </form>
    </div>
    <div class="row hidden-sm hidden-xs">
      <div class="col-md-offset-2 col-md-8" id="goodsType">
        <span class="label label-default">全部</span>
        <c:forEach items="${goodsType }" var="item">
          <span class="label label-default" type="${item.key}">${item.value}</span>
        </c:forEach>
      </div>
    </div>
    <div class="row">
      <hr>
    </div>
    <div class="row">
      <div class="col-md-offset-1 col-md-10">
        <ul class="nav nav-tabs" role="tablist" id="goodsTab">
          <li role="presentation" class=" hidden " order=""><a href="#home" aria-controls="home" role="tab"
            data-toggle="tab"
          >综合</a></li>
          <li role="presentation" order="sjcjsj" class="active"><a href="#profile" aria-controls="profile"
            role="tab" data-toggle="tab"
          >最新</a></li>
          <li role="presentation" order="salenum"><a href="#messages" aria-controls="messages" role="tab"
            data-toggle="tab"
          >销量</a></li>
          <li role="presentation" order="getnum"><a href="#settings" aria-controls="settings" role="tab"
            data-toggle="tab"
          >领券量</a></li>
        </ul>
        <!--列表样式  -->
        <div class="tab-content ">
          <div class="container-fluid text-center hidden" id="noGoods">
            <h5>
              <span class="glyphicon glyphicon-magnet"></span> <label>没有找到与“<strong><span id="typeInfo"></span><span
                  id="noResult"
                ></span></strong>”相关的宝贝
              </label>
            </h5>
          </div>
          <div class="col-xs-6 col-sm-4 col-md-2 goods_item" ng-repeat="value in goodsIndex.data">
            <div class="">
              <div class="caption goods-height">
                <p class="text-primary">
                  <c:if test="${manage eq true }">
                    <span goodsid="{{value.id}}" class="glyphicon glyphicon-remove pull-right good-remove"></span>
                  </c:if>
                </p>
                <div class=" thumbnail">
                  <img goodsid="{{value.id}}" class="goods-img" src="{{value.picture}}" alt="暂无图片">
                </div>
                <!-- <p class="">
                  销量： <span ng-bind="value.salenum"></span>
                </p>
                <p class="">
                  领券量： <span ng-bind="value.getnum"></span>
                </p> -->
                <p class="hidden">
                  原价： <span ng-bind="value.originalprice"></span>
                </p>
                <p class="hidden">
                  优惠券： <span ng-bind="value.coupon"></span>
                </p>
                <p class="hidden">
                  券后： 【<span ng-bind="value.price"></span> 元】
                </p>
                <p class="hidden">
                  商品名称： <span ng-bind="value.name"></span>
                </p>
                <p ng-click="goodsIndex.openApp(value.description)">
                  <!-- 商品描述： -->
                  <textarea readonly ng-bind="value.description" class="form-control goods-description"></textarea>
                </p>
                <p class="hidden">
                  商品状态：<span ng-bind="value.flag"></span>
                </p>
                <p class="text-danger hidden">
                  更新时间：<span ng-bind="value.sjgxsj | date:'yyyy-MM-dd hh:mm:ss'"></span>
                </p>
                <p class="text-success hidden">
                  创建时间： <span ng-bind="value.sjcjsj | date:'yyyy-MM-dd hh:mm:ss'"></span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="/cms/js/goods/index.js"></script>
</body>

