<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- WEB-INF 폴더에 LIB 폴더에 jdbc파일을 넣어 주면 된다.  -->

<%

Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","testdb","testdb1234");


Statement stmt = conn.createStatement();

String sql = "select * from member";
ResultSet rs = stmt.executeQuery(sql);

while(rs.next()) {
	System.out.println(rs.getString("mem_id")); %>
	<font color=red size=20>
	<% out.println(rs.getString("mem_id"));%>
	</font>
	<font color=blue size=20>
	<%out.println(rs.getString("mem_name") + "<br>"); %>
	</font><% 
	
	
}
rs.close();
stmt.close();
conn.close();

%>






CONNECTION SUCCESS