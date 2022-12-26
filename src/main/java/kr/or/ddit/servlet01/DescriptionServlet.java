package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿
 * 		: 웹상에서 발생하는 요청을 처리하고, 그에 따른 동적 응답을 생성하기 위한 객체의 필요요건(명세, specification)
 *	개발 단계
 *	1. HttpServlet 상속
 *	2. callback 메소드 재정의
 *	3. 컴파일 /WEB-INF/classes(context 의 최우선 classpath)에 배포.
 *	4. WAS(Serlvet Container)에 등록.
 * 		: web.xml -> servlet -> servlet-name, servlet-class (2.X)
 * 		: @WebServlet (3.X)
 * 	5. 서블릿 매핑 설정
 * 		: web.xml -> serlvet-mapping -> servlet-name, url-pattern (2.X)
 * 		: @WebServlet(속성들) (3.X)
 * 	6. 컨테이너 재구동
 * 
 * 	** container?
 * 		: 컨테이너에 의해 관리되는 서블릿 객체의 생명주기 제어.
 * 	서블릿 객체의 싱글턴 인스턴스 생성 : 구체적인 설정이 없는 한(loadOnStartup), 매핑된 조건의 요청이 발생했을때 생성.
 * 
 * 	** 서블릿에서 재정의할 수 있는 콜백 메소드 종류.
 * 	생명주기 이벤트
 * 		생성 : init
 * 		소멸 : destroy
 * 	요청 관리 이벤트(정해진 횟수 없음) : service, doXXX
 * 	
 * 	callback : 관련 이벤트가 발생했을때 시스템 내부적으로 자동 호출되는 코드 형태.
 * 	$("button").on("click", function(){
 * 		// 실행 코드
 * })
 */
@WebServlet(value="/desc.do", loadOnStartup=1)
public class DescriptionServlet extends HttpServlet{
	
	public DescriptionServlet() {
		super();
		//qualified name 출력
		System.out.printf("%s 생성\n",this.getClass().getName());  //this = 서블릿의 객체
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);  //슈퍼코드 살려야함
		System.out.printf("%s 서블릿 인스턴스 초기화\n", this.getClass().getSimpleName());
	}
	
	@Override
	public void destroy() { //슈퍼코드 필요없음, 갖고있는게 없음
		System.out.printf("%s 서블릿 인스턴스 소멸\n", this.getClass().getSimpleName());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("============service 시작============");
		//super.service(req, resp);  //doGet이 실행됨
		System.out.println("============service 종료============");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//확장 cgi방식을 보여주기 위함
		//10개의 스레드가 반복됨 
		//스레드 쿨링 정책을 씀
		//돌려막기 = 쿨링
		//자원의 관리를 효율적으로 할 수 있음
		//*서블릿은 톰캣이 관리*
		resp.getWriter().println(String.format("description-%s",Thread.currentThread().getName()));//현재요청을 처리하는 쓰레드
		System.out.println("doGet 실행");
	}
}
