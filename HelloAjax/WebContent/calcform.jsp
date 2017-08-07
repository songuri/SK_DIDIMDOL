<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

   
    <form action=calc.jsp>
    <input type=text id=op1 size=3>
    <select id=op>
    	<option value="add" selected>+</option>
    	<option value="sub" selected>-</option>
    	<option value="mul" selected>*</option>
    	<option value="div" selected>/</option>
    </select>
    <input type=text id=op2 size=3 value=2>
    <button id=add>=</button>&nbsp;&nbsp;<input type=text id=out size=3>  
    </form>
    
    <script>
    $("#calc").click(function(){
    	var q = 'calc.jsp?op1='+
 		$("#op1").val() + '&op2=' +$("#op2").val()+ '&op=' + $("#op").val();
    	console.log(q);
    })	
    
    </script>