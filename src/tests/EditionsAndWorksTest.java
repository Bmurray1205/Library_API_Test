package tests;

import org.json.JSONArray;
import org.json.JSONException;

import tasks.library;
import utility.Log;

public class EditionsAndWorksTest {
	
	public static String sAuthor1="OL1A";
	public static String sBook1="OL2040129W";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//returns all fields
		//ok for this work we have 8 editions 
		String sResults=library.getQueryEditionsAndWorks(sBook1, "", "", "");
		Log.checkResults("8", getCount(sResults));
		sResults=library.getQueryEditionsAndWorks(sBook1, "*", "2", "");
		Log.checkResults("2", getCount(sResults));
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
		Log.endTestCase("Edition & Works test");
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
