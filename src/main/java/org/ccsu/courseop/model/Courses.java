package org.ccsu.courseop.model;

import org.springframework.stereotype.Component;

@Component
public class Courses {

	private String courseName;
	private String courseNumber;
	
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
}
