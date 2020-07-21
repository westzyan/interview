package com.zyan;

public class ProducerDemo {
    private static final String lock = "lock";
    private int count = 0;
    private static final int MAX_SIZE = 10;


    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(producerDemo.new Producer(), "生产者-" + i).start();
            new Thread(producerDemo.new Consumer(), "消费者-" + i).start();
        }
    }


    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock) {
                while (count == MAX_SIZE) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                System.out.println("生产者" + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                lock.notifyAll();
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    while (count == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费者 " + Thread.currentThread().getName() + " 总共有 " + count + " 个资源");
                    lock.notifyAll();
                }
            }
        }
    }
}
