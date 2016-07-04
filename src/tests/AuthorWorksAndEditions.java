package tests;


import java.util.Iterator;

import org.apache.log4j.LogSF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tasks.library;
import utility.Log;

public class AuthorWorksAndEditions {
	
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
		getAuthorsWorksAndEditions (sAuthor1);
		Log.endTestCase("Author's works and Editions test");
	}

	/** 
	* this cycles through all of an authors works and all the editions of that work
	* currently needs some verification points
	* @author Brian Murray   	
	*/
	public static void getAuthorsWorksAndEditions (String sAuthor)
	{
		int iTotal=0;
		String sBook="";
		//author's works...GOOD
		String sWorks=library.getAuthorsWorks(sAuthor, "");
		//Log.info(sWorks);
		try {
			JSONObject obj = new JSONObject(sWorks);
			//returns how many authors works
			Log.checkResults("17", obj.getString("size"));
			Log.info("This author "+ sAuthor +" has "+ obj.getString("size") + " works");
			//this works for looping through array
			JSONArray getArray = obj.getJSONArray("entries");
			Log.info("This author "+ sAuthor +" has "+ getArray.length() + " works");
			for (int i = 0; i < getArray.length(); i++) {
	            JSONObject objects = getArray.getJSONObject(i);
	            sBook=objects.getString("key");
                sBook=sBook.replace("/works/", "");
                //get the editions for this work getting publish date and revision fields
	            String sEditions=library.getQueryEditionsAndWorks(sBook, "publish_date=&revision", "", "");
	            //log results
                Log.info(sEditions);
                //TODO:  need to do some checking here
                try {
                	JSONArray getArray2 =  new JSONArray(sEditions);
        			Log.info("This work " + sBook + " has "+ getArray2.length() + " editions");
        			iTotal=iTotal+getArray2.length();
                } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.info("This Author has a total of " + iTotal + " editions");
			//check that the total editions is correct can also use this to compare to get authors editions
			Log.checkResults("18", Integer.toString(iTotal));
			Log.info("Cross check results with results from Authors total editions");
			String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "", "", "");
			Log.checkResults(Integer.toString(iTotal), getCount(sResults));
		} catch (JSONException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		}
		
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
	
	
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//author's works...GOOD
		String sResults=library.getAuthorsWorks(sAuthor1, "0");
		//Log.info(sResults);
		try {
			JSONObject obj = new JSONObject(sResults);
			//returns how many authors works
			Log.info("This author has "+ obj.getString("size") + "works");
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
