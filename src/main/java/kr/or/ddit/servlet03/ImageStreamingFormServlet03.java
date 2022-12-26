package kr.or.ddit.servlet03;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 현재 컨트롤러가 받아야하는 요청의 종류.
 *	1. UI 제공 요청.
 *	2. 데이터 제공 요청.
 */
@WebServlet("/03/imageForm.do")
public class ImageStreamingFormServlet03 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	 	application.getMimeType(file)
		String imageFolder = getServletContext().getInitParameter("imageFolder");
//	 	resp.setContentType("text/html;charset=UTF-8");
		
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir, name)->{
			String mime = getServletContext().getMimeType(name);
			return mime != null && mime.startsWith("image/");  //null 중요
		});
		
//		req.setAttribute("imageFiles", imageFiles);
		
		String accept = req.getHeader("Accept");
		if(accept.contains("json")) { //특정 키워드 찾기
//			2. 데이터 제공 요청.
//			Marshalling
			String json =  marshalling(imageFiles);
			resp.setContentType("application/json");
			try (
					PrintWriter out = resp.getWriter();
			){
				out.print(json);
			}
		}else {
//			1. UI 제공 요청.
			String viewName = "/WEB-INF/views/02/imageForm_ajax.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
		
	}

	private String marshalling(File[] imageFiles) {
		StringBuffer json = new StringBuffer();
		json.append("[");
		String ptrn = "\"%s\":\"%s\"";
		for(File tmp : imageFiles) {
			String name = tmp.getName();
			String mime = getServletContext().getMimeType(name);
			json.append("{");
			json.append(String.format(ptrn, "name",name));
			json.append(",");
			json.append(String.format(ptrn, "mime",mime));
			json.append("}");
			json.append(",");
		}
		json.append("]");
		int lastIndex = json.lastIndexOf(",");
		if(lastIndex!=-1) {
			json.deleteCharAt(lastIndex);
		}
		return json.toString();
	}
}
