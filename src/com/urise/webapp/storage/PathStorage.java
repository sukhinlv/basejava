package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.filestrategy.FileReadWriteStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private final FileReadWriteStrategy readWriteStrategyStorage;

    protected PathStorage(String dir, FileReadWriteStrategy readWriteStrategyStorage) {
        this.readWriteStrategyStorage = Objects.requireNonNull(readWriteStrategyStorage, "Read/write " +
                "strategy class must not be null");
        Objects.requireNonNull(dir, "Directory must not be null");
        var directory = Paths.get(dir);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " must be readable/writable");
        }
        this.directory = directory;
    }

    private Resume doRead(InputStream inputStream) throws IOException {
        return readWriteStrategyStorage.doRead(inputStream);
    }

    private void doWrite(Resume r, OutputStream outputStream) throws IOException {
        readWriteStrategyStorage.doWrite(r, outputStream);
    }

    @Override
    protected Resume doGet(Path file) {
        try {
            return doRead(new BufferedInputStream(Files.newInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error reading file ", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path file) {
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new StorageException("Can not delete file", file.getFileName().toString(), e);
        }
    }

    @Override
    protected void doSave(Resume r, Path file) {
        try {
            Files.createFile(file);
        } catch (IOException e) {
            throw new StorageException("Error creating file ", file.getFileName().toString(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doUpdate(Resume r, Path file) {
        try {
            doWrite(r, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing to file ", file.getFileName().toString(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    protected List<Resume> getStorageAsList() {
        return new ArrayList<>(getListOfFilesInStorage().map(this::doGet).toList());
    }

    @Override
    public void clear() {
        getListOfFilesInStorage().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getListOfFilesInStorage().count();
    }

    private Stream<Path> getListOfFilesInStorage() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("I/O error occurs when opening the storage directory",
                    directory.getFileName().toString(), e);
        }
    }
}