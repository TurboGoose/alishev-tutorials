package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    int counter = 0;
    final Lock lock = new ReentrantLock();

    void increment() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    synchronized void synchronizedIncrement() {
        increment();
    }

    void lockedIncrement() {
        lock.lock();
        increment();
        lock.unlock();
    }


    static void run() throws InterruptedException {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        Thread t1 = new Thread(demo::lockedIncrement);
        Thread t2 = new Thread(demo::synchronizedIncrement);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(demo.counter);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }
}
