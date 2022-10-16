package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;

import java.util.Date;
import java.util.UUID;

class BaseTest {

    protected User createUserById(String id) {
        User user = createUser();
        user.setId(id);
        return user;
    }

    protected Note createNoteById(User user,String noteId) {
        Note note = createNote(user);
        note.setId(noteId);
        return note;
    }

    protected User createUser() {
        User user = new User();
        user.setPassword(getRandomString().substring(0, 7));
        user.setEmail(getRandomString().substring(0, 9) + "@gmail.com");
        user.setCreate(new Date());
        return user;
    }

    protected Note createNote(User user) {
        Note note = new Note();
        note.setNote(getRandomString().substring(0, 25));
        note.setTitle(getRandomString().substring(0, 19));
        note.setUpdateTime(new Date());
        note.setCreate(new Date());
        note.setUserId(user.getId());
        return note;
    }

    private String getRandomString() {
        return UUID.randomUUID().toString();
    }

    protected String generateId() {
        return getRandomString().substring(0, 25);
    }
}
