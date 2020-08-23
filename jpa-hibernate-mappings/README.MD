

# Spring Data JPA Relationship Mapping Example

Spring Data JPA provides excellent support to create Spring-powered applications that communicate with different relational databases to implement JPA based repositories.

**Java Persistence API (JPA)** is just a specification that defines an **object-relational mapping (ORM)** standard for *storing, accessing, and managing Java objects* in a relational database. 

**Hibernate is the most popular** and widely used implementation of JPA specifications. By default, *Spring Data JPA uses Hibernate as a JPA provider*. Hibernate provides a framework for mapping relational database tables to entity classes in your application. 
You can describe the relationships between these entities in a similar way as you define relationships among the tables in your relational database.

In this application, we implemented **one-to-one, one-to-many, many-to-one, many-to-many and composite key mapping** between two entities using Spring Data JPA in a Spring Boot and H2 application.

For **MySQL**, we need to manually define the database connection details in a properties file but by default, Spring Boot automatically configures the DataSource bean for in-memory databases like **H2 database**,  **HSQLDB**, and **Apache Derby**.

we have define some important property in **application.properties** file. Spring Boot will read these properties and will **auto-configure a DataSource bean** for you. 

The Hibernate property **spring.jpa.hibernate.ddl-auto = create** will automatically create database tables based on the entity classes in your application on startup.

We have also included the **createDatabaseIfNotExist=true** configuration property in spring.datasource.url to automatically create the database if it doesn't already exist.


### One-To-One Relationship

A one-to-one relationship refers to the relationship between two entities/database tables A and B in which only one element/row of A may only be linked to one element/row of B, and vice versa.

We can map the above requirement as a one-to-one relationship between the user and the address entities, The one-to-one relationship is defined by using a foreign key called user_id in the addresses table.


### One-To-Many Relationship

A one-to-many relationship refers to the relationship between two entities/tables A and B in which one element/row of A may only be linked to many elements/rows of B, but a member of B is linked to only one element/row of A.
For instance, think of A as a book, and B as pages. A book can have many pages but a page can only exist in one book, forming a one-to-many relationship. The opposite of one-to-many is many-to-one relationship.
We model the above relationship in the database by creating two tables, one for the books and another for the pages.



### Annotation & it's properties

**@Table**
	
	The @Table annotation is used to specify the name of the database table that should be mapped to this entity.

**@Id** and **@GeneratedVAlue**
	
	The id attributes are annotated with both @Id and @GeneratedValue annotations. 
	The former annotation indicates that they are the primary keys of the entities. 
	The latter annotation defines the primary key generation strategy. In most of the case, we use to declared that the primary key should be an AUTO INCREMENT field.


**@OneToOne**
	
	In Spring Data JPA, a one-to-one relationship between two entities is declared by using the @OneToOne annotation.
	In a bidirectional relationship, we have to specify the @OneToOne annotation in both entities. 
	But only one entity is the owner of the association. Usually, the child entity is one that owns the relationship and the parent entity is the inverse side of the relationship.
	refer **Relationship mapping parameter** to see list of accepted parameters.

**@OneToMany** Annotation
	
	A one-to-many relationship between two entities is defined by using the @OneToMany annotation in Spring Data JPA. 
	It declares the mappedBy element to indicate the entity that owns the bidirectional relationship. 
	Usually, the child entity is one that owns the relationship and the parent entity contains the @OneToMany annotation.
	refer **Relationship mapping parameter** to see list of accepted parameters.

**@ManyToOne** Annotation
	
	The @ManyToOne annotation is used to define a many-to-one relationship between two entities in Spring Data JPA. 
	The child entity, that has the join column, is called the owner of the relationship defined using the @ManyToOne annotation.
	refer **Relationship mapping parameter** to see list of accepted parameters.



**Relationship mapping parameter** 

**fetch**
	
	Defines a strategy for fetching data from the database. 
	By default, it is EAGER which means that the data must be eagerly fetched. We have set it to LAZY to fetch the entities lazily from the database.

**cascade** 
	
	Defines a set of cascadable operations that are applied to the associated entity. 
	CascadeType.ALL means to apply all cascading operations to the related entity. Cascading operations are applied when you delete or update the parent entity.

**mappedBy** 
	
	Defines the entity that owns the relationship which is the Address entity in our case.
	optional — Defines whether the relationship is optional. If set to false then a non-null relationship must always exist.



**@JoinColumn**
	
	The @JoinColumn annotation is used to specify the foreign key column in the owner of the relationship. 
	The inverse-side of the relationship sets the @OneToOne's mappedBy parameter to indicate that the relationship is mapped by the other entity.
	The @JoinColumn accepts the following two important parameters, among others:

	name — Defines the name of the foreign key column.
	nullable — Defines whether the foreign key column is nullable. By default, it is true.
	
	
	
### Create Repositories

Finally, let us create our repository interfaces to store and access the data from the database. 
We'll extend our repositories from Spring Data JPA's CrudRepository interface which provides generic CRUD methods out-of-the-box.
By doing this You're now ready to perform the CRUD operations on User and Address entities without implementing the above interfaces. This is what makes Spring Data JPA a very powerful tool
we can also defined some derived query methods like **findBy<property name>(Property prop)** to fetch a record by passed property types value.

