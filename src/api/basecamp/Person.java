package api.basecamp;

import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * Person Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class Person extends BaseCampEntity {

	private int 		id;
	private int 		clientId;
	private Calendar 	createdAt;
	private boolean 	isDeleted;
	private boolean 	hasAccessToNewProjects;
	private String 		imHandle;
	private String 		imService;
	private String 		phoneFax;
	private String 		phoneHome;
	private String 		phoneMobile;
	private String 		phoneOffice;
	private String 		phoneOfficeExt;
	private String 		title;
	private String 		token;
	private Calendar 	updatedAt;
	private String 		uuid;
	private String 		firstName;
	private String 		lastName;
	private int 		companyId;
	private String 		userName;
	private boolean 	isAdministrator;
	private String 		email;
	private String 		avatarUrl;

	/***
	 * Get instance of current user defined in BCAuth Object
	 * 
	 * @param auth	BCAuth Auth info for BaseCamp
	 */
	public Person(BCAuth auth) {
		
		super(auth);
		
		Element personElement = super.get("/me.xml");
		
		this.id						= ElementValue.getIntValue(personElement, "id");
		this.clientId 				= ElementValue.getIntValue(personElement,  "client-id");
		this.createdAt 				= ElementValue.getDateTimeValue(personElement, "created-at");
		this.isDeleted 				= ElementValue.getBoolValue(personElement, "deleted");
		this.hasAccessToNewProjects = ElementValue.getBoolValue(personElement, "has-access-to-new-projects");
		this.imHandle 				= ElementValue.getTextValue(personElement, "im-handle");
		this.imService 				= ElementValue.getTextValue(personElement, "im-service");
		this.phoneFax 				= ElementValue.getTextValue(personElement, "phone-number-fax");
		this.phoneHome 				= ElementValue.getTextValue(personElement, "phone-number-home");
		this.phoneMobile 			= ElementValue.getTextValue(personElement, "phone-number-mobile");
		this.phoneOffice 			= ElementValue.getTextValue(personElement, "phone-number-office");
		this.phoneOfficeExt 		= ElementValue.getTextValue(personElement, "phone-number-office-ext");
		this.title 					= ElementValue.getTextValue(personElement, "title");
		this.token 					= ElementValue.getTextValue(personElement, "token");
		this.updatedAt 				= ElementValue.getDateTimeValue(personElement, "updated-at");
		this.uuid 					= ElementValue.getTextValue(personElement, "uuid");
		this.firstName 				= ElementValue.getTextValue(personElement, "first-name");
		this.lastName 				= ElementValue.getTextValue(personElement, "last-name");
		this.companyId 				= ElementValue.getIntValue(personElement,  "company-id");
		this.userName 				= ElementValue.getTextValue(personElement, "user-name");
		this.isAdministrator 		= ElementValue.getBoolValue(personElement, "administrator");
		this.email 					= ElementValue.getTextValue(personElement, "email-address");
		this.avatarUrl 				= ElementValue.getTextValue(personElement, "avatar-url");
		
	}

	/***
	 * Get instance of user defined by personId
	 * 
	 * @param auth		BCAuth	BaseCamp Auth info object
	 * @param personId	ID of desired Person
	 */
	public Person(BCAuth auth, int personId) {
		
		super(auth);
		
		Element personElement = super.get("/people/"+personId+".xml");
		
		this.id						= personId;
		this.clientId 				= ElementValue.getIntValue(personElement,  "client-id");
		this.createdAt 				= ElementValue.getDateTimeValue(personElement, "created-at");
		this.isDeleted 				= ElementValue.getBoolValue(personElement, "deleted");
		this.hasAccessToNewProjects = ElementValue.getBoolValue(personElement, "has-access-to-new-projects");
		this.imHandle 				= ElementValue.getTextValue(personElement, "im-handle");
		this.imService 				= ElementValue.getTextValue(personElement, "im-service");
		this.phoneFax 				= ElementValue.getTextValue(personElement, "phone-number-fax");
		this.phoneHome 				= ElementValue.getTextValue(personElement, "phone-number-home");
		this.phoneMobile 			= ElementValue.getTextValue(personElement, "phone-number-mobile");
		this.phoneOffice 			= ElementValue.getTextValue(personElement, "phone-number-office");
		this.phoneOfficeExt 		= ElementValue.getTextValue(personElement, "phone-number-office-ext");
		this.title 					= ElementValue.getTextValue(personElement, "title");
		this.token 					= ElementValue.getTextValue(personElement, "token");
		this.updatedAt 				= ElementValue.getDateTimeValue(personElement, "updated-at");
		this.uuid 					= ElementValue.getTextValue(personElement, "uuid");
		this.firstName 				= ElementValue.getTextValue(personElement, "first-name");
		this.lastName 				= ElementValue.getTextValue(personElement, "last-name");
		this.companyId 				= ElementValue.getIntValue(personElement,  "company-id");
		this.userName 				= ElementValue.getTextValue(personElement, "user-name");
		this.isAdministrator 		= ElementValue.getBoolValue(personElement, "administrator");
		this.email 					= ElementValue.getTextValue(personElement, "email-address");
		this.avatarUrl 				= ElementValue.getTextValue(personElement, "avatar-url");
		
	}
	
	/***
	 * Build person from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth			BCAuth object
	 * @param personElement	XML Element representation of Person
	 */
	Person(BCAuth auth, Element personElement) {
		super(auth);
		
		this.id						= ElementValue.getIntValue(personElement, "id");
		this.clientId 				= ElementValue.getIntValue(personElement,  "client-id");
		this.createdAt 				= ElementValue.getDateTimeValue(personElement, "created-at");
		this.isDeleted 				= ElementValue.getBoolValue(personElement, "deleted");
		this.hasAccessToNewProjects = ElementValue.getBoolValue(personElement, "has-access-to-new-projects");
		this.imHandle 				= ElementValue.getTextValue(personElement, "im-handle");
		this.imService 				= ElementValue.getTextValue(personElement, "im-service");
		this.phoneFax 				= ElementValue.getTextValue(personElement, "phone-number-fax");
		this.phoneHome 				= ElementValue.getTextValue(personElement, "phone-number-home");
		this.phoneMobile 			= ElementValue.getTextValue(personElement, "phone-number-mobile");
		this.phoneOffice 			= ElementValue.getTextValue(personElement, "phone-number-office");
		this.phoneOfficeExt 		= ElementValue.getTextValue(personElement, "phone-number-office-ext");
		this.title 					= ElementValue.getTextValue(personElement, "title");
		this.token 					= ElementValue.getTextValue(personElement, "token");
		this.updatedAt 				= ElementValue.getDateTimeValue(personElement, "updated-at");
		this.uuid 					= ElementValue.getTextValue(personElement, "uuid");
		this.firstName 				= ElementValue.getTextValue(personElement, "first-name");
		this.lastName 				= ElementValue.getTextValue(personElement, "last-name");
		this.companyId 				= ElementValue.getIntValue(personElement,  "company-id");
		this.userName 				= ElementValue.getTextValue(personElement, "user-name");
		this.isAdministrator 		= ElementValue.getBoolValue(personElement, "administrator");
		this.email 					= ElementValue.getTextValue(personElement, "email-address");
		this.avatarUrl 				= ElementValue.getTextValue(personElement, "avatar-url");
	}
	
	//--- Getters

	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName;
	}

	public int getId() {
		return this.id;
	}
	
	public int getClientId() {
		return this.clientId;
	}

	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	public boolean isDeleted() {
		return this.isDeleted;
	}

	public boolean hasAccessToNewProjects() {
		return this.hasAccessToNewProjects;
	}

	public String getImHandle() {
		return this.imHandle;
	}

	public String getImService() {
		return this.imService;
	}

	public String getPhoneFax() {
		return this.phoneFax;
	}

	public String getPhoneHome() {
		return this.phoneHome;
	}

	public String getPhoneMobile() {
		return this.phoneMobile;
	}

	public String getPhoneOffice() {
		return this.phoneOffice;
	}

	public String getPhoneOfficeExt() {
		return this.phoneOfficeExt;
	}

	public String getTitle() {
		return this.title;
	}

	public String getToken() {
		return this.token;
	}

	public Calendar getUpdatedAt() {
		return this.updatedAt;
	}

	public String getUuid() {
		return this.uuid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public String getUserName() {
		return this.userName;
	}

	public boolean isAdministrator() {
		return this.isAdministrator;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAvatarUrl() {
		return this.avatarUrl;
	}
	
	
	
	
	

	
	

}
