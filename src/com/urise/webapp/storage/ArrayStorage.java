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

    public boolean delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            return false;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        storage[size] = null;
        return true;
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

    public boolean save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            return false;
        }
        storage[size] = r;
        size++;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean update(Resume resume) {
        int index = findIndex(resume.uuid);
        if (index < 0) {
            return false;
        }
        storage[index] = resume;
        return true;
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