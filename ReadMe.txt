Notes on testing:


1. Book codes seem to come in 3 different flavors.  I know the returned data is different, but I am not sure why.

	the standard from the example like this: OL1M.  That is what I coded on.

	the other ones are returned when you get the Author's works and are like this: OL14930758W

	or like this: OL4731M

2. I didn't code examples using RDF format as I was time limited and was unfamiliar with RDF format.  I did begin the research on JENA and read about it, but decided it was best to focus on more coverage than including this.    

3. I couldn't see to get this format to work even when used exactly like this:
	curl -s -H 'Accept: application/json' https://openlibrary.org/books/OL1M

4. I also can't get the query API to work:

	example query from web site->
curl 'http://openlibrary.org/query.json?type=/type/edition&authors=/authors/OL1A'

	my query->
curl.exe 'http://openlibrary.org/query.json?type=/type/edition&authors=/authors/OL1A'

mine returns Curl returned a runtime error: 1

which is CURLE_UNSUPPORTED_PROTOCOL (1)

The URL you passed to libcurl used a protocol that this libcurl does not support. The support might be a compile-time option that you didn't use, it can be a misspelled protocol string or just a protocol libcurl has no code for. 

5. I couldnt get offsets to work.  Index works.

6. results to modifications to objects of specified type, specified key and by an author respectively did not seem to work either

7. Status codes didnt seem to work for me.  Supposed to get get "Status code 200 OK is returned when the request is handled successfully." I never saw this.

8. negative numbers when used for limit didnt return an error in author query.  Same with using a string.  i9t just returned the default limit of 20.  In other queries negative and string returned error messages

9. I played with Login a little, but coulnt get it to work.  I did get a couple of different error codes depending on what i tried.  I suspect this was my error.