<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <%
    	Cookie cookie = new Cookie("id" , "hong");
    	response.addCookie(cookie);
    %>
    
    
    <html>
    <head><title>��Ű ����</title></head>
    </html>
    
    <body>
    <% cookie.getName(); %> ��Ű�� �� = "<%=cookie.getValue() %>"
    </body>
    
   
    