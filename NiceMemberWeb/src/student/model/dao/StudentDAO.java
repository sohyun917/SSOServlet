package student.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import student.model.vo.Student;

public class StudentDAO {

	public Student selectCheckLogin(Connection conn, Student student) {
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ? AND STUDENT_PW = ?";
		Student sOne = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPw());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				sOne = new Student();
				sOne.setStudentId(rset.getString("STUDENT_ID"));
				sOne.setStudentPw(rset.getString("STUDENT_PW"));
				sOne.setStudentName(rset.getString("STUDENT_NAME"));
				sOne.setStudentEmail(rset.getString("STUDENT_EMAIL"));
				sOne.setStudentPhone(rset.getString("STUDENT_PHONE"));
				sOne.setStudentAddress(rset.getString("STUDENT_ADDR"));
				sOne.setStudentGender(rset.getString("STUDENT_GENDER"));
				sOne.setStudentDate(rset.getDate("STUDENT_DATE"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sOne;
	}

	public int insertStudent(Connection conn, Student student) {
		String query = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,DEFAULT)";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPw());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentEmail());
			pstmt.setString(5, student.getStudentPhone());
			pstmt.setString(6, student.getStudentAddress());
			pstmt.setString(7, student.getStudentGender());
			result = pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
