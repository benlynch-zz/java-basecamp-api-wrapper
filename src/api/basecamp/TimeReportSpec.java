package api.basecamp;

import java.util.Calendar;

/***
 * 
 * Object used for spending report specifications to a TimeReport
 * 
 * @author jondavidjohn
 *
 */
public class TimeReportSpec {
	
	public int 		subject_id = 0;
	public Calendar to = null;
	public Calendar from = null;
	public int 		filterProjectId = 0;
	public int 		filterCompanyId = 0;
	
}
