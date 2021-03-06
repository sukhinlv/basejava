package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("Storage");
    protected static final Path STORAGE_PATH = Paths.get("Storage");

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";
    protected static final String NAME_1 = "Иван Иваныч Иванов";
    // необходимо проверить что "если fullName разных людей совпадает, то сортируйте дополнительно по uuid"
    protected static final String NAME_2 = "Иван Иваныч Иванов";
//    protected static final String NAME_2 = "Петр Петрович Петров";
    protected static final String NAME_3 = "Сидор Сидорович Сидоров";
    protected static final String DUMMY = "Dummy";
    protected static final Resume RESUME_1 = ResumeTestData.createTestResume(UUID_1, NAME_1);
    protected static final Resume RESUME_2 =ResumeTestData.createTestResume(UUID_2, NAME_2);
    protected static final Resume RESUME_3 =ResumeTestData.createTestResume(UUID_3, NAME_3);
    protected static final Resume RESUME_DUMMY =ResumeTestData.createTestResume(DUMMY, DUMMY);
    protected final AbstractStorage storage;

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        // порядок (1-3-2) сбит специально, не менять
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
    void getAllSorted() {
        ArrayList<Resume> expected = new ArrayList<>();
        expected.add(RESUME_1);
        expected.add(RESUME_2);
        expected.add(RESUME_3);
        List<Resume> actually = storage.getAllSorted();
        Assertions.assertAll(
                () -> assertSize(3),
                () -> Assertions.assertEquals(expected, actually));
    }

    @Test
    void save() throws FoundStorageException {
        Resume newResume =ResumeTestData.createTestResume(DUMMY, DUMMY);
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
        Resume newResume =ResumeTestData.createTestResume(UUID_3, NAME_3);
        storage.update(newResume);
        Assertions.assertAll(
                () -> assertSize(3),
                () -> Assertions.assertEquals(newResume, storage.get(newResume.getUuid())),
                () -> Assertions.assertThrows(NotFoundStorageException.class, () -> storage.update(ResumeTestData.createTestResume(DUMMY, DUMMY))));
    }

    void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }
}