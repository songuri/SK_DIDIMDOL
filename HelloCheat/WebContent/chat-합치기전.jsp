<%@page import="java.nio.channels.SeekableByteChannel"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
    <font size=30 color=blue>
    <!--  <meta111 http-equiv="Refresh" content="5; url=chat.jsp">-->
    <p1> This is chat Page </p1>
    </font>
   <!-- 쿠키는 브라우져 즉 클라이언트에 저장이 되기 떄문에 보안에 이슈가 되는것이다.
   		중요한 데이터를 요청한 모든 정보를 보내준다.
   		
   		쿠키와 세션의 차이점 사용용도에 대해서 알아야 한다.
   		
    -->
    <%
    		if(session.getAttribute("id") ==null){
    			response.sendRedirect("loginform.jsp");
    		}
    
    %>
    <!-- id라는 세션이라는 것을 가져 온것이다. -->
    
    
    <%
    	String message ="";
    	BufferedReader br = new BufferedReader(new FileReader("C:/Users/user/Desktop/2017skDIDIMDOL/chat.txt"));
    	String line="";
    	while((line = br.readLine()) !=null){
    		message +=line;
    	}
    	br.close();
    	
    	String chat = request.getParameter("chat");
    	if(chat != null){
    	String id = (String)session.getAttribute("id");
    	message += "["+id+"]"+ chat+"<br>";
    	
    	BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/2017skDIDIMDOL/chat.txt"));
    	bw.write(message);
    	bw.close();
    	}
    %>
    <!-- BufferedReader를 써서 한번에 받아와서 써서 진짜 빠름 FileReader 는 하나씩 가져와서 전부다 조합을 해야한다. -->
    
     <font size=5 color= white>
    <div id=div1 style="background-color:gray;width:100%;
     height:200px;overflow-y:auto;">
     <%=message%>
    </div>
    </font>
  
	<form action=chat.jsp>
	<input type=text name=chat id=chat>
	</form>
	
	
	<form action=Logout.jsp method=get> <!-- form으로 지정한 데이터를 서버로 전송할 수 있게끔 할 수 있다. -->
    <!-- name을 지정한 것들의 데이터가 Login.jsp파일로 넘어 간다. -->
    <input type=submit value=Logout>
    </form>
    
    
    <img src=0.png width=40 height=40 id=img1 vaa='55'>
	<img src=1.png width=40 height=40 id=img2 vaa='1'>
	
	<script type="text/javascript">
		$("#chat").focus();
		var h = $("#div1").prop("scrollHeight");
		$('#div1').scrollTop(h);
		
		$("#img1").click(function(){
			$("#chat").val("<img src=" + $(this).attr("src")+" height=40 width=40>");
			$("#chat").focus();
			$(this).data('vaa',20);
			console.log($(this).attr('vaa'));
		});
		
	</script>
	

	
	<<script type="text/javascript">
	</script>
	
	
	
	
	
	