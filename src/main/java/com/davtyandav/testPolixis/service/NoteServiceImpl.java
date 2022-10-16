package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.exception.NotAccessException;
import com.davtyandav.testPolixis.exception.NoteNotFoundException;
import com.davtyandav.testPolixis.exception.UserNotFoundException;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public void addNote(@Valid Note note, String userId) throws UserNotFoundException {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes(String userId) throws UserNotFoundException {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        return noteRepository.findNotesByUserId(userId);
    }

    @Override
    public void delete(String id, String userId) throws NoteNotFoundException, NotAccessException {
        Optional<Note> byId = noteRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NoteNotFoundException("Note Not Found");
        } else if (!byId.get().getUserId().equals(userId)) {
            throw new NotAccessException();
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note, String userId) throws NoteNotFoundException, NotAccessException {

        Optional<Note> byId = noteRepository.findById(note.getId());
        if (byId.isEmpty()) {
            throw new NoteNotFoundException("Note Not Found");
        } else if (!byId.get().getUserId().equals(userId)) {
            throw new NotAccessException();
        }

        noteRepository.save(note);

    }

}
