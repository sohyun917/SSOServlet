package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import notice.model.vo.Notice;

public class NoticeDAO {

	/**
	 * 공지사항 등록 dao
	 * @param conn
	 * @param notice
	 * @return result
	 */
	public int insertNotice(Connection conn, Notice notice) {
		String query="INSERT INTO NOTICE_TBL VALUES(SEQ_NOTICENO.NEXTVAL,?,?,?,DEFAULT,DEFAULT)";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getNoticeWriter());	//가지고 올 noticeWriter가 없기 때문에
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 공지사항 목록 조회
	 * @param conn
	 * @return nList
	 */
	public List<Notice> selectAllNotice(Connection conn, int currentPage) {
		String query = "SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_TBL.* FROM NOTICE_TBL) WHERE NUM BETWEEN ? AND ?";
		List<Notice> nList = null;
		//한번에 보여줄 페이지 수를 10으로 고정(이걸 동적으로 바뀌게 하려면 Connection conn이 있는곳에 같이 적어줘야해)
		int recordCountPerPage = 10;
		// currentPage=1, recordCountPerPage=10
		// start=1
		// currentPage=2, recordCountPerPage=10
		// start=11
		// currentPage=3, recordCountPerPage=10
		// start=21 를 바탕으로 수식을 만들어줌
		int start = (currentPage*recordCountPerPage)-(recordCountPerPage-1);
		int end = (currentPage*recordCountPerPage);
		try {
			//Statement stmt = conn.createStatement();
			//ResultSet rset = stmt.executeQuery(query);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while(rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
				notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
				notice.setViewCount(rset.getInt("VIEW_COUNT"));
				
				nList.add(notice);
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nList;
	}
	
	// 페이지 네비게이터를 만들어주는 메소드
	public String generatePageNavi(int currentPage) {
		// 전체 게시물 수 : 37 -> 네비게이터 수 : 4개
		int totalCount = 27;
		int recordCountPerPage = 10;
		int naviTotalCount = 0;
		if(totalCount % recordCountPerPage > 0) {
			naviTotalCount = totalCount / recordCountPerPage + 1;
		}else {
			naviTotalCount = totalCount / recordCountPerPage;
		}
		// 한 페이지당 몇개씩 보여주는지(맨 밑에 나오는애가)
		int naviCountPerPage = 5;
		int startNavi = ((currentPage-1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		// 유동적으로 바뀌는 페이지네비게이터를 만들기 위함
		StringBuilder sb = new StringBuilder();
		for(int i = startNavi; i <= endNavi; i++) {
			sb.append("<a href='/notice/list?page="+i+"'>"+i+"</a>");			
		}
		return sb.toString();
	}

	/**
	 * 공지사항 상세조회 dao
	 * @param conn
	 * @param noticeNo
	 * @return notice
	 */
	public Notice selectOneByNo(Connection conn, int noticeNo) {
		String query ="SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		Notice notice = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
				notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
				notice.setViewCount(rset.getInt("VIEW_COUNT"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	/**
	 * 공지사항 삭제 dao
	 * @param conn
	 * @param noticeNo
	 * @return result
	 */
	public int deleteNotice(Connection conn, int noticeNo) {
		String query = "DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		String query = "UPDATE NOTICE_TBL SET NOTICE_SUBJECT = ?, NOTICE_CONTENT = ? WHERE NOTICE_NO = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
