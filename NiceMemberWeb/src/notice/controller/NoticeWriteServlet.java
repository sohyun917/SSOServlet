package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet("/notice/write")
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/notice/write.jsp").forward(request, response);
	}
	// notice/write를 치면 doGet이 동작을 해서 저 파일(/WEB-INF/views/notice/write.jsp)을 보여줌

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		// session에서 아이디값을 가져와서 if문을 돌림
		if(session != null && (session.getAttribute("memberId")) != null) {
			String memberId = (String)session.getAttribute("memberId");
			Notice notice = new Notice(subject, content);
			notice.setNoticeWriter(memberId);	//로그인한 사용자의 ID를 가져온 후 작성자로 insert할 수 있음.
			NoticeService nService = new NoticeService();
			int result = nService.registerNotice(notice);
			if(result > 0) {
				//성공하면 게시판 리스트 이동
				response.sendRedirect("/notice/list");
			}else {
				//실패하면 에러페이지 이동
				request.setAttribute("title", "공지사항 등록실패");
				request.setAttribute("msg", "공지사항 등록이 완료되지 않았습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
			}			
		}else {	//로그인이 안돼서 발생하는 에러창
			request.setAttribute("title", "로그인 필요");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}

}
