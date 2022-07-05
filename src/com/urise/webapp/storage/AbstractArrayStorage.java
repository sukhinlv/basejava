package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_CAPACITY = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_CAPACITY];
    protected int size = 0;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public final void doDelete(Object index) {
        deleteResume((Integer) index);
        storage[--size] = null;
    }

    public final void doSave(Resume r, Object index) {
        if (size == STORAGE_CAPACITY) {
            throw new StorageException("Storage is full!", r.getUuid());
        }
        insertResume(r, (Integer) index);
        size++;
    }

    public final void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
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

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);
}