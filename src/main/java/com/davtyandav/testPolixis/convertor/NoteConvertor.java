package com.davtyandav.testPolixis.convertor;


import com.davtyandav.testPolixis.dto.NoteDto;
import com.davtyandav.testPolixis.model.Note;

public class NoteConvertor {

    public static NoteDto convertToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setNote(note.getNote());
        noteDto.setCreate(note.getCreate());
        noteDto.setUpdateTime(note.getUpdateTime());
        noteDto.setTitle(note.getTitle());
        noteDto.setUserId(note.getUserId());
        return noteDto;
    }

    public static Note convertToModel(NoteDto noteDto) {
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setNote(noteDto.getNote());
        note.setCreate(noteDto.getCreate());
        note.setUpdateTime(noteDto.getUpdateTime());
        note.setTitle(noteDto.getTitle());
        note.setUserId(noteDto.getUserId());
        return note;
    }

}
