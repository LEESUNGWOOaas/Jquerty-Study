package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberSearchServlet")
//Servlet�� JSON�� ����� ������ �Ѵ�.
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MemberSearchServlet�ȿ� �Ѿ���� �Ű�����(���̵�����,ȸ��������) �Ѿ�� ���� UTF-8�� ó��
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		String mName = request.getParameter("mName");
		
		response.getWriter().write(getJSON(mName)); //����Ҽ� �ִ� ���� JSON ���·� ȭ�鿡 ���
	}
	//parsing (�Ľ�)�ϱ� ���� �ϳ��� ���� Ư��ȸ�� ������ JSON ���·� ����ϰ� �װ��� �ٽ� �м��ؼ� ������
	public String getJSON(String mName) {
		if(mName == null) mName = "";
		StringBuffer result = new StringBuffer("");
		result.append("{\result\":["); // ���� ����
		memberDAO Dao = new memberDAO();
		ArrayList<Member> memberList = Dao.search(mName); //�˻��ϴ� ����
		for(int i = 0; i < memberList.size(); i++) {
			//�ϳ��� ȸ�������� �迭�� �������·� ������ 
			result.append("[{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"}],");
		}
		result.append("]}"); // �ݾ���
		return result.toString(); //��� ��ȯ
	}
}
