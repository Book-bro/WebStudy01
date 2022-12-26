<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table*{
		border: 1px solid;
	}

	.red{
		background-color:red; 
	}
	.green{
		background-color:green; 
	}
</style>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<!-- 1. JSTL 과 EL 2단부터 9단까지 구구단 출력(table 태그 활용). -->
<!-- 	(2*3=6) -->
<!-- 2. 세번째 row의 배경색을 빨간색으로 출력. (inline css 속성 사용하지 않도록.) -->
<!-- 3. 마지막 row의 배경색 초록색으로 -->
<c:set var="minDan" value="${empty param.min ? 2 : param.min }" />
<c:set var="maxDan" value="${not empty param.max ? param.max : 9 }" />
<form>
	<select name="min">
		<c:forEach var="dan1" begin="2" end="9">
			<option value="${dan1 }" ${dan1 eq minDan ? "selected":"" }>${dan1 }단</option>
		</c:forEach>
	</select>
	<select name="max">
		<c:forEach var="dan2" begin="2" end="9">
			<option value="${dan2 }" ${dan2 eq maxDan ? "selected":"" }>${dan2 }단</option>
		</c:forEach>	
	</select>
	<button type="submit">SUBMIT</button>
</form>
<table>
	<c:forEach var="i" begin="${minDan }" end="${maxDan}" varStatus="vs">
		<c:choose>
			<c:when test="${vs.count eq 3 }">
				<c:set var="clzAttr" value="red"/>
			</c:when>
			<c:when test="${vs.last}">
				<c:set var="clzAttr" value="green"/>
			</c:when>
			<c:otherwise>
				<c:set var="clzAttr" value="normal"/>
			</c:otherwise>
		</c:choose>
        <tr class="${clzAttr }">
        <c:forEach var="j" begin="1" end="9">
	        	<td>${i } x ${j } = ${i*j }</td>
        </c:forEach>
        </tr>
	</c:forEach>
</table>
<script type="text/javascript">
// 	$("[name=min]").val("${minDan}");
// 	$("[name=max]").val("${maxDan}");
</script>
</body>
</html>