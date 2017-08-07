<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <%
    	Cookie cookie = new Cookie("id" , "hong");
    	response.addCookie(cookie);
    %>
    
    
    <html>
    <head><title>쿠키 생성</title></head>
    </html>
    
    <body>
    <% cookie.getName(); %> 쿠키의 값 = "<%=cookie.getValue() %>"
    </body>
    
   
    