<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>

	<div align="center">
		<h3>게시글 삭제</h3>
		<p><span style="color:red;">삭제된 게시글은 다시는 복구 할 수 없습니다. 정말 삭제하시겠습니까?</span></p>
		<form action="bDelete" method="post">
			<input type="submit" value="삭제하기">
			<input type="button" value="취소" onclick="">
		</form>
	</div>
	
</body>
</html>