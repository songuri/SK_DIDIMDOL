<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
    
    <font color=red>
<%
	out.println("Hello");
	out.println("<br><img src=tomcat.png>");
	
	int sum = 0;
	for(int i = 0 ; i <= 10 ; i ++){
		sum += i;
	}
	out.println("<p>합"+sum +"</p>");

%>
	</font>
<img src="tomcat.png">

	
	
	
<!-- WebContent 폴더가 ROOT폴더이다 ㅇ,ㅇ  -->