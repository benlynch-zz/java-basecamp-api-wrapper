package api.basecamp;

public class BCAuth {

	public String 	username;
	public String 	password;
	public String 	company;
	public boolean 	useSsl;
	
	/***
	 * Init Empty BCAuth Object
	 * 
	 * Note: properties are public, so you can init them one by one if needed
	 */
	public BCAuth() {
		
		this.username = "";
		this.password = "";
		this.company  = "";
		this.useSsl	  = false;
		
	}
	
	/***
	 * Initialize BaseCamp Authentication Object
	 *
	 * @param un	BaseCamp Username
	 * @param pw	BaseCamp Password
	 * @param cp	BaseCamp Company Name (for url purposes)
	 * @param ssl	if ssl is enabled on your account
	 */
	public BCAuth(String un, String pw, String cp, boolean ssl) {
		
		this.username = un;
		this.password = pw;
		this.company  = cp;
		this.useSsl	  = ssl;
		
	}
	
}
