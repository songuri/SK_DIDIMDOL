<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    
    <%
    	String op = request.getParameter("op");
    	int op1 = Integer.parseInt(request.getParameter("op1"));
    	int op2 = Integer.parseInt(request.getParameter("op2"));
    	
    	int result = 0;
    	if(op.equals("add")) result = op1+op2;
    	else if(op.equals("sub")) result = op1-op2;
    	else if(op.equals("mul")) result = op1*op2;
    	else if(op.equals("div")) result = op1/op2;
    	
    %>
    {
    	"code" : "ok",
    	"result" : <%=result%>
   	}