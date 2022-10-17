package com.mvc.jdbc.student;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StudentRowMapper implements RowMapper<Student>{

	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		Student student=new Student();
		student.setSid(rs.getInt(1));
		student.setName(rs.getString(2));
		student.setDepartment(rs.getString(3));
		student.setCpa(rs.getDouble(4));
		student.setAddress(rs.getString(5));
		return student;
	}
	

}
