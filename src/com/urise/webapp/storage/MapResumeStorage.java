package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapResumeStorage extends AbstractStorage {
    private final HashMap<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        // TODO вот тут можно проигнорировать (??) searchKey и в качестве ключа использовать r.getUuid()
        storage.put((String) searchKey, r);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected String getSearchKey(String uuid) {
        /*
            на входе получаем fullName
            получить значения мапы getAllSorted
            найти в ней через binarySearch нужный нам объект
            return Arrays.binarySearch(storage, 0, size, new Resume(uuid, ""));
            возвращаем
                объект?
                uuid объекта?
        */
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        ArrayList<Resume> values = (ArrayList<Resume>) storage.values();
        values.sort(COMPARE_FULL_NAME);
        return values;
    }

    @Override
    public int size() {
        return storage.size();
    }
}