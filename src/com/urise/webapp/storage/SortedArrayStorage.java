package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected void insertResume(int index, Resume r) {
        if (index < 0) {
            index = -(index) - 1;
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    /**
     * @return like Arrays.binarySearch, findIndex should return index
     *         of the resume with specified uuid, if it is contained in the array
     *         otherwise, (-(insertion point)-1).  The insertion point
     *         is defined as the point at which the new resume would be inserted
     *         into the array.
     *         Note that this guarantees that the return value will be >= 0 if
     *         and only if the key is found.
     */
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}