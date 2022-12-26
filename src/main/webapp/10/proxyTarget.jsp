<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty param.target }">
	<c:import url="${param.target }" var="content"/>
	<c:out value="${content }" escapeXml="${not empty param.source }"/>
</c:if>