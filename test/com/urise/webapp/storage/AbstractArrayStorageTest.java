package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "Dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_DUMMY = new Resume(DUMMY);
    protected AbstractArrayStorage storage;

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
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

    @Test
    void delete() throws NotFoundStorageException {
        storage.delete(UUID_2);
        Assertions.assertAll(
                () -> assertSize(2),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(DUMMY)));
    }

    @Test
    void save() throws FoundStorageException {
        Resume newResume = new Resume(DUMMY);
        storage.save(newResume);
        Assertions.assertAll(
                () -> assertSize(4),
                () -> assertGet(newResume),
                () -> Assertions.assertThrows(FoundStorageException.class, () -> storage.save(new Resume(UUID_1))));
    }

    @Test
    void overflow() throws StorageException {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected storage overflow", e);
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume(DUMMY)));
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size);
    }
}