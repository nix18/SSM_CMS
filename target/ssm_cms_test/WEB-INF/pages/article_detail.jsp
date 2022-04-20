<%--
  Created by IntelliJ IDEA.
  User: Nix233
  Date: 2020/4/25
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>${article.aTitle}--文章详细信息--<%=sitename%></title>
    <script src="https://vjs.zencdn.net/7.7.6/video.js"></script>
    <link href="https://vjs.zencdn.net/7.7.6/video-js.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/wangEditor.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css" />
    <style>
        .title{
            font-weight: bold;
            font-size: 45px!important;
        }
        .main_text{
            font-size:25px; 		/*字体大小*/
            line-height: 30px;      /*行高*/
            font-family: 微软雅黑,"微软雅黑 Light",华文细黑,宋体, 黑体, serif; 	/*字体类型*/
            font-weight: normal;
            color: #062c33;
        }
    </style>
</head>
<body>
<%@include file="banner.jsp"%>
<c:set var="logined" value="${sessionScope.logined}"/>
<c:if test="${article.aIsPosted==0}">
    <c:if test="${logined.uid==article.aByUid||logined.isAdmin==1}">
    <div class="container">
        <div class="container">
            <div class="form-group text-center">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>
                        <a class="title">${article.aTitle}</a>
                    </legend>
                </fieldset>
            </div>
            <div class="form-group">
                <blockquote class="layui-elem-quote">
                        ${article.aIntro}
                </blockquote>
            </div>
            <div class="form-group">
                <div class="main_text">
                        ${article.aContent}
                </div>
            </div>
            <div class="form-group">
                <div class="form-group text-right" style="color: rgba(192,192,192,1)">
                    <label for="writer1">作者：</label>
                    <a id="writer1" href="/account/detail/${account.uid}" data-toggle="tooltip" title="${account.profile}">${account.username}</a>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group text-right" style="color: rgba(192,192,192,1)">
                    <label for="time1">最后修改时间：</label>
                    <a id="time1">${article.aTime}</a>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${!sessionScope.islogin||logined.uid!=article.aByUid}">
        <table class="table table-warning">
            <tr>
                <th>
                    文章未发布，非作者无法查看
                </th>
            </tr>
        </table>
    </c:if>
