package com.ag.sjh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

import com.ag.sjh.queries.custom.domains.Note;
import com.ag.sjh.queries.custom.repositories.NoteRepository;

@org.springframework.core.annotation.Order(value = 4)
@Component
public class QueryCustomStartupRunner implements CommandLineRunner {

	@Autowired
	NoteRepository noteRepository;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n=============================================================================================");
		System.out.println("######################    Custom Query demonstration Started    ##########################\n\n");



		List<Note> noteList = Arrays.asList(
				new Note("", 1, true, new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2020")),
				new Note("", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("15/08/2020")),
				new Note("Attend office meeting", 1, true, new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2020")),
				new Note("Gym", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/07/2020")),
				new Note("Gym", 4, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2020")),
				new Note("Gym", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2020")),
				new Note("Swimming", 6, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/07/2020")),
				new Note("Swimming", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/08/2020")),
				new Note("Swimming", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("13/09/2020")),
				new Note("Temple", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("12/09/2020")),
				new Note("Party", 4, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/06/2020")),
				new Note("party", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/07/2020")),
				new Note("party", 6, false, new SimpleDateFormat("dd/MM/yyyy").parse("11/08/2020")),
				new Note("party", 7, true, new SimpleDateFormat("dd/MM/yyyy").parse("11/09/2020")),
				new Note("office party", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("10/09/2020")),
				new Note("party with friends", 5, true, new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2020")),
				new Note("Purchase Grocery", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("16/09/2020"))
				);
		noteRepository.saveAll(noteList);



		List<Note> savedNotesList = (List<Note>) noteRepository.findAll();
		System.out.println("\n######################    All notes are below  -> List user  ##########################");
		savedNotesList.forEach(note -> {
			System.out.println(note.toString());
		});
		System.out.println("---------------------------------------------\n");


		/*** Find by title	*/
		List<Note> titleNotes = noteRepository.findByTitlePositionalBind("Gym");
		System.out.println("\n\n######################   notes by title ##########################");
		titleNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> titleNamedNotes = noteRepository.findByTitleNamedBind("Swimming");
		System.out.println("\n\n######################   notes by findByTitleNamedBind ##########################");
		titleNamedNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");		



		List<Note> titleNotesAsc = noteRepository.findByTitle2("party", Sort.by("created").ascending());
		System.out.println("\n\n######################   notes by title, sorted ascending ##########################");
		titleNotesAsc.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");
		/*** Find by title End */


		/*** Find by Priority	*/
		List<Note> priorityDescNotes = noteRepository.findByPriority(4, Sort.by("created").descending());
		System.out.println("\n\n######################   notes by Priority, sorted by priority descending ##########################");
		priorityDescNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> priorityLengthNotes = noteRepository.findByPriority(5, JpaSort.unsafe("LENGTH(title)"));
		System.out.println("\n\n######################   notes by Priority, sorted by length ##########################");
		priorityLengthNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");

		/*** Find by Priority End */


		/**
		 * Pagination
		 */
		Pageable pageable = PageRequest.of(0, 10, Sort.by("title").descending());
		Page<Note> notePage = noteRepository.findAllNotesWithPagination(pageable);
		System.out.println("\n\n######################   notes with pagination ##########################");
		notePage.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");

		/*** Pagination end	*/


		/**
		 * Find by feature
		 */
		List<Note> activeNotes = noteRepository.findByActiveNotes();
		System.out.println("\n\n######################   Active Notes ##########################");
		activeNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> featuredNotesNative = noteRepository.findByFeaturedNotesNative();
		System.out.println("\n\n######################   FeaturedNotesNative Notes ##########################");
		featuredNotesNative.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");

		/*** Find by feature End */


		/**
		 * Find by title and feature
		 */
		List<Note> titleAndFeaturedNotes = noteRepository.findByTitleAndFeaturedPositionalBind("Swimming", false);
		System.out.println("\n\n######################   titleAndFeaturedPositionalBind Notes ##########################");
		titleAndFeaturedNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> titleAndFeaturedNamedNotes = noteRepository.findByTitleAndFeaturedNamedBind(false, "Swimming");
		System.out.println("\n\n######################   findByTitleAndFeaturedNamedBind Notes ##########################");
		titleAndFeaturedNamedNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> titleAndFeaturedOrPriorityNotes = noteRepository.findByTitleAndFeaturedOrPriority("Swimming", false, 5);
		System.out.println("\n\n######################   findByTitleAndFeaturedNamedBind Notes ##########################");
		titleAndFeaturedOrPriorityNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");

		/*** Find by title and feature End */



		System.out.println("\n\n######################   Custom Query using Operator Started ##########################");
		System.out.println("##############################################################################");

		/**
		 * use of different type of operators started.
		 */

		List<Note> titleNotesEqual = noteRepository.findByTitle("Gym");
		System.out.println("\n\n######################   notes by title ##########################");
		titleNotesEqual.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> titleNotesIgnoreCaseOper = noteRepository.findByTitleIgnoreCase("party");
		System.out.println("\n\n######################   notes by title, Ignore case ##########################");
		titleNotesIgnoreCaseOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> titleNotesNotEqualOper = noteRepository.findByTitleNotEqual("party");
		System.out.println("\n\n######################   notes by title, not equal ##########################");
		titleNotesNotEqualOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> titleNotesLikeOper = noteRepository.findByTitleLike("party");
		System.out.println("\n\n######################   notes by title, Like ##########################");
		titleNotesLikeOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> priorityLessThanOper = noteRepository.findByPriorityLessThan(4);
		System.out.println("\n\n######################   notes by priority, less than ##########################");
		priorityLessThanOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> priorityGreaterThanOper = noteRepository.findByPriorityGreaterThan(4);
		System.out.println("\n\n######################   notes by priority, Greater than ##########################");
		priorityGreaterThanOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> priorityBetweenOper = noteRepository.findByPriorityBetween(3, 5);
		System.out.println("\n\n######################   notes by priority, Between than ##########################");
		priorityBetweenOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> createdBeforeOper = noteRepository.findByCreatedBefore(new SimpleDateFormat("dd/MM/yyyy").parse("01/08/2020"));
		System.out.println("\n\n######################   notes by Created, Brfore ##########################");
		createdBeforeOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		List<Note> createdAfterOper = noteRepository.findByCreatedAfter(new SimpleDateFormat("dd/MM/yyyy").parse("01/08/2020"));
		System.out.println("\n\n######################   notes by Created, After ##########################");
		createdAfterOper.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");


		/** FEW MORE METHOD WITH OPERATOR IS LEFT TO IMPLEMENT HERE */
		System.out.println("\n\n######################   Custom Query using Operator Ended ##########################");
		System.out.println("###############################################################################");

		/**
		 * ////////////////////////////////////////////////////////////////////////////////
		 * Modifying OPERATIONS
		 * ///////////////////////////////////////////////////////////////////////////////
		 **/

		int updated = noteRepository.updateTitleById("Updated Title", 10L);
		System.out.println("\n\n######################   Updated title ##########################");
		System.out.println("Updated:: "+updated);
		System.out.println("---------------------------------------------\n");



		Set<Long> titleSet = new HashSet<Long>();	
		titleNotesEqual.stream().map(entity->titleSet.add(entity.getId())).collect(Collectors.toSet());
		int updated2 = noteRepository.bulkUpdateTitle("Updated Gym", titleSet);
		System.out.println("\n\n######################   Updated title ##########################");
		System.out.println("Updated:: "+updated2);
		System.out.println("---------------------------------------------\n");

		

		List<Note> allNotesAfterUpdate = (List<Note>) noteRepository.findAll();
		System.out.println("\n######################    All notes after update operation  ##########################");
		allNotesAfterUpdate.forEach(note -> {
			System.out.println(note.toString());
		});
		


		System.out.println("\n\n######################   deleted by title  ##########################");
		noteRepository.deleteByTitle("Purchase Grocery");

		
		Set<Long> notesToDelete = new HashSet<Long>();	
		noteRepository.findByTitleNotEqual("Swimming")
			.stream().map(entity	->	notesToDelete.add(entity.getId()))
			.collect(Collectors.toSet());
		
		System.out.println("\n\n######################  bulk deleted by feature and list of id  ##########################");
		noteRepository.bulkDeleteByFeatured(true, notesToDelete);
		

				
		List<Note> allNotesDelete = (List<Note>) noteRepository.findAll();
		System.out.println("\n######################    All notes after delete  ##########################");
		allNotesDelete.forEach(note -> {
			System.out.println(note.toString());
		});


		System.out.println("---------------------------------------------\n");



		System.out.println("\n\n######################    Custom Query demonstration Ended    ##########################");
		System.out.println("===========================================================================================\n\n");

	}

}
