package reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Person> personClass = Person.class;
//        Class<?> personClass1 = Class.forName("reflections.Person");
//        Class<?> personClass2 = new Person().getClass();

        for (Method method : personClass.getMethods()) {
            System.out.println(method.getModifiers() + "; " +
                    method.getReturnType() + "; " +
                    method.getName() + "; " +
                    Arrays.toString(method.getParameterTypes()));
        }

        System.out.println();

        for (Field field : personClass.getDeclaredFields()) {
            System.out.println(field.getType() + "; " + field.getName());
        }

        System.out.println();

        for (Annotation annotation : personClass.getAnnotations()) {
            System.out.println(annotation.annotationType());
        }
    }
}
