<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <!-- method 의 get방식과 post방식의 차이를 알아야 한다. get은 숨길수 없다. post방식은 숨길수 있다. -->
    <form action=Login.jsp method=get> <!-- form으로 지정한 데이터를 서버로 전송할 수 있게끔 할 수 있다. -->
    id <input type=text name=user>
    pw <input type=password name=pwd><!-- name을 지정한 것들의 데이터가 Login.jsp파일로 넘어 간다. -->
    <input type=submit value=Login>
    </form>
    
    
    <a href=Logout.jsp>Logout</a>