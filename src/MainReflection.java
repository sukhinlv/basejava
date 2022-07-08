import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Resume r = new Resume("Look to me", "Look to me");

        @SuppressWarnings("rawtypes")
        Class someClass = r.getClass();
        System.out.println(someClass);
        for (Method m : someClass.getDeclaredMethods()) {
            System.out.println(m);
        }

        Field field = someClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "Don`t look at me");
        System.out.println(field.get(r));

        field.set(r, "Cool");
        @SuppressWarnings("unchecked")
        Method someMethod = someClass.getDeclaredMethod("toString");
        System.out.println(someMethod.invoke(r));
    }
}
