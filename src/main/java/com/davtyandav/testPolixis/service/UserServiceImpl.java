package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public UserServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Optional<Note> getNote(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public void addNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }
}
