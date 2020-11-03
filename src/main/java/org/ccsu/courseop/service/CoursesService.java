package org.ccsu.courseop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.util.AdvisorSchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {
	
	@Autowired
	private AdvisorSchemaFactory schemaFactory;

	/**
	 * SELECT QUERY 1:
	 * Graduate courses
	 * @throws IOException 
	 */
	public List<Courses> getListOfCourses() throws IOException {
		
		QueryExecution qexec;
		Query query;

		String selectQuery1 =
				"prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
				"prefix owl: <http://www.w3.org/2002/07/owl#> \r\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n" + 
				"prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n" + 
				"prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n" + 
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
				"prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
				"base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> "+

		   "JSON { \"course\": ?course ," +
		   " \"courseName\" : ?course_name , " +
		   " \"courseNumber\" : ?course_number } "+ 
		   "where {" +
		   "?course AdvisoryBot:courseName ?course_name ." +
		   "?course AdvisoryBot:courseNumber ?course_number }";
		   

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1,Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Courses> response = new ArrayList<Courses>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			//JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println(jsonObj.get("course") +"\n");
				System.out.println(jsonObj.get("course_name") +"\n");
				System.out.println(jsonObj.get("course_number") +"\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				response.add(course);
			}
		}
		finally {
			qexec.close();
		}
		return response;
	}

public List<Courses> getListOfUnderGraduateCourses() throws IOException {
		
		QueryExecution qexec;
		Query query;

		String selectQuery1 =
				"prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
				"prefix owl: <http://www.w3.org/2002/07/owl#> \r\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n" + 
				"prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n" + 
				"prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n" + 
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
				"prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
				"base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> "+

		   "JSON { \"course\": ?course ," +
		   " \"courseName\" : ?course_name , " +
		   " \"courseNumber\" : ?course_number } "+ 
		   "where {" +
		   "?course rdf:type AdvisoryBot:UnderGraduate ." +
		   "?course AdvisoryBot:courseName ?course_name ." +
		   "?course AdvisoryBot:courseNumber ?course_number }";
		   

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1,Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Courses> response = new ArrayList<Courses>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			//JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println(jsonObj.get("course") +"\n");
				System.out.println(jsonObj.get("course_name") +"\n");
				System.out.println(jsonObj.get("course_number") +"\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				response.add(course);
			}
		}
		finally {
			qexec.close();
		}
		return response;
	}
}
