package tests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tasks.Change;
import tasks.library;
import utility.Log;

public class RecentChanges {
	
	public static Change Change1 =  new Change("Author bio","update", "2016-07-05T13:42:14.688134");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//gets and compare first result to expected
		//testChange();
		//limits only
		testRecentChanges();
		//offsets
		testRecentChanges2();
		Log.endTestCase("Recent Changes test");
	}

	//TODO add tests for below.
	//I did try the sample query and checked results, but I didn't understand how the results were limited in the example 
	//Parameters, type, key and author can be specified to limit the results to modifications to objects of specified type, specified key and by an author

	/** 
	* testChange - checks for Changes data
	* @author Brian Murray   	
	*/
	///TODO:  need a way to set expected data. data changes too much
	//skip test
	public static void testChange()
	{
		String sResults=library.getRecentChanges("1", "", "");
		Log.info(sResults);
		Change ChangeAct=Change.setChangeData(sResults);
		//compare Change objects
		if (ChangeAct.equals(Change1))
			Log.pass("Change JSON Test1->Changes Match");
		else
			Log.fail("Change JSON Test1->Changes don't match");
	}
	
	/** 
	* testRecentChanges - test getting recent changes using various limits
	* @author Brian Murray   	
	*/
	public static void testRecentChanges()
	{
		Log.info("Test Recent Changes using limit of 5");
		String sResults=library.getRecentChanges("5", "");
		//Log.info(sResults);
		//getCount(sResults);
		Log.checkResults("5", getCount(sResults));
		
		Log.info("Test Recent Changes using limit of 1");
		sResults=library.getRecentChanges("1", "", "");
		//Log.info(sResults);
		//getCount(sResults);
		Log.checkResults("1", getCount(sResults));

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
		Log.checkResults("15", getCount(sResults));

		Log.info("Test Recent Changes using limit of 0 returns 0");
		sResults=library.getRecentChanges("0", "", "");
		Log.info(sResults);
		//getCount(sResults);
		Log.checkResults("0", getCount(sResults));

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
		Log.checkResults("1000", getCount(sResults));
		//Log.info(sResults);
		//getCount(sResults);
	}
	
	/** 
	* testRecentChanges2 - test getting recent changes using limits and Offsets
	* @author Brian Murray   	
	*/
	public static void testRecentChanges2()
	{
		Log.info("Test Recent Changes using Offset of 1");
		String sResults=library.getRecentChanges("2", "");
		//Log.info(sResults);
		String sExp=getXTimeStamp(sResults, 2);
		Log.info("TimeStamp for record 2 is: "+sExp);
		sResults=library.getRecentChanges("2", "1");
		//Log.info(sResults);
		String sAct=getXTimeStamp(sResults, 1);
		Log.info("TimeStamp for record 1 is: "+sAct);
		Log.info("TimeStamp for record 1 with offset of 1 should equal record 2 with no offset");
		Log.checkString(sExp, sAct);

		
		Log.info("Test Recent Changes using Offset of 15");
		sResults=library.getRecentChanges("16", "");
		//Log.info(sResults);
		sExp=getXTimeStamp(sResults, 16);
		Log.info("TimeStamp for record 16 is: "+sExp);
		sResults=library.getRecentChanges("2", "15");
		//Log.info(sResults);
		sAct=getXTimeStamp(sResults, 1);
		Log.info("TimeStamp for record 1 is: "+sAct);
		Log.info("TimeStamp for record 1 with offset of 1 should equal record 2 with no offset");
		Log.checkString(sExp, sAct);
		
		
		Log.info("Test Recent Changes using offset of -1");
		sResults=library.getRecentChanges("2", "-1", "");
		//Log.info(sResults);
		String sExpErr="OFFSET must not be negative";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		Log.info("Test Recent Changes using offset of a String");
		sResults=library.getRecentChanges("2", "test", "");
		//Log.info(sResults);
		sExpErr="column \\\"test\\\" does not exist\\nLINE 1: ...on t WHERE 1 = 1 ORDER BY t.created DESC LIMIT 2 OFFSET test";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
	}
	
	
	/** 
	* getXTimeStamp - Gets the Xth timestamp so we can test offsets
	* @param sResults -  the JSON results
	* @param iNum - the record to return
	* @return String - the count of the editions returned
	* @author Brian Murray   	
	*/
	public static String getXTimeStamp(String sResults, int iNum)
	{
		try {
			JSONArray obj = new JSONArray(sResults);
			JSONObject objects = obj.getJSONObject(iNum-1);
			String sTimeStamp = objects.getString("timestamp");
			return sTimeStamp;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	
	/** 
	* getCount - Gets the count of the items in the returned results array
	* @param sResults -  the JSON results
	* @return String - the count of the editions returned
	* @author Brian Murray   	
	*/
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
