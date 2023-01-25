<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>나이쓰 멤버 웹</title>
		<link rel="stylesheet" href="/resources/css/main.css">
	</head>
	<body>
	<!-- session키를 씀으로써 로그인이 풀리는걸 방지해준다.
		session은 탈취가 쉬워서 최소한의 정보만 넣어서 사용해야한다. -->
		<c:if test="${sessionScope.memberId eq null }">
		<div id="container">
		<h1>나이쓰 멤버 웹</h1>
			<h2>로그인 페이지</h2><br>
			<fieldset>
				<legend>로그인</legend>
				<!--로그인이라 get방식이 아니라 post로 방식을 바꿔줌  -->
				<form action="/member/login.kh" method="post">
					<input type="text" name="member-id" placeholder="ID"> <br>
					<input type="password" name="member-pw" placeholder="PW"> <br>
					<div id="login-area">
						<input type="submit" value="로그인">
						<input type="reset" value="취소">
					</div>
				</form>
			</fieldset>
			<span><a href="/member/enrollView.kh">회원가입</a></span>
		</div>
		</c:if>
		<c:if test="${sessionScope.memberId ne null }">
		<span id="member-id-span"> ${sessionScope.memberId }</span>님 환영합니다!<br>
		<input type="hidden" id="member-id" value="${sessionScope.memberId }">
		<!-- input태그를 쓰기 싫으면 위에처럼 span태그를 사용할 수도 있다. -->
		<a href="/member/myInfo.kh?member-id=${sessionScope.memberId }">마이페이지</a>
		<a href="/member/logout.kh">로그아웃</a>
		<!-- a태그의 링크기능 무효화 -->
		<a href="javascript:void(0);" onclick="removeCheck();">회원탈퇴</a>
		</c:if>
		<script>
			function removeCheck(){
				if(confirm("회원탈퇴를 하시겠습니까?")){
					// 방법1. (input태그 필요!)
					var memberId = document.querySelector("#member-id").value;
					//var memberId = document.querySelector("#member-id-span").innerHTML;
					// 방법2. 간단하지만 권장하지는 않는 방법
					// var memberId = "${memberId}";
					location.href = "/member/remove.kh?member-id="+memberId;
				}			
			}
		</script>
	</body>
</html>