<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>我的文具管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagination.js"></script>
    <script src="${pageContext.request.contextPath}/js/my.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mb_global.css">
</head>

<body class="hold-transition skin-red sidebar-mini">
<!-- .box-body -->
<div class="box-header with-border">
    <h3 class="box-title">文具列表</h3>
</div>
<div class="box-body">
    <%--新增按钮 /--%>
    <!--工具栏 数据搜索 -->
    <div class="box-tools pull-right">
        <div class="has-feedback">
            <form action="${pageContext.request.contextPath}/stationeryInfo/search" method="post">
                文具名称：<input name="name" value="${search.name}" placeholder="请输入">&nbsp&nbsp&nbsp&nbsp
                采购人：<input name="purchaser" value="${search.purchaser}" placeholder="请输入">&nbsp&nbsp&nbsp&nbsp
                供应商：<input name="supplier" value="${search.supplier}" placeholder="请输入">&nbsp&nbsp&nbsp&nbsp
                <input class="btn btn-primary" type="submit" value="搜索">
            </form>
        </div>
    </div>
    <!--工具栏 数据搜索 /-->
    <!-- 数据列表 -->
    <div class="table-box">
        <!-- 数据表格 -->
        <table id="dataList" class="table table-bordered table-striped table-hover dataTable text-center">
            <thead>
            <tr>
                <th class="sorting_asc">文具名称</th>
                <th class="sorting">规格/型号</th>
                <th class="sorting">供应商</th>
                <th class="sorting">采购人</th>
                <th class="sorting">价格</th>
                <th class="sorting">上架时间</th>
                <c:if test="${USER_SESSION.role =='ADMIN'}">
                    <th class="text-center">操作</th>
                </c:if>
            </tr>
            </thead>
            <tbody id="listBody">
            <c:forEach items="${pageResult.rows}" var="rows">
                <tr>
                    <td>${rows.name}</td>
                    <td>${rows.specification}</td>
                    <td>${rows.supplier}</td>
                    <td>${rows.purchaser}</td>
                    <td>${rows.price}</td>
                    <td>${rows.uploadTime}</td>
                    <c:if test="${USER_SESSION.role =='ADMIN'}">
                        <td class="text-center">
                            <button
                                id="addBtn"
                                type="button"
                                class="btn bg-olive btn-xs"
                                data-toggle="modal"
                                data-target="#addOrEditModal"
                                onclick="onRequisitionBtn(${rows.id},${USER_SESSION.id})"
                            > 新增领用
                            </button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!--数据表格/-->
        <%--分页插件--%>
        <div id="pagination" class="pagination"></div>
    </div>
    <!--数据列表/-->
</div>
<!-- /.box-body -->

<!-- 添加和编辑文具的模态窗口 -->
<div class="modal fade" id="addOrEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">新增领用信息</h3>
            </div>
            <div class="modal-body">
                <div style="margin-bottom: 12px;">
                    你领用过的文具有：
                    <div id="mergeData" style="margin: 12px 0 0 12px;"></div>
                </div>
                <form id="addOrEditForm">
                    <span><input type="hidden" id="aebid" name="ids"></span>
                    <table id="addOrEditTab" class="table table-bordered table-striped" width="800px">
                        <%--文具的id,不展示在页面--%>
                        <tr>
                            <td><span style="color: red">*</span>文具名称</td>
                            <td><input class="form-control" readonly placeholder="请输入名称" name="name" id="aaeb_name"></td>
                            <td><span style="color: red">*</span>规格/型号</td>
                            <td><input class="form-control" readonly placeholder="请输入规格/型号" name="specification" id="aaeb_specification"></td>
                           
                        </tr>
                        <tr>
                            <td><span style="color: red">*</span>价格</td>
                            <td><input class="form-control" readonly placeholder="请输入价格" name="price" id="aaeb_price"></td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" id="aoe" disabled onclick="addOrEdit()">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    /*分页插件展示的总页数*/
    pageargs.total = Math.ceil(${pageResult.total}/pageargs.pagesize);
    /*分页插件当前的页码*/
    pageargs.cur = ${pageNum}
    /*分页插件页码变化时将跳转到的服务器端的路径*/
    pageargs.gourl = "${gourl}"
    /*保存搜索框中的搜索条件，页码变化时携带之前的搜索条件*/
    stationeryListVO.name = "${search.name}"
    stationeryListVO.purchaser = "${search.purchaser}"
    stationeryListVO.supplier = "${search.supplier}"
    /*分页效果*/
    pagination(pageargs);
</script>
</html>