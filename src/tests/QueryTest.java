package tests;

import tasks.library;
import utility.Log;

public class QueryTest {
	
	public static String sAuthor1="OL1A";
	public static String sBook1="OL2040129W";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//returns all fields
		//ok works now 51 results
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "*", "", "");
		//works
		String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "id", "", "");
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
		Log.endTestCase("Author test");
	}

}
