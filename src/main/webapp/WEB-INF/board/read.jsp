<%--
  Created by IntelliJ IDEA.
  User: hanseul
  Date: 2021/08/19
  Time: 11:07 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 1. 택배 잘 받았나 확인 -->
<div>${boardDTO}</div>
<div>${pageDTO}</div>

<!-- 2. 버튼누르면 목록으로 가기 -->
<!-- 게시글에 들어갔다가 목록으로 다시 가도 URL에 정보가 그대로 유지되어있어야 함.-->
<a href="/board/list?page=${pageDTO.page}&size=${pageDTO.size}">목록가기</a>

<button><a href="/board/modify?bno=${boardDTO.bno}">수정</a></button>

<form action="/board/remove?bno=${boardDTO.bno}" method="post">
<button type="submit">삭제</button>
</form>


</body>
</html>
