package com.ag.sjh.queries.custom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.queries.custom.domains.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {

	
}
