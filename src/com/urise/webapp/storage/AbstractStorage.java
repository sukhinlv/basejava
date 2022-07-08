package com.urise.webapp.storage;

import com.urise.webapp.exception.FoundStorageException;
import com.urise.webapp.exception.NotFoundStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    public static final Comparator<Resume> COMPARE_FULL_NAME =
            Comparator.comparing(Resume::getFullName, String::compareTo)
                    .thenComparing(Resume::getUuid, String::compareTo);

    public final Resume get(String uuid) {
        return doGet(findExistSearchKey(uuid));
    }

    public final void delete(String uuid) {
        doDelete(findExistSearchKey(uuid));
    }

    public final void save(Resume r) {
        doSave(r, findNotExistSearchKey(r.getUuid()));
    }

    public final void update(Resume r) {
        doUpdate(r, findExistSearchKey(r.getUuid()));
    }

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    // возвращает индекс в массиве или еще какой другой индекс
    protected abstract Object getSearchKey(String key);

    protected abstract boolean isExist(Object searchKey);

    private Object findExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotFoundStorageException(uuid);
        }
        return searchKey;
    }

    private Object findNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new FoundStorageException(uuid);
        }
        return searchKey;
    }
}