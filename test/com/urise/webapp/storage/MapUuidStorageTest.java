package com.urise.webapp.storage;

public class    MapUuidStorageTest extends AbstractStorageTest {

//    TODO необходимо проверить что "если fullName разных людей совпадает, то сортируйте дополнительно по uuid"
    public MapUuidStorageTest() {
        super(new MapUuidStorage());
    }
}