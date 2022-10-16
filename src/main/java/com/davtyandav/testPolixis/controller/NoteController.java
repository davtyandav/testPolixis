package com.davtyandav.testPolixis.controller;

import com.davtyandav.testPolixis.convertor.NoteConvertor;
import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.service.NoteService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/users/{userId}/notes")
    public ResponseEntity<List<NoteDto>> getNotes(@PathVariable String userId) {
        var notes = noteService.getNotes(userId).stream()
                .map(NoteConvertor::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNodeById(@PathVariable String id) {
        return noteService.getNote(id)
                .map(NoteConvertor::convertToDto)
                .map(noteDto -> new ResponseEntity<>(noteDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<?> addNote(@PathVariable String userId, @RequestBody @Valid NoteDto noteDto) {
        Note note = NoteConvertor.convertToModel(noteDto);
        noteService.addNote(note, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/notes")
    public ResponseEntity<?> updateNote(@RequestParam String userId, @RequestBody @Valid NoteDto noteDto) {
        Note note = NoteConvertor.convertToModel(noteDto);
        noteService.update(note, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/notes/{noteId}")
    public ResponseEntity<?> delete(@PathVariable String userId, @PathVariable String noteId) {
        noteService.delete(noteId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
