<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/4
  Time: 下午 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>用户注册页--<%=sitename%></title>
</head>
<body>
<%@include file="banner.jsp"%>
    <div class="container">
        <script type="text/javascript">
            var check=function () {
                var obj=document.getElementById("password");
                var obj1=document.getElementById("username");
                if(obj1.value.length<2||obj1.value.length>40){
                    window.alert("用户名长度范围2-40");
                    return false;
                }
                if(obj.value.length<6){
                    window.alert("密码不能小于6位");
                    return false;
                }
                return true;
            }
        </script>
        <form action="/account/signup/do" method="post" onsubmit="return check()">
            <table class="table table-light table-bordered m-auto" style="width:35%;text-align: center;">
               <thead>
               <tr>
                   <th colspan="2" class="text-center">
                       用户注册
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
                            <input type="text" class="form-control" name="username" id="username">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        个人简介：
                    </td>
                    <td>
                        <label>
                            <input type="text"  class="form-control" name="profile">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        密码：
                    </td>
                    <td>
                        <label>
                            <input type="password"  class="form-control" name="password" id="password">
                        </label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" class="form-control" name="提交">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>
