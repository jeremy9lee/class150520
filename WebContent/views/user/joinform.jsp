<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="assets/css/user.css" rel="stylesheet"
	type="text/css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">

</head>

<script src="assets/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#idCheck").attr("disabled", true);
		$("#submitBut").attr("disabled", true);
		$("#idCheck").on("click", checkDuplicate);
		$("#email1").on("blur",validateEmail);
		$("#email1").on("focus", function() {
			$("#idCheckResult").html("");
		});
	});

	function validateEmail() {
		var email = $("#email1").val();
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		 
		if(!emailReg.test(email)){
			alert("email 형식으로 입력해주세요.");
			$("#idCheck").attr("disabled", true);
		}else{
			$("#idCheck").attr("disabled", false);
			$("#submitBut").attr("disabled", true);
		};
	}

	function checkDuplicate() {

		if ($("#email1").val() != null && $("#email1").val() != "") {

			var id = $("#email1").val();
			$.ajax({
				type : "get",
				url : "getMember.do",
				dataType : "json",
				contentType : "application/json; charset=utf-8",
				data : {
					userId : id
				},
				success : idCheckSuccess,
			/*   error : function(request){
			  	console.log(request);
			  } */
			});
		} else {
			alert("email을 입력하세요.");
		}
	}

	function idCheckSuccess(jsonData) {

		console.log("jsonData" + jsonData);
		if (jsonData.success == true) {
			console.log(jsonData.success);
			$("#idCheckResult").html(
					"<font style = 'color:red'>이미 있는 아이디입니다.</font>");
			$("#submitBut").attr("disabled", true);
		} else {
			$("#idCheckResult").html(
					"<font style = 'color:green'>사용 가능한 아이디입니다.</font>");
			$("#submitBut").attr("disabled", false);
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

				<form id="join-form" name="joinForm" method="post"
					action="insert.do">
					<label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text"> <label class="block-label"
						for="email">이메일</label> <input id="email1" name="email"
						type="email" placeholder="email 형식으로 입력"> <input
						type="button" value="id 중복체크" id="idCheck" class="btn btn-warning">
					<div id="idCheckResult"></div>
					<label class="block-label">패스워드</label> <input name="password"
						type="password">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" id="submitBut" value="가입하기"
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