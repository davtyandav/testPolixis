package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.model.Note;

import java.util.List;
import java.util.Optional;


public interface NoteService {

    Optional<Note> getNote(String id);

    void addNote(Note note);

    List<Note> getNotes();

    void delete(Note Note);

    Note update(Note noteDto);

}
