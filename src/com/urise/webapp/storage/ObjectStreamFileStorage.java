package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamFileStorage extends AbstractFileStorage {
    protected ObjectStreamFileStorage(File directory) {
        super(directory);
    }

    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
        try (var oos = new ObjectInputStream(inputStream)) {
            return (Resume) oos.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Wrong file format (ClassNotFoundException)", "", e);
        }
    }

    @Override
    protected void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try (var oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(r);
        }
    }
}