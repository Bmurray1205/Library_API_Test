package tasks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Change {
	
	//sample change
	//{"comment": "Created new account.", "kind": "new-account", "author": {"key": "/people/fortuna599"},
	//"timestamp": "2016-07-05T13:25:02.814718", "changes": [{"key": "/people/fortuna599", "revision": 1}, 
	//{"key": "/people/fortuna599/usergroup", "revision": 1}, {"key": "/people/fortuna599/permission", "revision": 1}],
	//"ip": null, "data": {}, "id": "50455394"}, 
	
	//TODO: add rest of the fields
	private static final String gsComment="comment";
	private static final String gsKind="kind";
	private static final String gsTimeStamp="timestamp";
	
	public Change(String sComment, String sKind, String sTimeStamp)
	{
		this.sComment=sComment;
		this.sKind=sKind;
		this.sTimeStamp=sTimeStamp;
	}
	
	private String sComment;
	private String sKind;
	private String sTimeStamp;

	/** 
	* setChangeData - Set the Changes Data for JSON format
	* @param String s - the JSON output
	* @return Change - the Change's data
	* @author Brian Murray   	
	*/
	//this works for 1st record only
	//TODO adapt this for array
	public static Change setChangeData(String s)
	{
		Change Change1 =  new Change("", "", "");
		try {
			JSONArray obj1 = new JSONArray(s);
			JSONObject obj = obj1.getJSONObject(0);
			Change1.sComment=obj.getString(gsComment);
			Change1.sKind=obj.getString(gsKind);
			Change1.sTimeStamp=obj.getString(gsTimeStamp);
			return Change1;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Change1;
		}
	}
	
	/** 
	* equals - compares 2 Change Objects
	* @param Bookr obj - the Change to compare to
	* @return boolean - are the objects the same
	* @author Brian Murray   	
	*/
	@Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            Change change = (Change) obj;
            if ((change.sComment).equals(this.sComment) &&
                    (change.sKind).equals(this.sKind) &&
            		(change.sTimeStamp).equals(this.sTimeStamp))
            {
                isEqual = true;
            }
        }

        return isEqual;
    }	

}
