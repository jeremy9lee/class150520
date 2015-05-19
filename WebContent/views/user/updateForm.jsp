<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/user.css" rel="stylesheet" type="text/css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">

</head>

<script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitBut").attr("disabled", true);
		$("#newPassword").on("blur", checkPwd);
		$("#oldPassword").on("blur", checkPwd);
		$("#newPassword").on("focus", function() {
			$("#pwdCheckResult").html("");
		});
		$("#oldPassword").on("focus", function() {
			$("#pwdCheckResult").html("");
		});
	
		
	});


	function checkPwd() {

		if ($("#oldPassword").val() != null && $("#oldPassword").val() != ""
			&& $("#newPassword").val() != null && $("#newPassword").val() != "") {

			var oldPwd = $("#oldPassword").val();
			var pwd = $("#newPassword").val();
			
			if(oldPwd==pwd){
				$("#pwdCheckResult").html("<font style = 'color:green'>Perfectly Matched!</font>");
				$("#submitBut").attr("disabled", false);
			}else{
				$("#pwdCheckResult").html("<font style = 'color:red'>PWD NOT MATCHED!</font>");
				$("#submitBut").attr("disabled", true);
			}
			
		} 
	}

</script>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/views/include/header.jsp" flush="false" />
		</div>
		<div id="content">
			<div id="user">

				<form id="join-form" name="updateForm" method="post"
					action="memberUpdate.do">
					<label class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value="${member.name}" disabled="disabled">
					<label class="block-label" for="email">이메일</label> <input
						id="email1" name="email" type="email" value="${member.email}"
						disabled="disabled">
					<label class="block-label">패스워드</label> <input id="oldPassword"
						name="password" type="password" value="${member.password}">
					<input id="newPassword" name="newPassword" type="password">
					<div id="pwdCheckResult"></div>


					<fieldset disabled="disabled">
						<legend>성별</legend>

						<c:choose>
							<c:when test="${member.gender eq female }">
								<label>여</label>
								<input type="radio" name="gender" value="female"
									checked="checked">
								<label>남</label>
								<input type="radio" name="gender" value="male">
							</c:when>
							<c:otherwise>
								<label>여</label>
								<input type="radio" name="gender" value="female">
								<label>남</label>
								<input type="radio" name="gender" value="male" checked="checked">
							</c:otherwise>
						</c:choose>
					</fieldset>

					<input type="submit" id="submitBut" value="save"
						class="btn btn-primary">

				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/views/include/navigation.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="/views/include/footer.jsp" />
		</div>
	</div>
</body>
</html>