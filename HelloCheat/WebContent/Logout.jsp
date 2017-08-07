<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    <font size=30 color=blue>
    <p1> This is logout Page </p1>
    </font>
    <%
    	session.removeAttribute("id");
    %>
    
    
    <script>
    location.href="index.jsp";
    
    </script>