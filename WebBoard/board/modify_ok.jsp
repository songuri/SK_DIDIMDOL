<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%><%@ page import="java.util.*" %>
<%@ page import="com.sk.jsp.*" %>

  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			
<%
	int idx = Integer.parseInt(request.getParameter("idx"));
	int pg = Integer.parseInt(request.getParameter("pg"));
	String title = request.getParameter("title");
	String memo = request.getParameter("memo");
	String password = request.getParameter("password");
	
	
	BoardManager bm = new BoardManager();	
	if(bm.checkPassword(idx, password)) {
		bm.update(idx, title, memo);
%>
				  <script language=javascript>
				  	self.window.alert("글이 수정되었습니다.");
				  	location.href="view.jsp?idx=<%=idx%>&pg=<%=pg%>";
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

