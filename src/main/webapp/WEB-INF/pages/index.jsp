<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/6
  Time: 下午 3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>首页--<%=sitename%></title>
    <style>
        .article {
            box-shadow: 5px 5px 25px #dadada;
            position: relative;
            padding: 0 20px;
            margin: 40px auto;
            max-width: 950px;
            background: #fff;
            text-align: center;
        }
        .article>time {
            position: absolute;
            top: 0;
            left: 0;
            border-bottom: 1px solid #ccc;
            font-size: 14px;
            padding: 4px 5px 0;
            color: #999;
        }
        .article h2 {
            padding-bottom: .5em;
            font-size: 1.75em;
            line-height: 1.225;
        }
        .article h2 a{
            color: #0b2e13;
        }
        .article>h2 {
            padding: 35px 0 45px;
            font-size: 22px;
            font-weight: 700;
            cursor: pointer;
        }
        .article>span {
            position: absolute;
            top: 0;
            right: 0;
            color: #999;
            padding: 3px 10px;
            background: #f1f1f1;
            font-size: 14px;
        }
        .article section {
            text-align: left;
            padding: 10px;
            font-size: 16px;
        }
        .article blockquote {
            padding: 0 15px;
            color: #777;
            border-left: 4px solid #ddd;
        }
        .article footer {
            padding: 25px 0 20px;
        }
        .article footer a {
            display: inline-block;
            color: #18bc9c;
            cursor: pointer;
            padding: 4px 20px;
            border-radius: 5px;
            transition: all .5s;
            border: 1px solid #18bc9c;
        }
        .index li{
            display:inline-block;
        }
    </style>
</head>
<body>
<%@include file="banner.jsp"%>
<%@include file="listbody.jsp"%>
    <div style="text-align: center">
        <ul class="index pagination" style="display:inline-block;float: none;">
            <c:if test="${currindex>1}">
                <li class="page-item"><a class="page-link" href="${currpath}1">首页</a></li>
                <li class="page-item"><a class="page-link"  href="${currpath}${currindex-1}">上一页</a></li>
            </c:if>
            <c:forEach var="item" items="${indexs}">
                <c:if test="${currindex == item}">
                    <li class="page-item"><a class="page-link"  href="${currpath}${item}" class="active">${item}</a></li>
                </c:if>
                <c:if test="${currindex != item}">
                    <li class="page-item"><a class="page-link"  href="${currpath}${item}">${item}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${currindex<maxindex}">
                <li class="page-item"><a class="page-link"  href="${currpath}${currindex+1}">下一页</a></li>
                <li class="page-item"><a class="page-link"  href="${currpath}${maxindex}">尾页</a></li>
            </c:if>
            <span style="line-height: 35px;color: gray;">  总共:<span style="color: red;">${articleCount}</span>篇文章</span>
        </ul>
    </div>
</div>
</body>
</html>
