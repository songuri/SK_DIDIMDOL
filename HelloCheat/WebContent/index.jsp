<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
      <font size=30 color=blue>
    <p1> This is MAIN PAGE </p1>
    </font>
    <br>
    <img src="jj.jpg" height=600 width=600>
    
    
     <form action=loginform.jsp method=get> <!-- form으로 지정한 데이터를 서버로 전송할 수 있게끔 할 수 있다. -->
    <input type=submit value=Login>
    </form>
    <form action=chat.jsp method=get> <!-- form으로 지정한 데이터를 서버로 전송할 수 있게끔 할 수 있다. -->
    <input type=submit value=chat>
    </form>
   