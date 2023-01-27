<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
		<link rel="stylesheet" href="/resources/css/notice.css">
	</head>
	<body>
		<h1>공지사항 수정</h1>
		<form action="/notice/modify" method="post">
			<!-- 사용자에게 보일 필요는 없고 개발자는 봐야하기 때문에 hidden으로 써준다(밑의 쿼리문실행을 위해 noticeNo값을 가져와야해 -->
			<!-- UPDATE NOTICE_TBL SET NOTICE_SUBJECT = ?, NOTICE_CONTENT = ? WHERE NOTICE_NO = ? -->
			<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
			<fieldset>
				<legend>공지사항 수정</legend>
				<ul>
					<li>
						<label>제목</label>
						<input type="text" value="${notice.noticeSubject }" name="subject">
					</li>
					<li>
						<label>내용</label>
						<textarea rows="30" cols="40" name="content">${notice.noticeContent }</textarea>
					</li>
				</ul>
			</fieldset>
			<div class="btn-area">
				<input type="submit" value="수정">
				<input type="reset" value="초기화">
			</div>
		</form>
	</body>
</html>