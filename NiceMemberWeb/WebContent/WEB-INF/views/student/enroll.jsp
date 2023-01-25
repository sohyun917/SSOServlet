<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>학생 회원가입</title>
		<link rel="stylesheet" href="/resources/css/student.css">
	</head>
	<body>
	<h1>학생 가입 정보</h1>
	<div>
		<form action="/student/enroll.do" method="post">
			<fieldset>
				<legend>학생 가입 정보</legend>
				<ul>
					<li>
						<label>아이디</label>
						<input type="text" name="student-id">
					</li>
					<li>
						<label>비밀번호</label>
						<input type="password" name="student-pw">
					</li>
					<li>
						<label>이름</label>
						<input type="text" name="student-name">
					</li>
					<li>
						<label>이메일</label>
						<input type="email" name="student-email">
					</li>
					<li>
						<label>전화번호</label>
						<input type="text" name="student-phone">
					</li>
					<li>
						<label>주소</label>
						<input type="text" name="student-address">
					</li>
					<li>
						<label>성별</label>
						<input type="text" name="student-gender">
					</li>
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="가입하기">
				<input type="reset" value="초기화">
			</div>
		</form>
	</div>
	</body>
</html>