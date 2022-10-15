package com.davtyandav.testPolixis.controller;

import com.davtyandav.testPolixis.UserNotFoundException;
import com.davtyandav.testPolixis.convertor.NoteConvertor;
import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.dto.UpdateNoteDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    private final NoteConvertor noteConvertor;

    public NoteController(NoteService noteService, NoteConvertor noteConvertor) {
        this.noteService = noteService;
        this.noteConvertor = noteConvertor;
    }

    @GetMapping()
    public List<NoteDto> getNotes() {
        return noteService.getNotes().stream().map(noteConvertor::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNodeById(@PathVariable String id) {
        return noteService.getNote(id)
                .map(noteConvertor::convertToDto)
                .map(noteDto -> new ResponseEntity<>(noteDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<?> addNote(@RequestParam String userId, @RequestBody NoteDto noteDto) {
        Note note = noteConvertor.convertToModel(noteDto);
        try {
            noteService.addNote(note, userId);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteDto updateNoteDto) {
        Note note = noteConvertor.convertToModel(updateNoteDto);
        boolean update = noteService.update(note);
        if (update) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        boolean delete = noteService.delete(id);
        if (delete) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
