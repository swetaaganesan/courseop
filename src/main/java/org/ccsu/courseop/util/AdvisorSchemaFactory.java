package org.ccsu.courseop.util;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		

		facultyInference.read(new FileInputStream(FACULTY_TTL_FILE), null, "TTL");
		courseInference.read(new FileInputStream(COURSE_TTL_FILE), null, "TTL");

		
		Property teaches = facultySchema.createProperty(base + "teaches");

		Resource course = courseSchema.getResource(base + "Course");
		Resource professor = facultySchema.getResource(base + "Faculty");
		
		professor.addProperty(RDF.type, RDFS.Class);

		teaches.addProperty(RDFS.domain, professor);
		teaches.addProperty(RDFS.range, course);
		
		Individual profChad = facultySchema.getIndividual(base + "Chad_Williams");
		Individual course502 = courseSchema.getIndividual(base + "CS502");
		Individual course505 = courseSchema.getIndividual(base + "CS505");
		Individual course492 = courseSchema.getIndividual(base + "CS492");
		
		profChad.addProperty(RDF.type, professor);
		profChad.addProperty(teaches, course502);
		profChad.addProperty(teaches, course505);
		profChad.addProperty(teaches, course492);
		
		
		Individual FatemehAbdollahzadeh = facultySchema.getIndividual(base + "Fatemeh_Abdollahzadeh");
		Individual course500 = courseSchema.getIndividual(base + "CS500");
		Individual course501 = courseSchema.getIndividual(base + "CS501");
		Individual course464 = courseSchema.getIndividual(base + "CS464");
		Individual course481 = courseSchema.getIndividual(base + "CS481");
		
		FatemehAbdollahzadeh.addProperty(RDF.type, professor);
		FatemehAbdollahzadeh.addProperty(teaches, course500);
		FatemehAbdollahzadeh.addProperty(teaches, course501);
		FatemehAbdollahzadeh.addProperty(teaches, course464);
		FatemehAbdollahzadeh.addProperty(teaches, course481);
		
		Individual YusufAlbayram = facultySchema.getIndividual(base + "Yusuf_Albayram");
		Individual course550 = courseSchema.getIndividual(base + "CS550");
		Individual course560 = courseSchema.getIndividual(base + "CS560");
		Individual course506 = courseSchema.getIndividual(base + "CS506");
		Individual course416 = courseSchema.getIndividual(base + "CS416");
		
		YusufAlbayram.addProperty(RDF.type, professor);
		YusufAlbayram.addProperty(teaches, course550);
		YusufAlbayram.addProperty(teaches, course560);
		YusufAlbayram.addProperty(teaches, course506);
		YusufAlbayram.addProperty(teaches, course416);
		
		Individual StanKukovsky = facultySchema.getIndividual(base + "Stan_Kukovsky");
		Individual course510 = courseSchema.getIndividual(base + "CS510");
		Individual course530 = courseSchema.getIndividual(base + "CS530");
		Individual course410 = courseSchema.getIndividual(base + "CS410");
		Individual course498 = courseSchema.getIndividual(base + "CS498");
		
		StanKukovsky.addProperty(RDF.type, professor);
		StanKukovsky.addProperty(teaches, course510);
		StanKukovsky.addProperty(teaches, course530);
		StanKukovsky.addProperty(teaches, course410);
		StanKukovsky.addProperty(teaches, course498);
		
		Individual NeliZlatareva = facultySchema.getIndividual(base + "Neli_Zlatareva");
		Individual course575 = courseSchema.getIndividual(base + "CS575");
		Individual course570 = courseSchema.getIndividual(base + "CS570");
		Individual course253 = courseSchema.getIndividual(base + "CS253");
		Individual course407 = courseSchema.getIndividual(base + "CS407");
		
		NeliZlatareva.addProperty(RDF.type, professor);
		NeliZlatareva.addProperty(teaches, course575);
		NeliZlatareva.addProperty(teaches, course570);
		NeliZlatareva.addProperty(teaches, course253);
		NeliZlatareva.addProperty(teaches, course407);
		
		Individual BradleyKjell = facultySchema.getIndividual(base + "Bradley_Kjell");
		Individual course152 = courseSchema.getIndividual(base + "CS152");
		Individual course254 = courseSchema.getIndividual(base + "CS254");
		Individual course494 = courseSchema.getIndividual(base + "CS494");
		
		BradleyKjell.addProperty(RDF.type, professor);
		BradleyKjell.addProperty(teaches, course152);
		BradleyKjell.addProperty(teaches, course254);
		BradleyKjell.addProperty(teaches, course494);
		
		Individual IrenaPevac = facultySchema.getIndividual(base + "Irena_Pevac");
		Individual course580 = courseSchema.getIndividual(base + "CS580");
		Individual course151 = courseSchema.getIndividual(base + "CS151");
		Individual course460 = courseSchema.getIndividual(base + "CS460");
		
		IrenaPevac.addProperty(RDF.type, professor);
		IrenaPevac.addProperty(teaches, course580);
		IrenaPevac.addProperty(teaches, course151);
		IrenaPevac.addProperty(teaches, course460);
		
		Individual SixiaChen = facultySchema.getIndividual(base + "Sixia_Chen");
		Individual course525 = courseSchema.getIndividual(base + "CS525");
		Individual course508 = courseSchema.getIndividual(base + "CS508");
		Individual course463 = courseSchema.getIndividual(base + "CS463");
		
		SixiaChen.addProperty(RDF.type, professor);
		SixiaChen.addProperty(teaches, course525);
		SixiaChen.addProperty(teaches, course508);
		SixiaChen.addProperty(teaches, course463);
		
		Individual ThomasKing = facultySchema.getIndividual(base + "Thomas_King");
		Individual course291 = courseSchema.getIndividual(base + "CS291");
		
		ThomasKing.addProperty(RDF.type, professor);
		ThomasKing.addProperty(teaches, course291);
		
		Individual ZdravkoMarkov = facultySchema.getIndividual(base + "Zdravko_Markov");
		Individual course354 = courseSchema.getIndividual(base + "CS354");
		Individual course385 = courseSchema.getIndividual(base + "CS385");
		
		ZdravkoMarkov.addProperty(RDF.type, professor);
		ZdravkoMarkov.addProperty(teaches, course354);
		ZdravkoMarkov.addProperty(teaches, course385);
		
		Individual MahiehZabihimayvan = facultySchema.getIndividual(base + "Mahieh_Zabihimayvan");
		Individual course355 = courseSchema.getIndividual(base + "CS355");
		Individual course493 = courseSchema.getIndividual(base + "CS493");
		
		MahiehZabihimayvan.addProperty(RDF.type, professor);
		MahiehZabihimayvan.addProperty(teaches, course355);
		MahiehZabihimayvan.addProperty(teaches, course493);	
	
		
		
		  facultyInference.write(System.out, "TURTLE");
		  courseInference.write(System.out, "TURTLE");
		  
		  
		  
		  
			
			
			  PrintWriter output = new PrintWriter(new FileWriter(FACULTY_TTL_FILE));
			  
			  
			  PrintWriter output1 =new PrintWriter(new FileWriter(COURSE_TTL_FILE));
			  
			  
			  facultyInference.write(output, "TURTLE");
			  
			  output.close();
			  courseInference.write(output1, "TURTLE");
			  
			  output1.close();
			 
		 


		return facultyInference;
}

}
