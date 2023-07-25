package annotations;

import java.util.LinkedList;
import java.util.List;

public class AnnotationsDemo {
    public static void main(String[] args) {
        //noinspection deprecation
        new A().old();

        @SuppressWarnings("rawtypes")
        List list = new LinkedList();
    }
}

class A {
    void foo() {
        System.out.println("foo");
    }

    @Deprecated
    void old() {
        System.out.println("I'm deprecated");
    }
}

class B extends A {
    @Override
    void foo() {
        System.out.println("boo");
    }
}
