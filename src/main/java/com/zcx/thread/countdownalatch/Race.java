package com.zcx.thread.countdownalatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Race {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                // 内部类，实现Runnable接口
                public void run() {
                    // 随机睡眠1到3秒
                    int i = new Random().nextInt(3) + 1;
                    try {
                        Thread.sleep(1000 * i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "已结束");
                    // 计数器-1
                    countDownLatch.countDown();
                }
            }, String.valueOf(i + 1));
            t.start();
        }

        try {
            // 当计数器为0是，再继续执行主线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=========");
        System.out.println("所有子线程均已结束");

    }
}

