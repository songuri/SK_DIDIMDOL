<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    
    <!-- method �� get��İ� post����� ���̸� �˾ƾ� �Ѵ�. get�� ����� ����. post����� ����� �ִ�. -->
    <form action=Login.jsp method=get> <!-- form���� ������ �����͸� ������ ������ �� �ְԲ� �� �� �ִ�. -->
    id <input type=text name=user>
    pw <input type=password name=pwd><!-- name�� ������ �͵��� �����Ͱ� Login.jsp���Ϸ� �Ѿ� ����. -->
    <input type=submit value=Login>
    </form>
    
    
    <a href=Logout.jsp>Logout</a>