package com.sk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberManager {
	
	Connection conn;
	
	public MemberManager() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.println("드라이버 로딩 성공");
		
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","testdb","testdb1234");
		System.out.println("커넥션 성공");
		
	}
	public void close() throws Exception{
		conn.close();
		
	}
	public boolean isExist(String user , String pwd) throws Exception{
		Statement stmt = conn.createStatement();
    	String sql = "select count(*) from member where mem_id='"+user+"' AND mem_pwd = '"+pwd+"'";
    	ResultSet rs = stmt.executeQuery(sql);
    	rs.next();
    	int tmp=rs.getInt(1);
    	rs.close();
    	stmt.close();
    	return tmp>1;
	}
	public void insertData(Member m)throws Exception{
		String sql = "INSERT INTO member VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.mem_id);
		pstmt.setString(2, m.mem_pwd);
		pstmt.setString(3, m.mem_name);
		pstmt.setString(4,m.gender);
		pstmt.setString(5, m.hobby);
	}
	public ArrayList<Member> select() throws Exception{
		ArrayList<Member> rt =new ArrayList<Member>();
		
		
		String sql = "SELECT * FROM member";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			Member m = new Member(
					rs.getString("mem_id"),
					rs.getString("mem_pwd"),
					rs.getString("mem_name"),
					rs.getString("gender"),
					rs.getString("hobby")
					);
			rt.add(m);
		}
		
		
		return rt;
	}
	public int delete(String mem_id) throws Exception{
		Statement stmt = conn.createStatement();
		String sql = "delete from member where mem_id= '"+mem_id+"'";
		int cnt = stmt.executeUpdate(sql);
		stmt.close();
		return cnt;
	}
	
	
	
	public void insertData2(String user , String pwd, String name,String gender ,String hobby)throws Exception{
		String sql = "INSERT INTO member VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user);
		pstmt.setString(2, pwd);
		pstmt.setString(3, name);
		pstmt.setString(4,gender);
		pstmt.setString(5, hobby);
	}
}
