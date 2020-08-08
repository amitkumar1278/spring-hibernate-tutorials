		
###########################################################################
Application Details

Currently Application is only connecting with H2 database	




###########################################################################
STEP TO EXECUTE Application AND OPEN h2 CONSOLE IN BRPWSER:

Step 1: Run the *Application.java file. We can see in the console that our application is successfully running on port given in application.properties.

Step 2: Open the browser and invoke the URL http://localhost:<port>/h2-console/. 

		It shows the Driver Class, JDBC URL that we have configured in the "application.properties" file. 
		the default User Name sa, for given user name and password refer "application.properties".
		
Step 3: We can also test the connection by clicking on the Test Connection button. If the connection is successful, it shows a message Test Successful.

Step 4: Click on the Connect button. It shows the structure of the table as per define in entity pojo.

Step 5: Execute select query using table name, data will appear as per "*.sql" file.
		
	