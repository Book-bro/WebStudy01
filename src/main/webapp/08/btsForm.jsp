<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<select name="member">
	<option value>멤버선택</option>
</select>
<script>
	let memberSelect = $('[name="member"]').on('change',function(event){
		let code = $(this).val();
<%-- 		location.href = "<%=request.getContextPath() %>/bts/" + code; --%>
		$.ajax({
			url : "<%=request.getContextPath() %>/bts/" +code,
			dataType : "html",
			success : function(resp) {
				memberSelect.after(resp);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
	});
	$.ajax({
		url : "<%=request.getContextPath() %>/bts",
		dataType : "json",
		success : function(resp) {
			let options = [];
			$.each(resp.bts,function(code, values){
				let option = $("<option>").val(code)
									.text(values[0]);
				options.push(option);
			});
			memberSelect.append(options);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
</script>
<jsp:include page="/includee/preScript.jsp"/>
</body>
</html>