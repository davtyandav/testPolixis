package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
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
}
