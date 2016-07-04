package tests;

import org.json.JSONArray;
import org.json.JSONException;

import tasks.library;
import utility.Log;

public class EditionsAndAuthorsTest {
	
	public static String sAuthor1="OL1A";
	public static String sBook1="OL2040129W";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//returns all fields
		//ok works now 51 results
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "*", "", "");
		//works
		String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "", "", "");
		Log.checkResults("18", getCount(sResults));
		//good returns just the keys
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "", "", "");
		//returns key and title
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "title", "", "");
		//returns just 2
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "title", "2", "");
		//returns next 2
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "title", "2", "2");
		
		//returns just key
		//String sResults=library.getQueryEditionsAndWorks(sBook1, "", "", "");
		//returns all
		//String sResults=library.getQueryEditionsAndWorks(sBook1, "*", "", "");
		//title plus key
		//String sResults=library.getQueryEditionsAndWorks(sBook1, "title", "", "");
		//String sResults=library.login();
		//doesnt work
		Log.info(sResults);
		//TODO: get list of author works and compare to master
		Log.endTestCase("Edition & Authors test");
	}
	
	public static String getCount(String sResults)
	{
		try {
			JSONArray obj = new JSONArray(sResults);
			//returns how many authors editions...subtract 1 so we dont count author
			String sRes=Integer.toString(obj.length()-1);
			Log.info(sRes);
			return sRes;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	
}
