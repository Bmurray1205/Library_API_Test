Being new to both GIT and RDF I had some decisions to make if I was to get the automation built.  I researched RDF and  helper tools likeJ ENA, but didn't implement that code yet. 

My approach was to get some basic testing of all API calls.  I spent more time on Book and Author as representative of the others.  I took liberties with both Book and Author objects by not creating all the properties.  Again, this was a time factor.

Overall, I was having quite a bit of fun with this.  I am new to the library and books space, so it was a learning experience.  I feel like I could spend a lot more time, but I thought it prudent to wrap this up sooner than later as I do still have my own job to do.

To summarize the tests that I have created:

AuthorTest1 - performs some simple validation tests including some negative tests.  Check the Author object returned by query vs expected Author object.  I found two issues with this around negative limit testing.

BookTest1 - performs similar tests to above including some editions testing.

RecentChanges - performs basic limit and offset testing, including negative testing.  I did not get into the Parameters, type, key and author because I did not quite understand the sample query.  Given more time I am sure I would have been able to figure it out.  I also coded up Change object, and created a test to compare actual change to expected, but changes are constantly occuring.  So this needs work.  Would need set a Change object based on timestamp and then search for same record and compare the objects that way.

History -  currently have skipped this as it is very similar to recent changes

AuthorWorksAndEditions - this tests that an author's work editions should equal the total of an author's editions.  These numbers are gotten two different ways.

EditionsAndAuthorsTest -  this tests various combinations of the Query API for EditionsAndAuthors.  Tests include various limit, offset and type variations.

EditionsAndWorksTest - ditto above test


I tried Login test, but was a little unclear on testing this.  Ditto with Callback.

The project requires two jar files in the build path. They are included.  I am new to GIT, so I was a little unsure if it was OK to put these up here.  I apologize if I shouldn't have and let me know and I will remove them ASAP.

Next Steps:
1. create complete Object classes for Works, Editions, History
2. Finish Object classes for Books, Change and Authors
3. Add lots more tests

The test plan is included here.

thanks,
Brian Murray






