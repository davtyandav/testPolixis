package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.convertor.NoteConvertor;
import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final NoteConvertor noteConvertor;

    public UserServiceImpl(NoteRepository noteRepository, NoteConvertor noteConvertor) {
        this.noteRepository = noteRepository;
        this.noteConvertor = noteConvertor;
    }

    @Override
    public NoteDto getNote(String id) {
        Optional<Note> byId = noteRepository.findById(id);

        if (byId.isEmpty()) {
            throw new RuntimeException("not nute");
        } else {
            return noteConvertor.convertToDto(byId.get());
        }

    }

    @Override
    public void addNote(NoteDto noteDto) {
        Note note = noteConvertor.convertToModel(noteDto);
        noteRepository.save(note);
    }

    @Override
    public List<NoteDto> getNotes() {
        return null;
    }
}
