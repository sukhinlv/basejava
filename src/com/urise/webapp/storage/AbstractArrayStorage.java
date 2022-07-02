package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.exception.StorageException;
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
            throw new NotFoundStorageException(uuid);
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

    public final void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotFoundStorageException(r.getUuid());
        }
        storage[index] = r;
    }

    public final void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotFoundStorageException(uuid);
        }
        deleteResume(index);
        storage[--size] = null;
    }

    public final void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_CAPACITY) {
            throw new StorageException("Storage is full!", r.getUuid());
        } else if (index >= 0) {
            throw new FoundStorageException(r.getUuid());
        } else {
            insertResume(index, r);
            size++;
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(int index, Resume r);

    protected abstract int findIndex(String uuid);
}