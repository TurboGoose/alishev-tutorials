package multithreading;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadRunner("1", 10));
        Thread t2 = new Thread(new ThreadRunner("2", 2));
        t1.setPriority(10);
        t2.setPriority(2);
        t1.wait();

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

class ThreadRunner implements Runnable {
    private String name;
    private int iterations;

    public ThreadRunner(String name, int iterations) {
        this.name = name;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        System.out.printf("MyThread %s started%n", name);
        for (int i = 0; i < iterations; i++) {
            System.out.printf("MyThread %s: %d%n", name, i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("MyThread %s finished%n", name);
    }
}

class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.printf("MyThread %s is here: %d%n", name, i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
