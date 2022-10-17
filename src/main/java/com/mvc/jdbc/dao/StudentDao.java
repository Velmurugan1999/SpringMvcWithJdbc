package com.mvc.jdbc.dao;

import java.util.List;

import com.mvc.jdbc.student.Student;

public interface StudentDao {
	int insert(Student s);
	List<Student> getAll();
	int update(String  s, int id);
	int delete(int id);
	Student findById(int id);
}
