package com.hk;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;

public class Test02 {
    public static void main(String[] args) {
        /*Callable<Integer> callable = new Callable<Integer>() {
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
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("异常啦");
        }

        System.out.println(integer);*/


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
