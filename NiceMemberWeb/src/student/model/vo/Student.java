package student.model.vo;

import java.sql.Date;

public class Student {
	private String studentId;
	private String studentPw;
	private String studentName;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String studentGender;
	private Date studentDate;

	public Student() {}
	
	public Student(String studentId, String studentPw) {
		this.studentId = studentId;
		this.studentPw = studentPw;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentPw() {
		return studentPw;
	}

	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public Date getStudentDate() {
		return studentDate;
	}

	public void setStudentDate(Date studentDate) {
		this.studentDate = studentDate;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentPw=" + studentPw + ", studentName=" + studentName
				+ ", studentEmail=" + studentEmail + ", studentPhone=" + studentPhone + ", studentAddress="
				+ studentAddress + ", studentGender=" + studentGender + ", studentDate=" + studentDate + "]";
	}
}
