package tests;

import org.json.JSONArray;
import org.json.JSONException;

import tasks.library;
import utility.Log;

public class RecentChanges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//doesn't work when i try to add 
		//Parameters, type, key and author can be specified to limit the results to modifications to objects of specified type, specified key and by an author respectively
		//String sResults=library.getRecentChanges("5", "", "edit-book");
		//Log.info(sResults);
		//getCount(sResults);
		//offset doesnt seem to work
		//String sResults=library.getRecentChanges("5", "2");
		String sResults=library.getRecentChanges("5", "");
		//getCount(sResults);
		checkResults("5", getCount(sResults));
		sResults=library.getRecentChanges("1", "", "");
		//Log.info(sResults);
		//getCount(sResults);
		checkResults("1", getCount(sResults));
		sResults=library.getRecentChanges("15", "", "");
		//Log.info(sResults);
		//getCount(sResults);
		checkResults("15", getCount(sResults));
		//max is 1000
		sResults=library.getRecentChanges("", "", "");
		checkResults("1000", getCount(sResults));
		//Log.info(sResults);
		//getCount(sResults);
		Log.endTestCase("Recent Changes test");
	}
	
	public static void checkResults(String sExp, String sAct)
	{
		if (sExp.equalsIgnoreCase(sAct))
			Log.pass("Expected number of recent changes returned "+sExp);
		else
			Log.fail("Expected number of recent changes not returned " + sExp + ":" + sAct);
	}

	public static String getCount(String sResults)
	{
		try {
			JSONArray obj = new JSONArray(sResults);
			//returns how many authors works
			String sRes=Integer.toString(obj.length());
			Log.info(sRes);
			return sRes;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
}
