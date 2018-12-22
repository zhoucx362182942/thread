package com.zcx.thread.callable;

import java.util.concurrent.*;

public class MyFuture {
    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            MyThread01 thread = new MyThread01();

            // 该call方法自动在一个线程上执行，并且会返回执行结果Future对象
            Future<Integer> future = executor.submit(thread);

            // 关闭执行服务对象
            executor.shutdown();
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread01 implements Callable<Integer> {
    public Integer call() throws Exception{
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(20);
            sum += i;
        }
        return sum;
    }
}
