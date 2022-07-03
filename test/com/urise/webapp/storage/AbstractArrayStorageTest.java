package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractArrayStorageTest {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String DUMMY = "Dummy";
    protected AbstractArrayStorage storage;

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_2));
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertAll(
                () -> Assertions.assertEquals(storage.size(), 0),
                () -> Assertions.assertEquals(storage.getAll().length, 0));
    }

    @Test
    void get() throws NotFoundStorageException {
        Assertions.assertAll(
                () -> Assertions.assertEquals(storage.get(UUID_1), new Resume(UUID_1)),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.get(DUMMY)));
    }

    @Test
    void getAll() {
        Assertions.assertEquals(storage.getAll().length, 3);
    }

    @Test
    void size() {
        Assertions.assertEquals(storage.size(), 3);
    }

    @Test
    void update() throws NotFoundStorageException {
        Assertions.assertAll(
                () -> storage.update(new Resume(UUID_2)),
                () -> Assertions.assertEquals(storage.size(), 3),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.update(new Resume(DUMMY))));
    }

    @Test
    void delete() throws NotFoundStorageException {
        Assertions.assertAll(
                () -> storage.delete(UUID_2),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.delete(DUMMY)),
                () -> Assertions.assertEquals(storage.size(), 2));
    }

    @Test
    void save() throws FoundStorageException {
        Assertions.assertAll(
                () -> Assertions.assertThrows(FoundStorageException.class, () -> storage.save(new Resume(UUID_1))),
                () -> {
                    storage.save(new Resume(DUMMY));
                    Assertions.assertEquals(storage.size(), 4);
                });
    }

    @Test
    void overflow() throws StorageException {
        try {
            for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("Unexpected storage overflow", e);
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume(DUMMY)));
    }

    @Test
    abstract void deleteResume();

    @Test
    abstract void insertResume();

    @Test
    abstract void findIndex();
}