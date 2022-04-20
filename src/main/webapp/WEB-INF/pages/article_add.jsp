<%--
  Created by IntelliJ IDEA.
  User: Nix233
  Date: 2020/4/25
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>文章新增页--<%=sitename%></title>
    <script type="text/javascript" src="/js/wangEditor.min.js"></script>
</head>
<body>
<%@include file="banner.jsp"%>
    <div class="container">
        <table class="table table-light table-bordered" style="width: 110%;text-align: center">
            <thead>
            <tr>
                <th colspan="2" class="text-center">
                    新增文章
                </th>
            </tr>
            </thead>
           <tbody>
           <tr>
               <td>
                   <form action="/article/addArticle/${aId}/do" method="post">
                       <table class="table table-light">
                           <tr>
                               <td>
                                   <label for="aTitle">文章标题：</label>
                                   <input type="text" class="form-control" name="aTitle" id="aTitle" value="${article.aTitle}">
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <label for="aTag">文章分类：</label>
                                   <input type="text"  class="form-control" name="aTag" id="aTag" placeholder="${article.aTag}">
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <label for="aIntro">文章概要：</label>
                                   <input type="text"  class="form-control" name="aIntro" id="aIntro" value="${article.aIntro}">
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <label for="div1">文章内容</label>
                                   <div class="form-group">
                                       <div id="div1" style="width: 700px">
                                       ${article.aContent}
                                       </div>
                                       <textarea id="aContent" name="aContent" style="display: none"></textarea>
                                   </div>
                               </td>
                           </tr>
                           <tr>
                               <td>
                                   <label for="poststatus">发布状态</label>
                                   <select class="custom-select" id="poststatus" name="aIsPosted">
                                       <option value="0">
                                           未发布
                                       </option>
                                       <option value="1">
                                           已发布
                                       </option>
                                   </select>
                               </td>
                           </tr>
                           <tr>
                               <td colspan="2">
                                   <input type="submit" class="form-control"  value="保存文章">
                               </td>
                           </tr>
                       </table>
                   </form>
               </td>
               <td>
                   <form action="/resource/uploadR/${aId}" method="post" enctype="multipart/form-data">
                       <table class="table table-info" style="table-layout:fixed;">
                           <tr>
                               <td colspan="2">
                                   <input type="file"  class="form-control"  name="upload">
                               </td>
                           </tr>
                           <tr>
                               <td colspan="2">
                                   <input type="submit" class="form-control"  value="上传">
                               </td>
                           </tr>
                           <%int index=0;%>
                           <c:forEach items="${resources}" var="resource">
                               <tr>
                                   <td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
                                       <c:if test="${resource.rCat.matches('jpg|png|JPG|PNG|bmp|BMP|gif|GIF')}">
                                           <a href="javascript:editor.txt.append('<img src=&quot;${resource.rUrl}&quot;></img>')">${resource.rName}</a>
                                       </c:if>
                                       <c:if test="${resource.rCat.matches('mp4|MP4|flv|FLV|WebM|webm|WEBM')}">
                                           <a href="javascript:editor.txt.append('<video id=&quot;my-video-${resource.rId}&quot; class=&quot;video-js  vjs-default-skin vjs-big-play-centered&quot; controls  width=&quot;640&quot; height=&quot;264&quot;> <source src=&quot;${resource.rUrl}&quot; /></video>')">${resource.rName}</a>
                                       </c:if>
                                       <c:if test="${resource.rCat.matches('mp3|MP3|wav|WAV')}">
                                           <a href="javascript:editor.txt.append('<audio controls><source src=&quot;${resource.rUrl}&quot;></audio>')">${resource.rName}</a>
                                       </c:if>
                                       <c:if test="${!resource.rCat.matches('jpg|png|JPG|PNG|bmp|BMP|gif|GIF|mp4|MP4|flv|FLV|WebM|webm|WEBM|mp3|MP3|wav|WAV')}">
                                           <a href="javascript:editor.txt.append('<p><a href=&quot;http://${pageContext.request.getHeader("host")}${resource.rUrl}&quot;>${resource.rName}</a></p>')">${resource.rName}</a>
                                       </c:if>
                                   </td>
                                   <td>
                                       <a href="/resource/deleteR/${resource.rId}">删除</a>
                                   </td>
                               </tr>
                               <%index++;%>
                           </c:forEach>
                       </table>
                   </form>
               </td>
           </tr>
           </tbody>
        </table>

    </div>
<br/>
<%--WangEditor JS代码--%>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#div1')
    var $text1 = $('#aContent')
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        $text1.val(html)
    }
    editor.create()
    // 初始化 textarea 的值
    $text1.val(editor.txt.html())
</script>
<script type="text/javascript">
    (function(){
        document.getElementById("poststatus")[${article.aIsPosted}].selected=true;
    })();
</script>
</body>
</html>
