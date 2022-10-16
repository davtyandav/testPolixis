package com.davtyandav.testPolixis.controller;

import com.davtyandav.testPolixis.exception.NotAccessException;
import com.davtyandav.testPolixis.exception.NoteNotFoundException;
import com.davtyandav.testPolixis.exception.UserNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class NoteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    private final NoteConvertor noteConvertor;

    public NoteController(NoteService noteService, NoteConvertor noteConvertor) {
        this.noteService = noteService;
        this.noteConvertor = noteConvertor;
    }

    @GetMapping("/users/{userId}/notes")
    public ResponseEntity<List<NoteDto>> getNotes(@PathVariable String userId) {
        try {
            List<NoteDto> notes = noteService.getNotes(userId).stream()
                    .map(noteConvertor::convertToDto).collect(Collectors.toList());
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("notes/{id}")
    public ResponseEntity<NoteDto> getNodeById(@PathVariable String id) {
        return noteService.getNote(id)
                .map(noteConvertor::convertToDto)
                .map(noteDto -> new ResponseEntity<>(noteDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<?> addNote(@PathVariable String userId, @RequestBody @Valid NoteDto noteDto) {
        Note note = noteConvertor.convertToModel(noteDto);
        try {
            noteService.addNote(note, userId);
        } catch (UserNotFoundException e) {
            LOGGER.error("Error has occurred during getting user: UserId = " + userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateNote(@RequestParam String userId, @RequestBody @Valid NoteDto updateNoteDto) {
        Note note = noteConvertor.convertToModel(updateNoteDto);
        try {
            noteService.update(note, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            LOGGER.error("Error has occurred during getting note: NoteId = " + updateNoteDto.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotAccessException e) {
            LOGGER.error("Error has occurred during checking access user to note. UserId: " + userId);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<?> delete(@PathVariable String userId, @PathVariable String noteId) {
        try {
            noteService.delete(noteId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoteNotFoundException e) {
            LOGGER.error("Error has occurred during getting note: NoteId = " + noteId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotAccessException e) {
            LOGGER.error("Error has occurred during checking access user to note. UserId: " + userId);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
