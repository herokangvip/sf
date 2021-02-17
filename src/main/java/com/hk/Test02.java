package com.hk;

import java.util.Random;
import java.util.concurrent.*;

public class Test02 {
    public static void main(String[] args) {
        //callbale();
        //consumer();
        testAbortStrategy();
    }

    private static void testAbortStrategy() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1));
        try {
            for (int i = 0; i < 3; i++) {
                int finalI = i;
                executor.execute(() -> System.out.println(finalI));
            }
        } catch (RejectedExecutionException e) {
            System.out.println("异常啦");
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("=====");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void callbale() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //return new Random().nextInt(5);
                return 1/0;
            }
        };

        FutureTask<Integer> future = new FutureTask<>(callable);
        new Thread(future).start();

        Integer integer = null;
        try {
            integer = future.get();
        } catch (InterruptedException e) {
            System.out.println("异常啦1");
        } catch (ExecutionException e) {
            System.out.println("异常啦2");
        }catch (Exception e){
            System.out.println("异常啦3");
        }

        System.out.println(integer);
    }

    private static void consumer() {
        SynchronousQueue<String> queue = new SynchronousQueue<String>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    int a = new Random().nextInt(3)+1;
                    try {
                        Thread.sleep(a*1000);
                        System.out.println("===生产");
                        queue.put("xx");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();


        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    int a = new Random().nextInt(3)+1;
                    try {
                        Thread.sleep(a*1000);
                        System.out.println("消费："+queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable2).start();
    }
}
