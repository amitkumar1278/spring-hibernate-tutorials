
# Dynamic Queries with Spring Data JPA Specifications

Spring Data JPA allows us to connect with different relational databases through a common interface to create JPA (Java Persistence API) based repositories. We can easily create database queries by using method names, the @Query annotation, and named queries to retrieve data from the database.

The Spring Data repository abstraction is a powerful feature that hides the store-specific implementation details and allows us to write business logic code at a higher abstraction level. All we need to do is only extend any of the Spring Data repositories to take the full advantage of this feature. A typical repository interface looks something like below:

		public interface StudentRepository extends JpaRepository<Student, Long> {
		
		    Student findByName(String name);
		    
		    List<Student> findByColor(String color, Sort sort);
		
		    Page<Student> findByAgeGreaterThan(int age, Pageable pageable)
		}
		
The first method simply finds and returns a single student with a given name. 
The second method returns a list of students with a given color and applies the dynamic sorting to the results through the given Sort object. 
Finally, the last method returns a Page of students that are older than the given age.


Although the **above approach looks really convenient** (you don't need to write a single line of implementation code to execute queries), it has at least **two drawbacks**:

1. **The number of query methods are fixed** as we cannot dynamically define a new method on runtime. As the application grows, these query methods increase quickly making it difficult to maintain the persistence layer.
2. **Each query method defines a fixed set of criteria.** We have predefined the number and type of parameters that cannot be changed dynamically on runtime.

For larger and complex applications, we need a robust and flexible query generation strategy to handle different types of use cases. That's where the Spring Data JPA specifications come in.



# Spring Data JPA Specifications

Spring Data JPA **Specifications allow us to create dynamic database queries** by using the JPA Criteria API. It defines a specification as a predicate over an entity. Here is how the Specification interface looks like:

		public interface Specification<T> extends Serializable {
	
		    @Nullable
		    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
		    // other methods
		}


As you can see above, there is only one abstract method toPredicate() which returns an instance of Predicate. In the following sections, you'll learn how to use this interface to write dynamic queries for different use cases.


# Example Application:


## Create an Application

Let us start with creating a simple Spring Boot application with the Spring Data JPA and H2 in-memory database. Our application has just one Movie entity class, as shown below:

	Movie.java
		
	@Entity
	public class Movie implements Serializable {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	
	    private String title;
	    private String genre;
	    private double rating;
	    private double watchTime;
	    private int releaseYear;
	
	    // default constructor, parameterize constructor, getters and setters, equals(), toString(), ... (omitted )
	}


The next step is to create a repository interface called MovieRepository to retrieve data from the database. To use Specifications, we also need to extend our repository interface from the JpaSpecificationExecutor interface. This interface provides methods to execute Specifications.

Here is how our repository interface looks like:

		MovieRepository.java
					
		public interface MovieRepository extends CrudRepository<Movie, Long>,
		        JpaSpecificationExecutor<Movie> {
		
		    // TODO: add queries
		}


## Create Specifications

Let us now start with the most interesting part of this application — **creating specifications to execute dynamic queries for searching movies in the database**.

The **first step** is to create a simple **enum class* that defines different **search operations**:

		SearchOperation.java
				
		public enum SearchOperation {
		    GREATER_THAN,
		    LESS_THAN,
		    GREATER_THAN_EQUAL,
		    LESS_THAN_EQUAL,
		    NOT_EQUAL,
		    EQUAL,
		    MATCH,
		    MATCH_START,
		    MATCH_END,
		    IN,
		    NOT_IN
		}

**Next**, create the **SearchCriteria** class that represents a single search criteria:

		SearchCriteria.java
	
		public class SearchCriteria {
		    private String key;
		    private Object value;
		    private SearchOperation operation;
		
		    // default constructor, parameterize constructor, getters and setters, toString(), ... (omitted for brevity)
		}


As you can see above, the **SearchCriteria** class holds a basic representation of a single constraint:

**key** — Represents the entity field name, i.e. **title, genre, rating**, etc.
**value** — Represents the parameter value, i.e. **Deadpool, Action**, etc.
**operation** — Indicates the search operation, i.e. equality, match, comparison, etc.

Finally, let us create a MovieSpecification class that extends the Specification interface and provides an implementation for the toPredicate() method:

		MovieSpecification.java
				
		public class MovieSpecification implements Specification<Movie> {
		
		    private List<SearchCriteria> list;
		
		    public MovieSpecification() {
		        this.list = new ArrayList<>();
		    }
		
		    public void add(SearchCriteria criteria) {
		        list.add(criteria);
		    }
		
		    @Override
		    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		        //create a new predicate list
		        List<Predicate> predicates = new ArrayList<>();
		
		        //add add criteria to predicates
		        for (SearchCriteria criteria : list) {
		           ....
		        }
		
		        return builder.and(predicates.toArray(new Predicate[0]));
		    }
		}
		
		
