<%--
  Created by IntelliJ IDEA.
  User: 12437
  Date: 2020/5/16
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>评论管理页面--<%=sitename%></title>
</head>
<body>
<%@include file="banner.jsp"%>
<div class="container">
    <table class="layui-table table-bordered" style="table-layout:fixed; text-align: center;" >
        <thead>
            <tr>
                <td>
                    评论者
                </td>
                <td>
                    评论内容
                </td>
                <td>
                    操作
                </td>
                <td>

                </td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${comments}" var="comment">
            <tr>
                <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                    ${comment.key.cName}
                </td>

                <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                    ${comment.key.cContent}
                </td>

                <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                    <a href="/mainComment/delMainComment/${comment.key.cId}">删除评论</a>
                </td>

                <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                    <a href="javascript:;"  id="mainbtn-${comment.key.cId}"  style="text-decoration: none;" onclick="btnReplyClick(${comment.key.cId})">展开回复</a>
                </td>
            </tr>
            <tr  class="layui-hide" id="main-${comment.key.cId}">
                <td  colspan="4">
                    <table class="m-auto"  style="table-layout: fixed;width: 100%; text-align: center;">
                        <thead>
                            <tr>
                                <td>
                                 回复者
                                </td>
                                <td>
                                 回复内容
                                </td>
                                <td>
                                 操作
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${comment.value}" var="subComment">
                                <tr>
                                    <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                                            ${subComment.scName}
                                    </td>

                                    <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                                            ${subComment.scContent}
                                    </td>

                                    <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                                        &nbsp;&nbsp;  <a href="/subComment/delSubComment/${subComment.scId}">删除回复</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <script type="text/javascript">
        function btnReplyClick(elem) {
                var a=document.getElementById("main-"+elem);
                var b=document.getElementById("mainbtn-"+elem);

            if(a.getAttribute("class") == "layui-hide"){
                a.setAttribute("class", "");
            }else{
                a.setAttribute("class", "layui-hide");
            }
            if(b.innerText == "展开回复"){
                b.innerText="收起";
            }else{
                b.innerText="展开回复";
            }
        }
    </script>
</div>
</body>
</html>
