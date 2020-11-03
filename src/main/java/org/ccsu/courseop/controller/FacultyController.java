package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.ccsu.courseop.model.Faculty;
import org.ccsu.courseop.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping("/permanentFaculty")
	public String getPermanentFaculty(Map<String, Object> model) throws IOException {
		List<Faculty> response = facultyService.getListOfPermanentFaculty();
		model.put("faculty", response);
		return "permanentFaculty";
	}
	
	@RequestMapping("/adjunctFaculty")
	public String getAdjunctFaculty(Map<String, Object> model) throws IOException {
		List<Faculty> response = facultyService.getListOfAdjunctFaculty();
		model.put("faculty", response);
		return "adjunctFaculty";
	}
	
	@RequestMapping("/homefaculty")
	public String getHomeFaculty(Map<String, Object> model) throws IOException {
		return "homefaculty";
	}

}
