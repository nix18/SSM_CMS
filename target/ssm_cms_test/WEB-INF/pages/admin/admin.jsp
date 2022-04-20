<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/6
  Time: 下午 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="../header.jsp"%>
    <title>管理员管理页面--<%=sitename%></title>
</head>
<body>
<%@include file="../banner.jsp"%>
<table class="table table-light table-bordered m-auto" style="width:30%;text-align: center;">
    <tbody>
    <tr>
        <td>
            <a href="/admin/manageArticle">管理文章</a><br/>
        </td>
    </tr>
    <tr>
        <td>
            <a href="/admin/updateinfo">修改用户信息</a><br/>
        </td>
    </tr>
    <tr>
        <td>
            <table class="table table-info" style="text-align: center">
                <tr>
                    <td>
                        <label for="delid"> 删除用户：</label>
                        <input id="delid" class="form-control" placeholder="用户ID">
                        <script>
                            function jump() {
                                var name = $("#delid").val();
                                self.location="/admin/deleteAccount/"+name;
                            }
                        </script>
                        <br/>
                        <input class="btn btn-primary" type="button" value="提交" onclick="jump()">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="queryid"> 查询用户：</label>
                        <input id="queryid" class="form-control" placeholder="用户ID">
                        <br/>
                        <script>
                            function jumpquery() {
                                var queryname = $("#queryid").val();
                                self.location="/admin/findbyid/"+queryname;
                            }
                        </script>
                        <input type="button" value="提交" class="btn btn-primary" onclick="jumpquery()">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
