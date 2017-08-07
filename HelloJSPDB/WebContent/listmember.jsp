<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*"%>
<%@ page import="com.sk.db.*" %>
<%
	MemberManager mm = new MemberManager();
	ArrayList<Member> list = mm.select();
%>

<a href=registerform.jsp>회원 가입</a>
<table border=1>
<%
		for(int i = 0 ; i < list.size() ; i ++){
			Member m = list.get(i);
	
%>

	<tr>
		<td> <%=m.mem_id %> </td>
		<td> <%=m.mem_name %></td>
		<td> <%=m.gender %></td>
		<td> <a href=deletemember.jsp?mem_id>삭제</a></td>
		<td> <a href=editmemeberform.jsp?mem_id>수정</a></td>
	</tr>

<%
}
	mm.close();
	%>
	
</table>