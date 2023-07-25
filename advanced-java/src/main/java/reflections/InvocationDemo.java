package reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class InvocationDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        try (Scanner sc = new Scanner(System.in)) {
            Class<?> class1 = Class.forName(sc.next());
            Class<?> class2 = Class.forName(sc.next());
            String methodName = sc.next();

            Object obj1 = class1.getDeclaredConstructor().newInstance();
            Method method = class1.getMethod(methodName, class2);
            Object obj2 = class2.getDeclaredConstructor(String.class).newInstance("String value");

            method.invoke(obj1, obj2);
            System.out.println(obj1);
            System.out.println(obj2);
        }
    }
}
