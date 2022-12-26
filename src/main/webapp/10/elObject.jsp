<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elObject.jsp</title>
</head>
<h4>EL 기본 객체</h4>
<body>
<pre>
	1. scope 객체(Map&lt;String,Object&gt;) : pageScope, requestScope, sessionScope, applicationScope
	2. 파라미터 객체 : param(Map&lt;String,String&gt;), paramValues(Map&lt;String,String[]&gt;)
	<a href="?name1=value1&name2=value2">TEST</a>
		<%=request.getParameter("name1") %>, ${param.name1}, ${param['name1'] }
		<%--=request.getParameterValues("name1")[1] --%>, ${paramValues.name1[1] }, ${paramValues['name1'][1] }
	3. 요청 헤더 객체 : header(Map&lt;String,String&gt;), headerValues(Map&lt;String,String[]&gt;)
		<%=request.getHeader("user-agent") %>,
		 ${header.user-agent },
		 ${header['user-agent'] }
	4. 쿠키 객체 : cookie(Map&lt;String,Cookie&gt;) <%-- Cookie test; test.getMaxAge() --%>
		<%=request.getCookies() %>
		${cookie.JSESSIONID.getValue() }
		${cookie.JSESSIONID.value }
		${cookie['JSESSIONID'].value }
		${cookie['JSESSIONID']['value'] }
	5. 컨텍스트 파라미터 객체 : initParam(Map&lt;String,String&gt;)
		<%=application.getInitParameter("imageFolder") %>
		${initParam.imageFolder }, ${initParam['imageFolder'] }
	6. pageContext : ${pageContext.request.contextPath }, ${pageContext.session.id }
</pre>
<!-- 현재 요청의 파라미터 중 (sample) 이라는 이름의 파라미터 값을 출력하라(EL). -->
<!-- 단, 해당 파라미터가 없는 경우, ("SAMPLE")이라는 기본값을 사용함. -->
${not empty param['sample'] ? param.sample : "SAMPLE"}
<%=Optional.ofNullable(request.getParameter("sample")).orElse("SAMPLE")%>
</body>
</html>