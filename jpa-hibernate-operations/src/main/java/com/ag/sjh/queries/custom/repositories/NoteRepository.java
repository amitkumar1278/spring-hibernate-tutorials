package com.ag.sjh.queries.custom.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ag.sjh.queries.custom.domains.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {

	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * find by title
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitlePositionalBind(String title);

	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitle2(String title, Sort sort);

	@Query("SELECT n FROM Note n WHERE n.title = :title")
	List<Note> findByTitleNamedBind(@Param("title") String title);
	
	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * find by priority
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n FROM Note n WHERE n.priority = ?1")
	List<Note> findByPriority(int priority, Sort sort);

		
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * Pagination
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n FROM Note n")
	Page<Note> findAllNotesWithPagination(Pageable pageable);

	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * JPQL vs native query: featured notes
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n FROM Note n WHERE n.featured = true")
	List<Note> findByActiveNotes();

	@Query(value = "SELECT * FROM Note n WHERE n.featured = 1", nativeQuery = true)
	List<Note> findByFeaturedNotesNative();

	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * find by title and featured
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n FROM Note n WHERE n.title = ?1 AND n.featured = ?2")
	List<Note> findByTitleAndFeaturedPositionalBind(String title, boolean featured);


	@Query("SELECT n FROM Note n WHERE n.title = :title AND n.featured = :featured")
	List<Note> findByTitleAndFeaturedNamedBind(@Param("featured") boolean featured, @Param("title") String title);

	// And / Or
	@Query("SELECT n FROM Note n WHERE n.title = ?1 AND n.featured = ?2 OR n.priority = ?3")
	List<Note> findByTitleAndFeaturedOrPriority(String title, boolean featured, int priority);

	
	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of operators started
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	
	/** Equality */
	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitle(String title);
	
	/** Ignore Case */
	@Query("SELECT n FROM Note n WHERE lower(n.title) = lower(?1) ")
	List<Note> findByTitleIgnoreCase(String title);
	
	/** Not Equal */
	@Query("SELECT n FROM Note n WHERE n.title <> ?1")
	List<Note> findByTitleNotEqual(String title);
	
	/** Like / Contains / Starts With / Ends With */
	@Query("SELECT n FROM Note n WHERE n.title LIKE %?1%")
	List<Note> findByTitleLike(String pattern);
	
	/** Less Than */
	@Query("SELECT n FROM Note n WHERE n.priority < ?1")
	List<Note> findByPriorityLessThan(int priority);
	
	/** Greater Than */
	@Query("SELECT n FROM Note n WHERE n.priority > ?1")
	List<Note> findByPriorityGreaterThan(int priority);

	/** Between */
	@Query("SELECT n FROM Note n WHERE n.priority BETWEEN  ?1 AND ?2")
	List<Note> findByPriorityBetween(int start, int end);
	
	/** Before */
	@Query("SELECT n FROM Note n WHERE n.created < ?1")
	List<Note> findByCreatedBefore(Date before);

	/** After */
	@Query("SELECT n FROM Note n WHERE n.created > ?1")
	List<Note> findByCreatedAfter(Date before);
	
	/** ///////////////////////////////////////////////////////////////////////////////
	// below method is not implemented
	/////////////////////////////////////////////////////////////////////////////// */

	/** Null */
	@Query("SELECT n FROM Note n WHERE n.title IS NULL")
	List<Note> findByTitleIsNull();

	/** Not Null */
	@Query("SELECT n FROM Note n WHERE n.title IS NOT NULL")
	List<Note> findByTitleIsNotNull();

	/** In */
	@Query("SELECT n FROM Note n WHERE n.priority IN ?1")
	List<Note> findByPriorityIn(Set<Integer> priorities);

	/** static ordering */
	@Query("SELECT n FROM Note n WHERE n.title = ?1 ORDER BY n.priority ASC")
	List<Note> findByTitleOrderByPriorityAsc(String title);

	@Query("SELECT n FROM Note n WHERE n.featured = ?1 ORDER BY n.created DESC")
	List<Note> findByFeaturedOrderByCreatedDesc(boolean featured);

	/** dynamic sorting */
	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitle(String title, Sort sort);
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of operators ended
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	
	
	
	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of Modifying annotation
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Modifying
	@Transactional
	@Query("UPDATE Note n SET n.title = ?1 WHERE n.id = ?2")
	int updateTitleById(String title, Long id);

	@Modifying
	@Transactional
	@Query("DELETE FROM Note n WHERE n.title = ?1")
	void deleteByTitle(String title);

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("UPDATE Note n SET n.title = ?1 WHERE n.id IN ?2")
	int bulkUpdateTitle(String title, Set<Long> id);

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("DELETE FROM Note n WHERE n.featured = ?1 AND n.id IN ?2")
	void bulkDeleteByFeatured(boolean featured, Set<Long> id);

	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of Modifying annotation ended
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/



	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of SpEL Expressions Started
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	@Query("SELECT n from #{#entityName} n WHERE n.title = ?1")
	List<Note> findByTitleGeneric(String title);

	@Query("SELECT n FROM Note n WHERE lower(n.title) LIKE %?#{[0].toLowerCase()}%")
	List<Note> findByTitleIgnoreCaseSpEL(String title);

	/**
	 * ////////////////////////////////////////////////////////////////////////////////
	 * use of SpEL Expressions Ended
	 * ///////////////////////////////////////////////////////////////////////////////
	 **/
	

}
