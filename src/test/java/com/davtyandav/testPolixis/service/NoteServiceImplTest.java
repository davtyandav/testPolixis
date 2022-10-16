package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void getNote() {
        NoteService noteService = new NoteServiceImpl(noteRepository, userRepository);
        User user = getUser();
        Note note = getNote(user);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById("1")).thenReturn(userOptional);
        Optional<Note> noteOptional = Optional.of(note);
        when(noteRepository.findById("1")).thenReturn(noteOptional);

        Note note1 = noteService.getNote("1").get();
        assertEquals(note, note1);
    }

    @Test
    void addNote() {
        NoteService service = new NoteServiceImpl(noteRepository, userRepository);
        User user = getUser();
        Note note = getNote(user);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById("1")).thenReturn(userOptional);
        Optional<Note> noteOptional = Optional.of(note);
        when(noteRepository.findById("1")).thenReturn(noteOptional);
        service.addNote(note, user.getId());

    }


    @Test
    void noteValidation() {
        Note note = new Note();
        note.setTitle("dsa");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set violations = validator.validate(note);
        assertFalse(violations.isEmpty());
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


    private Note getNote(User user) {
        Note note = new Note();
        note.setId("1");
        note.setNote("note");
        note.setTitle("title");
        note.setUserId(user.getId());
        note.setUpdateTime(new Date());
        note.setCreate(new Date());
        return note;
    }

    private User getUser() {
        User user = new User();
        user.setId("1");
        user.setCreate(new Date());
        user.setEmail("dav.davtyan@gmail.com");
        user.setPassword("12345678");
        return user;
    }
}