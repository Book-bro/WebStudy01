<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.MemoVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>성능 고려사항</h4>
<pre>
	응답 소요 시간 : latency time(99.999%) + processing time
	case 1 - 14ms
	case 2 - 1130ms, DBCP case 4 - 20ms
	case 3 - 26ms
	
	<%
		long startTime = System.currentTimeMillis();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  code, writer, content, \"DATE\"  ");
		sql.append(" FROM   tbl_memo                        ");
		for(int i=1; i<=100; i++){
			try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
		
				ResultSet rs = pstmt.executeQuery();
				List<MemoVO> memoList = new ArrayList<>();
				while (rs.next()) {
					MemoVO memo = new MemoVO();
					memoList.add(memo);
					memo.setCode(rs.getInt("CODE"));
					memo.setWriter(rs.getString("WRITER"));
					memo.setContent(rs.getString("CONTENT"));
					memo.setDate(rs.getString("DATE"));
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		long endTime = System.currentTimeMillis();
	%>
	소요시간 : <%=endTime-startTime %>ms
	
</pre>
</body>
</html>