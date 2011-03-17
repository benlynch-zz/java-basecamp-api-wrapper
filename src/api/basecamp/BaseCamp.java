//package api.basecamp;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import sun.misc.BASE64Encoder;
//
//public class BaseCamp {
//	
//	//--- User / Company Authentication information
//	private String username;
//	private String password;
//    private String baseUrl;
//
//	//--- BaseCamp Connection information
//	private HttpURLConnection 	connection;
//
//	public BaseCamp(String un, String pw, String cp, boolean ssl) {
//		
//		this.username = un;
//		this.password = pw;
//		this.baseUrl  = cp+".basecamphq.com";
//		
//		//Attach http/https
//		if (ssl) { this.baseUrl = "https://"+baseUrl; }
//		else 	 { this.baseUrl = "http://"+baseUrl; }
//		
//	}
//	
//	//---------- Account
//	
//	public Account getAccount() {
//		Element accountElement = this.get("/account.xml");
//		Account account = new Account(accountElement);
//		return account;
//	}
//	
//	//---------- People
//
//	public Person getMe() {
//		Element meElement = this.get("/me.xml");
//		Person me = new Person(meElement);
//		return me;
//	}
//	
//	public Person getPerson(int personId) {
//		Element personElement = this.get("/people/"+personId+".xml");
//		Person person = new Person(personElement);
//		return person;
//	}
//	
//	public People getAllPeople() {
//		Element peopleElement = this.get("/people.xml");
//		People people = new People(peopleElement);
//		return people;
//	}
//	
//	public People getCompanyPeople(int companyId) {
//		Element peopleElement = this.get("companies/"+companyId+"/people.xml");
//		People people = new People(peopleElement);
//		return people;
//	}
//	
//	public People getProjectPeople(int projectId) {
//		Element peopleElement = this.get("projects/"+projectId+"/people.xml");
//		People people = new People(peopleElement);
//		return people;
//	}
//	
//	//---------- Projects
//	
//	public Project getProject(int projectId) {
//		Element projectElement = this.get("/projects/"+projectId+".xml");
//		Project project = new Project(projectElement);
//		return project;
//	}
//	
//	public Projects getAllProjects() {
//		Element projectsElement = this.get("/projects.xml");
//		Projects projects = new Projects(projectsElement);
//		return projects;
//	}
//	
//	//---------- Companies
//	
//	public Company getCompany(int companyId) {
//		Element companyElement = this.get("/companies/"+companyId+".xml");
//		Company company = new Company(companyElement);
//		return company;
//	}
//	
//	public Companies getCompanies() {
//		Element companiesElement = this.get("/companies.xml");
//		Companies companies = new Companies(companiesElement);
//		return companies;
//	}
//	
//	public Companies getProjectCompanies(int projectId) {
//		Element companiesElement = this.get("/projects/"+projectId+"/companies.xml");
//		Companies companies = new Companies(companiesElement);
//		return companies;
//	}
//	
//	//---------- Catagories
//	
//	//TODO - Catagories
//	
//	//---------- Messages
//	
//	//TODO - Messages
//	
//	//---------- Comments
//	
//	//TODO - Comments
//	
//	//---------- To-do Lists
//	
//	//TODO - To-do Lists
//	
//	//---------- To-do List Items
//	
//	//TODO - To-do List Items
//	
//	//---------- Milestones
//	
//	//TODO - Milestones
//	
//	//---------- Time Tracking
//	
//	public TimeEntry getTimeEntry(int entryId) {
//		Element entryElement = this.get("/time_entries/"+entryId+".xml");
//		TimeEntry entry = new TimeEntry(entryElement);
//		return entry;
//	}
//	
//	public TimeReport getTimeReport(TimeReportSpec spec) {
//		String options = "?";
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
//		
//		if (spec.subject_id != 0) { options += "subject_id="+spec.subject_id+"&"; }
//		
//		if (spec.from != null) { options += "from="+sdf.format(spec.from)+"&"; }
//		
//		if (spec.to != null) { options += "to="+sdf.format(spec.to)+"&"; }
//		
//		if (spec.filterCompanyId != 0) { options += "filter_company_id="+spec.filterCompanyId+"&"; }
//		
//		if (spec.filterProjectId != 0) { options += "filter_project_id="+spec.filterProjectId+"&"; }
//		
//		Element entriesElement = this.get("/time_entries/report.xml"+options);
//		TimeReport report = new TimeReport(entriesElement);
//		return report;
//	}
//	
//	public TimeReport getProjectTimeReport(int projectId) {
//		Element entriesElement = this.get("/projects/"+projectId+"/time_entries.xml");
//		TimeReport report = new TimeReport(entriesElement);
//		return report;
//	}
//	
//	public TimeReport getToDoTimeReport(int toDoId) {
//		Element entriesElement = this.get("/todo_items/"+toDoId+"/time_entries.xml");
//		TimeReport report = new TimeReport(entriesElement);
//		return report;
//	}
//	
//	public TimeEntry createTimeEntry(int projectId, String requestString) {
//		Element newEntryElement = this.post("/projects/5878743/time_entries.xml", requestString);
//		TimeEntry newEntry = new TimeEntry(newEntryElement);
//		return newEntry;
//	}
//	
//	//---------- Files
//	
//	//TODO - Files
//	
//	
//	//---------- REST VERB OPERATIONS
//	
//	/***
//	 * GET
//	 * 
//	 * @param	request	Request URI	
//	 * @return	Element Root Element of XML Response
//	 */
//	private Element get(String request) {
//		
//		Element rootElement;
//		
//		try {
//	
//	        URL url = new URL(this.baseUrl + request);
//	        connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod("GET");
//	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
//	        String userpassword = this.username + ":" + this.password;
//	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
//	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
//	        connection.setRequestProperty("Content-type", "application/xml");
//	        connection.setRequestProperty("Accept", "application/xml");
//	        
//	        InputStream responseStream = (InputStream) connection.getInputStream();
//	        
//	        //--- Parse XML response InputStream into DOM
//	        
//	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	        DocumentBuilder db = dbf.newDocumentBuilder();
//	        
//	        Document doc = db.parse(responseStream);
//	        
//	        rootElement = doc.getDocumentElement();
//	        
//		} catch(Exception e) {
//			
//			System.out.print(e.toString());
//			rootElement = null;
//			
//		} finally {
//	        
//			if(connection != null) {
//	            connection.disconnect(); 
//	        }
//		
//		}
//		
//		return rootElement;
//	}
//	
//	/***
//	 * POST
//	 * 
//	 * @param	request	Request URI	
//	 * @param	POST Data in XML Element form
//	 * @return	Element Root Element of XML Response
//	 */
//	private Element post(String request, String requestData) {
//		
//		Element response;
//		
//		try {
//	
//	        URL url = new URL(this.baseUrl + request);
//	        connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod("POST");
//	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
//	        String userpassword = this.username + ":" + this.password;
//	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
//	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
//	        connection.setRequestProperty("Content-type", "application/xml");
//	        connection.setRequestProperty("Accept", "application/xml");
//	        
//	        //--- Send POST data
//	        connection.setDoOutput(true);
//	        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//	        wr.write(requestData);
//	        wr.flush();
//	        wr.close();
//	        
//	        
//	        response = null;
//	        
//	        if (connection.getHeaderField(0) == "HTTP/1.1 201 Created" ) {
//	        	response = this.get(connection.getHeaderField("Location"));
//	        }
//	        
//		} catch(Exception e) {
//			
//			System.out.print(e.toString());
//			response = null;
//			
//		} finally {
//	        
//			if(connection != null) {
//	            connection.disconnect(); 
//	        }
//		
//		}
//		
//		return response;
//	}
//}
