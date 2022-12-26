<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Language)</h4>
<pre>
	: 표현(속성 데이터 출력)을 목적으로 하는 스크립트 형태의 언어
	<%
		String data = "데이터";
		pageContext.setAttribute("attr-Data", data);
	%>
	1. 속성(EL 변수(스코프안에 저장되어 있는 데이터)) 접근 방법
		<%=data %>, ${attr-Data }
	pageScope, requestScope, sessionScope, applicationScope
		${pageScope.attr-Data } , ${pageScope['attr-Data'] }
	2. 연산자 종류
		산술연산자 : +-*/%
			${3+4 }, ${"3"+4 }, ${"3"+"4" }, ${attr+4 }, \${"a"+4 }
			${4/3 }, ${attr*data }
		논리연산자 : &&(and, short-curkit), ||(or, ,short-curkit), !(not)
			${true and true } ${"true" and true }, ${true or "false" }, ${false or attr }, ${not attr }
		비교연산자 : >(&gt;(gt)), <(&lt;(lt)), >=(ge), <=(le), ==(eq), !=(ne)
			${3 lt 4 }, ${4 ge 3 }, ${4 eq 3 }, ${4 ne 3 }
		단항연산자 : empty , exists -> null -> length, size check
		<%
			pageContext.setAttribute("attr", " "); 
			pageContext.setAttribute("listAttr", Arrays.asList("a", "b"));
		%>
			${empty attr }
			list attr : ${not empty listAttr }
		삼항연산자 : 조건식? 참표현:거짓표현
			${not empty attr ? "attr 비어있음" : attr}
			${listAttr }, ${not empty listAttr ? listAttr : "기본값" }
	3. (속성)객체에 대한 접근법
		<%
			MemoVO memo = new MemoVO();
			pageContext.setAttribute("memoAttr",memo);
			memo.setWriter("작성자");
		%>
		${memoAttr }, ${memoAttr.writer }, ${memoAttr['writer'] }
	4. (속성)집합 객체에 대한 접근법 : <a href="elCollection.jsp">elCollection.jsp</a>
	5. EL 기본객체 : <a href="elObject.jsp">elObject.jsp</a>
</pre>
</body>
</html>