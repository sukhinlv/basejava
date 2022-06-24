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
        Resume res = new Resume();
        res.uuid = r.uuid;
        storage[size] = res;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                Resume r = new Resume();
                r.uuid = storage[i].uuid;
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                int movelen = size - i - 1;
                if (movelen > 0) {
                    System.arraycopy(storage, i + 1, storage, i, movelen);
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
        Resume[] stor = new Resume[size];
        System.arraycopy(storage, 0, stor, 0, size);
        return stor;
    }

    int size() {
        return size;
    }
}
