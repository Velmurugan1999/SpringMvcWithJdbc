package com.mvc.jdbc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mvc.jdbc.student.Student;
import com.mvc.jdbc.student.StudentRowMapper;
@Component
public class StudentDaoImpl implements StudentDao{
	JdbcTemplate template;
	Logger log=Logger.getLogger("Student");
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int insert(Student s) {
		String sqal="insert into student_details values("+s.getSid()+",'"+s.getName()+"','"+
				s.getDepartment()+"',"+s.getCpa()+",'"+s.getAddress()+"')";
		int i=template.update(sqal);
		if(i==1)
			log.info("Insertion Successfully!!!!!!");
		else
			log.error("Insertion Failed!!!!!!!!");
		return i;
	}

	public List<Student> getAll() {
		String sql="select *from student_details";
		log.info(String.format("%-5s %-15s %-15s %-15s %-15s","ID","Name","Department","Address","CGPA"));
		List<Student> list=template.query(sql, new StudentRowMapper());
		list.forEach(s->log.info(String.format("%-5s %-15s %-15s %-15s",s.getSid(),s.getName(),s.getDepartment(),s.getCpa(),s.getAddress())));
		System.out.println();
		return list;
	}

	public int update(String s, int id) {
		String sql="update student_details set sname='"+s+"' where sid="+id+"";
		int i=template.update(sql);
		if(i==1)
			log.info("Updated Successfully!!!!!!");
		else
			log.error("Updating Failed!!!!!!!!");
		return i;
	}

	public int delete(int id) {
		String sql="delete from student_details where sid="+id+"";
		int i=template.update(sql);
		if(i==1)
			log.info("deleted Successfully!!!!!!");
		else
			log.error("deleted Failed!!!!!!!!");
		return i;
	}

	@Override
	public Student findById(int id) {
		String sql="select * from student_details where sid="+id;
		try {
		Student s=template.queryForObject(sql, new StudentRowMapper());
		
			log.info(s.toString());
			return s;
		}
		catch (Exception e) {
			log.error("No record found!!!");
			return null;
		}
	}

}
