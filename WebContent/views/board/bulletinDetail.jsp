<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp"></c:import>
		</div>
		<div id="content">
			<div id="bulletinboard">
				<center><h2>자유게시판</h2></center>
				<ul>
					<li>
						<table  style="width: 100%" class="table table-hover">
							<tr align="center" style="font-weight: bold"  class="info">
								<td>NO</td>
								<td>TITLE</td>
								<td>WRITER</td>
								<td>DATE</td>
							</tr>
							<tr>
								<td>${bulletin.b_no}</td>
								<td>${bulletin.b_title}</td>
								<td>${bulletin.name}</td>
								<td>${bulletin.reg_date}</td>
								
							</tr>
							<tr>
								<td colspan=4>${bulletin.content}</td>
							</tr>

							<tr>
								<td>prev</td>
								<c:choose>
									<c:when test="${prevAndNext.prevNo eq 0}">
										<td colspan='3'>${prevAndNext.prevTitle}</td>
									</c:when>
									<c:otherwise>
										<td colspan='3'><a
											href="bulletinDetail.do?no=${prevAndNext.prevNo}">${prevAndNext.prevTitle}</a></td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>next</td>
								<c:choose>
									<c:when test="${prevAndNext.nextNo eq 0}">
										<td colspan='3'>${prevAndNext.nextTitle}</td>
									</c:when>
									<c:otherwise>
										<td colspan='3'><a
											href="bulletinDetail.do?no=${prevAndNext.nextNo}">${prevAndNext.nextTitle}</a></td>
									</c:otherwise>
								</c:choose>

							</tr>
						</table> <br>
						<div id="buttonArea" align="right">
							<c:if test="${bulletin.memberno eq sessionNo}">
								<a href="bulletinUpdateForm.do?no=${bulletin.b_no}"><input
									type="button" value="Modify" style="width: 100px" class="btn btn-info"></a>
								<a href="bulletinDelete.do?b_no=${bulletin.b_no}"><input type="button"
								value="Delete" style="width: 100px" class="btn btn-info"></a>
							</c:if>
							<a href="bulletinMain.do"><input type="button"
								value="List" style="width: 100px" class="btn btn-info"></a>
						</div> <input type="hidden" name="sessionNo" value="${sessionNo }">
						<input type="hidden" name="name" value="${session }"> <br>
					</li>
				</ul>
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