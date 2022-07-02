public class MainString {
    public static void main(String[] args) {
        String[] strArray = {"1", "2", "3", "4", "5"};
        StringBuilder sb = new StringBuilder();
        for (String s : strArray) {
            sb.append(s).append(", ");
        }
        System.out.println(sb);

        String s1 = "abc";
        String s2 = "abc";
        String s3 = "ab";
        String s4 = "c";
        //noinspection StringEquality
        System.out.println(s1 == s2);
        //noinspection StringEquality
        System.out.println((s1 == (s3 + s4)));
        //noinspection StringEquality
        System.out.println((s1 == (s3 + s4).intern()));
    }
}
