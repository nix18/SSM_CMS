<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/3
  Time: 下午 4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="../header.jsp"%>
    <title>管理员文章管理页面--<%=sitename%></title>
    <script type="text/javascript" src="/js/wangEditor.min.js"></script>
</head>
<body>
<%@include file="../banner.jsp"%>
    <div class="container">
        <table class="table">
            <c:forEach items="${articles}" var="article">
                <tr class="text-center">
                    <td colspan="4">
                        <a class="alert-link" href="${article.aUrl}">${article.aTitle}</a>
                    </td>
                </tr>
                <tr class="text-center">
                    <td>
                        <a href="/article/${article.aId}">查看文章</a>
                    </td>
                    <td>
                        <a href="/article/updateArticle/${article.aId}">修改文章</a>
                    </td>
                    <td>
                        <a href="/article/manageComment/${article.aId}">管理评论</a>
                    </td>
                    <td>
                        <a href="/article/delArticle/${article.aId}">删除文章</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="button-container">
            <a href="/"><input type="button" class="button" value="返回首页"></a>
        </div>
    </div>
</body>
</html>
