package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Override
    protected List<Resume> getStorageAsList() {
        return new ArrayList<>(List.of(Arrays.copyOf((storage), size)));
    }

    public final int size() {
        return size;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);
}