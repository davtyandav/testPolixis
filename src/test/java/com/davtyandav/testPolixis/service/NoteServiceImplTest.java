package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @Test
    void getNote() {
        NoteService service = new NoteServiceImpl(noteRepository);
        Note note = new Note();
        note.setId("1");
        note.setNote("note");
        note.setTitle("title");
        note.setUpdateTime(new Date());
        note.setCreate(new Date());
        Optional<Note> userOptional = Optional.of(note);
        when(service.getNote("1")).thenReturn(userOptional);

    }

    @Test
    void addNote() {
    }

    @Test
    void getNotes() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}