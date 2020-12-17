package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class memberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public memberDAO() {
		try {
			String dbURL = "jdbc:oracle://localhost:3306/AJAX";
			String dbID="root";
			String dbPassword="root";
			Class.forName("com.oracle.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	//회원정보를 ArrayList로 가져와서 search라는 함수를 만들고 userName이고 매개변수를 담을 수 있게 만든다.
	public ArrayList<Member> search(String mName) {
		String SQL = "SELECT * FROM  MEMBER WHERE = mName LIKE ?";
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			//실제로 연결된 데이터베이스의 sql문장을 넣는구문
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, mName);
			while(rs.next()) {
				Member member = new Member();
				member.setmName(rs.getString(1));
				member.setmAge(rs.getInt(2));
				member.setmGender(rs.getString(3));
				member.setmEmail(rs.getString(4));
				memberList.add(member);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return memberList;
	}
}
