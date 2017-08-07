<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sk.db.*" %>

<meta http-equiv="Refresh" content="3; url=index.jsp">
<%
	String mem_id = request.getParameter("mem_id");
	String mem_pwd = request.getParameter("mem_pwd");
	String mem_name = request.getParameter("mem_name");
	String gender = request.getParameter("gender");
	String hobbys[] = request.getParameterValues("hobby");
	String hobby = Arrays.toString(hobbys).toString();
	Member m1 = new Member(mem_id, mem_pwd,mem_name,gender,hobby);
	MemberManager mm =new MemberManager();
	mm.insertData(m1);
	mm.close();
	
	%>
	
	이름 : <%=mem_name %><br/>
	아이디: <%=mem_id %><br/>
	패스워드: <%=mem_pwd %><br/>
	성별: <%=gender %><br/>
	취미: <%=hobby %><br/>
	<a gref=index.jsp> Main page(after 3 second)</a>