package org.ccsu.courseop.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.springframework.stereotype.Component;

import openllet.jena.PelletReasonerFactory;

@Component
public class AdvisorSchemaFactory{

	//private static final Logger logger = LogManager.getLogger(TTLFileReader.class);
	private static final String COURSE_TTL_FILE = "src/main/resources/static/StudentProg-3.1.ttl";
	private static final String FACULTY_TTL_FILE = "src/main/resources/static/Faculty-me.ttl";

	private OntModel courseSchema = ModelFactory.createOntologyModel();
	private OntModel facultySchema = ModelFactory.createOntologyModel();

	private Reasoner reasoner = PelletReasonerFactory.theInstance().create();
	
	private InfModel courseInference = ModelFactory.createInfModel(reasoner, courseSchema);
	private InfModel facultyInference = ModelFactory.createInfModel(reasoner, facultySchema);
	
	private String base = "http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#";
	
	public Model readCourseSchema() throws IOException {

		// reading the file from a local drive
		courseInference.read(new FileInputStream(COURSE_TTL_FILE), null, "TTL");
		return courseInference;
	}

	public Model readFacultySchema() throws IOException{

		facultyInference.read(new FileInputStream(FACULTY_TTL_FILE), null, "TTL");
		return facultyInference;
	}
	
	public Model readIntegratedSchema() throws IOException{
		
		facultySchema.read(new FileInputStream(FACULTY_TTL_FILE), null, "TTL");
		courseInference.read(new FileInputStream(COURSE_TTL_FILE), null, "TTL");
		
		Property teaches = facultySchema.createProperty(base + "teaches");

		Resource course = courseSchema.getResource(base + "Course");
		Resource professor = facultySchema.getResource(base + "Faculty");
		
		Individual profChad = facultySchema.getIndividual(base + "Chad_Williams");
		Individual course502 = courseSchema.getIndividual(base + "CS502");
		
		professor.addProperty(RDF.type, RDFS.Class);

		teaches.addProperty(RDFS.domain, professor);
		teaches.addProperty(RDFS.range, course);
		
		profChad.addProperty(RDF.type, professor);
		profChad.addProperty(teaches, course502);


		return facultyInference;
	}

}
