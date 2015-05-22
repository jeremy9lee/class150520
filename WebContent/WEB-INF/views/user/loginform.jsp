<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/user.css" rel="stylesheet"
	type="text/css">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false" />
		</div>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post"
					action="login.do">
					<label class="block-label" for="email">이메일</label> <input
						id="email" name="email" type="text" value=""> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<c:if test="${result eq false}">
						<p>로그인에 실패 했습니다.</p>
					</c:if>
					
					<input type="submit" value="로그인" class="btn btn-info">
				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/include/navigation.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/include/footer.jsp" />
		</div>
	</div>
</body>
</html>