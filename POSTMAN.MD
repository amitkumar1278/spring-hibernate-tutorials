API Calling from POSTMAN::

Open the Postman and do the following:

1. For POST Request:
	Click "+" icon for new request
	Select the POST
	Enter request URL to Invoke: like http://localhost:8085/user
	Select the Body
	Select "raw" or "form-data" and select/set the Content-Type JSON (application/json).
	Insert the data in the body. for example:
		{  
		    "id" : "106",
		    "userid": "olivia",
		    "name": "Olivia",
		    "email": "ol@ol.am",
		    "mobile": "6789012345",
		    "gender": "female"
		}   
	Click on the Send.
	
When the request is successfully executed, it shows the Status:200 OK. It means the record has been successfully inserted in the database.

2.	For GET Request:

3. 	For PUT Request:

3. 	For DELETE Request: