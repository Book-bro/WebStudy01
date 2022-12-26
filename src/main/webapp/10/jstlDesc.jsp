<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그들 중에서 많이 활용될수 있는 것들을 모아놓은 라이브러리.
	&lt;prefix:tagname attr_name="attr_value"&gt;
	1. 커스텀 태그 로딩 : taglib directive
	2. prefix 를 통한 태그 접근
	
	** core 태그 종류
	1. EL 변수(속성)와 관련된 태그 : set, remove
		<c:set var="sample" value="샘플값" scope="session"/>
		${sample }, ${sessionScope.sample }
		<c:remove var="sample" scope="session"/>
		--> ${sample }
	2. 조건문 : if(조건식){블럭문}else{}, switch~case(조건값)...~default
		단일조건문 : if
			<c:if test="${empty param.name1 }">
				파라미터 없음.
			</c:if>
			<c:if test="${not empty param.name1 }">
				파라미터 있음.
			</c:if>
		다중조건문 : choose ~ when ~ otherwise
			<c:choose>
				<c:when test="${empty param.name1 }">
					파라미터 없음.
				</c:when>
				<c:otherwise>
					파라미터 있음.
				</c:otherwise>
			</c:choose>
	3. 반복문 : foreach, forTokens, for(선언절, 조건절, 증감절) for(임시 블럭 변수 : 반복대상 집합객체)
		<c:set var="array" value='<%=new String[]{"value1","value2"} %>'/>
		<c:forEach var="i" begin="0" end="${fn:length(array)-1 }" step="1" varStatus="vs">
			${array[i] }--> 현재 반복의 상태(LoopTagStatus) : ${vs.index }, ${vs.count }, ${vs.first }, ${vs.last }
		</c:forEach>
		<c:forEach items="${array }" var="element">
			${element }
		</c:forEach>
		
		int num = 3;
		intnum=3;
		<c:forTokens items="아버지 가방에 들어가신다" delims=" " var="token">
			${token }
		</c:forTokens>
		<c:forTokens items="1,2,3,4" delims="," var="token">
			${token * 10}
		</c:forTokens>
		
	4. 기타
		url 재작성 : url(client side path, session parameter), redirect
			<c:url value="/06/memoView.jsp"/>
			<a href="<c:url value='/10/jstlDesc.jsp'/>">세션 유지</a>
<%-- 			<c:redirect url="/06/memoView.jsp"/> --%>
			<%--
				String location = request.getContextPath()+"/06/memoView.jsp";
				response.sendRedirect(location);
			--%>
		표현구조 : out
			
			<c:out value="<h4>출력값</h4>" escapeXml="false"/>
</pre>
<c:import url="https://www.naver.com" var="naver"/>
<c:out value="${naver }" escapeXml="true"/>
</body>
</html>






















