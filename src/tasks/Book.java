package tasks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {
	
	
	//{"pagination": "8, 304 p.", "identifiers": {}, "classifications": {}, "key": "/books/OL1M", "title": "Kabit\u0101.",
	//"lccn": ["sa 64009056"], "price": "$4.50", "number_of_pages": 304, "created": {"type": "/type/datetime",
	//"value": "2008-04-01T03:28:50.625462"}, "lc_classifications": ["PK2579.R255 K3"], "publish_date": "1962", 
	//"last_modified": {"type": "/type/datetime", "value": "2010-07-27T14:44:34.015872"}, 
	//"authors": [{"key": "/authors/OL1A"}], "latest_revision": 51, "oclc_numbers": ["31249133"], 
	//"works": [{"key": "/works/OL14930766W"}], "type": {"key": "/type/edition"}, 
	//"notes": "Bibliographical footnotes.\r\nIn Oriya.", "revision": 51}
	
	//TODO: add rest of the fields
	private static final String gsTitle="title";
	private static final String gsPrice="price";
	private static final String gsNumPages="number_of_pages";
	private static final String gsPublishDate="publish_date";
	
	public Book(String sTitle, String sPrice, int iNumPages, String sPublishDate, String sAuthor)
	{
		this.sTitle=sTitle;
		this.sPrice=sPrice;
		this.iNumPages=iNumPages;
		this.sPublishDate=sPublishDate;
		this.sAuthor=sAuthor;
	}
	
	private String sTitle;
	private String sPrice;
	private int iNumPages;
	private String sPublishDate;
	private String sAuthor;

	/** 
	* setBookData - Set the Book Data for JSON format
	* @param String s - the JSON output
	* @return Book - the Book's data
	* @author Brian Murray   	
	*/
	public static Book setBookData(String s)
	{
		Book Book =  new Book("", "", 0,  "", "");
		try {
			JSONObject obj = new JSONObject(s);
			Book.sTitle=obj.getString(gsTitle);
			Book.sPrice=obj.getString(gsPrice);
			Book.iNumPages=obj.getInt(gsNumPages);
			Book.sPublishDate=obj.getString(gsPublishDate);
			return Book;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Book;
		}
	}
	
	/** 
	* setBookData - Set the Book Data for JSON format
	* @param String s - the JSON output
	* @return Book - the Book's data
	* @author Brian Murray   	
	*/
	public static Book setBookData2(String s)
	{
		Book Book =  new Book("", "", 0,  "", "");
		try {
			JSONObject obj = new JSONObject(s);
			Book.sTitle=obj.getString(gsTitle);
			Book.sPrice=obj.getString(gsPrice);
			Book.iNumPages=obj.getInt(gsNumPages);
			Book.sPublishDate=obj.getString(gsPublishDate);
			JSONArray getArray = obj.getJSONArray("authors");
			//just get 1st author
			JSONObject objects = getArray.getJSONObject(0);
			String sAuthor = objects.getString("key");
			//fix author
			sAuthor=sAuthor.replace("/authors/", "");
			String sResults=library.getAuthor(sAuthor, library.gsJSON, false);
			//Log.info(sResults);
			Author AuthorAct=Author.setAuthorData(sResults);
			Book.sAuthor=AuthorAct.sName;
			return Book;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Book;
		}
	}
	
	/** 
	* equals - compares 2 Book Objects
	* @param Book obj - the Book to compare to
	* @return boolean - are the objects the same
	* @author Brian Murray   	
	*/
	@Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if (this.getClass() == obj.getClass())
        {
            Book book = (Book) obj;
            if ((book.sTitle).equals(this.sTitle) &&
                    (book.sPrice).equals(this.sPrice) &&
            		(book.iNumPages==this.iNumPages) &&
            		(book.sPublishDate).equals(this.sPublishDate)  &&
            		(book.sAuthor).equals(this.sAuthor))
            {
                isEqual = true;
            }
        }

        return isEqual;
    }	

}
