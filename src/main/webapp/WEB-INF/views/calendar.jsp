<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<c:set var="dayCount" value="1"/>
<c:set var="offset" value="${calendar.offset }" />
<c:set var="lastDate" value="${calendar.lastDate }" />
<c:set var="weekDays" value="${calendar.weekDays }" />
<c:set var="months" value="${calendar.months }" />
<h4>
<a href="#" class="moveBtn" data-year="${calendar.beforeYear }" data-month="${calendar.beforeMonth }">이전달</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
${calendar }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="#" class="moveBtn" data-year="${calendar.nextYear }" data-month="${calendar.nextMonth }">다음달</a>
</h4>
<form id="calendarForm">
   <input type="number" name="year" placeholder="2022" value="${calendar.currentYear }"/>
   <select name="month">
      <c:forEach items="${months }" var="monthTxt" varStatus="vs">
         <c:if test="${not empty monthTxt }">
            <c:set var="selected" value="${vs.index eq calendar.currentMonth ? 'selected' : '' }" />
            <option value="${vs.index }" ${selected } >${monthTxt }</option>
         </c:if>
      </c:forEach>
   </select>
   <select name="language">
      <c:forEach items="${Locale.getAvailableLocales() }" var="tmp">
         <c:if test="${not empty tmp.displayLanguage }">
            <c:set var="selected" value="${tmp eq calendar.locale ? 'selected' : '' }" />
            <option value="${tmp.toLanguageTag() }" ${selected }>${tmp.displayLanguage }(${tmp.displayCountry }) </option>
         </c:if>
      </c:forEach>
   </select>
   <input type="submit" value="전송" />
</form>
<table>
<thead>
   <tr>
      <c:forEach var="idx" begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
         <td>${weekDays[idx] }</td>
      </c:forEach>
   </tr>
</thead>
<tbody>
<c:forEach begin="1" end="6">
   <tr>
   <c:forEach begin="<%=Calendar.SUNDAY %>" end="<%=Calendar.SATURDAY %>">
      <c:set var="dayStr" value="${dayCount - offset}"/>
      <c:choose>
         <c:when test="${dayStr gt 0 and dayStr le lastDate }">
            <td>${dayStr }</td>
         </c:when>
         <c:otherwise>
            <td>&nbsp;</td>
         </c:otherwise>      
      </c:choose>
      <c:set var="dayCount" value="${dayCount+1 }"/>      
   </c:forEach>
   </tr>
</c:forEach>
</tbody>
</table>
<script type="text/javascript">
   let calendarForm = $("#calendarForm");
   $("a.moveBtn").on("click", function(event){
      let year = $(this).data("year");
      let month = $(this).data("month");
      
      calendarForm.find("[name=year]").val(year);
//       calendarForm.get(0).month.value = month;
      calendarForm.find("[name=month]").val(month);
      calendarForm.submit();
   });
</script>
</body>
</html>

