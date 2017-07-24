package JAVA_ORACLE_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib ������ ==>ojdbc5�� ����Ѵ�.
 * 	������Ʈ - property - java build path - �ܺ� ���̺귯�� �߰� Referenced Libraries���� Ȯ���Ѵ�.
 * �̷��Ը� �س����� ����Ŭ ���� ��ü�� �Ȱ��̴�.
 * 
 * JDBC ����̹�
 *  oracle.jdbc.driver.OracleDriver Ŭ������ ����� �Ǿ �ڹٿ��� DB�� ����� �� �ִ�.
 * ���� URL : jdbc:oracle:thin:@<server-ip>:1521:xe
 * 
 */
/*
 * <Table ����>
  SQL > create table employee
  (no number(3) not null primary key,
  name varchar2(15),
  position varchar2(10),
  deptno number(2),
  email varchar2(30));
  
  SQL> insert into employee values(1,'�輱��','����',10,'kim123@naver.com');
  SQL> insert into employee values(2,'����ȣ', '����',20,'park765@hanmail.net');
  SQL> insert into employee values(3,'������', '�븮,20,'justice125@naver.com');
  SQL> commit;
 */
public class JDBCTest {
	
	final static String sql = "select * from employee";
	
	public static void main(String[] args){
		//����̹� ������ �ε��� �Ǿ�� ����� �����ϴ�.
		//����̹��� url�� �Բ� ����ؾ� �Ѵ�.
		//ORACLE ������
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		//My SQL ����
		//String driver = "com.mysql.jdbc.Driver";
		//String url = "jdbc:mysql://localhost:3306/jdbc";
		
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver); // �̷��� ���ڸ� �ָ� ����̹� �ε��� �Ǿ� ����.
			//������ ���� ��ü �ʱ�ȭ. �� Connection��ü�� ���ؼ� �츮�� ���ϴ� ������ ���̽��� ��� �� �� �ִ�.
			con = DriverManager.getConnection(url,"testdb","testdb1234"); //���ڸ� �̷������� �ָ� �ȴ�.
			stmt = con.createStatement(); // SQL������ ������ �ϰڴ�.
			rs = stmt.executeQuery(sql); // �������� �̷������� ��� �ϸ�ȴ�.
			System.out.println("��ȣ \t �̸� \t ��å\t �μ���ȣ \t  �̸��� \t");
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
				//�ڿ� ���� ��Ű�� ����.
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
