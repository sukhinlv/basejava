package com.urise.webapp.exception;

public class FoundStorageException extends StorageException {

    public FoundStorageException(String uuid) {
        super("Resume " + uuid + " already in storage!", uuid);
    }

}
