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
	//ȸ�������� ArrayList�� �����ͼ� search��� �Լ��� ����� userName�̰� �Ű������� ���� �� �ְ� �����.
	public ArrayList<Member> search(String mName) {
		String SQL = "SELECT * FROM  MEMBER WHERE = mName LIKE ?";
		ArrayList<Member> memberList = new ArrayList<Member>();
		try {
			//������ ����� �����ͺ��̽��� sql������ �ִ±���
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
