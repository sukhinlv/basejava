import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.model.Resume;
/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.uuid = "uuid1";
        Resume r2 = new Resume();
        r2.uuid = "uuid2";
        Resume r3 = new Resume();
        r3.uuid = "uuid3";

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.uuid));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        System.out.print("\nTry to get \"John\": ");
        System.out.println(ARRAY_STORAGE.get("John") != null ? "John found" : "John not found");
        System.out.print("Try to get r2: ");
        System.out.println(ARRAY_STORAGE.get(r2.uuid) != null ? "r2 found" : "r2 not found");

        Resume ur = new Resume();
        ur.uuid = "Test resume";
        System.out.print("\nTry to update " + ur.uuid);
        System.out.println(ARRAY_STORAGE.update(ur) ? " - success" : " - failed");
        System.out.print("Try to delete " + ur.uuid);
        System.out.println(ARRAY_STORAGE.delete(ur.uuid) ? " - success" : " - failed");
        ur.uuid = "uuid2";
        System.out.print("Try to update " + ur.uuid);
        System.out.println(ARRAY_STORAGE.update(ur) ? " - success" : " - failed");

        printAll();
        System.out.println(ARRAY_STORAGE.delete(r1.uuid) ? "\nr1 successfully deleted" : "delete r1 failed");
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        for (int i = 0; i <= ArrayStorage.STORAGE_CAPACITY; i++) {
            Resume r = new Resume();
            r.uuid = String.valueOf(i).intern();
            if (!ARRAY_STORAGE.save(r)) {
                System.out.println("\nStorage overflow when trying to add element " + (i + 1));
            }
        }
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
