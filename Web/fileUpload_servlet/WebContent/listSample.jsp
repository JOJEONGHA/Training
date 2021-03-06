<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<head>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
</style>
<meta charset="UTF-8">
<title>Sample FileUpload</title>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>번호</td>
			<td>내용</td>
			<td>파일제목</td>
		</tr>
		<c:choose>
			<c:when test="${listSample ==null }">
				<tr height="10">
					<td colspan="4">
						<p align="center">
							<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>
			<c:when test="${listSample !=null }">
				<c:forEach var="list" items="${listSample}"
					varStatus="articleNum">
					<tr align="center">
						<td width="5%">${list.num}</td>
						<td width="10%">${list.title}</td>
						<td width="10%">${list.imageFileName}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a class="cls1" href="${contextPath}/upload/addSample.do">
	<p class="cls2">글쓰기</p></a>
</body>
</html>
