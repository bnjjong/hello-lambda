package kr.re.kitri.hello.lambdademo;

public class LambdaDemoWithThread {

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyRunnable());

        // intellij 에서 자동 변경이 가능 함.
        Thread yourThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inner class thread running...");
            }
        });

        Thread lambdaThread = new Thread(() -> System.out.println("lambda thread running..."));


        myThread.run();
        yourThread.run();
        lambdaThread.run();
        System.out.println("main thread running...");
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("first thread running...");
        }
    }
}
