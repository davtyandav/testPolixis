package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteServiceImplTest extends BaseTest {

    @Mock
    private NoteRepository noteRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void getNote() {
        NoteService noteService = new NoteServiceImpl(noteRepository, userRepository);
        String userId = generateId();
        String noteId = generateId();
        User user = createUserById(userId);
        Note note = createNoteById(user, noteId);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(userId)).thenReturn(userOptional);

        Optional<Note> noteOptional = Optional.of(note);
        when(noteRepository.findById(noteId)).thenReturn(noteOptional);

        Note createdNote = noteService.getNote(noteId).get();
        assertEquals(note, createdNote);
    }

    @Test
    void getUser() {
        String userId = generateId();
        User user = createUserById(userId);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(userId)).thenReturn(userOptional);
    }

}