<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function goToMain() {
		location.href = "main.do";
	}
</script>
<div onclick="goToMain()">
	<h1>MySite</h1>
</div>
<ul>

	<c:if test="${empty session}">
		<li id="login"><a href="loginform.do">로그인</a>
		<li>
		<li><a href="goToJoinForm.do">회원가입</a>
		<li>
	</c:if>
	<c:if test="${not empty session}">
		<li><a href="updateMemberForm.do">회원정보수정</a>
		<li>
		<li id="logout"><a href="logout.do">로그아웃</a>
	</c:if>

	<li><c:choose>
			<c:when test="${not empty session}">
				<li>${session}님 안녕하세요^^;</li>
			</c:when>
			<c:otherwise>
				<li class="selected">로그인을 해주세요</li>
			</c:otherwise>
		</c:choose>
</ul>
