package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new LinkedList<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.set((Integer) searchKey, r);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int idx = 0;
        for (Resume r: storage) {
            if (r.getUuid().equals(uuid)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getStorageAsList() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}