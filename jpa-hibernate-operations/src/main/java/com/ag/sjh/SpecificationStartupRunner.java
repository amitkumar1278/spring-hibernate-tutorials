package com.ag.sjh;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ag.sjh.specifications.domain.Movie;
import com.ag.sjh.specifications.repositories.MovieRepository;
import com.ag.sjh.specifications.repositories.specs.MovieSpecification;
import com.ag.sjh.specifications.repositories.specs.SearchCriteria;
import com.ag.sjh.specifications.repositories.specs.SearchOperation;

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
		//		Pageable pageable = (Pageable) PageRequest.of(0, 3, Sort.by("releaseYear").descending());
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
