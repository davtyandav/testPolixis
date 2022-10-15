package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.UserNotFoundException;
import com.davtyandav.testPolixis.model.Note;

import java.util.List;
import java.util.Optional;


public interface NoteService {

    Optional<Note> getNote(String id);

    void addNote(Note note, String userId) throws UserNotFoundException;

    List<Note> getNotes();

    boolean delete(String id);

    boolean update(Note noteDto);

    List<Note> findNotesByUserId(String userId);

}
