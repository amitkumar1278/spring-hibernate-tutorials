
Spring Topics:

1. Spring Repository



###########################################################################
Spring Repository:

1. Spring Boot CrudRepository

Spring Boot provides an interface called CrudRepository that contains methods for CRUD operations. 
It is defined in the package org.springframework.data.repository. It extends the Spring Data Repository interface. 
It provides generic Crud operation on a repository. If we want to use CrudRepository in an application, we have to create an interface and extend the CrudRepository.

Syntax

public interface CrudRepository<T,ID> extends Repository<T,ID>  
where,

T is the domain type that repository manages.
ID is the type of the id of the entity that repository manages.
For example:

public interface StudentRepository extends CrudRepository<Student, Integer>  
{  
}  
In the above example, we have created an interface named StudentRepository that extends CrudRepository. Where Student is the repository to manage, and Integer is the type of Id that is defined in the Student repository.


2. Spring Boot JpaRepository
JpaRepository provides JPA related methods such as flushing, persistence context, and deletes a record in a batch. 
It is defined in the package org.springframework.data.jpa.repository. JpaRepository extends both CrudRepository and PagingAndSortingRepository.

For example:

public interface BookDAO extends JpaRepository   
{  
}  

Spring Data Repository Interface:
		Repository  
			|
		CrudRepository
			|
		PagingAndSorting
			Repository
			|
		JpaRepository
		
Repository, CrudRepository and PagingAndSorting belongs to Spring Data Commons
JpaRepository belongs to Spring Data JPA
		
		
					
Why should we use these interfaces?
The interfaces allow Spring to find the repository interface and create proxy objects for that.
It provides methods that allow us to perform some common operations. We can also define custom methods as well.

CrudRepository vs. JpaRepository
1. CrudRepository does not provide any method for pagination and sorting.	
JpaRepository extends PagingAndSortingRepository. It provides all the methods for implementing the pagination.

2. It works as a marker interface.	
JpaRepository extends both CrudRepository and PagingAndSortingRepository.

3. It provides CRUD function only. For example findById(), findAll(), etc.	
It provides some extra methods along with the method of PagingAndSortingRepository and CrudRepository. For example, flush(), deleteInBatch().

4. It is used when we do not need the functions provided by JpaRepository and PagingAndSortingRepository.	
It is used when we want to implement pagination and sorting functionality in an application.



###########################################################################


