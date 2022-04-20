<%--
  Created by IntelliJ IDEA.
  User: Nix233
  Date: 2020/4/19
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>请登录--<%=sitename%></title>
</head>
<body>
<%@include file="banner.jsp"%>
<div class="container">
    <c:choose>
        <c:when test="${sessionScope.islogin}">
            <br/>
            <h3 class="text-center">已登录账号，<a href="${pageContext.request.contextPath}/">返回首页</a></h3>
        </c:when>
        <c:when test="${!sessionScope.islogin}">
            <form action="/account/login/do" method="post">
                <table class="table table-light table-bordered m-auto" style="width:30%;text-align: center;">
                    <thead>
                    <tr>
                        <th colspan="2" class="text-center">
                            未登陆账号，请登录
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            用户名：
                        </td>
                        <td>
                            <label>
                                <input type="text" class="form-control"  name="username">
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            用户密码：
                        </td>
                        <td>
                            <label>
                                <input type="password" class="form-control"  name="password">
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" class="form-control"  name="登录" value="登录">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </c:when>
    </c:choose>
</div>
</body>
</html>
