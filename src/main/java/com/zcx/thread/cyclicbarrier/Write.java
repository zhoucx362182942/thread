package com.zcx.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Write {
    public static void main(String[] args) {
        int i = 8;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(i);
        for (int j = 0; j < i; j++) {
            Thread t = new Thread(new MyThread(cyclicBarrier), String.valueOf(j + 1));
            t.start();
        }
        System.out.println("main执行完毕");
    }
}

class MyThread implements Runnable{
    private CyclicBarrier cyclicBarrier;

    public MyThread(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {

        int i = new Random().nextInt(6) +1;
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕。。。");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("所有子线程均已执行完毕");
    }
}
