package tests;

import org.json.JSONArray;
import org.json.JSONException;

import tasks.library;
import utility.Log;

public class EditionsAndWorksTest {
	
	public static String sAuthor1="OL1A";
	public static String sWork1="OL2040129W";
	

	public static void main(String[] args) {
		// TODO should use JSON to count properties, currently just counting string
		Log.testSetup();
		//limits and offsets
		testQueryEditionsAndWork();
		//test getting different properties
		testQueryEditionsAndWork2();
		//TODO: get list of author works and compare to master
		Log.endTestCase("Edition & Works test");
	}
	
	/** 
	* testQueryEditionsAndAuthor - test querying getting different properties
	* @author Brian Murray   	
	*/
	public static void testQueryEditionsAndWork2()
	{
		//returns key field only and total record
		//interesting, when no limit then an extra field is returned with Work key
		Log.info("Editions using property title");
		String sResults=library.getQueryEditionsAndWorks(sWork1, "title", "", "");
		//Log.info(sResults);
		String sExp="\"title\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==8)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		//Log.info(sResults);
		Log.checkResults("8", getCount(sResults));
		
		Log.info("Editions using property title and limit 3");
		sResults=library.getQueryEditionsAndWorks(sWork1, "title", "3", "");
		//Log.info(sResults);
		sExp="\"title\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==3)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		//Log.info(sResults);
		sExp="\"key\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==3)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		sExp="\"latest_revision\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==0)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		Log.checkResults("3", getCount(sResults));
		
		//should return all properties
		Log.info("Editions using property *, limit 3 and offset 2");
		sResults=library.getQueryEditionsAndWorks(sWork1, "*", "3", "2");
		//Log.info(sResults);
		sExp=" \"title\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==3)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		sExp="\"latest_revision\"";
		Log.info(Integer.toString(Log.count(sResults, sExp)));
		if (Log.count(sResults, sExp)==3)
			Log.pass(sExp+" property was correctly found: " + sExp);
		else
			Log.fail(sExp+" property was not correctly found: " + sExp);
		//Log.info(sResults);
		Log.checkResults("3", getCount(sResults));
	}
	
	/** 
	* testQueryEditionsAndWork - test querying using various limits
	* @author Brian Murray   	
	*/
	public static void testQueryEditionsAndWork()
	{
		//returns key field only and total record
		//interesting, when no limit then an extra field is returned with Work key
		Log.info("Editions using no arguments with header format");
		String sResults=library.getQueryEditionsAndWorks(sWork1, "", "", "", true);
		//Log.info(sResults);
		Log.checkResults("8", getCount(sResults));
		
		//limit = 15
		Log.info("Editions using limit 15");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "7", "");
		Log.checkResults("7", getCount(sResults));
		
		//limit = 1
		Log.info("Editions using limit 1");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "1", "");
		//Log.info(sResults);
		Log.checkResults("1", getCount(sResults));

		//limit > max
		Log.info("Editions using limit Max Plus");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "25", "");
		//Log.info(sResults);
		Log.checkResults("8", getCount(sResults));

		//limit > -1
		Log.info("Editions using limit -1");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "-1", "");
		//Log.info(sResults);
		String sExpErr="LIMIT must not be negative";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		//offset > -1
		Log.info("Editions using limit 5 offset -1");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "5", "-1");
		//Log.info(sResults);
		sExpErr="OFFSET must not be negative";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);

		//limit > test
		//FAILS..seems to just ignore the string and return all
		Log.info("Editions using limit test");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "test", "");
		//Log.info(sResults);
		sExpErr="column \\\"test\\\" does not exist\\nLINE 1: ...ction t WHERE 1 = 1 ORDER BY t.created DESC LIMIT test OFFSE...";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		//offset > test
		//FAIL just ignores the offset
		Log.info("Editions using limit 5 offset test");
		sResults=library.getQueryEditionsAndWorks(sWork1, "", "5", "test");
		//Log.info(sResults);
		sExpErr="column \\\"test\\\" does not exist\\nLINE 1: ...on t WHERE 1 = 1 ORDER BY t.created DESC LIMIT 2 OFFSET test";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
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
			//returns how many authors editions...subtract 1 so we dont count author
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
