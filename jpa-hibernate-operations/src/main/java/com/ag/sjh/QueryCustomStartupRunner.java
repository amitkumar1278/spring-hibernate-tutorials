package com.ag.sjh;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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
				new Note("Attend office meeting", 1, true, new SimpleDateFormat("dd/MM/yyyy").parse("15/09/2020")),
				new Note("Gym", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/07/2020")),
				new Note("Gym", 4, false, new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2020")),
				new Note("Gym", 2, true, new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2020")),
				new Note("Swimming", 6, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/07/2020")),
				new Note("Swimming", 5, false, new SimpleDateFormat("dd/MM/yyyy").parse("13/07/2020")),
				new Note("Swimming", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("13/09/2020")),
				new Note("Temple", 4, true, new SimpleDateFormat("dd/MM/yyyy").parse("12/09/2020")),
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

		

		List<Note> titleNotes = noteRepository.findByTitlePositionalBind("party");
		System.out.println("\n\n######################   notes by title ##########################");
		titleNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");
		

		
		List<Note> titleNotesAsc = noteRepository.findByTitle2("party", Sort.by("created").ascending());
		System.out.println("\n\n######################   notes by title, sorted ascending ##########################");
		titleNotesAsc.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> priorityDescNotes = noteRepository.findByPriority(4, Sort.by("created").descending());
		System.out.println("\n\n######################   notes by Priority, sorted by priority descending ##########################");
		priorityDescNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> priorityLengthNotes = noteRepository.findByPriority(5, JpaSort.unsafe("LENGTH(title)"));
		System.out.println("\n\n######################   notes by Priority, sorted by length ##########################");
		priorityLengthNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		Pageable pageable = PageRequest.of(0, 10, Sort.by("title").descending());
		Page<Note> notePage = noteRepository.findAllNotesWithPagination(pageable);
		System.out.println("\n\n######################   notes with pagination ##########################");
		notePage.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> activeNotes = noteRepository.findByActiveNotes();
		System.out.println("\n\n######################   Active Notes ##########################");
		activeNotes.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");



		List<Note> featuredNotesNative = noteRepository.findByFeaturedNotesNative();
		System.out.println("\n\n######################   FeaturedNotesNative Notes ##########################");
		featuredNotesNative.forEach(System.out::println);
		System.out.println("---------------------------------------------\n");










































		System.out.println("\n\n######################    Custom Query demonstration Ended    ##########################");
		System.out.println("===========================================================================================\n\n");

	}

}
