package org.ccsu.courseop.model;

import java.util.List;

public class Programs {
	
	private String programName;
	private List<String> requiredCourses;
	private int totalCredits;
	private String admissionRequirements;
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public List<String> getRequiredCourses() {
		return requiredCourses;
	}
	public void setRequiredCourses(List<String> requiredCourses) {
		this.requiredCourses = requiredCourses;
	}
	public int getTotalCredits() {
		return totalCredits;
	}
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}
	public String getAdmissionRequirements() {
		return admissionRequirements;
	}
	public void setAdmissionRequirements(String admissionRequirements) {
		this.admissionRequirements = admissionRequirements;
	}
	
}
