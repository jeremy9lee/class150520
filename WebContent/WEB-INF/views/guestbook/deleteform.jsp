<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="header">
		<c:import url="/views/include/header.jsp"></c:import>
		</div>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="guestdelete.do">
					<input type='hidden' name="no" value="${no}">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="submit" value="확인" class="btn btn-info">
				</form>
				<c:if test="${not empty msg }">
				${msg}
				</c:if>
				<a href="guestbookform.do">방명록 리스트</a>
			</div>
		</div>
		<div id="navigation">
		<c:import url="/views/include/navigation.jsp"></c:import>
		</div>
		<div id="footer">
		<c:import url="/views/include/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>