package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected abstract Resume doRead(InputStream inputStream) throws IOException;

    protected abstract void doWrite(Resume r, OutputStream outputStream) throws IOException;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " must be readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error reading file ", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Can not delete file", file.getName());
        }
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing to file ", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing to file ", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected List<Resume> getStorageAsList() {
        var result = new ArrayList<Resume>();
        var fileList = directory.listFiles(File::isFile);
        if (fileList == null) {
            throw new StorageException(directory.getAbsolutePath() + " does not denote a directory, or an I/O error occurs", "");
        }
        for (var file : fileList) {
            result.add(doGet(file));
        }
        return result;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        var fileList = directory.listFiles(File::isFile);
        if (fileList == null) {
            throw new StorageException(directory.getAbsolutePath() + " does not denote a directory, or an I/O error occurs", "");
        }
        for (var file : fileList) {
            if (!file.delete()) {
                throw new StorageException("Can not delete file", file.getName());
            }
        }
    }

    @Override
    public int size() {
        var files = directory.listFiles(File::isFile);
        if (files == null) {
            throw new StorageException(directory.getAbsolutePath() + " does not denote a directory, or an I/O error occurs", "");
        } else {
            return files.length;
        }
    }
}