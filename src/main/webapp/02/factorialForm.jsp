<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
// 	$(document).ready(function(){});
// 	$(document).on("ready",function(){});
	$(function(){
		let resultArea = $("#resultArea")
		//[name]: name을 가진 모든태그
		$("form[name]").on("submit", function(event){
			event.preventDefault();
			console.log(this); //그냥 html출력
			console.log($(this)); //배열형태로 관리됨
			console.log($(this)[0]); //(this)와 같음
			console.log($(this).get(0)); //(this)와 같음
			let url = this.action;
			let method = this.method;
			let data = $(this).serialize();
			console.log(data);
			$.ajax({
				url : url,
				method : method,
				data : data,
				dataType : "html",
				success : function(resp) {
					resultArea.html(resp);
				},
				error : function(jqXHR, status, error) {
					console.log(jqXHR);
					console.log(status);
					console.log(error);
				}
			});
			return false; //이벤트 중단시키기
		});
		
	});
</script>
</head>
<body>
<form name="facForm" action="<%=request.getContextPath() %>/02/factorial.do">
	<input type="number" name="number" />
	<input type="submit" value="전송" />
	<input type="reset" value="취소" />
	<input type="button" value="그냥 버튼" />
</form>
<div id="resultArea">

</div>
</body>
</html>