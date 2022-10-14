package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.dto.NoteDto;

import java.util.List;


public interface NoteService {

    NoteDto getNote(String id);

    void addNote(NoteDto note);

    List<NoteDto> getNotes();

}
