package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Can`t delete %s, resume not found\n", uuid);
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public void save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            System.out.printf("Can`t save %s, max storage capacity reached\n", r.getUuid());
        } else {
            int saveToIndex = findIndex(r.getUuid());
            if (saveToIndex >= 0) {
                System.out.printf("%s already in storage\n", r.getUuid());
            } else {
                saveToIndex = -(saveToIndex) - 1;
                System.arraycopy(storage, saveToIndex, storage, saveToIndex + 1, size - saveToIndex);
                storage[saveToIndex] = r;
                size++;
            }
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}