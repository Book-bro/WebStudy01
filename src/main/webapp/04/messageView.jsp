<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/messageView.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<select id="selName">
	<option>prop1</option>
	<option>prop2</option>
	<option>prop3</option>
	<option>prop4</option>
	<option>prop5</option>
	<option>hi</option>
</select>
<h4 id="greetingArea" data-key1="prop1" data-key2="sample" data-other-key="TEST"></h4>
<input type="radio" name="dataType" data-data-type="json" checked/>JSON
<input type="radio" name="dataType"  data-data-type="xml"/>XML
<input type="radio" name="dataType"  data-data-type="text"/>PLAIN

<input type="radio" name="language" data-locale="ko"/>한국어
<input type="radio" name="language" data-locale="en"/>영어
<script>
	let greetingArea = $("#greetingArea");
	console.log(greetingArea.data("key1"));
	console.log(greetingArea.data("otherKey"));
	greetingArea.data("key2", "otherValue");
	console.log(greetingArea.data("key2"));
	let radioBtns = $('[type="radio"]');
	let dataTypes = radioBtns.filter("[name=dataType]");
	let locales = radioBtns.filter("[name=language]");
	let successes={
		json:function(resp){
			console.log(resp);
			greetingArea.text(resp.message);
		},
		xml:function(docResp){
			console.log(docResp);
			let message = $(docResp).find("message").text();
			greetingArea.html(message);
		},
		text:function(plain){
			console.log(plain);
			greetingArea.html(plain);
		}
	}
	
	let settings={
			url : "<%=request.getContextPath() %>/04/getGreetingMessage",
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		};
	
	
	
	radioBtns.on("change", function(){
// 		greetingArea.empty();
		greetingArea.html("");
		let dataType = dataTypes.filter(":checked").data("dataType");
		settings.dataType=dataType;
		settings.success=successes[dataType];
		settings.data={
// 			name:greetingArea.data("key1")	
			name:greetingArea.attr("data-key1")	
		}
		let locale = locales.filter(":checked").data("locale");
		if(locale){
// 			settings.data={
// 				locale:locale
// 			}
			settings.data.locale=locale;
		}
		console.log("===================")
		console.log(settings);
		console.log("===================")
		$.ajax(settings);
	}).trigger("change");
	
	
	
</script>
</body>
</html>


















