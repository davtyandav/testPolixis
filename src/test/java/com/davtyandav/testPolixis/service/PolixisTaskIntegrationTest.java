package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.Note;
import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.NoteRepository;
import com.davtyandav.testPolixis.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
@ContextConfiguration(initializers = PolixisTaskIntegrationTest.Initializer.class)
class PolixisTaskIntegrationTest extends BaseTest {

    @Container
    public static GenericContainer mongoContainer = new GenericContainer("mongo:4.0").withExposedPorts(27017);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @AfterEach
    public void removeEntities() {
        mongoTemplate.dropCollection("users");
        mongoTemplate.dropCollection("notes");
    }

    @Test()
    public void getAllUser() {
        User firstUser = createUser();
        User secondUser = createUser();

        mongoTemplate.insert(firstUser);
        mongoTemplate.insert(secondUser);

        Note note = createNote(firstUser);
        mongoTemplate.insert(note);

        List<User> all = userRepository.findAll();
        List<Note> notes = noteRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
        assertThat(notes.size()).isEqualTo(1);
    }

    @Test()
    public void addNote() {
        User user = createUser();
        User insertUser = mongoTemplate.insert(user);
        Note note = createNote(insertUser);

        noteService.addNote(note, insertUser.getId());
        List<Note> notes = noteService.getNotes(user.getId());

        assertThat(notes.size()).isEqualTo(1);
    }

    @Test()
    public void updateNode() {
        User user = createUser();
        User insertUser = mongoTemplate.insert(user);

        Note note = createNote(insertUser);
        Note insertNote = mongoTemplate.insert(note);

        List<Note> notes = noteService.getNotes(user.getId());
        assertThat(notes.size()).isEqualTo(1);

        assertThat(note).isEqualToIgnoringGivenFields(insertNote, "id");

        insertNote.setNote("tstststststs");
        noteService.update(insertNote, insertUser.getId());

        Note updateNote = noteService.getNote(insertNote.getId()).get();
        assertThat(insertNote).isEqualToIgnoringGivenFields(updateNote, "note");

    }

    @Test()
    public void deleteNode() {
        User user = createUser();
        User insertUser = mongoTemplate.insert(user);

        Note note = createNote(insertUser);
        Note insertNote = mongoTemplate.insert(note);

        List<Note> notes = noteService.getNotes(user.getId());
        assertThat(notes.size()).isEqualTo(1);

        assertThat(note).isEqualToIgnoringGivenFields(insertNote, "id");

        noteService.delete(insertNote.getId(), insertUser.getId());
        List<Note> afterDelete = noteService.getNotes(user.getId());
        assertThat(afterDelete.size()).isEqualTo(0);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(configurableApplicationContext,
                    format("spring.data.mongodb.uri=mongodb://%s:%s", mongoContainer.getContainerIpAddress(), mongoContainer.getFirstMappedPort()));
        }
    }

}