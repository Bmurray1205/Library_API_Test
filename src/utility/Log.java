package utility;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Script Name   : <b>Log</b>
 * author: bmurray
 */

public class Log {

//Initialize Log4j logs

	private static Logger Log = Logger.getLogger(Log.class.getName());//
	public static int iWait=10;
	
	public static void testSetup(){
		//clean up old logs first
		String textLogName = System.getProperty("user.dir")+"\\results.txt";
		if(fileExists(textLogName))
		{
			//maybe back up old one?
			//FileHelper.copyFile(fileIn, fileOut)
			deleteFile(textLogName);
		}
		DOMConfigurator.configure(System.getProperty("user.dir")+"\\log4j.xml");
		scriptStart();
	}
	
	/**
	 * Deletes specified file or directory (if directory is specified the method 
	 * will recursively delete all files and or subdirectories within the specified parent directory. 
	 * (i.e. deleteFile("c:\\my test folder\\") will delete the directory "my test folder" and all files
	 * and subdirectories within that directory. Be extremely careful using this function<p>
	 * @param filename = path & filename of file or directory to delete
	 */    
	public static void deleteFile(String filename)//used
	{
		try
		{
			// Create a File object to represent the filename
			File f = new File(filename);
			// Make sure the file or directory exists and isn't write protected
			if (!f.exists())
			{
				return;
			}
			if (!f.canWrite())
			{
				f.setWritable(true);
			}
			// If it is a directory, recursively delete all files in the directory
			if (f.isDirectory())
			{
				String[] files = f.list();

				if (files.length > 0)
				{
					for (int x = 0; x < files.length; x++)
					{
						deleteFile(f.getAbsolutePath() + File.separator + files[x]);
					}
				}
			}

			// If we passed all the tests, then attempt to delete it
			boolean success = f.delete();
			// directory deletion always fails
			if (!success)
			{
				return;
			}
		}
		catch (IllegalArgumentException iae)
		{
			Log.error("deleting file", iae);
			return;
		}
	}
	
  public static boolean fileExists(String sFileName)//used
  {
  	File fileToCheck = new File(sFileName);
		return fileToCheck.exists();
  }
	
	
	// This is to print log for the beginning of the test script
	public static void scriptStart(){
		Log.info(" [SCRIPT START] ");
	}

	public static void startTestCase(String message){
		Log.info(" [TEST START] " + message);
	}

	//This is to print log for the ending of the test case

	public static void endTestCase(String message){
		Log.info(" [TEST COMPLETE] " + message);
	}

	// Need to create these methods, so that they can be called  

	public static void info(String message) {
		Log.info(" [INFO] " + message);
	}


	public static void pass(String message) {
		info(" PASS: " + message);
	}

	public static void fail(String message) {
		info(" FAIL: " + message);
	}

	public static void error(String message, IOException ioex) {
		Log.info(message);
	}

	public static void error(String message, IllegalArgumentException iae) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(" " + message);
	}

	public static void error(String message) {
		Log.error(" " + message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}

	/**
	 * Sleep for a specified number of seconds
	 * @param numberOfSecondsToSleep - number of seconds to sleep
	 * @author Brian Murray
	 */
	public static void sleep(double numberOfSecondsToSleep)
	{
		try {
			Thread.sleep((long) (numberOfSecondsToSleep * 1000));
		} catch (InterruptedException e) {
		}
	}
	
}