<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
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
			<div id="guestbook">
				<form action="guestinsert.do" method="post">
					<table class="table table-hover">
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 " class = "btn btn-info"></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
					
					<c:if test="${not empty list }">
					<c:forEach items="${list}" var="g">
						<table>
							<tr>
								<td>${g.no}</td>
								<td>${g.name}</td>
								<td>${g.reg_date}</td>
								<td><a href="guestdeleteForm.do?id=${g.no}">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>${g.message}</td>
							</tr>
						</table>
						<br>
					</c:forEach>
					</c:if>
					</li>
				</ul>
			</div>
		</div>
		<div id="navigation">
		<c:import url="/views/include/navigation.jsp"></c:import>
		</div>
		<div id="footer">
			<p>(c)opyright 2014 </p>
		</div>
	</div>
</body>
</html>