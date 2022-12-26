<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>${header['user-agent'] }</h4>
<h4>jQuery와  BootStrap 사용 가능한 페이지</h4>
<jsp:include page="${contentPage }"/>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>