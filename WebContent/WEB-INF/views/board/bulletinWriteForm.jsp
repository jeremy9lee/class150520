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
			<div id="guestbook">
			<center><h2>자유게시판</h2></center>
				<form action="bulletinWrite.do" method="post">
					<table class="table table-hover">
						<tr  class="info">
							<td colspan='2'>작성자  : <span>${session} </span></td>
							</tr>
							<tr>
							<td>TITLE</td>
							<td><input type="text" name="title" style="width : 98%;"></td>
						</tr>
						<tr>
							<td colspan=4 style="height: 100%; width:100%"><textarea name="content" id="content" style="width: 100%;" ></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right>
							<input type="submit" VALUE=" save " class="btn btn-info">
							<input type="reset"	VALUE=" cancel " class="btn btn-info"></td>
							
						</tr>
					</table>
					<input type = "hidden" name = "sessionNo" value ="${sessionNo }">
						<input type = "hidden" name = "name" value ="${session }">
				</form>
			</div>
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