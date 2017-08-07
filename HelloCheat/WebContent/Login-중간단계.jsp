<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    <%
    	String user;
  		String pwd;
  		String message;
    	user = request.getParameter("user");
    	pwd = request.getParameter("pwd");
    	if(user.equals(pwd)) {%>
    	<script> location.href="chat.jsp"; </script>
    	 <font size=20 color=blue> <p1>Login Success</p1></font>
    	<%} else {  %>
    		<script>
    		alert("please retry login");
    		history.go(-1);
    		/* <font size=20 color=red> <p1>Login Fail</p1></font>
    		<br><a href=javascript:history.go(-1)>BACK</a> */
    		</script>
    	<% 
    	}
    %>

    <br>
    id=<%=user%> <br>
    pwd=<%=pwd%> <br>
    
    