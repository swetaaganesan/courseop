package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProgramsController {
	
	@Autowired
	private CoursesService coursesService;
	
	@RequestMapping("/programs")
	public String getPrograms(Map<String, Object> model) {
		//TODO service to get course timings
		return "programs";
	}
	
	@RequestMapping("/programCourses")
	public String getProgramCourses(Map<String, Object> model) throws IOException {
		List<Courses> response = coursesService.getListOfCourses();
		model.put("courses", response);
		return "programCourses";
	}
}
