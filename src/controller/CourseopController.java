package org.ccsu.courseop.controller;

import java.util.List;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.service.GraduateCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CourseopController {
	
	@Autowired
	private GraduateCoursesService graduateCoursesService;
	
	@GetMapping("/courses")
	public ResponseEntity<List<Courses>> getCourses() {
		List<Courses> response = graduateCoursesService.getCourses();
		 return new ResponseEntity<List<Courses>>(response, HttpStatus.OK);
	}

}
