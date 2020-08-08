====================================================================================================================
Application details:

==>>	What are Profiles?
	Every enterprise application has many environments, like:

	Dev | Test | Stage | Prod | UAT / Pre-Prod

	Each environment requires a setting that is specific to them. 
	For example, 
		in DEV, we do not need to constantly check database consistency. 
		Whereas in TEST and STAGE, we need to. These environments host specific configurations called Profiles.
		
	
Step 1:
==>>	How do we Maintain Profiles?
	In spring boot application, we need different configurations for different environments. 
	We have application.properties file to maintain application specific properties. 
	
	In order to create profiles for different environments , we need to create various properties files as below –

	DEV – application-dev.properties
	TEST– application-test.properties
	PROD – application-prod.properties
	
	application.properties will remain as a master properties file, but if we override any key in the profile-specific file, the latter will gain precedence.
	
	
	
Step 2:		Specify in application.properties file which environment property needs to be loaded.
	ex:
		spring.profiles.active=prod
		spring.application.name=springbootprofiles
		
	Here we are loading PROD properties when application gets booted.
	
	
	
Step 3:		Define DB configuration properties for in respective properties file and add code in "DBConfiguration.class" to pick the appropriate settings.
	This will load the properties file as per the "spring.profiles.active" property we have mentioned in application.properties file.
	
	
Step 4: 	You can connect to database as shown below –	
	After executing in browser open:

	##	URL to access h2 db console:
	http://localhost:<server.port from "application-<profile>.properties" >/<url mapping for h2 define in BeanConfig.java>/

	example:	http://localhost:8081/h2-console/

	## login credentials for h2 environment:
	select "application-<profile>.properties" file for "jdbc url", "username" and password.
	
	in logs, you can see which profile database is loaded during application boot up.
	
	
==============================================================================================================
like db details you can add any application related properties for selected environement.



	
	
	
	