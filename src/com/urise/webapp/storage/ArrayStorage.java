package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t delete %s, resume not found\n", uuid);
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public void save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            System.out.printf("Can`t save %s, max storage capacity reached\n", r.getUuid());
        } else if (findIndex(r.getUuid()) >= 0) {
            System.out.printf("%s already in storage\n", r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

        protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return i;
            }
        }
        return -1;
    }
}