<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.sk.db.*" %>

<%
	String mem_id = request.getParameter("mem_id");
	
	MemberManager mm = new MemberManager();
	int cnt = mm.delete(mem_id);
	mm.close();
	%>
	
	삭제레코드 <%=cnt%>
	<a href=listmember.jsp>목록</a>