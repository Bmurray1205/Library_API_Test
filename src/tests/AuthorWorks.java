package tests;


import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tasks.library;
import utility.Log;

public class AuthorWorks {
	
	public static String sAuthor1="OL1A";
	public static String sAuthor1Bio="OL1518080A";
	public static String sBook1="OL1M";
	public static String sBook2="OL14930765W";//"OL27258W";//OL14930765W
	//get other book codes from querying Author Works
	public static String sBook1Info="OL6807502M";
	public static String sBook3="OL4731M";//different data than book1?
	
	
	
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		String sBook="";
		String sBookResult="";
		//author's works...GOOD
		String sResults=library.getAuthorsWorks(sAuthor1, "2");
		Log.info(sResults);
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
	                sBook=objects.getString("key");
	                //remove works
	                sBook=sBook.replace("/works/", "");
	        		//sBookResult=library.getBook(sBook, library.gsJSON, false);
	        		//Log.info(sBookResult);
	                Log.info(sBook);
	            }
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//author's works...GOOD
		String sResults=library.getAuthorsWorks(sAuthor1, "0");
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
