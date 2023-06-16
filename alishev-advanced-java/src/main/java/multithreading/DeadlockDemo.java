package multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {
    Account acc1 = new Account();
    Account acc2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    void takeLocks(Lock lock1, Lock lock2) {
        while (true) {
            boolean lock1Taken = lock1.tryLock();
            boolean lock2Taken = lock2.tryLock();
            if (lock1Taken && lock2Taken) {
                return;
            }
            if (lock1Taken) {
                lock1.unlock();
            }
            if (lock2Taken) {
                lock2.unlock();
            }
        }
    }

    void transferForward() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            takeLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    void transferBackward() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            takeLocks(lock2, lock1);
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    void printAccountStates() {
        System.out.println("Account1: " + acc1.getBalance());
        System.out.println("Account2: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }

    public static void main(String[] args) throws InterruptedException {
        DeadlockDemo demo = new DeadlockDemo();
        Thread thread1 = new Thread(demo::transferForward);
        Thread thread2 = new Thread(demo::transferBackward);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        demo.printAccountStates();
    }
}

class Account {
    private int balance = 10000;

    public void credit(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account from, Account to, int amount) {
        from.credit(amount);
        to.deposit(amount);
    }
}