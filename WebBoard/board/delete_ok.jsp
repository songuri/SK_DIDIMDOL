<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><%@ page import="java.util.*" %>
<%@ page import="com.sk.jsp.*" %>
			
<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int pg = Integer.parseInt(request.getParameter("pg"));
	String password = request.getParameter("password");
	
	BoardManager bm = new BoardManager();
	
	
	if( bm.checkPassword(idx, password) ) {
		bm.delete(idx);
			   			
	 
%>
  			<script language=javascript>
   				self.window.alert("해당 글을 삭제하였습니다.");
   				location.href="list.jsp?pg=<%=pg%>";
  			</script>

<%
		
		 } else { 
%>
			<script language=javascript>
			 self.window.alert("비밀번호를 틀렸습니다.");
				location.href="javascript:history.back()";
			</script>
<%		
		 } 	
%>