package com.davtyandav.testPolixis.repository;


import com.davtyandav.testPolixis.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    List<Note> findNotesByUserId(String userId);
}
