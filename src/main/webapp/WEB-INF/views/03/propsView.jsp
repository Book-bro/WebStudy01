<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h4>properties 파일 뷰어</h4>
<label>
   <input type="radio" name="dataType" value="json" checked />JSON
</label>
<label>
   <input type="radio" name="dataType" value="xml"/>XML
</label>
<button type="button" class="LoadData">LOAD DATA</button>
<button type="button" class="clearData">CLEAR DATA</button>
<table>
<thead>
   <tr>
      <th>key</th>
      <th>value</th>
   </tr>
</thead>
<tbody id="listBody">

</tbody>

</table>
<script>
   let listBody = $("#listBody");
   let dataTypes = $("[name=dataType]");
   let makeTrTag = function(name,value){
      let tr = $("<tr>").append(
            $("<td>").html(name)      
            ,$("<td>").html(value)      
      );
      return tr;
   }
   
   let sucesses = {
      json:function(resp){
         let trTags = [];
         $.each(resp,function(name,value){
            trTags.push(makeTrTag(name,value));
         });
         listBody.empty(); //자식들을 지움 !=remove
         listBody.append(trTags);
      },
      xml:function(domResp){
         let root = $(domResp).find("Properties");
         let trTags = [];
         root.children().each(function(idx,child){
            let name = child.tagName;
            let value = child.innerHTML;
            let tr = makeTrTag(name,value);
            trTags.push(makeTrTag(name,value));
         });
         listBody.empty(); //자식들을 지움 !=remove
         listBody.append(trTags);
      }
   }

   let btn = $(".LoadData").on("click",function(){
      let dataType = dataTypes.filter(":checked").val();
      $.ajax({
         dataType : dataType,
         success : sucesses[dataType],
         error : function(jqXHR, status, error) {
            console.log(jqXHR);
            console.log(status);
            console.log(error);
         }
      });
   });
   
   let clearBtn = $(".clearData").on("click",function(){
      listBody.empty();
   });
</script>
<!-- <img src="../../resources/images/cat1.jpg"> -->
<%-- <img src="<%=request.getContextPath() %>/resources/images/cat1.jpg"> --%>
</body>
</html>