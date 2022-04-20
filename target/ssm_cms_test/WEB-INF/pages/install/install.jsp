<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/10
  Time: 下午 6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../header.jsp"%>
    <title>安装SoraCMS</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<br/>
<br/>
<c:if test="${id==1}">
    <div class="container container-fluid">
        <form class="form-group" method="post" action="/install/1/do">
            <table class="table table-info">
                <thead>
                <tr>
                    <th>
                        安装CMS，第一步：配置数据库
                    </th>
                </tr>
                </thead>
                <tr>
                    <td>
                        <input type="checkbox" value="1" name="check" id="check">
                        <label for="check">我已确认：在项目根目录/resources/jdbc.properties中正确配置数据库驱动、url、用户名、密码；</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="checkbox" value="1" name="check1" id="check1">
                        <label for="check1">我已知晓：使用本系统造成的所有法律责任与系统作者无关，由使用者承担一切责任；</label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="form-control" type="submit" value="下一步">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</c:if>
<c:if test="${id==2}">
    <div class="container container-fluid">
        <form class="form-group" method="post" action="/install/2/do">
            <table class="table table-info">
                <thead>
                <tr>
                    <th>
                        安装CMS，第二步：注册管理员
                    </th>
                </tr>
                </thead>
                <tr>
                    <td>
                        <label for="username">管理员 用户名：</label>
                        <input class="form-control" type="text" name="username" id="username">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="profile">管理员 简介：</label>
                        <input class="form-control" type="text" name="profile" id="profile">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">管理员 密码：</label>
                        <input class="form-control" type="text" name="password" id="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="form-control" type="submit" value="下一步">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</c:if>
<c:if test="${id==3}">
    <div class="container container-fluid">
        <form class="form-group" method="post" action="/install/3/do">
            <table class="table table-info">
                <tr>
                    <td>
                        <input class="form-control" type="submit" value="\开启云上之旅/">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</c:if>
</body>
</html>
