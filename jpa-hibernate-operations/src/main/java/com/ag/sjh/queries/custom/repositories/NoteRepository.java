package com.ag.sjh.queries.custom.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.queries.custom.domains.Note;


public interface NoteRepository extends CrudRepository<Note, Long> {

	/**
	 *  positional based bind parameters
	 * @param title
	 * @return
	 */
	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitlePositionalBind(String title);

	/**
	 *  dynamic sorting
	 * @param title
	 * @param ascending
	 * @return
	 */
	@Query("SELECT n FROM Note n WHERE n.title = ?1")
	List<Note> findByTitle2(String title, Sort sort);

	@Query("SELECT n FROM Note n WHERE n.priority = ?1")
	List<Note> findByPriority(int priority, Sort sort);

	/**
	 * pagination
	 * @param pageable
	 * @return
	 */
	@Query("SELECT n FROM Note n")
	Page<Note> findAllNotesWithPagination(Pageable pageable);

	/**
	 * JPQL vs native query
	 * @return
	 */
	@Query("SELECT n FROM Note n WHERE n.featured = true")
	List<Note> findByActiveNotes();

	@Query(value = "SELECT * FROM Note n WHERE n.featured = 1",
			nativeQuery = true)
	List<Note> findByFeaturedNotesNative();



}
