<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공!</title>
</head>
<body>
	<h1>로그인 성공!</h1>
	<span id="member-id-span"> ${memberId }</span>님 환영합니다!<br>
	<input type="hidden" id="member-id" value="${memberId }">
	<!-- input태그를 쓰기 싫으면 위에처럼 span태그를 사용할 수도 있다. -->
	
	<a href="/member/myInfo.kh?member-id=${memberId }">마이페이지</a>
	<!-- a태그의 링크기능 무효화 -->
	<a href="javascript:void(0);" onclick="removeCheck();">회원탈퇴</a>
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