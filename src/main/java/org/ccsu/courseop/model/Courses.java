package org.ccsu.courseop.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Courses {

	private String courseName;
	private String courseNumber;
	private String type;
	private List<String> prerequisite;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(List<String> prerequisite) {
		this.prerequisite = prerequisite;
	}
}
