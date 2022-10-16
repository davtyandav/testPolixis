package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.exception.NotAccessException;
import com.davtyandav.testPolixis.exception.NoteNotFoundException;
import com.davtyandav.testPolixis.exception.UserNotFoundException;
import com.davtyandav.testPolixis.model.Note;

import java.util.List;
import java.util.Optional;


public interface NoteService {

    Optional<Note> getNote(String id);

    void addNote(Note note, String userId) throws UserNotFoundException;

    List<Note> getNotes(String userId) throws UserNotFoundException;

    void delete(String id, String userId) throws NoteNotFoundException, NotAccessException;

    void update(Note noteDto,String userId) throws NoteNotFoundException, NotAccessException;

}
