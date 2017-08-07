<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>

<form action="register.jsp" method="get"> 
아이디 : <input type="text" name="id"><br/>
패스워드 : <input type="password" name="pwd"><br/>
이름:<input type="text" name="name"><br/>
성별:<input type="radio" value="man" name="gender">남자
<input type="radio" value="woman" name="gender">여자<br/>
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