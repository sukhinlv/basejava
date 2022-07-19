package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage<KT, DT> {

    void clear();

    void delete(KT uuid);

    Resume get(KT uuid);

    List<DT> getAllSorted();

    void save(DT r);

    int size();

    void update(DT r);
}