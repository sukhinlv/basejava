import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        printTabbedFileNames(".");
    }

    private static void printTabbedFileNames(String dirName) {
        printTabbedFileNames(dirName, 0);
    }

    private static void printTabbedFileNames(String dirName, int dirLevel) {
        Objects.requireNonNull(dirName, "Directory name must not be null");
        var directory = new File(dirName);
        if (!directory.isDirectory()) {
            System.out.println(dirName + " is not directory");
            return;
        }
        var list = directory.listFiles();
        Objects.requireNonNull(list, directory.getName() + " does not denote a directory, or an I/O error occurs");
        if (list.length == 0) {
            System.out.println("Directory " + dirName + " is empty");
            return;
        }
        for (var file : list) {
            if (file.isDirectory()) {
                System.out.println(getTabs(dirLevel) + file.getName().toUpperCase());
                printTabbedFileNames(file.getAbsolutePath(), dirLevel + 1);
            } else {
                System.out.println(getTabs(dirLevel) + file.getName());
            }
        }
    }

    private static String getTabs(int tabsCount) {
        return "\t".repeat(tabsCount);
    }
}