<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

    <%
    	String user;
  		String pwd;
  		String message;
    	user = request.getParameter("user");
    	pwd = request.getParameter("pwd");
    	if(user.equals(pwd)) {
    		session.setAttribute("id", user);
    		response.sendRedirect("chat.jsp");}
        else {  %>
    		<script>
    		alert("please retry login");
    		history.go(-1);
    		</script>
    		<%
    	}
    %>

    <br>
    id=<%=user%> <br>
    pwd=<%=pwd%> <br>
    
    