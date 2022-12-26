<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script src="<%=request.getContextPath() %>/resources/js/custom.js"></script>
</head>
<body>
<h4>Restful 기반의 메모 관리</h4>
<form name="memoForm" action="${pageContext.request.contextPath}/memo" method="post">
	<input type="text" name="writer" placeholder="작성자"/>
	<input type="date" name="date" placeholder="작성일"/>
	<textarea name="content"></textarea>
	<input type="submit" value="등록" />
</form>
<table class="table-bordered">
	<thead>
		<tr>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
// 	$('[name="memoForm"]')
	let memoForm = $(document.memoForm).on('submit',function(event){
		event.preventDefault();
// 		event.target
// 		this
// 		$(this)
		let url = this.action;
		let method = this.method;
// 		let data = $(this).serialize(); // writer=작성자&date=작성일&content=내용(QueryString)
		let data = $(this).serializeObject(); // writer=작성자&date=작성일&content=내용(QueryString)
		$.ajax({
			url : url,
			method : method,
			contentType:"application/json;charset=UTF-8", // request content-type			
			data : JSON.stringify(data),
			dataType : "json", // request Accept , response content-type
			success : function(resp) {
				makeListBody(resp.memoList);
				memoForm[0].reset(); 
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return false;
	});
// 	EDD(Event Driven Development), TDD(Test Driven Development)
	$("#exampleModal").on("show.bs.modal", function(event){
// 		this==event.target : modal 창
		let memo = $(event.relatedTarget).data("memo"); // modal을 오픈할때 사용한 클릭 대상, tr
		$(this).find(".modal-body").html(memo.content);
	}).on("hidden.bs.modal",function(event){
		$(event.target).find(".modal-body").empty();
	});

	let listBody = $("#listBody");
	let makeListBody = function(memoList){
		listBody.empty();
		let trTags = [];
		if(makeListBody.length>0){
// 			data-bs-toggle="modal" data-bs-target="#exampleModal"
			$.each(memoList, function(index, memo){
				let tr = $("<tr>").append(
					$("<td>").html(memo.writer)		
					,$("<td>").html(memo.date)		//또다른 참조 방법, this.date
				).attr({
					"data-bs-toggle":"modal"
					, "data-bs-target":"#exampleModal"
				}).data("memo", memo);
				trTags.push(tr);
			});
		}else{
			let tr = $("<tr>").html(
				$("<td>")
					.attr("colspan", "2")		
					.html("작성된 메모 없음.")
			);
			trTags.push(tr);
		}
		listBody.append(trTags);
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/memo",
		method : "get",  
		dataType : "json",
		success : function(resp) {
			makeListBody(resp.memoList);
		},
		error : function(jqXHR, status, error) {
			console.log(jqXHR);
			console.log(status);
			console.log(error);
		}
	});
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>