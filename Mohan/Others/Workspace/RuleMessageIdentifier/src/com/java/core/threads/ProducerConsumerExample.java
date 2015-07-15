package com.java.core.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
    	 ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>() {
		};
    	 ProducerConsumerExample p=new ProducerConsumerExample();
        (new Thread(p.new Producer(queue))).start();
        (new Thread(p.new Consumer(queue))).start();
    }
   
    public class Producer implements Runnable {
        private ConcurrentLinkedQueue<String> drop;

        public Producer(ConcurrentLinkedQueue<String> drop) {
            this.drop = drop;
        }

        public void run() {
            String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
            };
            Random random = new Random();

            for (int i = 0;
                 i < importantInfo.length;
                 i++) {
                drop.add(importantInfo[i]);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {}
            }
            drop.add("DONE");
        }
    }
    public class Consumer implements Runnable {
        private ConcurrentLinkedQueue<String> drop;

        public Consumer(ConcurrentLinkedQueue<String> drop) {
            this.drop = drop;
        }

        public void run() {
            Random random = new Random();
            for (String message = drop.remove();
                 ! message.equals("DONE");
                 message = drop.remove()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {}
            }
        }
    }
}