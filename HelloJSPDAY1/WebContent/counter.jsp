<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

    
<%--     <%
    	String cnt = String.valueOf(2356);
    	for(int i =0 ; i <cnt.length() ; i++){
    		out.println("<img src="+cnt.charAt(i)+".png width=50 height=50>");
    	}
    %>명이 방문했습니다. ----방법 1
 --%>
 
 <%--  <%
    	String cnt = String.valueOf(2356);
    	for(int i =0 ; i <cnt.length() ; i++){
   %>
    <img src=<% out.print(cnt.charAt(i)); %>.png width=50 height=50>
    <%
    	}
    %>	<!-- html과 jsp를 완전히 분리하는 방법이다 -->
    명이 방문했습니다. --%>
    
     <%
    	String cnt = String.valueOf(2356);
    	for(int i =0 ; i <cnt.length() ; i++){
   %>
    <img src=<%=cnt.charAt(i)%>.png width=50 height=50>
    <%
    	}
    %>	<!-- html과 jsp를 완전히 분리하는 방법이다의 업그래이드 숏컷 ㅇ.ㅇ -->
    명이 방문했습니다.
    
    
    <%!
    	int add(int a , int b ){
    	return a+b;
    }
    
    %><!-- 이런식으로 함수를 선언할 수 있다. -->
    
    
    
    	