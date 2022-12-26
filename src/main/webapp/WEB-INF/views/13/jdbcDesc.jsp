<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	jdbc 드라이버란? java.sql 패키지의 인터페이스들에 대한 구현 객체 모음.
	
	JDBC 단계
	1. 벤더 제공 드라이버 확보
	2. 드라이버를 빌드패스에 추가
	3. 드라이버 로딩
	4. Connection 수립
	5. 쿼리 객체 생성
		- Statement : 기본 쿼리 객체로 런타임에 쿼리가 동적 변경될 수 있음. sql injection에 취약함.
		- PreparedStatement : 쿼리 파라미터를 포함하여, 쿼리 객체시 쿼리를 미리 컴파일함.
		- CallableStatement : function/procedure 와 같은 절차적 코드 집합을 실행할때 사용하는 쿼리 객체.
	6. 쿼리 실행
		- ResultSet executeQuery : SELECT
		- int executeUpdate : INSERT, UPDATE, DELETE
	7. ResultSet 핸들링
	8. close : 세션 종료
</pre>
<form>
	<input type="text" name="propertyName" placeholder="검색할 프로퍼티명" value="${param.propertyName }"/>
	<input type="submit" value="검색"/>
</form>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach items="${list }" var="propVO">
					<tr>
						<td>${propVO.propertyName }</td>
						<td>${propVO.propertyValue }</td>
						<td>${propVO.description }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3">조회 결과 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</body>
</html>




