package api.basecamp;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import sun.misc.BASE64Encoder;



/***
 * Base for building out BaseCamp Entities
 * 
 * Includes Basic REST Operations with Authentication
 * 
 * @author jondavidjohn
 */
abstract class BaseCampEntity {
	
	//--- User / Company Authentication information
	private String username;
	private String password;
    private String baseUrl;
	
	protected BaseCampEntity(BCAuth auth) {
		
		this.username = auth.username;
		this.password = auth.password;
		this.baseUrl  = auth.company+".basecamphq.com";
		
		//Attach http/https
		if (auth.useSsl) { this.baseUrl = "https://"+baseUrl; }
		else 	         { this.baseUrl = "http://"+baseUrl; }
		
	}
	
	//--- Base REST interaction methods
	
	/***
	 * GET HTTP Operation
	 * 
	 * @param	request	Request URI	
	 * @return	Element Root Element of XML Response
	 */
	protected Element get(String request) {
		
		HttpURLConnection connection = null;
		
		Element rootElement;
		
		try {
	
	        URL url = new URL(this.baseUrl + request);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        String userpassword = this.username + ":" + this.password;
	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
	        connection.setRequestProperty("Content-type", "application/xml");
	        connection.setRequestProperty("Accept", "application/xml");
	        
	        InputStream responseStream = connection.getInputStream();
	        
	        //--- Parse XML response InputStream into DOM
	        
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        
	        Document doc = db.parse(responseStream);
	        
	        rootElement = doc.getDocumentElement();
	        
		} catch(Exception e) {
			
			System.out.print(e.toString());
			rootElement = null;
			
		} finally {
	        
			if(connection != null) {
	            connection.disconnect(); 
	        }
		
		}
		
		return rootElement;
	}
	
	/***
	 * POST HTTP Operation
	 * 
	 * @param	request	Request URI	
	 * @param	POST Data in String form
	 * @return	int ID of inserted (posted) element
	 */
	protected int post(String requestURL, String requestData) {
		
		HttpURLConnection connection = null;

		int itemID;
		
		try {
	
	        URL url = new URL(this.baseUrl + requestURL);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        String userpassword = this.username + ":" + this.password;
	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
	        connection.setRequestProperty("Content-type", "application/xml");
	        connection.setRequestProperty("Accept", "application/xml");
	        
	        //--- Send POST data
	        connection.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(requestData);
	        wr.flush();
	        wr.close();
	        
	        String responseHeader = connection.getHeaderField(0);
	        String[] headerArray = responseHeader.split(" ");
	        
	        int responseCode = Integer.parseInt(headerArray[1]);
	        
	        if (responseCode == 201) {
	        	
	        	String locationURL = connection.getHeaderField("Location");
	        	String[] locationArray = locationURL.split("/");
	        	itemID = Integer.parseInt(locationArray[locationArray.length - 1]);
	        	
	        }
	        else {
	        	
	        	itemID = 0;
	        
	        }
	        
	        
		} catch(Exception e) {
			
			System.out.print(e.toString());
			itemID = 0;
			
		} finally {
	        
			if(connection != null) {
	            connection.disconnect(); 
	        }
		
		}
		
		return itemID;
	}
	
	/***
	 * PUT HTTP OPERATION
	 * 
	 * @param	request	Request URI	
	 * @param	POST Data in String form
	 * @return	Boolean if operation was successful
	 */
	protected boolean put(String requestURL, String requestData) {
		
		HttpURLConnection connection = null;

		boolean response;
		
		try {
	
	        URL url = new URL(this.baseUrl + requestURL);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("PUT");
	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        String userpassword = this.username + ":" + this.password;
	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
	        connection.setRequestProperty("Content-type", "application/xml");
	        connection.setRequestProperty("Accept", "application/xml");
	        
	        //--- Send POST data
	        connection.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(requestData);
	        wr.flush();
	        wr.close();
	        	        
	        String responseHeader = connection.getHeaderField(0);
	        String[] headerArray = responseHeader.split(" ");
	        
	        int responseCode = Integer.parseInt(headerArray[1]);
	        
	        if (responseCode == 200) {
	        	
	        	response = true;
	        	
	        }
	        else {
	        	
	        	response = false;
	        
	        }
	        
		} catch(Exception e) {
			
			System.out.print(e.toString());
			response = false;
			
		} finally {
	        
			if(connection != null) {
	            connection.disconnect(); 
	        }
		
		}
		
		return response;
	}

	/***
	 * DELETE HTTP OPERATION
	 * 
	 * @param	request	Request URI	
	 * @return	Boolean success
	 */
	protected boolean delete(String request) {
		
		HttpURLConnection connection = null;
		
		boolean response;
		
		try {
						
	        URL url = new URL(this.baseUrl + request);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("DELETE");
	        BASE64Encoder enc = new sun.misc.BASE64Encoder();
	        String userpassword = this.username + ":" + this.password;
	        String encodedAuthorization = enc.encode( userpassword.getBytes() );
	        connection.setRequestProperty("Authorization", "Basic "+ encodedAuthorization);
	        connection.setRequestProperty("Content-type", "application/xml");
	        connection.setRequestProperty("Accept", "application/xml");
	        
	        String responseHeader = connection.getHeaderField(0);
	        String[] headerArray = responseHeader.split(" ");
	        
	        int responseCode = Integer.parseInt(headerArray[1]);
	        
	        if (responseCode == 200) {
	        	
	        	response = true;
	        	
	        }
	        else {
	        	
	        	response = false;
	        
	        }

	        
		} catch(Exception e) {
			
			System.out.print(e.toString());
			response = false;
			
		} finally {
	        
			if(connection != null) {
	            connection.disconnect(); 
	        }
		
		}
		
		return response;
	}

}
