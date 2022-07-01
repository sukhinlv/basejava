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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
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
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index < 0) {
            System.out.printf("Can`t update resume, %s not found\n", resume.getUuid());
            return;
        }
        storage[index] = resume;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t delete %s, resume not found\n", uuid);
            return;
        }
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public void save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            System.out.printf("Can`t save %s, max storage capacity reached\n", r.getUuid());
        } else {
            int index = findIndex(r.getUuid());
            if (index >= 0) {
                System.out.printf("%s already in storage\n", r.getUuid());
            } else {
                index = -(index) - 1;
                insertResume(index, r);
                size++;
            }
        }
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(int index, Resume r);

    /**
     * @return like Arrays.binarySearch, findIndex should returns index
     *         of the resume with specified uuid, if it is contained in the array
     *         otherwise, (-(insertion point)-1).  The insertion point
     *         is defined as the point at which the new resume would be inserted
     *         into the array.
     *         Note that this guarantees that the return value will be >= 0 if
     *         and only if the key is found.
     */
    protected abstract int findIndex(String uuid);
}