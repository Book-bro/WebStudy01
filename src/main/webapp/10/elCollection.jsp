<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elCollection.jsp</title>
</head>
<body>
<h4>EL에서 집합 객체 접근 방법</h4>
<%
	String[] array = new String[]{"value1","value2"};
	List<String> list = Arrays.asList(array);
	Set<String> set = list.stream().collect(Collectors.toSet());
	
	Map<String,Object> map = new HashMap<>();
	map.put("key-1","value1");
	map.put("key 2","value2");
	
	pageContext.setAttribute("array", array);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("set", set);
	pageContext.setAttribute("map", map);
	
	pageContext.setAttribute("memo", null);
%>
<pre>
	array : <%--=array[3] --%>, ${array[3] }
	list : <%--=list.get(3) --%>, ${list[3] }
	ex) memo's writer : ${memo.writer } , ${memo.getWriter() }, \${memo['writer'] }
	set : <%=set %>${set }
	map : <%=map.get("key-1") %>, ${map.get("key-1") }, ${map.key-1 }, ${map['key-1'] }
	<%=map.get("key 2") %>, ${map.get("key 2") }, \${map.key 2 }, ${map['key 2'] }
	ex) int i 2;
</pre>
</body>
</html>