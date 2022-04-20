<%--
  Created by IntelliJ IDEA.
  User: kyj20
  Date: 2020/5/12
  Time: 下午 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <c:forEach items="${articles}" var="article">
    <c:if test="${article.key.aIsPosted==1}">
    <article class="article">
        <time>${article.key.aTime}</time>
        <h2 class="title"><a href="${article.key.aUrl}">${article.key.aTitle}</a></h2>
        <p style="text-align: center; color:rgba(192,191,192, 1); font-size: 80%;">[浏览量：${article.key.readCount}]</p>
        <span><i>${article.value.username}</i></span>
        <section class="article-content markdown-body">
            <blockquote>
                <p>${article.key.aIntro}</p>
            </blockquote>
            ......
        </section>
        <footer>
            <a href="${article.key.aUrl}">阅读全文</a>
        </footer>
    </article>
    </c:if>
    </c:forEach>
