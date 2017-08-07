import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sk.db.Member;
import com.sk.db.MemberManager;


/*
 * 자바만의 컴파일 에러 - 예외처리 관련.
 * 
 */
/*
class A{
	public int fun() throws Exception{ // 이런식으로 함수가 구현되어 있으면 이 함수를 사용하려고 하면 무조건 예외처리를 해야한다.
										// 예외처리를 강제적으로 하게 하려 할때 이런식으로 구현한다.
		return 1;
	}
}
//자바에서 대문자로 시작하면 클래스라는 것이다.
//소문자로 시작하면 키워드라는 것이다.*/
public class JDBCtest {
	
	public static void main(String[] args) throws Exception{
		MemberManager m = new MemberManager();
		Member m1 = new Member("park", "sk", "kihyen", "man", "coding");
		if(m.isExist("ckt", "ckt"))System.out.println("있어요!");
		else System.out.println("없어요!");
		
		m.insertData(m1);
	}
	
	
	
	
	
	
	public static void main2(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		System.out.println("드라이버 로딩 성공");
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","testdb","testdb1234");
		System.out.println("커넥션 성공");
		
		Statement stmt = conn.createStatement();
		
		String sql = "select * from member";
		ResultSet rs = stmt.executeQuery(sql);
		
		rs.next(); // 맨처음에 포인터가 첫 레코드의 윗부분을 가르키고 있기 때문에 처음에 넥스트를 한번 해줘야 한다.
		System.out.println(rs.getString("mem_id"));
		
		while(rs.next()) {
			System.out.println(rs.getString("mem_id"));
		}
		rs.close();
		stmt.close();
		
		String mem_id = "ckt";
		String mem_pwd = "ckt";
		String mem_name = "최권택";
		String gender = "man";
		String hobby ="영화,여행";		
		
		
		//sql  = "insert into member values('ckt','ckt,'최권택','man,'컴퓨터')";
		// 이런식으로 하면 동적으로 문장을 사용할 수 있다.
		sql = "INSERT INTO member VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mem_id);
		pstmt.setString(2, mem_pwd);
		pstmt.setString(3, mem_name);
		pstmt.setString(4,gender);
		pstmt.setString(5, hobby);
		
		/*
		 * 데이터 베이스의 리턴값은 2개이다 
		 * 1. 레코드 셋	 2.이 쿼리로 영향을 받은 레코드의 갯수
		 * 그래서 쿼리로 삭제를 햇을때 5개의 레코드가 삭제 될경우 5를 리턴한다.
		 */
		
		int out = pstmt.executeUpdate();
		System.out.println(" out 의 값 " + out);
		conn.close();		
				
	}
}
