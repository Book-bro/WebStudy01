<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/viewCookie.jsp</title>
</head>
<body>
<h4>동일경로</h4>
<pre>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie tmp : cookies){
				out.println(
					String.format("%s : %s", tmp.getName(), URLDecoder.decode(tmp.getValue(),"UTF-8"))	
				);
			}
		}
			
	%>
</pre>
</body>
</html>