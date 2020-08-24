

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


### Many-To-Many Relationship

A many-to-many relationship refers to the relationship between two entities/tables A and B in which one element/row of A may only be associated with many elements/rows of B and vice versa.

A typical example of such a many-to-many relationship is the relationship between students and courses. A student can enroll in multiple courses and a course can also have multiple students, thus forming a many-to-many relationship.

We model the above relationship in the database by creating three tables, one each for both students and courses, and another one for holding relationship keys.


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


**@ManyToMany** Annotation

	A many-to-many relationship between two entities is defined by using the @ManyToMany annotation in Spring Data JPA. 
	It uses the mappedBy attribute to indicate the entity that owns the bidirectional relationship. 
	In a bidirectional relationship, the @ManyToMany annotation is defined in both entities but only one entity can own the relationship. 
	We've picked the Student class as an owner of the relationship in the above example.
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
	
	
**@JoinTable Annotation**

	The @JoinTable annotation defines the join table between two entities on the owner's side of the relationship. 
	We have used this annotation to define the students_courses table. 
	If the @JoinTable annotation is left out, the default values of the annotation elements apply. T
	he name of the join table is supposed to be the table names of the associated primary tables concatenated together (owning side first) using an underscore.

	
### Create Repositories

Finally, let us create our repository interfaces to store and access the data from the database. 
We'll extend our repositories from Spring Data JPA's CrudRepository interface which provides generic CRUD methods out-of-the-box.
By doing this You're now ready to perform the CRUD operations on User and Address entities without implementing the above interfaces. This is what makes Spring Data JPA a very powerful tool
we can also defined some derived query methods like **findBy<property name>(Property prop)** to fetch a record by passed property types value.


=============================================================================================================

# Spring Data JPA Composite Primary Key Mapping

Above we explained different mappings having primary key was an single attribute for example "id". 
We created a join table to hold the primary keys of both relationship tables. These keys act as a composite primary for the join database table.
Simply put, a composite primary key — also known as a composite key — is a *key that contains two or more columns to form a primary key for the table*.

We will explain how to map a composite primary key in a Spring Boot project using Spring Data JPA's both @IdClass and @EmbeddedId annotations.



# Mapping Composite Key using @IdClass Annotation

Let us consider an application that manages different types of bank accounts. Each bank account has an account number and type (i.e. checking or saving), among other information. Now we want to create a compound key using this information to uniquely identify each account in the database.

in example application, the accounts table has a composite primary key, which consists of two columns:

	account_number
	account_type
	
To map this database relationship using Spring Data JPA, we need to create a separate composite primary key class with both these primary key columns:


	public class AccountId implements Serializable {
	
	    private String accountNumber;
	    private String accountType;
	
	   /**   setter and getter	*/   
	}
	
The composite primary key class must be public, contains a no-argument constructor, defines both equals() and hashCode() methods, and implements the Serializable interface.	

The next step is to create an Account entity class that declares all attributes of AccountId and annotate them with the @Id annotation:

	@Entity
	@Table(name = "accounts")
	@IdClass(AccountId.class)
	public class Account implements Serializable {
	
	    @Id
	    private String accountNumber;
	    @Id
	    private String accountType;
	
	    private double balance;
		
	    // default constructor, parameterized constructor,  getters and setters, equals(), 
	    // hashcode(), toString() .... (omitted)
	}


As you can see above, we have annotated the Account class with @IdClass to specify a composite primary key class that is mapped to multiple fields of the entity. The @Id annotation is then used to indicate all properties which are a part of the compound key.


With the @IdClass annotation, you can easily query data without using the name of the composite key class. Let us first create a repository interface for this purpose:

	public interface AccountRepository extends CrudRepository<Account, AccountId> {
    		// TODO: add queries
	}


# Mapping Composite Key using @EmbeddedId Annotation


In additional to @IdClass, Spring Data JPA provides another annotation — @EmbeddedId — to define a composite primary key.

Let us consider another example application that manages employees of a company with multiple departments. Each employee has a unique ID within his own department. But the same ID can be assigned to a different employee in another department. So we cannot uniquely identify an employee just by his employee ID.
To uniquely identify an employee, we need to know his employee ID as well as his department ID. As you can see in the below Entity-Relationship (ER) diagram, the employees table contains a composite primary key that includes both employee_id and department_id columns.

		employee
		============
		department_id
		employment_id
		name
		email
		phone_number
		
To map the above relationship using Spring Data JPA, you need to create a separate composite primary key class annotated with @Embeddable.

	@Embeddable
	public class EmployeeId implements Serializable {
	
	    @Column(name = "employee_id")
	    private Long employeeId;
	
	    @Column(name = "department_id")
	    private Long departmentId;
	
	    // default constructor, parameterized constructor,  getters and setters, equals(), hashcode() .... (omitted)
	}
		

The next step is to create the Employee class and embed the above composite primary class into it by using the @EmbeddedId annotation:

	@Entity
	@Table(name = "employees")
	public class Employee implements Serializable {
	
	    @EmbeddedId
	    private EmployeeId employeeId;
	
	    private String name;
	    @Column(unique = true)
	    private String email;
	    private String phoneNumber;
	
	
	     // default constructor, parameterized constructor,  getters and setters, equals(), 
	     // hashcode(), toString() .... (omitted for brevity)
	}	
	
In the above Employee class, we have specified the composite key class using the @EmbeddedId annotation and mark it as a primary key of the entity.

Next, We can create a repository interface for retrieving Employee entities from the database.



**@IdClass vs @EmbeddedId**

The main difference between @IdClass and @EmbeddedId annotations is that with @IdClass, you need to specify the primary key columns twice — once in the composite primary key class and then again in the entity class with the @Id annotation.

The @EmbeddedId annotation is more verbose than @IdClass as you can access the entire primary key object using the field access method. This also gives a clear notion of the fields that are part of the composite key because they are all aggregated in a class that is only accessible through a field access method.

Another difference between @IdClass and @EmbeddedId is when it comes to creating custom JPQL queries.
	For example, with @IdClass, the query is a little simpler:
	
	SELECT a.accountType FROM Account a
	With @EmbeddedId, you have to write more text for a similar query:

	SELECT e.employeeId.departmentId FROM Employee e
	The @IdClass annotation can be a preferred choice over @EmbeddedId in situations where the composite primary key class is not accessible or comes in from another module or legacy code. 
	For such scenarios, where you cannot modify the composite key class, the @IdClass annotation is the only way-out.

		
