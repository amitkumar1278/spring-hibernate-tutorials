# Pagination with Spring Data JPA

In web Application, the response time of website is a critical factor for higher search engine ranking. Visitors are expecting pages to load quickly and only show the relevant information. 
For example, if you own an e-commerce website with tens of thousands of products, this means only displaying a small number of products at once, and not all of them.

To help you deal with such situations, Spring Data JPA provides the concepts of pagination. It makes it easy to deal with a large amount of data in the most efficient way.

In this part of application, We will demonstrate how to paginate the query results in Spring Data JPA. I will show you how pagination works with derived and custom queries as well.


# Spring Data's Pageable Interface

Just like the special Sort parameter, we used for the dynamic sorting, Spring Data JPA supports another special parameter called Pageable for paginating the query results. 
These special parameters are automatically recognized by the Spring Data infrastructure to apply pagination and sorting to database queries dynamically.
The Pageable interface contains the information about the requested page such as the size and the number of the page. It provides the following methods, among others, to add paging to statically define queries:

		public interface Pageable {
		
		    // returns the current page number (zero-based)
		    int getPageNumber();
		
		     // returns the size of the page
		    int getPageSize();
		
		     // returns the sorting parameters
		    Sort getSort();
		
		   // ... other methods
		}
		
Whenever you want to apply pagination to query results, all you need to do is just add Pageable to the query method definition as a parameter and set the return by Page<T>:

		Page<Person> findByLastName(String lastName, Pageable pageable);
		
While calling the above method, you need to create an object of Pageable and pass it to the invoked repository method. 
The simplest way to create an instance of Pageable is to use the PageRequest class which provides the implementation of Pageable methods:

		Pageable pageable = PageRequest.of(0, 10);
		
This will create a page request for the first page (page index is zero-based) with 10 as the size of the page to be returned.

You can even apply dynamic sorting by using the Pageable instance as shown below:

		// pageable instance with dynamic sorting
		Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending());
		
		// mutlple sort parameters
		Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending()
		        .and(Sort.by("lastName").ascending()));
        


# Spring Data's Slice and Page        
        
The example, we have defined above, returns a Page object. A Page contains information about the total number of elements and pages available in the database. It is because the Spring Data JPA triggers a count query to calculate the number of elements.

Depending on the database you are using in your application, it might become expensive as the number of items increased. To avoid this costly count query, you should instead return a Slice. Unlike a Page, a Slice only knows about whether the next slice is available or not. This information is sufficient to walk through a larger result set.

Both Slice and Page are part of Spring Data JPA, where Page is just a sub-interface of Slice with a couple of additional methods. You should use Slice if you don't need the total number of items and pages. A good example of such a scenario is when you only need Next Page and Previous Page buttons. Here is an example query that returns a Slice:
        
      Slice<Person> findByAgeGreaterThan(int age, Pageable pageable);
      
      
# Paginating Query Results with Pageable

Now we know what's the Pageable interface and what are the differences between a Page and a Slice, let us create database queries that accept a Pageable object as a parameter to paginate their results.

The PagingAndSortingRepository interface, we are extending above, provides us the findAll(Pageable pageable) method to paginate all Person entities available in the database. All you need to do is just create an instance of Pageable and pass it to this method to get a Page.

However, if you want to get a Slice or a List, you need to define your own queries in the repository interface.


## Derived Queries

Applying pagination to derived queries is a straightforward task. You just need to pass the Pageable interface as a parameter to any derived query and set the desired return type as shown below:

		Page<Person> findByFirstName(String firstName, Pageable pageable);
		
		Slice<Person> findByAgeBetween(int start, int end, Pageable pageable);

 
## Custom Queries with @Query Annotation



## Named Queries




## Using the Pageable Interface

The following examples show how to create an instance of Pageable and then call different query methods that paginate their results:

		// get all persons by last name
		Pageable pageable = PageRequest.of(0, 3);
		Page<Person> personPage = personRepository.findByLastName("Doe", pageable);
		
		// get all persons sorted by their age in the descending order
		Pageable pageable2 = PageRequest.of(0, 5, Sort.by("age").descending());
		Page<Person> personPage2 = personRepository.findAll(pageable2);

If you want to skip the pagination for query methods that require an instance of Pageable, just use the Pageable.unpaged() method:

		Slice<Person> personSlice = personRepository.findByAgeBetween(20, 60, Pageable.unpaged());





        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
