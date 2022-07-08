package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListStorageTest.class,
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        MapUuidStorageTest.class
})
public final class AllStorageTest {
}
// TODO разобраться, почему аннотации не работают
