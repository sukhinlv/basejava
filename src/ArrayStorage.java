/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int STORAGE_CAPACITY = 10_000;
    private Resume[] storage = new Resume[STORAGE_CAPACITY];
    private int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size == STORAGE_CAPACITY) {
            return;
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                int len = size - i - 1;
                if (len > 0) {
                    System.arraycopy(storage, i + 1, storage, i, len);
                }
                size--;
                storage[size] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    int size() {
        return size;
    }
}
