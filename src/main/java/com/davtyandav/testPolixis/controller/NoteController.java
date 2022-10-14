package com.davtyandav.testPolixis.controller;

import com.davtyandav.testPolixis.convertor.NoteConvertor;
import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    private Logger logger = LoggerFactory.getLogger(NoteController.class);
    
    private final NoteService noteService;

    private final NoteConvertor noteConvertor;

    public NoteController(NoteService noteService, NoteConvertor noteConvertor) {
        this.noteService = noteService;
        this.noteConvertor = noteConvertor;
    }

    @GetMapping()
    public List<NoteDto> getUsers() {
        return noteService.getNotes().stream().map(noteConvertor::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNodeById(@PathVariable String id) {
        Optional<Note> note = noteService.getNote(id);
        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            NoteDto noteDto = noteConvertor.convertToDto(note.get());
            return new ResponseEntity<>(noteDto, HttpStatus.OK);
        }
    }

    @PostMapping()
    public void addNote(@RequestBody NoteDto noteDto) {
        Note note = noteConvertor.convertToModel(noteDto);
        noteService.addNote(note);
    }

    @PutMapping()
    public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
        Optional<Note> note = noteService.getNote(noteDto.getId());
        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Note update = noteService.update(note.get());
            NoteDto updateDto = noteConvertor.convertToDto(update);
            return new ResponseEntity<>(updateDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDto> delete(@PathVariable String id) {
        Optional<Note> note = noteService.getNote(id);
        if (note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Note deleteNote = note.get();
            noteService.delete(deleteNote);
            NoteDto noteDto = noteConvertor.convertToDto(deleteNote);
            return new ResponseEntity<>(noteDto, HttpStatus.OK);
        }
    }
}
