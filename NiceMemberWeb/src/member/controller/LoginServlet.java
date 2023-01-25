package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/login.kh")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member-id");	//form input[name=member-id]
		String memberPw = request.getParameter("member-pw");	//form input[name=member-pw]
		
		MemberService mService = new MemberService();
		int result = mService.selectCheckLogin(memberId, memberPw);
//		System.out.println(result);
		
		if(result > 0) {
			// 로그인 성공
//			request.setAttribute("memberId", memberId);
//			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");  // 저 페이지에서만 유효하게 한다(request)
//			view.forward(request, response);
			//session은 서버가 작동하는 한 전역변수처럼 계속 사용할 수 있다.(파괴하지 않는 이상 계속 사용이 가능함)
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			response.sendRedirect("/index.jsp");	// 로그인하면 계속 정보를 갖고있는다(session)
		}else {
			// 로그인 실패
			request.setAttribute("title", "로그인 실패");
			request.setAttribute("msg", "아이디와 비밀번호를 확인해주세요.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			view.forward(request, response);					
		}
	}

}
