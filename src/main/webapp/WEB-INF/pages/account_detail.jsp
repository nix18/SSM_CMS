<%--
  Created by IntelliJ IDEA.
  User: Nix233
  Date: 2020/4/17
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>账户详情--<%=sitename%></title>
    <link rel="stylesheet" href="/css/bootstrap4.0.min.css" >
    <script src="/js/jquery.slim.min.js" ></script>
    <script src="/js/popper.min.js" ></script>
    <script src="/js/bootstrap4.0.min.js"></script>
</head>
<body>
<%@include file="banner.jsp"%>
<div class="container">
    <table class="table table-light table-bordered m-auto" style="width:30%;text-align: center;">
        <thead>
        <tr>
            <th colspan="2">
                UID:${account.uid}用户详情
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                用户名
            </td>
            <td>
                ${account.username}
            </td>
        </tr>
        <tr>
            <td>
                用户简介
            </td>
            <td>
                ${account.profile}
            </td>
        </tr>
        <tr>
            <td>
                用户权限(1为管理员)
            </td>
            <td>
                ${account.isAdmin}
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
