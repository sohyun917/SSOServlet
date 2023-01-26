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
	public List<Notice> selectAllNotice(Connection conn) {
		String query = "SELECT * FROM NOTICE_TBL";
		List<Notice> nList = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
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
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nList;
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
}
