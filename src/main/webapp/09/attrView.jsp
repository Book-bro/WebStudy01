<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>공유된 속성 데이터들</h4>
<pre>
	page scope : <%=pageContext.getAttribute("pageAttr") %>, ${pageAttr }
	request scope : <%=request.getAttribute("requestAttr") %>, ${requestAttr }
	session scope : <%=session.getAttribute("sessionAttr") %>, ${sessionAttr }
	<%
		// flash attribute
		session.removeAttribute("sessionAttr");
	%>
	application scope : <%=application.getAttribute("applicationAttr") %>, ${applicationAttr }
</pre>
</body>
</html>