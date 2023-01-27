<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 목록</title>
		<link rel="stylesheet" href="/resources/css/notice.css">
	</head>
	<body>
		<h1>공지사항목록</h1>
		<table>
			<colgroup>
				<col width="10%">	<!-- %로 나누려면 width의 값이 정해져있어야해 -->
				<col width="55%">
				<col width="10%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<!-- for문같은 역할 1부터 5까지 1씩증가 -->
				<%-- <c:forEach begin="1" end="5" step="1" varStatus="status">	<!-- status라는 변수에 넣었다. --> --%>
				<c:forEach items="${nList }" var="notice">
				<tr>
					<td style="text-align:center;">${notice.noticeNo }</td>	<!-- 여기서 가져오는 변수는 Notice.java 와 통일해야해 -->
					<td><a href="/notice/detail?notice-no=${notice.noticeNo }">${notice.noticeSubject }</a></td>
					<td>${notice.noticeWriter }</td>
					<td>${notice.noticeDate }</td>
					<td style="text-align:center;">${notice.viewCount }</td>
				</tr>				
				</c:forEach>
				<tr>
					<td colspan = "5" align="center">
						${pageNavi }
						<!-- 이렇게 고정으로 만들지 않고 동적으로 만들어줘야해(DAO에서 만들어준다 generatePageNavi) -->
						<!-- <a href="/notice/list?page=1">1</a> <a href="/notice/list?page=2">2</a> <a href="/notice/list?page=3">3</a>
						<a href="/notice/list?page=4">4</a> <a href="/notice/list?page=5">5</a> -->
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>