###########################################################################

API Details

User API

	1. 	@PostMapping("/user")
		creating post mapping that post the user detail in the database
	 	@param user
		@return ID
	
		To make API call follow below steps in POSTMAN:
			Select the POST	 ->	
			Enter request URL: http://localhost:8085/user	->
			Select the Body -> "raw" ->  set the Content-Type JSON (application/json) 	->
			Insert the data in the body:
			{  
			    "id" : "106",
			    "userid": "olivia",
			    "name": "Olivia",
			    "email": "ol@ol.am",
			    "mobile": "6789012345",
			    "gender": "female"
			}   
		  Click on the Send.
		
		When the request is successfully executed, it shows the Status:200 OK and ID:106. It means the record has been successfully inserted in the database.
		
		Similarly, we can insert the following data.
	
		{  
		    "id" : "107",
		    "userid": "jasmine",
		    "name": "Jasmine",
		    "email": "ja@ja.am",
		    "mobile": "7890123456",
		    "gender": "female"
		}  
	
		{  
		    "id" : "108",
		    "userid": "lily",
		    "name": "Lily",
		    "email": "li@li.am",
		    "mobile": "8901234567",
		    "gender": "female"
		}
		
		{  
		    "id" : "109",
		    "userid": "john",
		    "name": "John",
		    "email": "jo@jo.am",
		    "mobile": "901234567",
		    "gender": "male"
		}
	
	
	2.	@GetMapping("/users")
		creating a get mapping that retrieves all the users detail from the database
		@return List<User>
		
		To make API call follow below steps in POSTMAN:
			Select the GET	 ->	
			Enter request URL: http://localhost:8085/users	-> 
		    Click on the Send.
		When the request is successfully executed, it shows the Status:200 OK and saved data will display in Body, for example:	 
		
				[
				    {
				        "id": 101,
				        "userid": "amit",
				        "name": "Amit Kumar",
				        "email": "am@am.am",
				        "mobile": "1234567890",
				        "gender": "male"
				    },
				    {
				        "id": 102,
				        "userid": "sumit",
				        "name": "Sumit Kumar",
				        "email": "su@su.am",
				        "mobile": "2345678901",
				        "gender": "male"
				    },
				    {
				        "id": 103,
				        "userid": "ajit",
				        "name": "Ajit Kumar",
				        "email": "aj@aj.am",
				        "mobile": "3456789012",
				        "gender": "male"
				    },
				    {
				        "id": 104,
				        "userid": "vinit",
				        "name": "Vinit Kumar",
				        "email": "vi@vi.am",
				        "mobile": "4567890123",
				        "gender": "male"
				    },
				    {
				        "id": 105,
				        "userid": "ankit",
				        "name": "Ankit Kumar",
				        "email": "an@an.am",
				        "mobile": "5678901234",
				        "gender": "male"
				    },
				    {
				        "id": 106,
				        "userid": "olivia",
				        "name": "Olivia",
				        "email": "ol@ol.am",
				        "mobile": "6789012345",
				        "gender": "female"
				    }
				]
	
	
	3.	@GetMapping("/user/{userID}")
		creating a get mapping that retrieves the detail of a specific user
		@param userID
		@return User
			
		To make API call follow below steps in POSTMAN:
			Select the GET	 ->	
			Enter request URL: http://localhost:8085/user/{id} => http://localhost:8085/user/101	-> 
		    Click on the Send.
		When the request is successfully executed, it shows the Status:200 OK and saved data will display in Body, for example:	
			{
			    "id": 101,
			    "userid": "amit",
			    "name": "Amit Kumar",
			    "email": "am@am.am",
			    "mobile": "1234567890",
			    "gender": "male"
			}
			
			
	4.	@PutMapping("/user")
		creating put mapping that updates the user detail
		@param user
		@return User
			
		To make API call follow below steps in POSTMAN:
			Select the PUT	 ->	
			Enter request URL: http://localhost:8085/user -> 
			Select the Body -> "raw" ->  set the Content-Type JSON (application/json) 	->
			Insert the data in the body:
			{
			    "id": 101,
			    "userid": "amit",
			    "name": "Amit Kumar updated",
			    "email": "am@am.am",
			    "mobile": "1234567890123",
			    "gender": "male"
			}   
		  Click on the Send.
		
		When the request is successfully executed, it shows the Status:200 OK and in body same data will display which we sent.
		
	5.	@DeleteMapping("/user/{userID}")
		creating a delete mapping that deletes a specified user
		@param userID
		@return 
			
		To make API call follow below steps in POSTMAN:
			Select the GET	 ->	
			Enter request URL: http://localhost:8085/user/{id} => http://localhost:8085/user/101	-> 
		    Click on the Send.
		When the request is successfully executed, it shows the Status:200 OK, which means selected record is deleted from table.	
	
	