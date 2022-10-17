package com.mvc.jdbc.scontroller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mvc.jdbc.services.StudentService;
import com.mvc.jdbc.student.Student;

@RestController()
public class StudentController {
	@Autowired
	private StudentService service;
	public static Logger log=Logger.getLogger("Student");
	
	@GetMapping("/")
	public String welcome() {
		PropertyConfigurator.configure("C:\\Users\\hai\\SiemensTraining\\SpringMvcJdbc\\src\\main\\resources\\application.properties");
		log.info("Application Started!!!!!!!");
		
		log.info("Welcome method called");
		return "Welcome!!!   URLS::::  http://localhost:8095/SpringMvcJdbc/all \n "
				+ "http://localhost:8095/SpringMvcJdbc/insert"
				+ "\n http://localhost:8095/SpringMvcJdbc/update/{id}/{name}"+
				"\n http://localhost:8095/SpringMvcJdbc/delete/{id} \n"
				+ "http://localhost:8095/SpringMvcJdbc/findbyid/1{id}";
	}
	
	@GetMapping(path="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAllDetails() {
		service.getAll();
		log.info("All Student details are retrived.............");
		return (new Gson().toJson(service.getAll()));
	}
	@GetMapping(path="/findbyid/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public String findById(@PathVariable int id)
	{
		Student st=service.findById(id);
		if(st!=null) {
			return new Gson().toJson(st);
		}
		else
			return "No record found!!!";
	}
	@PostMapping(path="/insert",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String insert(@RequestBody Student student) {
		if(service.insert(student))
			return "Successfuly inserted!!!";
		else
			return "Failed to insert---------->";
	}
	@GetMapping(path="/update/{id}/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public String update(@PathVariable int id,@PathVariable String name)
	{
		if(service.update(id, name))
			return "Updated Successfully!!!";
		else
			return "Failed to update---------->";
	}
	
	@DeleteMapping(path="/delete/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public String delete(@PathVariable int id)
	{
		if(service.delete(id))
			return "Deleted Successfully!!!!!!";
		else
			return "Failed to delete!!!!!!!!Please check the id....";
	}
	
}
