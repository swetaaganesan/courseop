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
public class HomeController {


	@Autowired
	private CoursesService coursesService;

	@RequestMapping("/")
	public String getCourses(Map<String, Object> model) {
		return "home";
	}

	@RequestMapping("/homecourses")
	public String getHomeCourses(Map<String, Object> model) throws IOException {
		List<Courses> response = coursesService.getListOfCourses();
		model.put("courses", response);
		return "courses";
	}


}
