package com.davtyandav.testPolixis.controller;

import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public List<NoteDto> getUsers() {
        return noteService.getNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNodeById(@PathVariable String id) {
        NoteDto note = noteService.getNote(id);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @PostMapping("/add")
    public void addNote(@RequestBody NoteDto noteDto) {
        noteService.addNote(noteDto);
    }

}
