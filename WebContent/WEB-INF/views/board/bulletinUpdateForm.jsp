<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp"></c:import>
		</div>
		<div id="content">
		<form action="bulletinUpdate.do" method="post">
			<div id="bulletinboard">
				<center><h2>자유게시판</h2></center>
				<ul>
					<li>
						<table style="width: 100%" class="table table-hover">
						<tr align="center" style="font-weight: bold"  class="info">
						<td>NO</td>
						<td>TITLE</td>
						<td>WRITER</td>
						<td>DATE</td>
						</tr>
										<tr>
											<td>${bulletin.b_no}</td>
											<td><input type="text" name="title" style="width : 98%;" value = "${bulletin.b_title}"></td>
											<td>${bulletin.name}</td>
											<td>${bulletin.reg_date}</td>
										</tr>
										<tr>
											<td colspan=4><textarea name="content" id="content" style="width: 100%;">${bulletin.content}</textarea></td>
										</tr>
						</table>
						<br>
						<div id = "buttonArea" align="right">
						<input type = "submit" value = "modify" style="width: 100px" class="btn btn-info">
						</div>
						<input type = "hidden" name = "b_no" value = "${bulletin.b_no}">
						<input type = "hidden" name = "sessionNo" value ="${sessionNo }">
						<input type = "hidden" name = "name" value ="${session }">
						<br>
					</li>
				</ul>
			</div>
			</form>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp"></c:import>
		</div>
		<div id="footer">
			<p>(c)opyright 2014</p>
		</div>
	</div>

</body>
</html>