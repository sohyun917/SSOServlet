package student.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import student.model.dao.StudentDAO;
import student.model.vo.Student;

public class StudentService {
	
	private StudentDAO stdDao;
	
	public StudentService() {
		stdDao = new StudentDAO();
	}

	public Student selectCheckLogin(Student student) {
		Connection conn = JDBCTemplate.getConnection();
		Student sOne = stdDao.selectCheckLogin(conn, student);
		return sOne;
	}

	public int registerStudent(Student student) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = stdDao.insertStudent(conn, student);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
