<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*,javax.sql.*, javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<%
	
	Context initCtx = new InitialContext();
	Context envCtx = (Context)initCtx.lookup("java:comp/env");
	DataSource dataSource = (DataSource)envCtx.lookup("jdbc/oracle");
	Connection con = dataSource.getConnection();
	

	String mem_id = request.getParameter("mem_id"); 
	
	String sql = "select * from member where mem_id = '" + mem_id + "'";
	
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	
	rs.next();
	
	String mem_name = rs.getString("mem_name");
	String ssn = rs.getString("ssn");
	
	con.close();
	
	
	
%>	
	
<form action="editmember.jsp"> 
아이디 : <input type="text" name="mem_id" readonly value=<%=mem_id%>><br/>
패스워드 : <input type="password" name="ssn" value=<%=ssn%>><br/>
이름:<input type="text" name="mem_name" value=<%=mem_name%>><br/>
성별:<input type="radio" value="남" name="gender">남자
<input type="radio" value="여" name="gender">여자<br/>
취미:
<input type="checkbox" value="운동 " name="hobby">운동
<input type="checkbox" value="영화" name="hobby">영화
<input type="checkbox" value="여행 " name="hobby">여행
<input type="checkbox" value="게임" name="hobby">게임
<input type="checkbox" value="독서" name="hobby">독서
<input type="checkbox" value="낚시 " name="hobby">낚시<br/>
<input type="submit" value="보내기"/> 
<input type="reset" value="다시입력"/>

</form>
	
 
</body>
</html>