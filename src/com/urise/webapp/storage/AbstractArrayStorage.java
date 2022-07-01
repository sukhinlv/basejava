package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_CAPACITY = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_CAPACITY];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t get %s, resume not found\n", uuid);
            return null;
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final int size() {
        return size;
    }

    public final void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            System.out.printf("Can`t update resume, %s not found\n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t delete %s, resume not found\n", uuid);
            return;
        }
        deleteResume(index);
        storage[--size] = null;
    }

    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_CAPACITY) {
            System.out.printf("Can`t save %s, max storage capacity reached\n", r.getUuid());
        } else if (index >= 0) {
            System.out.printf("%s already in storage\n", r.getUuid());
        } else {
            insertResume(index, r);
            size++;
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(int index, Resume r);

    protected abstract int findIndex(String uuid);
}