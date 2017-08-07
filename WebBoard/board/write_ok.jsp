<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sk.jsp.*" %>
<%@ page import="java.io.*" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
  
<%
	String saveDir = application.getRealPath("/") + "download";
	int maxSize = 1024*1024*100;
	
	MultipartRequest multipartRequest
		= new MultipartRequest(request, saveDir, maxSize, "UTF-8", new DefaultFileRenamePolicy());

	String uname = multipartRequest.getParameter("uname");
	String password = multipartRequest.getParameter("password");
	String title = multipartRequest.getParameter("title");
	String memo = multipartRequest.getParameter("memo");
	String file = multipartRequest.getOriginalFileName("file");
	
	out.write(saveDir);
	out.write(uname + "<br>");
	out.write(password + "<br>");
	out.write(title + "<br>");
	out.write(memo + "<br>");
	out.write(file + "<br>");
	
	
	BoardManager bm = new BoardManager();	
	bm.insert(new Board(title, memo, uname, password, file));
	
%>
<script language=javascript>
   alert("입력한 글을 저장하였습니다.");
   location.href="list.jsp";
</script>