package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.UserNotFoundException;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;


    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Note> getNote(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public void addNote(Note note, String userId) throws UserNotFoundException {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        } else {
            note.setUserId(userId);
        }
        noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public boolean delete(String id) {
        boolean exists = noteRepository.existsById(id);
        if (exists) {
            noteRepository.deleteById(id);
        }
        return exists;
    }

    @Override
    public boolean update(Note note) {
        boolean exists = noteRepository.existsById(note.getId());
        if (exists) {
            noteRepository.save(note);
        }
        return exists;
    }

    @Override
    public List<Note> findNotesByUserId(String userId) {
        return noteRepository.findNotesByUserId(userId);
    }
}
