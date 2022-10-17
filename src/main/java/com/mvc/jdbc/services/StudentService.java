package com.mvc.jdbc.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mvc.jdbc.dao.StudentDao;
import com.mvc.jdbc.student.Student;

@Service
public class StudentService {
	
	ApplicationContext jdbcContext=new ClassPathXmlApplicationContext("JdbcContext.xml");
	
	StudentDao dao=(StudentDao) jdbcContext.getBean("sdao");
	
	public List<Student> getAll(){
		List<Student> list=dao.getAll();
		
		return list;
	}
	public boolean insert(Student s)
	{
		int i=dao.insert(s);
		if(i==1)
			return true;
		else
			return false;
	}
	public boolean update(int id,String name)
	{
		int i=dao.update(name, id);
		if(i==1)
			return true;
		else
			return false;
	}
	public boolean delete(int id)
	{
		int i=dao.delete(id);
		if(i==1)
			return true;
		else
			return false;
	}
	public Student findById(int id)
	{
		return dao.findById(id);
	}
}
