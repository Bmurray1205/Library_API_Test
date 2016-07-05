package tasks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import utility.Log;

public class library {

	public static String gsAutoSupportBaseDir = System.getProperty("user.dir");
	public static String gsToolsPath = gsAutoSupportBaseDir + "\\curl\\";
	/**Global string for user path*/
	public static String gsFlagFileName = gsAutoSupportBaseDir+"\\curlTemp.txt";

	public static final String gsLibraryRoot = "http://openlibrary.org";
	public static final String gsAuthor = "/authors/";
	public static final String gsAuthorBad = "/author/";
	public static final String gsAuthorWorks = "/works.json";
	public static final String gsBook = "/books/";
	public static final String gsWorks = "/works/";
	public static final String gsBookEditions = "/editions.json";
	public static final String gsSetLimit="limit=";
	public static final String gsSetOffset="offset=";
	public static final String gsRecentChanges="/recentchanges.json";
	public static final String gsEditionsAuthors="/query.json?type=/type/edition&authors=/authors/";
	public static final String gsEditionsWorks="/query.json?type=/type/edition&works=/works/";
	public static final String gsJSON = ".json";
	public static final String gsRDF = ".rdf";
	
	/** 
	* getAuthor - Get the specified author's information
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @param String bAccept - requested format can be specified using Accept: header
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	public static String getAuthor (String sKey, String sFormat, boolean bAccept)
	{
		String sToolString = "";
		if (bAccept)
			sToolString = constructCurlString2 (gsLibraryRoot+gsAuthor+sKey, sFormat);
		else	
			sToolString = constructCurlString (gsLibraryRoot+gsAuthor+sKey+sFormat);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getAuthor - Get the specified author's information
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @param String bAccept - requested format can be specified using Accept: header
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	public static String getAuthorBad (String sKey, String sFormat, boolean bAccept)
	{
		String sToolString = "";
		if (bAccept)
			sToolString = constructCurlString2 (gsLibraryRoot+gsAuthorBad+sKey, sFormat);
		else	
			sToolString = constructCurlString (gsLibraryRoot+gsAuthorBad+sKey+sFormat);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getAuthor - Get the specified author's information
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @author Brian Murray   	
	*/
	//no difference
	public static String getAuthorCallback (String sKey, String sFormat)
	{
		String sToolString = constructCurlString (gsLibraryRoot+gsAuthor+sKey+sFormat+"?callback=process");
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getBook - Get the specified Book's information
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @param String bAccept - requested format can be specified using Accept: header
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	public static String getBook (String sKey, String sFormat, boolean bAccept)
	{
		String sToolString = "";
		if (bAccept)
			sToolString = constructCurlString2 (gsLibraryRoot+gsBook+sKey, sFormat);
		else	
			sToolString = constructCurlString (gsLibraryRoot+gsBook+sKey+sFormat);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getBookHistory - Get the specified Object's History
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @param String bAccept - requested format can be specified using Accept: header
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	public static String getBookHistory (String sKey, String sFormat, boolean bAccept)
	{
		String sToolString = "";
		if (bAccept)
			sToolString = constructCurlString2 (gsLibraryRoot+gsBook+sKey+"?m=history", sFormat);
		else	
			sToolString = constructCurlString (gsLibraryRoot+gsBook+sKey+sFormat+"?m=history");
		runCurl(sToolString);
		return getSearchResultsString();
	}

	/** 
	* getAuthorHistory - Get the specified Object's History
	* @param String sKey - What are we looking for
	* @param String sFormat - JSON or RDF
	* @param String bAccept - requested format can be specified using Accept: header
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	public static String getAuthorHistory (String sKey, String sFormat, boolean bAccept)
	{
		String sToolString = "";
		if (bAccept)
			sToolString = constructCurlString2 (gsLibraryRoot+gsAuthor+sKey+"?m=history", sFormat);
		else	
			sToolString = constructCurlString (gsLibraryRoot+gsAuthor+sKey+sFormat+"?m=history");
		runCurl(sToolString);
		return getSearchResultsString();
	}

	/** 
	* getAuthorsWorks - Get the Author's works
	* @param String sKey - What are we looking for
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//JSON format only
	public static String getAuthorsWorks (String sKey, String sNum)
	{
		if (sNum.length()>0)
			sNum="?"+gsSetLimit+sNum;
		String sToolString = constructCurlString (gsLibraryRoot+gsAuthor+sKey+gsAuthorWorks+sNum);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getAuthorsWorks - Get the Author's works
	* @param String sKey - What are we looking for
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//JSON format only
	//offset doesnt work with author works?
	public static String getAuthorsWorks (String sKey, String sNum, String sOffSet)
	{
		if (sNum.length()>0)
			sNum="?"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sNum=sNum+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString (gsLibraryRoot+gsAuthor+sKey+gsAuthorWorks+sNum);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getRecentChanges - Get recent Changes
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//TODO: add support for
	//Parameters, type, key and author can be specified to limit the results to modifications to objects of specified type, specified key and by an author respectively. Also limit and offset can be specified to limit number of results and offset.
	//$ curl http://openlibrary.org/recentchanges.json?type=/type/page
	//$ curl http://openlibrary.org/recentchanges.json?author=/people/anand&offset=20&limit=20
	//JSON format only
	public static String getRecentChanges (String sNum, String sOffSet)
	{
		if (sNum.length()>0)
			sNum="?"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sNum=sNum+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString3 (gsLibraryRoot+gsRecentChanges+sNum);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	public static String getRecentChanges (String sNum, String sOffSet, String sType)
	{
		if (sType.length()>0)
			sType="?type=/type/"+sType;
		if (sNum.length()>0)
			sNum=sType+"?"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sNum=sNum+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString3 (gsLibraryRoot+gsRecentChanges+sNum);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	//TODO
	//Query
	//The Query API allows querying the Open Library system for matching objects.
	//$ curl 'http://openlibrary.org/query.json?type=/type/edition&authors=/authors/OL1A'
	/** 
	* getQueryEditionsAndAuthors - Get Editions and Authors
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//curl 'http://openlibrary.org/query.json?type=/type/edition&authors=/authors/OL1A'
	public static String getQueryEditionsAndAuthors (String sKey, String sProps, String sNum, String sOffSet)
	{
		if (sProps.length()>0)
			sProps="&"+sProps+"=";
		if (sNum.length()>0)
			sProps=sProps+"&"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sProps=sProps+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString3 (gsLibraryRoot+gsEditionsAuthors+sKey+sProps);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/** 
	* getQueryEditionsAndAuthors - Get Editions and Authors
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//curl 'http://openlibrary.org/query.json?type=/type/edition&works=/works/OL2040129W'
	public static String getQueryEditionsAndWorks (String sKey, String sProps, String sNum, String sOffSet)
	{
		if (sProps.length()>0)
			sProps="&"+sProps+"=";
		if (sNum.length()>0)
			sProps=sProps+"&"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sProps=sProps+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString3 (gsLibraryRoot+gsEditionsWorks+sKey+sProps);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	public static String login ()
	{

		String sToolString = "cmd /C " + gsToolsPath + "curl.exe -i -H 'Content-Type: application/json' -d '{\"username\": \"joe\", \"password\": \"secret\"}' https://openlibrary.org/account/login";// HTTP/1.1 200 OK Set-Cookie: session=\"/user/joe%2C2009-02-19T07%3A52%3A13%2C74fc6%24811f4c2e5cf52ed0ef83b680ebed861f\"; Path=/";
		runCurl(sToolString);
		return getSearchResultsString();
	}

	
	/** 
	* getEditions - Get Editions of a book
	* @param String sKey - What are we looking for
	* @return String - the results of the call
	* @author Brian Murray   	
	*/
	//JSON format only
	//question..wasn't sure if offset was supported...don't think so.
	public static String getBookEditions (String sKey, String sNum, String sOffSet)
	{
		if (sNum.length()>0)
			sNum="?"+gsSetLimit+sNum;
		if (sOffSet.length()>0)
			sNum=sNum+"&"+gsSetOffset+sOffSet;
		String sToolString = constructCurlString (gsLibraryRoot+gsWorks+sKey+gsBookEditions+sNum);
		runCurl(sToolString);
		return getSearchResultsString();
	}
	
	/**
	 * constructCurlString - build the API Curl executable string
	 * @param sLibraryURL - The server URL
	 * @author bsm
	 */
	public static String constructCurlString (String sLibraryURL)
	{
		if (sLibraryURL.contains(gsRDF))
			sLibraryURL=sLibraryURL.replace("http", "https");
		return "cmd /C " + gsToolsPath + "curl.exe " + sLibraryURL + " -k -o " + gsFlagFileName;
	}
	
	/**
	 * constructCurlString - build the API Curl executable string using accept: header
	 * @param sLibraryURL - The server URL
	 * @author bsm
	 */
	public static String constructCurlString2 (String sLibraryURL, String sFormat)
	{
		sFormat=sFormat.replace(".", "");
		sLibraryURL=sLibraryURL.replace("http", "https");
		return "cmd /C " + gsToolsPath + "curl.exe -s -H \"Accept: application/" + sFormat+ "\" " + sLibraryURL + " -k -o " + gsFlagFileName;
	}
	
	/**
	 * constructCurlString - build the API Curl executable string for query
	 * @param sLibraryURL - The server URL
	 * @author bsm
	 */
	public static String constructCurlString3 (String sLibraryURL)
	{
		if (sLibraryURL.contains(gsRDF))
			sLibraryURL=sLibraryURL.replace("http", "https");
		return "cmd /C " + gsToolsPath + "curl.exe \"" + sLibraryURL + "\" -k -o " + gsFlagFileName;
	}
	
	/**
	 * getSearchResultsString - get the query or search results from the results file
	 * @return  String - the results
	 * @author bsm
	 */
	public static String getSearchResultsString ()
	{
		return getFileContents(gsFlagFileName, "UTF8"); 
	}

	/**
	 * Get file contents from specified file by given charset.
	 * @param filename - absolute path of target file
	 * @param charset - charset such as "UTF-8"
	 * @return String - content of target file or null if there is any exception occurred
	 * author: wengm
	 */
	public static String getFileContents(String filename, String charset){
		//create reader - if charset is not specified or not supported, use default charset;
		InputStreamReader reader = null;
		if(charset==null){
			try {
				reader = new InputStreamReader(new FileInputStream(filename));
			} catch (FileNotFoundException e) {
				Log.error("Error in FileHelper#getFileContents(String filename, String charset): " + e.getMessage());
				return "";
			}
		}else{
			try {
				try {
					reader = new InputStreamReader(new FileInputStream(filename),charset);
				} catch (FileNotFoundException e) {
					Log.error("Error in FileHelper#getFileContents(String filename, String charset): " + e.getMessage());
					return "";
				}
			} catch (UnsupportedEncodingException e) {
				try {
					reader = new InputStreamReader(new FileInputStream(filename));
				} catch (FileNotFoundException ee) {
					Log.error("Error in FileHelper#getFileContents(String filename, String charset): " + ee.getMessage());
					return "";
				}
			}
		}
		
		//read content
		char[] c = new char[1024];
		int count = 0;
		StringBuffer buffer = new StringBuffer();
		try {
			while((count=reader.read(c))!=-1){
				buffer.append(c,0,count);
			}
		} catch (IOException e) {
			Log.error("Error in FileHelper#getFileContents(String filename, String charset): " + e.getMessage());
			return "";
		}
		
		//return
		return buffer.toString();
	}
	
	/** 
	* runCurl - run the curl Command String
	* @param String sToolName - the The Executable Curl string to run
	* @author Brian Murray   	
	*/
	public static void runCurl(String sToolName)
	{
		Process ftpProcess;
		int ftpReturnValue=0;
		Log.info(sToolName);
		try{
			ftpProcess = Runtime.getRuntime().exec(sToolName);
			ftpReturnValue = ftpProcess.waitFor();
			if (ftpReturnValue != 0)
				Log.info("Curl returned a runtime error: " + ftpReturnValue);
		}
		catch(Exception e){
			Log.fail(e.toString());
		}
	}
	
	

}
