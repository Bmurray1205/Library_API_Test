package tests;


import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tasks.Author;
import tasks.library;
import utility.Log;


public class AuthorTest1 {
	
	public static String sAuthor1="OL1A";
	public static String sAuthor1Bio="OL1518080A";
	public static String sBook1="OL1M";
	public static String sBook1Info="OL6807502M";
	public static String sBook2="OL4731M";//different data than book1?
	public static Author Author1 =  new Author("Sachi Rautroy","Sachi Rautroy","2004","1916");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//author test
		testAuthor (sAuthor1);
		//author neg test
		testNegAuthor (sAuthor1+"bad");
		//checking counts only
		testAuthorWorks (sAuthor1);
		//TODO: get list of author works and compare to master
		Log.endTestCase("Author test");
	}
	
	
	/** 
	* testAuthor - checks for author data 
	* @param String sAuthor - valid author
	* @author Brian Murray   	
	*/
	public static void testAuthor(String sAuthor)
	{
		//get author data
		String sResults=library.getAuthor(sAuthor, library.gsJSON, false);
		//Log.info(sResults);
		Author AuthorAct=Author.setAuthorData(sResults);
		//compare author objects (short version)
		if (AuthorAct.equals(Author1))
			Log.pass("Authors JSON Test1->Authors Match");
		else
			Log.fail("Authors JSON Test1->Authors don't match");
		
		sResults=library.getAuthor(sAuthor, library.gsJSON, true);
		//Log.info(sResults);
		AuthorAct=Author.setAuthorData(sResults);
		//compare author objects (short version)
		if (AuthorAct.equals(Author1))
			Log.pass("Authors JSON Test2->Authors Match");
		else
			Log.fail("Authors JSON Test2->Authors don't match");
		//TODO: add RDF format checks
	}

	/** 
	* testNegAuthor - checks for author data for bad author
	* @param String sAuthor - invalid author
	* @author Brian Murray   	
	*/
	public static void testNegAuthor(String sAuthor)
	{
		String sResults=library.getAuthor("OASDJ!", library.gsJSON, false);
		
		String sExpErr="\"error\": \"notfound\"";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		if (sResults.contains("\"error\": \"notfound\""))
			Log.pass("Error was correctly found");
		else
			Log.fail("Error was not correctly found");
	}

	/** 
	* testAuthorWorks - does various test for author works around offsets
	* @param String sAuthor - invalid author
	* @author Brian Murray   	
	*/
	public static void testAuthorWorks (String sAuthor)
	{
		//author's works...GOOD
		//first no limit and make sure default limit of 20 is imposed if num works greater than 20
		Log.info("Test Author Works using default");
		String sResults=library.getAuthorsWorks(sAuthor, "");
		//Log.info(sResults);
		try {
			JSONObject authorObj = new JSONObject(sResults);
			//returns how many authors works
			String sWorks = authorObj.getString("size");
			//not sure why but size value is off by one
			int iWorks = Integer.parseInt(sWorks)-1;
			Log.info("Author " + sAuthor + " has " + iWorks + " Works");
			//this works for looping through array
			JSONArray getArray = authorObj.getJSONArray("entries");
			checkWorksCount(iWorks, getArray.length(), 20);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test limit < default and less than expected size
		Log.info("Test Author Works using limit of 3");
		sResults=library.getAuthorsWorks(sAuthor, "3");
		//Log.info(sResults);
		try {
			JSONObject authorObj = new JSONObject(sResults);
			//returns how many authors works
			String sWorks = authorObj.getString("size");
			//not sure why but size value is off by one
			int iWorks = Integer.parseInt(sWorks)-1;
			Log.info("Author " + sAuthor + " has " + iWorks + " Works");
			//this works for looping through array
			JSONArray getArray = authorObj.getJSONArray("entries");
			checkWorksCount(iWorks, getArray.length(), 3);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this fails for author
		//test limit = negative 1 is bad query and returns previous set of results
		Log.info("Test Author Works using limit of -1");
		sResults=library.getAuthorsWorks(sAuthor, "3");
		//Log.info(sResults);
		String sExpErr="LIMIT must not be negative";
		if (sResults.contains(sExpErr))
			Log.pass("Error was correctly found: " + sExpErr);
		else
			Log.fail("Error was not correctly found: " + sExpErr);
		
		//test limit = string acts like default
		//for RecentChanges this returns an error
		//I think it should be consistent, so maybe a defect
		Log.info("Test Author Works using limit of string");
		sResults=library.getAuthorsWorks(sAuthor, "test");
		//Log.info(sResults);
		try {
			JSONObject authorObj = new JSONObject(sResults);
			//returns how many authors works
			String sWorks = authorObj.getString("size");
			//not sure why but size value is off by one
			int iWorks = Integer.parseInt(sWorks)-1;
			Log.info("Author " + sAuthor + " has " + iWorks + " Works");
			//this works for looping through array
			JSONArray getArray = authorObj.getJSONArray("entries");
			checkWorksCount(iWorks, getArray.length(), 20);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test limit = zero acts like default
		Log.info("Test Author Works using limit of zero");
		sResults=library.getAuthorsWorks(sAuthor, "0");
		//Log.info(sResults);
		try {
			JSONObject authorObj = new JSONObject(sResults);
			//returns how many authors works
			String sWorks = authorObj.getString("size");
			//not sure why but size value is off by one
			int iWorks = Integer.parseInt(sWorks)-1;
			Log.info("Author " + sAuthor + " has " + iWorks + " Works");
			//this works for looping through array
			JSONArray getArray = authorObj.getJSONArray("entries");
			checkWorksCount(iWorks, getArray.length(), 20);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void checkWorksCount(int iExp, int iArraySize, int iDefault)
	{
		if (iExp<=iDefault)
		{
			if (iExp==iArraySize)
				Log.pass("Author works equals returned count of "+iExp);
			else
				Log.fail("Author works does not equal returned count of "+iExp);
		}
		else
		{
			if (iDefault==iArraySize)
				Log.pass("Author works equals returned default or set limit of " + iDefault);
			else
				Log.fail("Author works does not equal default or set limit of " + iDefault);
		}
	}

	public static void testAuthorWorks2 (String sAuthor)
	{
		//author's works...GOOD
		String sResults=library.getAuthorsWorks(sAuthor, "3", "3");
		//Log.info(sResults);
		try {
			JSONObject obj = new JSONObject(sResults);
			//returns how many authors works
			Log.info(obj.getString("size"));
			//this works for looping through array
			JSONArray getArray = obj.getJSONArray("entries");
			for (int i = 0; i < getArray.length(); i++) {
	            JSONObject objects = getArray.getJSONObject(i);
	            Iterator<?> key = objects.keys();
	            while (key.hasNext()) {
	                String k = key.next().toString();
	                if (k.equalsIgnoreCase("title"))
	                	Log.info("Key : " + k + ", value : " + objects.getString(k));
	            }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void getAuthorData(String s)
	{
		try {
			JSONObject obj = new JSONObject(s);
			Log.info("name:" + obj.getString("name"));
			Log.info("personal name: " + obj.getString("personal_name"));
			Log.info("death date: " + obj.getString("death_date"));
			Log.info("key: " + obj.getString("key"));
			Log.info("birth date: " + obj.getString("birth_date"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
