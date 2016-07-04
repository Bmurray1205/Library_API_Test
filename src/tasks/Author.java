package tasks;

import org.json.JSONException;
import org.json.JSONObject;

import utility.Log;

public class Author {

	//TODO: add rest of the fields
	private static final String gsName="name";
	private static final String gsPersonalName="personal_name";
	private static final String gsDeath="death_date";
	private static final String gsBirth="birth_date";
	private static final String gsKey="key";
	
	public Author(String sName, String sPersonalName, String sDeathDate, String sBirthDate)
	{
		this.sName=sName;
		this.sPersonalName=sPersonalName;
		this.sDeathDate=sDeathDate;
		this.sBirthDate=sBirthDate;
	}
	
	public String sName;
	public String sPersonalName;
	public String sDeathDate;
	public String sBirthDate;
	
	public static void getAuthorData(String s)
	{
		try {
			JSONObject obj = new JSONObject(s);
			Log.info("name:" + obj.getString(gsName));
			Log.info("personal name: " + obj.getString(gsPersonalName));
			Log.info("death date: " + obj.getString(gsDeath));
			Log.info("key: " + obj.getString(gsKey));
			Log.info("birth date: " + obj.getString(gsBirth));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	* setAuthorData - Set the Author Data for JSON format
	* @param String s - the JSON output
	* @return Author - the Author's data
	* @author Brian Murray   	
	*/
	public static Author setAuthorData(String s)
	{
		Author Author =  new Author("", "", "", "");
		try {
			JSONObject obj = new JSONObject(s);
			Author.sName=obj.getString(gsName);
			Author.sPersonalName=obj.getString(gsPersonalName);
			Author.sDeathDate=obj.getString(gsDeath);
			Author.sBirthDate=obj.getString(gsBirth);
			return Author;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Author;
		}
	}	
	
	/** 
	* equals - compares 2 Author Objects
	* @param Author obj - the author to compare to
	* @return boolean - are the objects the same
	* @author Brian Murray   	
	*/
	@Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            Author author = (Author) obj;
            if ((author.sName).equals(this.sName) &&
                    (author.sPersonalName).equals(this.sPersonalName) &&
            		(author.sDeathDate).equals(this.sDeathDate) &&
            		(author.sBirthDate).equals(this.sBirthDate))
            {
                isEqual = true;
            }
        }

        return isEqual;
    }
}
