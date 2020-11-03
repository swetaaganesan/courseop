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
import org.ccsu.courseop.model.Faculty;
import org.ccsu.courseop.util.AdvisorSchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
	@Autowired
	private AdvisorSchemaFactory schemaFactory;
	
	private QueryExecution qexec;
	private Query query;
	/**
	 * SELECT QUERY 1:
	 * Graduate courses
	 * @throws IOException 
	 */
	public List<Faculty> getListOfPermanentFaculty() throws IOException {

		String selectQuery1 =
				 
				"prefix owl: <http://www.w3.org/2002/07/owl#> \r\n" + 
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n" + 
				"prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n" + 
				"prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n" + 
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
				"prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
				"base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> \r\n" + 
				"\r\n" + 
				"JSON {\r\n" + 
				"  \"Faculty\": ?faculty ,\r\n" + 
				"  \"firstName\" : ?first_name , \r\n" + 
				"  \"lastName\" : ?last_name , \r\n" + 
				"  \"email\" : ?email ,\r\n" + 
				"  \"website\" : ?website \r\n" + 
				"}\r\n" + 
				"where{\r\n" + 
				"	   ?faculty rdf:type owl:NamedIndividual, AdvisoryBot:Permanent.\r\n" + 
				"	   ?faculty AdvisoryBot:firstName ?first_name .\r\n" + 
				"	   ?faculty AdvisoryBot:lastName ?last_name .	   \r\n" + 
				"	   ?faculty AdvisoryBot:email ?email .\r\n" + 
				"	   OPTIONAL {?faculty AdvisoryBot:website ?website .}\r\n" + 
				"}";
		   System.out.println("Query=="+selectQuery1);

		Model schema = schemaFactory.readFacultySchema();
		query = QueryFactory.create(selectQuery1,Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Faculty> response = new ArrayList<Faculty>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Permanent Faculty");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println(jsonObj.get("faculty") +"\n");
				System.out.println(jsonObj.get("first_name") +"\n");
				System.out.println(jsonObj.get("last_name") +"\n");
				System.out.println(jsonObj.get("email") +"\n");
				System.out.println(jsonObj.get("website") +"\n");
				Faculty faculty = new Faculty();
				faculty.setFirstName(jsonObj.get("first_name").toString().replace("\"", ""));
				faculty.setLastName(jsonObj.get("last_name").toString().replace("\"", ""));
				faculty.setEmail(jsonObj.get("email").toString().replace("\"", ""));
				faculty.setWebsite(jsonObj.get("website").toString().replace("\"", ""));
				response.add(faculty);
				System.out.println(faculty);
			}
		}
		finally {
			qexec.close();
		}
		return response;
	}

public List<Faculty> getListOfAdjunctFaculty() throws IOException {
		

	String selectQuery1 =
			"prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
			"prefix owl: <http://www.w3.org/2002/07/owl#> \r\n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n" + 
			"prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n" + 
			"prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n" + 
			"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n" + 
			"prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n" + 
			"base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> \r\n" + 
			"\r\n" + 
			"JSON {\r\n" + 
			"  \"Faculty\": ?faculty ,\r\n" + 
			"  \"firstName\" : ?first_name , \r\n" + 
			"  \"lastName\" : ?last_name , \r\n" + 
			"  \"email\" : ?email ,\r\n" + 
			"  \"website\" : ?website \r\n" + 
			"}\r\n" + 
			"where{\r\n" + 
			"	   ?faculty rdf:type owl:NamedIndividual, AdvisoryBot:Adjunct.\r\n" + 
			"	   ?faculty AdvisoryBot:firstName ?first_name .\r\n" + 
			"	   ?faculty AdvisoryBot:lastName ?last_name .	   \r\n" + 
			"	   ?faculty AdvisoryBot:email ?email .\r\n" + 
			"	   OPTIONAL {?faculty AdvisoryBot:website ?website .}\r\n" + 
			"}";
	   

	Model schema = schemaFactory.readFacultySchema();
	query = QueryFactory.create(selectQuery1,Syntax.syntaxARQ);
	qexec = QueryExecutionFactory.create(query, schema);
	List<Faculty> response = new ArrayList<Faculty>();
	try {
		Iterator<JsonObject> json = qexec.execJsonItems();
		//JsonArray QueryExecution.execJson()
		System.out.println("===============================================================================");
		System.out.println("SELECT QUERY 1 :");
		System.out.println("Adjunct Faculty");
		System.out.println("===============================================================================");
		while (json.hasNext()) {
			JsonObject jsonObj = json.next();
			System.out.println(jsonObj.get("faculty") +"\n");
			System.out.println(jsonObj.get("first_name") +"\n");
			System.out.println(jsonObj.get("last_name") +"\n");
			System.out.println(jsonObj.get("email") +"\n");
			System.out.println(jsonObj.get("website") +"\n");
			Faculty faculty = new Faculty();
			faculty.setFirstName(jsonObj.get("first_name").toString().replace("\"", ""));
			faculty.setLastName(jsonObj.get("last_name").toString().replace("\"", ""));
			faculty.setEmail(jsonObj.get("email").toString().replace("\"", ""));
			faculty.setWebsite(jsonObj.get("website").toString().replace("\"", ""));
			response.add(faculty);
		}
	}
	finally {
		qexec.close();
	}
	return response;
}
}
