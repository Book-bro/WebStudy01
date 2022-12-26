<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="7kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4>response(HttpServletResponse)</h4>
<pre>
	Http 의 response packaging
	1. Response Line : Status Code(응답상태코드, XXX)
		100~ : ...ING...
		200~ : OK
		300~ : 최종 처리하기 위해 클라이언트의 추가 액션이 필요함. (response body가 없음.)
			304(cache data관련) : Not Modified
			301/302/307 : Moved + Location response header 와 함께 사용.(redirect request)
			<%--
// 				request.getRequestDispatcher("/04/messageView.jsp").forward(request, response); // 서버 내에서 이동
				String location = request.getContextPath() + "/04/messageView.jsp";
				response.sendRedirect(location); // 클라이언트로부터 새로운 요청이 발생.
			--%>
		400~ : client side error -> Fail 
			400 : <%=HttpServletResponse.SC_BAD_REQUEST %>, 클라이언트 전송 데이터(파라미터) 검증시 활용.
			401 / 403 : 인증(Authentication)과 인가(Authorization) 기반의 접근 제어에 활용
				<%=HttpServletResponse.SC_UNAUTHORIZED %>, <%=HttpServletResponse.SC_FORBIDDEN %>
			404 : <%=HttpServletResponse.SC_NOT_FOUND %>
			405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>, 현재 요청의 메소드에 대한 콜백 메소드가 재정의되지 않았을때.
			406 / 415 : content-type(MIME) 과 관련된 상태코드 
				<%=HttpServletResponse.SC_NOT_ACCEPTABLE %>: Accept request 헤더에 설정된 MIME 데이터를 만들어낼수 없을때.
					ex)accept:application/json
					   content-type:application/json(XXX)
				<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %> : Content-Type request 헤더를 해석할 수 없을때.
				ex)content-type:application/json --> unmarshalling(XXX)
		500~ : server side error -> Fail, 500(Internal Server Error)
		
	2. Response Header :  meta data
		Content(body) 에 대한 부가정보 설정  : Content-*, Content-Type(MIME), Content-Length(size)
								Content-Disposition(content name, 첨부여부)
			<%--
				response.setHeader("Content-Dispostion", "inline[attatchement];filename=\"파일명\"");
			--%>					
		Cache control : 자원에 대한 캐싱 설정
		Auto Request : 주기적으로 갱신되는 자원에 대한 자동 요청
		Location 기반의 이동구조(Redirection).
	3. Response Body(Content body, message body) :	 
<%-- 		response.getWriter(), response.getOutputStream(), <%= %>, out --%>
</pre>
</body>
</html>














