package com.urise.webapp.storage;

import com.urise.webapp.storage.filestrategy.ObjectStreamStorage;

class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage("storage", new ObjectStreamStorage()));
    }
}