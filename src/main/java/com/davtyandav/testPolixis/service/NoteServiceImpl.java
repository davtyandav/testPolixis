package com.davtyandav.testPolixis.service;


import com.davtyandav.testPolixis.dto.ExceptionDto;
import com.davtyandav.testPolixis.exception.ApiException;
import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
class NoteServiceImpl implements NoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    @Override
    public Optional<Note> getNote(String id) {
        return noteRepository.findById(id);
    }

    @Override
    public void addNote(Note note, String userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            LOGGER.error("Error has occurred during getting user: UserId = " + userId);
            throw userNotFoundException();
        }
        noteRepository.save(note);
    }

    @Override
    public List<Note> getNotes(String userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw userNotFoundException();
        }
        return noteRepository.findNotesByUserId(userId);
    }

    @Override
    public void delete(String id, String userId) {
        Optional<Note> byId = noteRepository.findById(id);
        if (byId.isEmpty()) {
            LOGGER.error("Error has occurred during getting note: NoteId = " + id);
            throw noteNotFoundException();
        } else if (!byId.get().getUserId().equals(userId)) {
            LOGGER.error("Error has occurred during checking access user to note. UserId: " + userId);
            throw notAccessException();
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note, String userId) {

        Optional<Note> byId = noteRepository.findById(note.getId());
        if (byId.isEmpty()) {
            LOGGER.error("Error has occurred during getting note: NoteId = " + note.getId());
            throw noteNotFoundException();
        } else if (!byId.get().getUserId().equals(userId)) {
            LOGGER.error("Error has occurred during checking access user to note. UserId: " + userId);
            throw notAccessException();
        }
        noteRepository.save(note);
    }


    private static ApiException noteNotFoundException() {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .message("Note Not Found")
                .status(HttpStatus.NOT_FOUND)
                .build();
        throw new ApiException(exceptionDto);
    }

    private static ApiException userNotFoundException() {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .message("User Not Found")
                .status(HttpStatus.NOT_FOUND)
                .build();
        throw new ApiException(exceptionDto);
    }

    private static ApiException notAccessException() {
        ExceptionDto exceptionDto = ExceptionDto.builder()
                .message("Not access")
                .status(HttpStatus.FORBIDDEN)
                .build();
        throw new ApiException(exceptionDto);
    }

}
