<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
   <form action='${cPath}/imageStreaming.do'>
      <select name='image'>
      ${options}
      </select> <input type='submit' value='전송' />
   </form>
   <img src="">
<script type="text/javascript">
   $('[name=image]').on("change", function(event){
      event.target===this;
//       this.form.submit(); // submit 이벤트가 발생하지 않음
//        $(this).parents("form").submit(); // 위와 같지만 submit 이벤트가 발생함
      console.log($(this).val());
      $('img').attr("src", '${cPath}/imageStreaming.do?image=' + $(this).val());
   });
</script>
</body>
</html>