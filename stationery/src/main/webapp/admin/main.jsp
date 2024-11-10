<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>文具领用管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mb-main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mb_global.css">

    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript">
        function SetIFrameHeight() {
            var iframeid = document.getElementById("iframe");
            if (document.getElementById) {
                /*设置 内容展示区的高度等于页面可视区的高度*/
                iframeid.height = document.documentElement.clientHeight;
            }
        }
    </script>
</head>

<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">
    <!-- 页面头部 -->
    <header class="main-header">
        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/admin/main.jsp" class="logo">
            <span class="logo-lg"><b>文具领用管理系统</b></span>
        </a>
        <!-- 头部导航 -->
        <nav class="navbar navbar-static-top">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a>
                            <img src="${pageContext.request.contextPath}/img/user2.jpg" class="user-image"
                                 alt="User Image">
                            <span class="hidden-xs">${USER_SESSION.name}</span>
                        </a>
                    </li>
                    <li class="dropdown user user-menu">
                        <a href="${pageContext.request.contextPath}/user/logout">
                            <span class="hidden-xs">注销</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                
                <li >
                    <a href="${pageContext.request.contextPath}/stationeryInfo/search" target="iframe">
                        <i class="fa fa-circle-o"></i>文具列表
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/stationeryRequisition/search" target="iframe">
                        <i class="fa fa-circle-o"></i>领用信息列表
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/stationeryRequisition/searchBorrowed" target="iframe">
                        <i class="fa fa-circle-o"></i>当前领用列表
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/StationerySearchRecord/searchRecords" target="iframe">
                        <i class="fa fa-circle-o"></i>领用记录
                    </a>
                </li>
                <!-- 员工管理 -->
                <li id="admin-login">
                    <a href="${pageContext.request.contextPath}/user/search" target="iframe">
                        <i class="fa fa-circle-o"></i>员工管理
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- 导航侧栏 /-->
    <!-- 内容展示区域 -->
    <div class="content-wrapper">
        <iframe width="100%" id="iframe" name="iframe" onload="SetIFrameHeight()"
                frameborder="0" src="${pageContext.request.contextPath}/stationeryInfo/search"></iframe>
    </div>
</div>
</body>
</html>