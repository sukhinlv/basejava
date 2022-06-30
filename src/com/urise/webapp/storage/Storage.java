package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void delete(String uuid);

    Resume get(String uuid);

    Resume[] getAll();

    void save(Resume r);

    int size();

    void update(Resume resume);
}