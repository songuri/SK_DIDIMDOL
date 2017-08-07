<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.sk.db.*" %>

<%-- <%
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","testdb","testdb1234");
%>

    <%
    	String user;
  		String pwd;
  		String message;
    	user = request.getParameter("user");
    	pwd = request.getParameter("pwd");
    	
    	Statement stmt = conn.createStatement();
    	String sql = "select count(*) from member where mem_id='"+user+"' AND mem_pwd = '"+pwd+"'";
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	if(rs.getInt(1) > 0) {
    		session.setAttribute("id", user);
    		response.sendRedirect("chat.jsp");}
        else {  %>
    		<script>
    		alert("please retry login");
    		history.go(-1);
    		</script>
    		<%
    	}
    	rs.close();
    	stmt.close();
    	conn.close();
    %>

    <br>
    id=<%=user%> <br>
    pwd=<%=pwd%> <br>
   --%>
   
   
   
    <%
    	String user;
  		String pwd;
  		String message;
    	user = request.getParameter("user");
    	pwd = request.getParameter("pwd");
    	
    	MemberManager mm = new MemberManager();
    	
    	if(mm.isExist(user, pwd)){
    		mm.close();
    		session.setAttribute("id", user);
    		response.sendRedirect("chat.jsp");
    	}else mm.close(); 
    	
    	%>
    