</c:if>
<c:if test="${article.aIsPosted==1}">
    <div class="container">
        <div class="container">
            <div class="form-group text-center">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend>
                        <a class="title">${article.aTitle}</a>
                    </legend>
                </fieldset>
            </div>
            <div class="form-group">
                <blockquote class="layui-elem-quote">
                    ${article.aIntro}
                </blockquote>
            </div>
            <div class="form-group">
                <div class="main_text">
                        ${article.aContent}
                </div>
            </div>
            <div class="form-group">
                <div class="form-group text-right" style="color: rgba(192,192,192,1)">
                    <label for="writer">作者：</label>
                    <a id="writer" href="/account/detail/${account.uid}" data-toggle="tooltip" title="${account.profile}">${account.username}</a>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group text-right" style="color: rgba(192,192,192,1)">
                    <label for="time">最后修改时间：</label>
                    <a id="time">${article.aTime}</a>
                </div>
            </div>
        </div>
        <table style="width: 100%">
            <tr class="table-container">
                <td>
                    <form class="layui-form" action="/mainComment/addMainComment/do" method="post" style="width: 100%;">
                        <div class="text-center">
                            <input type="text" class="form-control m-auto" style="width: 80%" name="cName" id="cName" placeholder="昵称：">
                            <input type="hidden" value="${article.aId}" name="cByAid">
                            <div class="layui-input-block m-auto" style="width: 80%">
                                <textarea id="cContent" name="cContent" placeholder="请输入评论" class="layui-textarea form-control" style="height: 150px;"></textarea>
                            </div>
                            <br/>
                            <div class="layui-input-block" style="text-align: left">
                                <input type="submit" class="layui-btn" value="评论">
                            </div>
                        </div>
                    </form>
                    <br/>
                    <div>
                        <ul>
                            <!-- 先遍历留言信息（一条留言信息，下面的全是回复信息） -->
                            <c:forEach items="${comments}" var="comment">
                                <!-- 如果留言信息是在本文章下的才显示 -->
                                <li style="border-top: 1px dotted #01AAED">
                                    <br/>
                                    <div style="text-align: left;color:#444">
                                        <div>
                                            <span style="color:#01AAED">${comment.key.cName}</span>
                                        </div>
                                        <div>${comment.key.cContent}</div>
                                    </div>
                                    <div>
                                        <div class="comment-parent" style="text-align:left;margin-top:7px;color:#444">
                                            <span>${comment.key.cTime}</span>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <p>
                                                <a href="javascript:;"  id="mainbtn-${comment.key.cId}"  style="text-decoration: none;" onclick="btnReplyClick(1 ,${comment.key.cId})">回复</a>
                                            </p>
                                            <hr style="margin-top: 7px;"/>
                                        </div>
                                        <!-- 回复表单默认隐藏 start-->
                                        <div class="replycontainer layui-hide"  id="main-${comment.key.cId}"  style="margin-left: 61px;">
                                            <form action="/subComment/addSubComment/do" method="post" class="layui-form">
                                            <input name="scName" id="scName" class="form-control" type="text" placeholder="昵称：" style="width: 80%"/>
                                            <input name="scByCid" value="${comment.key.cId}" hidden="hidden"/>
                                            <div class="layui-form-item">
                                            <textarea name="scContent" id="scContent" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px; width: 80%">回复 ${comment.key.cName} :</textarea>
                                            </div>
                                            <div class="layui-form-item">
                                            <button id="replyBtn" class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>

                                    <!-- 以下是回复信息 -->
                                    <c:forEach items="${comment.value}" var="subComment">
                                        <!-- 每次遍历出来的留言下存在回复信息才展示（本条回复信息是本条留言下的就显示在当前留言下） -->
                                        <div style="text-align: left;margin-left:61px;color: #444">
                                            <div>
                                                <span style="color:#5FB878">${subComment.scName}</span>
                                            </div>
                                            <div>${subComment.scContent}</div>
                                        </div>
                                        <div>
                                            <div class="comment-parent" style="text-align:left;margin-top:7px;margin-left:61px;color:#444">
                                                <span>${subComment.scTime}</span>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <p>
                                                    <a href="javascript:;" id="subbtn-${subComment.scId}" style="text-decoration: none;" onclick="btnReplyClick(0, ${subComment.scId})" >回复</a>
                                                </p>
                                                <hr style="margin-top: 7px;"/>
                                            </div>
                                            <!-- 回复表单默认隐藏 -->
                                            <div class="replycontainer layui-hide"  id="sub-${subComment.scId}"  style="margin-left: 61px;">
                                                <form action="/subComment/addSubComment/do" method="post" class="layui-form">
                                                    <input name="scName" type="text" class="form-control" placeholder="昵称：" style="width: 80%"/>
                                                    <input name="scByCid" value="${comment.key.cId}" hidden="hidden"/>
                                                    <div class="layui-form-item">
                                                        <textarea name="scContent"  lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;width: 80%">回复 ${subComment.scName} :</textarea>
                                                    </div>
                                                    <div class="layui-form-item">
                                                        <button id="replyBtn1" class="layui-btn layui-btn-mini" lay-submit="formReply" lay-filter="formReply">提交</button>
                                                    </div>
                                                </form>
                                            </div>

                                        </div>
                                    </c:forEach>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
            <c:if test="${haslast!=0||hasnext!=0}">
                <tr class="layui-table-link">
                    <td>
                        <c:if test="${haslast!=0}">
                            <div class="button-container">
                                <a href="/article/${haslast}">
                                    <input type="button" id="haslast" class="form-control" value="上一篇：${lastname.toString()}">
                                </a>
                            </div>
                        </c:if>
                        <c:if test="${hasnext!=0}">
                            <div class="button-container">
                                <a href="/article/${hasnext}">
                                    <input type="button" id="hasnext" class="form-control" value="下一篇：${nextname.toString()}">
                                </a>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:if>
        </table>
    </div>
</c:if>
<br/>
<script type="text/javascript">
    (function(){
        var options={
            bigPlayButton: true,
            textTrackDisplay: false,
            posterImage: false,
            errorDisplay: false,
            playbackRates: [0.5,1,1.5,2,3,5],
        }
        var prevideos = document.getElementsByTagName('video');
        for(i=0; i<prevideos.length; i++) {
            var prevideo = prevideos[i];
            prevideo.id=prevideo.id+"-"+i.toString();
        }
        var videos = document.getElementsByTagName('video');
        for(i=0; i<videos.length; i++) {
            var video = videos[i];
            if(video.className.indexOf('video-js') > -1) {
                videojs(video.id,options).ready(function(){
                    console.log('播放器'+video.id+'初始化完成');
                });
            }
        }
    })();
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script type="text/javascript">
    function btnReplyClick(ismain,elem) {
        if(ismain==1){
            var a=document.getElementById("main-"+elem);
            var b=document.getElementById("mainbtn-"+elem);
        }else {
            var a=document.getElementById("sub-"+elem);
            var b=document.getElementById("subbtn-"+elem);
        }

        if(a.getAttribute("class") == "replycontainer layui-hide"){
            a.setAttribute("class", "replycontainer layui-show");
        }else{
            a.setAttribute("class", "replycontainer layui-hide");
        }
        if(b.innerText == "回复"){
            b.innerText="收起";
        }else{
            b.innerText="回复";
        }
    }
</script>
</body>
</html>
