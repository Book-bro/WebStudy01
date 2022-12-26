package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/imageForm.do")
public class ImageStreamingFormServlet02 extends HttpServlet{
	
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
		
		String pattern = "<option>%s</option>\n";
		StringBuffer options = new StringBuffer();
		for(File tmp : imageFiles) {
			options.append(String.format(pattern, tmp.getName()));
		}
		
		req.setAttribute("cPath", req.getContextPath());
		req.setAttribute("options", options);
		
//		req.getRequestDispatcher("/01/imageForm.tmpl").forward(req, resp);
		String viewName = "/WEB-INF/views/01/imageForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
