# Spring Data JPA Auditing using Spring Boot and H2

### Auditing

Auditing helps us in tracking and logging the persistence layer changes made by the user in an application. 
By using auditing, we can easily determine who created or updated the entity record or when it happened. 
Basically, we keep track of every action that changes the state of the entity like insert, update, and delete operations.

Spring Data JPA provides excellent support to transparently keep track of who created or changed an entity and the point in time this happened.

To enable the auditing feature in Spring Boot, we can make use of Spring Data JPA's **@CreateDate, @CreatedBy, @LastModifiedDate, and @LastModifiedBy** annotations. 
You can add these annotations either directly to your entity classes or by extending an abstract class that defines annotated audit fields.


### Auditable.java

Since we need auditing feature for most of the entities, in this article, we will create a generic Auditable abstract class with audit fields:
*createdBy, createdDate, lastModifiedBy, and lastModifiedDate*. 

**This generic class acts as a base class with all the common auditing fields for the child entities.** 
Any entity can later extend this abstract class to enable the auditing functionality.

To let the Spring Boot knows about these audit fields, you have to annotate the fields with:
*@CreatedBy, and @LastModifiedBy* to track the user who created or updated the entity 
as well as *@CreatedDate and @LastModifiedDate* to log the time when these changes were made.

the Auditable class is also annotated with *@MappedSuperclass* and *@EntityListeners* annotations.
The **@MappedSuperclass** annotation indicates that the Auditable class is only a superclass and is not a JPA entity.
The **@EntityListeners** annotation is used to configure a JPA entity listener AuditingEntityListener to capture auditing information on persisting and updating entities. 
This entity listener class contains callback methods (annotated with @PrePersist and @PreUpdate) to persist and update audit fields when there is any create or update activity on the entity.

Now any entity that extends the Auditable abstract class will benefit from the JPA auditing feature. 
Spring Data JPA will automatically manage CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate columns when the entity is persisted or updated.


### Auditing Author with AuditorAware

how to tell the auditing infrastructure about the author who made these changes? 
It somehow needs to know this information since we have defined the @CreatedBy and @LastModifiedBy annotations in our Auditable abstract class.
To tell the auditing infrastructure about the current logged in user, we have to provide the implementation of AuditorAware and override its getCurrentAuditor method, 
as shown in **"EntityAuditorAware.java"**.


### Enable JPA Auditing

Finally, we need to enable the JPA auditing feature by specifying **@EnableJpaAuditing** on one of our configuration classes. 
We also need to define a bean of type AuditorAware and return an instance of the **EntityAuditorAware** class.

You can either create a separate configuration class or use the main application class to define these configurations. 
We created **AuditConfiguration** class to let the Spring Data JPA knows we want to enable auditing.


That's all you need to do to enable Spring Data JPA auditing functionality in your Spring Boot application. 


### Testing the Application

Main application class of our Spring Boot project also exposes a bean of type **CommandLineRunner**
 that defines a **run()** method which is invoked by Spring Boot after the application context has been loaded.
we have first saved several todos and then retrieved them by using the findAll() method. The last statement prints all todos on the console.


	
	
	
