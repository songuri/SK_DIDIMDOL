package JAVA_ORACLE_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 폴더에 ==>ojdbc5을 사용한다.
 * 	프로젝트 - property - java build path - 외부 라이브러리 추가 Referenced Libraries에서 확인한다.
 * 이렇게만 해놓으면 오라클 연동 자체는 된것이다.
 * 
 * JDBC 드라이버
 *  oracle.jdbc.driver.OracleDriver 클래스가 생기게 되어서 자바에서 DB를 사용할 수 있다.
 * 연결 URL : jdbc:oracle:thin:@<server-ip>:1521:xe
 * 
 */
/*
 * <Table 생성>
  SQL > create table employee
  (no number(3) not null primary key,
  name varchar2(15),
  position varchar2(10),
  deptno number(2),
  email varchar2(30));
  
  SQL> insert into employee values(1,'김선동','부장',10,'kim123@naver.com');
  SQL> insert into employee values(2,'박태호', '과장',20,'park765@hanmail.net');
  SQL> insert into employee values(3,'손유일', '대리,20,'justice125@naver.com');
  SQL> commit;
 */
public class JDBCTest {
	
	final static String sql = "select * from employee";
	
	public static void main(String[] args){
		//드라이버 파일이 로딩이 되어야 사용이 가능하다.
		//드라이버와 url을 함께 사용해야 한다.
		//ORACLE 연동시
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//My SQL 연동
		//String driver = "com.mysql.jdbc.Driver";
		//String url = "jdbc:mysql://localhost:3306/jdbc";
		
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver); // 이렇게 인자를 주면 드라이버 로딩이 되어 진다.
			//접속을 위한 객체 초기화. 이 Connection객체를 통해서 우리가 원하는 데이터 베이스를 사용 할 수 있다.
			con = DriverManager.getConnection(url,"testdb","testdb1234"); //인자를 이런식으로 주면 된다.
			stmt = con.createStatement(); // SQL문장을 시작을 하겠다.
			rs = stmt.executeQuery(sql); // 쿼리문을 이런식으로 사용 하면된다.
			System.out.println("번호 \t 이름 \t 직책\t 부서번호 \t  이메일 \t");
			System.out.println("--------------------------------------");
			while(rs.next()){
				System.out.print(rs.getInt("no"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.print(rs.getString("position"));
				System.out.print("\t");
				System.out.print(rs.getInt("deptno"));
				System.out.print("\t");
				System.out.print(rs.getString("email"));
				System.out.println(" ");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
				//자원 해제 시키는 과정.
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
