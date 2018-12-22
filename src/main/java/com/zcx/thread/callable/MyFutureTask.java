package com.zcx.thread.callable;

import java.util.concurrent.*;

public class MyFutureTask {
    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            MyThread02 myThread = new MyThread02();
            FutureTask<Integer> futureTask = new FutureTask<Integer>(myThread);
            executor.submit(futureTask);
            System.out.println(futureTask.get());
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread02 implements Callable<Integer> {
    public Integer call() throws Exception{
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            Thread.sleep(20);
            sum += i;
        }
        return sum;
    }
}
