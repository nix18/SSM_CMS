<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/6
  Time: 下午 6:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css">
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap4.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css" />
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
</head>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" style="background-color:rgba(10,10,10,0.8);">
    <!-- Brand -->
    <a class="navbar-brand" href="/"><img src="/pic/logo.png" height="32px" width="32px"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <!-- Links -->
    <ul class="navbar-nav  mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/">首页</a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">分类</a>
            <div class="dropdown-menu">
                <c:forEach var="tag" items="${Tags}">
                    <a class="dropdown-item" href="/article/Tag/${tag}/1">${tag}</a>
                </c:forEach>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/about">关于</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/article/top10/1">Top10</a>
        </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="/article/search/1" method="get" target="_blank">
        <input name="search" class="form-control mr-sm-2" type="text" placeholder="文章搜索">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
    </form>
    <ul class="navbar-nav">
        <!-- Dropdown -->
        <c:choose>
            <c:when test="${sessionScope.islogin}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            ${sessionScope.logined.username}
                    </a>
                    <div class="dropdown-menu dropdown-menu-right">
                        <c:choose>
                            <c:when test="${sessionScope.logined.isAdmin==1}">
                                <a class="dropdown-item" href="/admin/">管理员页面</a>
                                <form name="form1" action="/article/addArticle" method="post">
                                    <a class="dropdown-item" href="javascript:document.form1.submit();">发表文章</a>
                                </form>
                            </c:when>
                            <c:when test="${sessionScope.logined.isAdmin!=1}">
                                <form name="form1" action="/article/addArticle" method="post">
                                    <a class="dropdown-item" href="javascript:document.form1.submit();">发表文章</a>
                                </form>
                                <a class="dropdown-item" href="/article/manage">管理文章</a>
                            </c:when>
                        </c:choose>
                        <a class="dropdown-item" href="/account/updateinfo">修改用户信息</a>
                        <a class="dropdown-item" href="/account/logout">登出</a>
                    </div>
                </li>
            </c:when>
            <c:when test="${!sessionScope.islogin}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">
                        登录
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" style="padding: 15px;padding-bottom: 0;width: 200px">
                        <form action="/account/login/do" method="post">
                            <input type="text" name="username" class="form-control" placeholder="用户名" style="margin-bottom: 15px;">
                            <input type="password" name="password" class="form-control" placeholder="密码" style="margin-bottom: 15px;">
                            <div style="text-align: center;margin-bottom: 1px;">
                                <input type="submit" class="btn btn-primary" value="登录" style="margin-bottom: 1px;">
                            </div>
                        </form>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/account/signup">注册</a>
                </li>
            </c:when>
        </c:choose>
    </ul>
    </div>
</nav>
<br/>
<br/>
<br/>
<br/>
<div style="position: relative;">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Success </strong>${succ}
        </div>
    </c:if>
    <c:if test="${!empty url}">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>链接 </strong><a href="http://${pageContext.request.getHeader("host")}${url}">http://${pageContext.request.getHeader("host")}${url}</a>
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Error </strong>${error}
        </div>
    </c:if>
</div>

