package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream outputStream) throws IOException;

    protected AbstractPathStorage(Path directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " must be readable/writable");
        }
        this.directory = directory;
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
            doWrite(r, new BufferedOutputStream(Files.newOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing to file ", file.getFileName().toString(), e);
        }
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
        return Path.of(directory.toString(), uuid);
    }

    @Override
    protected List<Resume> getStorageAsList() {
        try (var listOfStorageFiles =  Files.list(directory)){
            return new ArrayList<>(listOfStorageFiles.map(this::doGet).toList());
        } catch (IOException e) {
            throw new StorageException("I/O error occurs when opening the storage directory",
                    directory.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path file) {
        return Files.exists(file);
    }

    @Override
    public void clear() {
        try (var listOfStorageFiles =  Files.list(directory)){
            listOfStorageFiles.forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("I/O error occurs when opening the storage directory",
                    directory.getFileName().toString(), e);
        }
    }

    @Override
    public int size() {
        try (var listOfStorageFiles =  Files.list(directory)){
            return (int) listOfStorageFiles.count();
        } catch (IOException e) {
            throw new StorageException("IO error occurs when trying to determine storage size",
                    directory.getFileName().toString(), e);
        }
    }

    public Path getDirectory() {
        return directory;
    }
}