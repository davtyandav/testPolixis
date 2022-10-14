package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.convertor.NoteConvertor;
import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements NoteService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map(noteConvertor::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {

        Optional<Note> byId = noteRepository.findById(id);

        if (byId.isEmpty()) {
            throw new RuntimeException("not nute");
        } else {
            noteRepository.delete(byId.get());
        }
    }

    @Override
    public NoteDto update(NoteDto noteDto) {

        Optional<Note> byId = noteRepository.findById(noteDto.getId());

        if (byId.isEmpty()) {
            throw new RuntimeException("not nute");
        } else {
            Note save = noteRepository.save(byId.get());
            return noteConvertor.convertToDto(save);
        }
    }
}
