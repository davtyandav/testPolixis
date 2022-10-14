package com.davtyandav.testPolixis.repository;


import com.davtyandav.testPolixis.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
