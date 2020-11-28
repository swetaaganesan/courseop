package org.ccsu.courseop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.model.Faculty;
import org.ccsu.courseop.util.AdvisorSchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CoursesService {

	@Autowired
	private AdvisorSchemaFactory schemaFactory;
	QueryExecution qexec;
	Query query;

	public List<Courses> getListOfCourses() throws IOException {
		List<Courses> response = new ArrayList<Courses>();
		response.addAll(getListOfUnderGraduateCourses());
		response.addAll(getListOfGraduateCourses());
		response.addAll(getListOfFacultyCourse() );
		return response;
	}

	/**
	 * SELECT QUERY 1: Graduate courses
	 * 
	 * @throws IOException
	 */
	public List<Courses> getListOfGraduateCourses() throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> " +

				"JSON { \"course\": ?course ,\r\n" + "\"courseName\" : ?course_name , \r\n"
				+ "\"courseNumber\" : ?course_number ,\r\n" + "\"coursePrerequisite\" : ?prerequisite } \r\n"
				+ "where {\r\n" + "?course rdf:type AdvisoryBot:Graduate .\r\n"
				+ "OPTIONAL { ?course AdvisoryBot:hasPrerequisite ?prerequisite .}\r\n"
				+ "?course AdvisoryBot:courseName ?course_name .\r\n"
				+ "?course AdvisoryBot:courseNumber ?course_number .}";

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Courses> response = new ArrayList<Courses>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("course_name") + "\n");
				System.out.println(jsonObj.get("course_number") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				course.setType("Graduate");
				List<String> prerequisites = new ArrayList<String>();
				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				Optional<Courses> existingCourseOpt = response.stream()
						.filter(res -> res.getCourseName().equals(course.getCourseName())).findAny();
				if (existingCourseOpt.isPresent()) {
					Courses existingCourse = existingCourseOpt.get();
					if (!(prerequisite.equals("AD_CIT")) && !(prerequisite.equals("AD_SE")) && !CollectionUtils.isEmpty(existingCourse.getPrerequisite())) {
						existingCourse.getPrerequisite().add(prerequisite);
					}
					//existingCourse.setPrerequisite(prerequisites);
				} else {
					if (prerequisite.equals("AD_CIT") || prerequisite.equals("AD_SE")) {
						prerequisite = "None";
					}
					prerequisites.add(prerequisite);
					course.setPrerequisite(prerequisites);
					response.add(course);
					getListOfFacultyCourse();
				}
			}
		} finally {
			qexec.close();
			
		}
		return response;
	}

	public List<Courses> getListOfUnderGraduateCourses() throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> " +

				"JSON { \"course\": ?course ," + " \"courseName\" : ?course_name , "
				+ " \"courseNumber\" : ?course_number , " + " \"coursePrerequisite\" : ?prerequisite } " + "where {"
				+ "?course rdf:type AdvisoryBot:Undergraduate ."
				+ "OPTIONAL { ?course AdvisoryBot:hasPrerequisite ?prerequisite . }"
				+ "?course AdvisoryBot:courseName ?course_name ." 
				+ "?course AdvisoryBot:courseNumber ?course_number .}";

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Courses> response = new ArrayList<Courses>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			// JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 2 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("course_name") + "\n");
				System.out.println(jsonObj.get("course_number") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				course.setType("UnderGraduate");
				List<String> prerequisites = new ArrayList<String>();
				//course.setPrerequisite(jsonObj.get("prerequisite").toString().replace("\"", ""));
				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				Optional<Courses> existingCourseOpt = response.stream()
						.filter(res -> res.getCourseName().equals(course.getCourseName())).findAny();
				if (existingCourseOpt.isPresent()) {
					Courses existingCourse = existingCourseOpt.get();
					if (!(prerequisite.equals("AD_CIT")) && !(prerequisite.equals("AD_SE")) && !CollectionUtils.isEmpty(existingCourse.getPrerequisite())) {
						existingCourse.getPrerequisite().add(prerequisite);
					}
					//existingCourse.setPrerequisite(prerequisites);
				} else {
					if (prerequisite.equals("AD_CIT") || prerequisite.equals("AD_SE")) {
						prerequisite = "None";
					}
					prerequisites.add(prerequisite);
					course.setPrerequisite(prerequisites);
					response.add(course);
				}
			}
		} finally {
			qexec.close();
		}
		return response;
	}
	
	public List getListOfFacultyCourse() throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> " +

				"JSON {  \"Faculty\" : ?faculty , "
				+ " \"courseNumber\" : ?course_number , } "
				+ "where {"
				+ " ?Faculty :teaches ?course_number . }";

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List response = new ArrayList();
		//List<Faculty> response1 = new ArrayList<Faculty>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			// JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("Test===== 1 :");
			System.out.println("Faculty courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("faculty") + "\n");
				System.out.println(jsonObj.get("course_number") + "\n");
				
				Courses course = new Courses();
				Faculty faculty = new Faculty();
				//faculty.setFirstName(jsonObj.get("faculty").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				course.setType("UnderGraduate");
			
					response.add(course);
					
					response.add(faculty);
				}
			
		} finally {
			qexec.close();
		}
		return response;
	}

	public List getCourseDetails() throws IOException {
		Model schema = schemaFactory.readIntegratedSchema();
		List response = new ArrayList();
		return response;

	}

}
