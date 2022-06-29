import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.model.Resume;
/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.print("Try to get r2: ");
        System.out.println(ARRAY_STORAGE.get(r2.getUuid()) != null ? "r2 found\n" : "r2 not found\n");

        System.out.println("Try to save another uuid3");
        ARRAY_STORAGE.save(r3);

        Resume ur = new Resume();
        ur.setUuid("Test resume");
        System.out.println("\nTry to update " + ur.getUuid());
        ARRAY_STORAGE.update(ur);
        System.out.println("\nTry to delete " + ur.getUuid());
        ARRAY_STORAGE.delete(ur.getUuid());
        ur.setUuid("uuid2");
        System.out.println("\nTry to update " + ur.getUuid());
        ARRAY_STORAGE.update(ur);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        for (int i = 1; i <= ArrayStorage.STORAGE_CAPACITY + 1; i++) {
            Resume r = new Resume();
            r.setUuid(String.valueOf(i).intern());
            ARRAY_STORAGE.save(r);
        }
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}