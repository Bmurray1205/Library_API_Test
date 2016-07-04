package tests;


import org.json.JSONException;
import org.json.JSONObject;

import tasks.library;
import utility.Log;

public class test1 {
	
	public static String sAuthor1="OL1A";
	public static String sAuthor1Bio="OL1518080A";
	public static String sBook1="OL1M";
	public static String sBook2="OL14930765W";//"OL27258W";//OL14930765W
	//get other book codes from querying Author Works
	public static String sBook1Info="OL6807502M";
	public static String sBook3="OL4731M";//different data than book1?
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//author's works...GOOD
		//String sResults=library.getAuthorsWorks(sAuthor1);
		
		//book edition's...GOOD
		//String sResults=library.getEditions(sBook2, "2");
		
		//String sResults=library.getAuthor("OASDJ!", library.gsJSON, false);
		//above results in {"key": "/authors/OASDJ!", "error": "notfound"}
		
		//String sResults=library.getAuthorBad(sAuthor1, library.gsJSON, false);
		//above returned the results of previous query...DEFECT
		
		//String sResults=library.getBookHistory(sBook1, library.gsJSON, false);
		//works for books...default limit of 20 worked
		
		//String sResults=library.getAuthorHistory(sAuthor1, library.gsJSON, false);
		//works
		//String sResults=library.getRecentChanges();
		//Log.info(sResults);

		//sResults=library.getAuthor(sAuthor1, library.gsRDF, false);
		//getAuthorData(sResults);
		//Log.info(sResults);
		
		//sResults=library.getAuthor(sAuthor1Bio, library.gsJSON);
		//Log.info(sResults);
		//sResults=library.getAuthor("sAuthor2", library.gsRDF);
		//Log.info(sResults);
		
		//books
		String sResults=library.getBook(sBook3, library.gsJSON, false);
		Log.info(sResults);
		//sResults=library.getBook(sBook1, library.gsRDF);
		//Log.info(sResults);
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
			Log.info("id: " + obj.getString("id"));
			Log.info("revision: " + obj.getString("revision"));
			Log.info("Last Modified:type:" + obj.getJSONObject("last_modified").getString("type"));
			Log.info("Last Modified:value:" + obj.getJSONObject("last_modified").getString("value"));
			Log.info("Type:key:" + obj.getJSONObject("type").getString("key"));
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
