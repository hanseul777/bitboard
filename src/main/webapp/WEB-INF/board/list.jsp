<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/18
  Time: 4:19 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Page List</h1>
${pageMaker}
<ul>
    <c:forEach items="${dtoList}" var="dto">
    <li>${dto}</li>
    </c:forEach>
</ul>
<hr/>
<style>
    .pageList{
        list-style: none;
        display: flex;
        flex-direction: row;
    }
    .pageList li{
        margin-left: 0.3em;
        background-color: green;
        font-family: "Roboto Light";
        border: 1px solid greenyellow;
    }
</style>
<ul class="pageList">
    <c:if test="${pageMaker.prev}"><!--test에 입력되어있는 값을 조건으로 pageMaker.prev가 true면 밑의 문장을 실행-->
        <li><a href="/board/list?page=${pageMaker.start-1}&size=${pageMaker.size}}">prev</a></li>
    </c:if>
    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
        <li><a href="/board/list?page=${page}&size=${pageMaker.size}">${page}</a></li>
    </c:forEach>
    <c:if test="${pageMaker.next}"><!--test에 입력되어있는 값을 조건으로 pageMaker.prev가 true면 밑의 문장을 실행-->
    <li><a href="/board/list?page=${pageMaker.end+1}&size=${pageMaker.size}">next</a></li>
    </c:if>
</ul>

</body>
</html>
