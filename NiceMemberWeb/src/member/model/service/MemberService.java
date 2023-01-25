package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	private MemberDAO mDao;
	
	public MemberService() {
		// 생성자 만들기
		mDao = new MemberDAO();
	}
	/**
	 * 회원 로그인
	 * @param memberId
	 * @param memberPw
	 * @return result
	 */
	public int selectCheckLogin(String memberId, String memberPw) {
		//로그인 진행
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = mDao.selectCheckLogin(conn, memberId, memberPw);
		return result;
		//아이디 비번이 있는지 없는지 검사
	}
	/**
	 * 회원가입 Service
	 * @param member
	 * @return result
	 */
	public int registerMember(Member member) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = mDao.insertMember(conn, member);
		if(result > 0) {
			// 커밋
			JDBCTemplate.commit(conn);
		}else {
			// 롤백
			JDBCTemplate.rollback(conn);
		}
		// 연결 해제
		return result;
	}
	/**
	 * 아이디로 조회 Service
	 * @param memberId
	 * @return member
	 */
	public Member printOneById(String memberId) {
		Member member = null;
		Connection conn = JDBCTemplate.getConnection();
		member = mDao.selectOneById(conn, memberId);		
		return member;
	}
	/**
	 * 회원 정보 수정 Service
	 * @param member
	 * @return result
	 */
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = mDao.updateMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	/**
	 * 회원 탈퇴 Service
	 * @param memberId
	 * @return result
	 */
	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = mDao.deleteMember(conn, memberId);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
}
