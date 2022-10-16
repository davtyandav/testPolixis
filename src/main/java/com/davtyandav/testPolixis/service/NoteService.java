package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.model.Note;

import java.util.List;
import java.util.Optional;


public interface NoteService {

    Optional<Note> getNote(String id);

    void addNote(Note note, String userId) ;

    List<Note> getNotes(String userId);

    void delete(String id, String userId);

    void update(Note note,String userId);

}
