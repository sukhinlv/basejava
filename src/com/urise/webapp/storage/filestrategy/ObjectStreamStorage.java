package com.urise.webapp.storage.filestrategy;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamStorage implements FileReadWriteStrategy {

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (var oos = new ObjectInputStream(inputStream)) {
            return (Resume) oos.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Wrong file format (ClassNotFoundException)", "", e);
        }
    }

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        try (var oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(r);
        }
    }
}