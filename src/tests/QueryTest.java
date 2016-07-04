package tests;

import tasks.library;
import utility.Log;

public class QueryTest {
	
	public static String sAuthor1="OL1A";
	public static String sBook1="OL1M";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.testSetup();
		//returns all fields
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "*", "", "");
		//returns all fields
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "title", "", "");
		//doesnt work
		String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "", "", "");
		//doesnt work
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "", "2", "");
		//doesnt work
		//String sResults=library.getQueryEditionsAndAuthors(sAuthor1, "key", "", "");
		Log.info(sResults);
		//TODO: get list of author works and compare to master
		Log.endTestCase("Author test");
	}

}
