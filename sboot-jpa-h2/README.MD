		
###########################################################################
Application Details::

Currently Application is only connecting with H2 database.	
Implemented Spring Spring Data Commons repository -: our Repository are extending CrudRepository
Exposing different API related to User for CRUD operation using Spring JPA.




###########################################################################
STEP TO EXECUTE Application AND OPEN h2 CONSOLE IN BRPWSER:

Step 1: Run the *Application.java file. We can see in the console that our application is successfully running on port given in application.properties.

Step 2: Open the browser and invoke the URL http://localhost:<port>/h2-console/. 

		It shows the Driver Class, JDBC URL that we have configured in the "application.properties" file. 
		the default User Name sa, for given user name and password refer "application.properties".
		
Step 3: We can also test the connection by clicking on the Test Connection button. If the connection is successful, it shows a message Test Successful.

Step 4: Click on the Connect button. It shows the structure of the table as per define in entity pojo.

Step 5: Execute select query using table name, data will appear as per "*.sql" file.
		
###########################################################################
STEP TO EXECUTE Application AND OPEN JSP home page in BRPWSER:

Open the browser and invoke the URL http://localhost:<port>/home/
	

###########################################################################

SERVCIES:

different API for following services we have been created:	

1. USER
	private int saveUser(@RequestBody User user) 
	private List<User> getAllUsers() 
	private User getUser(@PathVariable("userID") int userID) 
	private User update(@RequestBody User user) 
	private void deleteUser(@PathVariable("userID") int userID) 
	

for API call details follow "API-CALLS.MD".	
	
	

