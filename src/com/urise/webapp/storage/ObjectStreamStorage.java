package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;

public class ObjectStreamStorage extends AbstractPathStorage {
    private final FileReadWriteStrategy readWriteStrategyStorage;

    protected ObjectStreamStorage(Path directory, FileReadWriteStrategy readWriteStrategyStorage) {
        super(directory);
        this.readWriteStrategyStorage = Objects.requireNonNull(readWriteStrategyStorage, "Read/write " +
                "strategy class must not be null");
    }


    @Override
    protected Resume doRead(InputStream inputStream) throws IOException {
        return readWriteStrategyStorage.doRead(inputStream);
    }

    @Override
    protected void doWrite(Resume r, OutputStream outputStream) throws IOException {
        readWriteStrategyStorage.doWrite(r, outputStream);
    }
}