The **MovieSpecification** class allows you to combine multiple specifications to filter the movies using multiple constraints. Using this class, you can easily generate different kinds of database queries dynamically.



## Test the Application

Now is the time to create the main application class to test Specifications by writing dynamic search queries:

		SpecificationStartupRunner.java 
		
		
		@org.springframework.core.annotation.Order(value = 4)
		@Component
		public class SpecificationStartupRunner implements CommandLineRunner {
		
			@Autowired
			MovieRepository movieRepository;
		
			@Override
			public void run(String... args) throws Exception {
		
				System.out.println("\n\n=============================================================================================");
				System.out.println("######################    JPA Specification Mapping demonstration Started    ##########################\n\n");
		
				/**
				 * Create new Movies
				 */
				movieRepository.saveAll(Arrays.asList(
						new Movie("Troy", "Drama", 7.2, 196, 2004),
						new Movie("The Godfather", "Crime", 9.2, 178, 1972),
						new Movie("Invictus", "Sport", 7.3, 135, 2009),
						new Movie("Black Panther", "Action", 7.3, 135, 2018),
						new Movie("Joker", "Drama", 8.9, 122, 2018),
						new Movie("Iron Man", "Action", 8.9, 126, 2008),
						new Movie("Bad Boys for Life", "Comedy", 7.1, 96, 2020),
						new Movie("Birds of Prey", "Crime", 9.1, 111, 2020),
						new Movie("Extraction", "Action", 9.3, 136, 2020),
						new Movie("Deadpool 2", "Comedy", 9.3, 105, 2018),
						new Movie("Rocky", "Sports", 9.5, 119, 1976),
						new Movie("The Shawshank Redemption", "Drama", 8.9, 144, 1994),
						new Movie("The Ring", "Supernatural", 7.1, 145, 2002)
						));
		
				/**
				 * retrieve all movies into list 
				 */
				List<Movie> lstMovies = (List<Movie>) movieRepository.findAll();
				System.out.println("\n######################    All movies are below  -> List user  ##########################");
				lstMovies.forEach(movie -> {
					System.out.println("\nname: "+movie.getTitle()+", release year: "+movie.getReleaseYear());
					System.out.println(movie.toString());
				});
				System.out.println("---------------------------------------------\n");
		
		
				/**
				 * Search Movie by genre
				 */
				MovieSpecification mvGenre = new MovieSpecification();
				mvGenre.add(new SearchCriteria("genre", "Comedy", SearchOperation.EQUAL));
				List<Movie> mvGenreList = movieRepository.findAll(mvGenre);
				System.out.println("\n\n######################   movies by genre    ##########################");
				mvGenreList.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
		
				/**
				 * Search movie by title and rating
				 */
				MovieSpecification mvTitleRating = new MovieSpecification();
				mvTitleRating.add(new SearchCriteria("title", "The", SearchOperation.MATCH));
				mvTitleRating.add(new SearchCriteria("rating", 8, SearchOperation.GREATER_THAN));
				List<Movie> mvTitleRatingList = movieRepository.findAll(mvTitleRating);
				System.out.println("\n\n######################   movies by title and rating    ##########################");
				mvTitleRatingList.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
		
				/**
				 * search movies by release year < 2015 and rating > 8
				 */
				MovieSpecification mvRYearRating = new MovieSpecification();
				mvRYearRating.add(new SearchCriteria("releaseYear", 2015, SearchOperation.LESS_THAN));
				mvRYearRating.add(new SearchCriteria("rating", 8, SearchOperation.GREATER_THAN));
				List<Movie> mvRYearRatingList = movieRepository.findAll(mvRYearRating);
				System.out.println("\n\n######################   movies by Release year and rating    ##########################");
				mvRYearRatingList.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
		

				/**
				 * search movies by watch time >= 145 and sort by title
				 */
				MovieSpecification mvWatchTime = new MovieSpecification();
				mvWatchTime.add(new SearchCriteria("watchTime", 140, SearchOperation.GREATER_THAN_EQUAL));
				List<Movie> mvWatchTimeList = movieRepository.findAll(mvWatchTime, Sort.by("title"));
				System.out.println("\n\n######################   movies by watchTime and sorted by title    ##########################");
				mvWatchTimeList.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
		
				/**
				 * Search movies by title <> white and paginate results
				 */
				MovieSpecification mvTitle = new MovieSpecification();
				mvTitle.add(new SearchCriteria("title", "The", SearchOperation.NOT_EQUAL));
				PageRequest pageable2 = PageRequest.of(0, 3, Sort.by("releaseYear").descending());
				Page<Movie> msTitleList = movieRepository.findAll(mvTitle, pageable2);
				System.out.println("\n\n######################   movies by title and sorted by release year, showing in page request    ##########################");
				msTitleList.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
		
				MovieSpecification mvGenre2 = new MovieSpecification();
				mvGenre2.add(new SearchCriteria("genre", "Action", SearchOperation.NOT_EQUAL));
				
				MovieSpecification mvRYearRating2 = new MovieSpecification();
				mvRYearRating2.add(new SearchCriteria("releaseYear", 2015, SearchOperation.LESS_THAN));
				mvRYearRating2.add(new SearchCriteria("rating", 8, SearchOperation.GREATER_THAN));
				
				
				/**
				 *  combine using `AND` operator
				 */
				List<Movie> moviesWhereAND = movieRepository.findAll(Specification.where(mvGenre2).and(mvRYearRating2));
				System.out.println("\n\n######################   movies by genre and rating using where clause and 'AND' operation    ##########################");
				moviesWhereAND.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
				
				
				/**
				 * combine using `OR` operator
				 */
				List<Movie> moviesWhereOR = movieRepository.findAll(Specification.where(mvGenre2).or(mvRYearRating2));
				System.out.println("\n\n######################   movies by genre and rating using where clause and 'OR' operation    ##########################");
				moviesWhereOR.forEach(System.out::println);
				System.out.println("---------------------------------------------\n");
				
				
				System.out.println("\n\n######################    JPA Specification Mapping demonstration Ended    ##########################");
				System.out.println("===========================================================================================\n\n");
			}
		
		}


The above example demonstrates how to use Specifications to **dynamically generate different database queries**. You can also apply **sorting** and **pagination** to query results in the same way as you apply to **other query generation strategies**.

You can even **combine** multiple **Specifications** together to create a new one on the fly. To do so, Spring Data JPA's Specification interface provides **and() and or()** methods to concatenate different Specifications. There is also a **where()** method that makes the expression more readable.

Specifications provide us a robust and flexible approach of creating database queries to handle complex use cases. This tutorial covers almost all basic operations that can be used to implement a powerful search feature.

