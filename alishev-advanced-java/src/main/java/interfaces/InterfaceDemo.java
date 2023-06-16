package interfaces;

public class InterfaceDemo {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run(new ExecutableImpl());
        runner.run(new Executable() {
            @Override
            public void execute() {
                System.out.println("Hello from anonymous class");
            }
        });
        runner.run(() -> System.out.println("Hello from lambda"));

        Repeatable repeatable = String::repeat;
        String str = "beb";
        System.out.println(repeatable.repeat(str, 5));

    }
}

interface Repeatable {
    String repeat(String str, int times);
}

interface Executable {
    void execute();
}

class ExecutableImpl implements Executable {

    @Override
    public void execute() {
        System.out.println("Hello from ExecutableImpl");
    }
}

class Runner {
    void run(Executable executable) {
        executable.execute();
    }
}