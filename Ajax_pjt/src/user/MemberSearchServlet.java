package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberSearchServlet")
//Servlet은 JSON을 만드는 역할을 한다.
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MemberSearchServlet안에 넘어오는 매개변수(아이디정보,회원정보등) 넘어온 값을 UTF-8로 처리
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		String mName = request.getParameter("mName");
		
		response.getWriter().write(getJSON(mName)); //출력할수 있는 구문 JSON 형태로 화면에 출력
	}
	//parsing (파싱)하기 쉬운 하나의 형태 특정회원 정보가 JSON 형태로 출력하고 그것을 다시 분석해서 보여줌
	public String getJSON(String mName) {
		if(mName == null) mName = "";
		StringBuffer result = new StringBuffer("");
		result.append("{\result\":["); // 여는 구문
		memberDAO Dao = new memberDAO();
		ArrayList<Member> memberList = Dao.search(mName); //검색하는 구문
		for(int i = 0; i < memberList.size(); i++) {
			//하나의 회원정보를 배열의 원소형태로 보여줌 
			result.append("[{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"},");
			result.append("{\"value\":\"" + memberList.get(i).getmName() + "\"}],");
		}
		result.append("]}"); // 닫아줌
		return result.toString(); //결과 반환
	}
}
