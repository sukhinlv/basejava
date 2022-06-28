package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int STORAGE_CAPACITY = 10_000;
    private Resume[] storage = new Resume[STORAGE_CAPACITY];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t delete %s, resume not found", uuid);
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        storage[size] = null;
        return;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            System.out.printf("Can`t save %s, max storage capacity reached", r.getUuid());
            return;
        }
        if (findIndex(r.getUuid()) >= 0) {
            System.out.printf("%s already in storage", r.getUuid());
            return;
        }
        storage[size] = r;
        size++;
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            System.out.printf("Can`t update resume. %s not found", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return i;
            }
        }
        return -1;
    }
}