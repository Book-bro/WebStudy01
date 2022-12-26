package kr.or.ddit.servlet01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//xml에도 설정하면 멀티
//마크어노테이션 @WebServlet()
//싱글밸류어노테이션 @WebServlet(asd)
//멀티마크어노테이션 @WebServlet(value="/01/imageForm.do",initParams= {@WebInitParam(name="imageFolder",value="D:\\contents\\images")})

//1번째단계: 위치 가져오기
//2 : 폴더안에 자식찾기
@WebServlet(value="/01/imageForm.do")
//,initParams= {@WebInitParam(name="imageFolder",value="D:\\contents\\images")})
public class ImageStreamingFormServlet01 extends HttpServlet{
	private ServletContext application;  //싱글톤
	private String imageFolder;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");  //멀티밸류생략
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//슈퍼코드 제거해야 405에러 안뜸
		//mime은 출력스트림 하기전 써야함
		resp.setContentType("text/html;charset=UTF-8");
		
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)->{
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/");  //null 중요
		});
		StringBuffer content = new StringBuffer();
		content.append("<html>\n");
		content.append("	<body>         \n");
		content.append(String.format("<form action='%s/imageStreaming.do'>\n",req.getContextPath()));
		content.append("		<select name='image'>   \n");
		
		String pattern = "<option>%s</option>\n";
		for(File tmp : imageFiles) {
			content.append(String.format(pattern, tmp.getName()));
		}
		
		content.append("		</select>  \n");
		content.append("<input type='submit' value='전송' />");
		content.append("</form>\n");
		content.append("	</body>        \n");
		content.append("</html>            \n");
		
		// try with resource라고함, 장점은 알아서 close가 됨
		try(
			//string을 받으므로 writer
			PrintWriter out = resp.getWriter();
		){
			out.println(content);
		}
	}
}
