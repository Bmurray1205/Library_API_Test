package tests;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tasks.Book;
import tasks.library;
import utility.Log;

public class BookTest1 {
	
	public static String sAuthor1="OL1A";
	public static String sAuthor1Bio="OL1518080A";
	public static String sBook1="OL1M";
	public static String sBook1Info="OL6807502M";
	public static String sBook2="OL4731M";//different data than book1?
	public static String sBook3="OL14930760W";
	public static Book Book1 =  new Book("Kabit\u0101.","$4.50", 304, "1962", "Sachi Rautroy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//Books test
		testBook(sBook1);
		//neg Books test
		testNegBook(sBook1+"bad");
		Log.endTestCase("Book test");
	}
	
	public static void testBook(String sBook)
	{
		String sResults=library.getBook(sBook, library.gsJSON, false);
		//Log.info(sResults);
		Book BookAct=Book.setBookData2(sResults);
		//compare Book objects (short version)
		if (BookAct.equals(Book1))
			Log.pass("Books JSON Test1->Books Match");
		else
			Log.fail("Books JSON Test1->Books don't match");
	
		//Accept: header doesnt seem to work for me
		/*sResults=library.getBook(sBook, library.gsJSON, true);
		Log.info(sResults);
		BookAct=Book.setBookData2(sResults);
		//compare Book objects (short version)
		if (BookAct.equals(Book1))
			Log.pass("Books JSON Test1->Books Match");
		else
			Log.fail("Books JSON Test1->Books don't match");*/
	
		//TODO: add RDF format
	}
	
	/** 
	* testNegBook - checks for Book data for bad author
	* @param String sBook - invalid book
	* @author Brian Murray   	
	*/
	public static void testNegBook(String sBook)
	{
		String sResults=library.getAuthor("OASDJ!", library.gsJSON, false);
		if (sResults.contains("\"error\": \"notfound\""))
			Log.pass("Error was correctly found");
		else
			Log.fail("Error was not correctly found");
	}
	
	public static void testBookEditions (String sBook)
	{
		//author's works...GOOD
		//first no limit and make sure default limit of 20 is imposed if num works greater than 20
		Log.info("Test Author Works using default");
		String sResults=library.getBookEditions(sBook, "", "");
		//Log.info(sResults);
		try {
			JSONObject obj = new JSONObject(sResults);
			//returns how many authors works
			String sEditions = obj.getString("size");
			//not sure why but size value is off by one
			int iEditions = Integer.parseInt(sEditions)-1;
			Log.info("Book " + sBook + " has " + iEditions + " Works");
			//this works for looping through array
			JSONArray getArray = obj.getJSONArray("entries");
			checkWorksCount(iEditions, getArray.length(), 20);

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
				Log.pass("Book editions equals returned count of "+iExp);
			else
				Log.fail("Book editions does not equal returned count of "+iExp);
		}
		else
		{
			if (iDefault==iArraySize)
				Log.pass("Book editions equals returned default or set limit of " + iDefault);
			else
				Log.fail("Book editions does not equal default or set limit of " + iDefault);
		}
	}
		

}