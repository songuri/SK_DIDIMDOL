import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sk.db.Member;
import com.sk.db.MemberManager;


/*
 * �ڹٸ��� ������ ���� - ����ó�� ����.
 * 
 */
/*
class A{
	public int fun() throws Exception{ // �̷������� �Լ��� �����Ǿ� ������ �� �Լ��� ����Ϸ��� �ϸ� ������ ����ó���� �ؾ��Ѵ�.
										// ����ó���� ���������� �ϰ� �Ϸ� �Ҷ� �̷������� �����Ѵ�.
		return 1;
	}
}
//�ڹٿ��� �빮�ڷ� �����ϸ� Ŭ������� ���̴�.
//�ҹ��ڷ� �����ϸ� Ű������ ���̴�.*/
public class JDBCtest {
	
	public static void main(String[] args) throws Exception{
		MemberManager m = new MemberManager();
		Member m1 = new Member("park", "sk", "kihyen", "man", "coding");
		if(m.isExist("ckt", "ckt"))System.out.println("�־��!");
		else System.out.println("�����!");
		
		m.insertData(m1);
	}
	
	
	
	
	
	
	public static void main2(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		System.out.println("����̹� �ε� ����");
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","testdb","testdb1234");
		System.out.println("Ŀ�ؼ� ����");
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from member";
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next(); // ��ó���� �����Ͱ� ù ���ڵ��� ���κ��� ����Ű�� �ֱ� ������ ó���� �ؽ�Ʈ�� �ѹ� ����� �Ѵ�.
		System.out.println(rs.getString("mem_id"));
		
		while(rs.next()) {
			System.out.println(rs.getString("mem_id"));
		}
		rs.close();
		stmt.close();
		
		String mem_id = "ckt";
		String mem_pwd = "ckt";
		String mem_name = "�ֱ���";
		String gender = "man";
		String hobby ="��ȭ,����";		
		
		
		//sql  = "insert into member values('ckt','ckt,'�ֱ���','man,'��ǻ��')";
		// �̷������� �ϸ� �������� ������ ����� �� �ִ�.
		sql = "INSERT INTO member VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		pstmt.setString(2, mem_pwd);
		pstmt.setString(3, mem_name);
		pstmt.setString(4,gender);
		pstmt.setString(5, hobby);
		
		/*
		 * ������ ���̽��� ���ϰ��� 2���̴� 
		 * 1. ���ڵ� ��	 2.�� ������ ������ ���� ���ڵ��� ����
		 * �׷��� ������ ������ ������ 5���� ���ڵ尡 ���� �ɰ�� 5�� �����Ѵ�.
		 */
		
		int out = pstmt.executeUpdate();
		System.out.println(" out �� �� " + out);
		conn.close();		
				
	}
}
