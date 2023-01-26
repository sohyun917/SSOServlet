package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeRemoveServlet
 */
@WebServlet("/notice/remove")
public class NoticeRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter("notice-id")값이 널인지 아닌지 판단해서 널인경우 0을 보내준다.
		//detail.jsp에 있는 쿼리스트링값과 일치시켜줘야해
		String noticeId = request.getParameter("notice-id") != null ? request.getParameter("notice-id") : "0";
		int noticeNo = Integer.parseInt(noticeId);
		NoticeService nService = new NoticeService();
		int result = nService.deleteNotice(noticeNo);
		if(result > 0) {
			// 성공하면 공지사항 목록
			response.sendRedirect("/notice/list");
		}else {
			// 실패하면 에러페이지 이동
			request.setAttribute("title", "공지사항 삭제 실패");
			request.setAttribute("msg", "공지사항이 삭제되지 않았습니다.");			
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
		
	}

}
