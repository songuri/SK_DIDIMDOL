<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sk.jsp.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <title>게시판</title>
 </head>
 <body>

<%
	final int ROWSIZE = 4;
	
	int pg = 1;
	
	if(request.getParameter("pg")!=null) {
		pg = Integer.parseInt(request.getParameter("pg"));
	}
	
	int start = (pg*ROWSIZE) - (ROWSIZE-1);
	int end = (pg*ROWSIZE);
	
	BoardManager bm = new BoardManager();
	 	
	
	int allPage = 0;
	int total = bm.al.size();	
	allPage = (int)Math.ceil(total/(double)ROWSIZE);
		
	out.print("총 게시물 : " + total + "개");
	
	ArrayList<Board> al = bm.selectPage( (pg-1) * ROWSIZE + 1,  pg * ROWSIZE  );
		
%>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr height="5"><td width="5"></td></tr>
 <tr style="background:url('img/table_mid.gif') repeat-x; text-align:center;">
   <td width="5"><img src="img/table_left.gif" width="5" height="30" /></td>
   <td width="73">번호</td>
   <td width="379">제목</td>
   <td width="73">작성자</td>
   <td width="164">작성일</td>
   <td width="58">조회수</td>
   <td width="7"><img src="img/table_right.gif" width="5" height="30" /></td>
  </tr>
<%
	if(total==0) {
%>
	 		<tr align="center" bgcolor="#FFFFFF" height="30">
	 	   <td colspan="6">등록된 글이 없습니다.</td>
	 	  </tr>
<%
	 	} else {
	 		for(int i = 0 ; i < al.size(); i++ ) {
				Board b = al.get(i);
		
%>
<tr height="25" align="center">
	<td>&nbsp;</td>
	<td><%=b.idx %></td>
	<td align="left">
	<a href="view.jsp?idx=<%=b.idx%>&pg=<%=pg%>"><%=b.title %></a>
	</td>
	<td align="center"><%=b.uname %></td>
	<td align="center"><%=b.time %></td>
	<td align="center"><%=b.hit %></td>
	<td>&nbsp;</td>
</tr>
  <tr height="1" bgcolor="#D2D2D2"><td colspan="6"></td></tr>
<% 
		}
	} 
%>
 <tr height="1" bgcolor="#82B5DF"><td colspan="6" width="752"></td></tr>
 </table>
 
<table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr><td colspan="4" height="5"></td></tr>
  <tr>
	<td align="center">
		
		<%
			for(int i=1; i<= allPage; i++){
		%>
				[<a href="list.jsp?pg=<%=i %>"><%=i %></a>]
		<%		
			}
		%>
		
		</td>
		</tr>
		  <tr align="center">
   <td><input type=button value="글쓰기" OnClick="window.location='write.jsp'"></td>
  </tr>
 </table>
 </body>
</html>