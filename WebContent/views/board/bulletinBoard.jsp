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
						<td>HIT</td>
						</tr>
							<c:choose>
								<c:when test="${not empty listByAdmin }">
									<c:forEach items="${listByAdmin}" var="b">
										<tr style="font-weight: bold">
											<td>${b.b_no}</td>
											<td><a href = "bulletinDetail.do?no=${b.b_no}"><strong>${b.b_title}</strong></a></td>
											<td><b>${b.name}</b></td>
											<td>${b.reg_date}</td>
											<td>${b.hit }</td>
										</tr>
										<br>
									</c:forEach>
									<c:forEach items="${list}" var="b">
										<tr>
											<td>${b.b_no}</td>
											<td><a href = "bulletinDetail.do?no=${b.b_no}">${b.b_title}</a></td>
											<td>${b.name}</td>
											<td>${b.reg_date}</td>
											<td>${b.hit }</td>
										</tr>
										
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td>등록된 게시물이 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</table>
						<br>
						<div id = "buttonArea" align="right">
						<a href = "bulletinWriteForm.do"><input type = "button" value = "write" class="btn btn-info"></a>
						</div>
						<input type = "hidden" name = "sessionNo" value ="${sessionNo }">
						<input type = "hidden" name = "name" value ="${session }">
						<br>
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