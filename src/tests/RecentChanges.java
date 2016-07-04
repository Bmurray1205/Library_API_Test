package tests;

import org.json.JSONArray;
import org.json.JSONException;

import tasks.library;
import utility.Log;

public class RecentChanges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		testRecentChanges();
		Log.endTestCase("Recent Changes test");
	}
	
	public static void testRecentChanges()
	{
		//doesn't work when i try to add 
		//Parameters, type, key and author can be specified to limit the results to modifications to objects of specified type, specified key and by an author respectively
		//String sResults=library.getRecentChanges("5", "", "edit-book");
		//Log.info(sResults);//check
		//getCount(sResults);
		//offset doesnt seem to work
		//String sResults=library.getRecentChanges("5", "2");
		
		Log.info("Test Recent Changes using limit of 5");
		String sResults=library.getRecentChanges("5", "");
		Log.info(sResults);
		//getCount(sResults);
		checkResults("5", getCount(sResults));
		
		Log.info("Test Recent Changes using limit of 1");
		sResults=library.getRecentChanges("1", "", "");
		//Log.info(sResults);
		//getCount(sResults);
		checkResults("1", getCount(sResults));

		Log.info("Test Recent Changes using limit of -1");
		sResults=library.getRecentChanges("-1", "", "");
		//Log.info(sResults);
		String sExpErr="LIMIT must not be negative";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		Log.info("Test Recent Changes using limit of 15");
		sResults=library.getRecentChanges("15", "", "");
		//Log.info(sResults);
		//getCount(sResults);
		checkResults("15", getCount(sResults));

		Log.info("Test Recent Changes using limit of 0 returns 0");
		sResults=library.getRecentChanges("0", "", "");
		Log.info(sResults);
		//getCount(sResults);
		checkResults("0", getCount(sResults));

		Log.info("Test Recent Changes using limit of string test");
		sResults=library.getRecentChanges("test", "", "");
		sExpErr="column \\\"test\\\" does not exist\\nLINE 1: ...ction t WHERE 1 = 1 ORDER BY t.created DESC LIMIT test OFFSE...";
		//Log.info(sResults);
		//getCount(sResults);
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		//max is 1000		
		Log.info("Test Recent Changes using limit of empty string, default of 1000 returned");
		sResults=library.getRecentChanges("", "", "");
		checkResults("1000", getCount(sResults));
		//Log.info(sResults);
		//getCount(sResults);
		
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
