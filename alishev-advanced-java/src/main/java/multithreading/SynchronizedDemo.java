package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedDemo {
    Random random = new Random();

    final Object lock1 = new Object();
    final Object lock2 = new Object();

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    void addToList1() {
        synchronized (lock1) {
            addToList(list1);
        }
    }

    void addToList2() {
        synchronized (lock2) {
            addToList(list2);
        }
    }

    void addToList(List<Integer> list) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        list.add(random.nextInt(100));
    }

    void work() {
        for (int i = 0; i < 1000; i++) {
            addToList1();
            addToList2();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();

        Thread thread1 = new Thread(demo::work);
        Thread thread2 = new Thread(demo::work);

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long end = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (end - start));
        System.out.println("List1: " + demo.list1.size());
        System.out.println("List2: " + demo.list2.size());
    }
}
