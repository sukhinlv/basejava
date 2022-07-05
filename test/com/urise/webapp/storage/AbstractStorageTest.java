package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AbstractStorageTest {
    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String DUMMY = "Dummy";
    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);
    protected static final Resume RESUME_DUMMY = new Resume(DUMMY);
    protected final AbstractStorage storage;

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    void delete() throws NotFoundStorageException {
        storage.delete(UUID_2);
        Assertions.assertAll(
                () -> assertSize(2),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(DUMMY)));
    }

    @Test
    void get() throws NotFoundStorageException {
        Assertions.assertAll(
                () -> assertGet(RESUME_1),
                () -> assertGet(RESUME_2),
                () -> assertGet(RESUME_3),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> assertGet(RESUME_DUMMY)));
    }

    @Test
    void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] actually = storage.getAll();
        Arrays.sort(actually);
        Assertions.assertAll(
                () -> assertSize(3),
                () -> Assertions.assertArrayEquals(expected, actually));
    }

    @Test
    void save() throws FoundStorageException {
        Resume newResume = new Resume(DUMMY);
        storage.save(newResume);
        Assertions.assertAll(
                () -> assertSize(4),
                () -> assertGet(newResume),
                () -> Assertions.assertThrows(FoundStorageException.class, () -> storage.save(RESUME_1)));
    }

    @Test
    void size() {
        assertSize(3);
    }

    @Test
    void update() throws NotFoundStorageException {
        Resume newResume = new Resume(UUID_3);
        storage.update(newResume);
        Assertions.assertAll(
                () -> assertSize(3),
                () -> Assertions.assertSame(newResume, storage.get(newResume.getUuid())),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.update(new Resume(DUMMY))));
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }
}