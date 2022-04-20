<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/4
  Time: 下午 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="../header.jsp"%>
    <title>用户信息修改页--<%=sitename%></title>
</head>
<body>
<%@include file="../banner.jsp"%>
    <div class="container">
        <form action="/admin/updateinfo/do" method="post">
            <table class="table table-light table-bordered m-auto" style="width:30%;text-align: center;">
                <thead>
                <tr>
                    <th colspan="2" class="text-center">
                        用户信息修改
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        要修改的用户id：
                    </td>
                    <td>
                        <label>
                            <input type="text" class="form-control" name="uid">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        用户名：
                    </td>
                    <td>
                        <label>
                            <input type="text" class="form-control" name="username">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        用户简介：
                    </td>
                    <td>
                        <label>
                            <input type="text" class="form-control" name="profile">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        用户密码：
                    </td>
                    <td>
                        <label>
                            <input type="password" class="form-control" name="password">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        用户权限：
                    </td>
                    <td>
                        <select class="custom-select" name="isAdmin" id="isAdmin">
                            <option value="0">
                                普通用户
                            </option>
                            <option value="1">
                                管理员
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" class="form-control" value="提交修改">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>
