package com.java.core.threads;

import java.util.Random;

public class ProducerConsumer {
	    public static void main(String[] args) {
	        Drop drop = new Drop();
	        ProducerConsumer pc=new ProducerConsumer();
	        (new Thread(pc.new Producer(drop))).start();
	        (new Thread(pc.new Consumer(drop))).start();
	    }
	    
	    public class Consumer implements Runnable {
	        private Drop drop;

	        public Consumer(Drop drop) {
	            this.drop = drop;
	        }

	        public void run() {
	            Random random = new Random();
	            for (String message = drop.take();
	                 ! message.equals("DONE");
	                 message = drop.take()) {
	                System.out.format("MESSAGE RECEIVED: %s%n", message);
	                try {
	                    Thread.sleep(random.nextInt(5000));
	                } catch (InterruptedException e) {}
	            }
	        }
	    }
	    
	    public class Producer implements Runnable {
	        private Drop drop;

	        public Producer(Drop drop) {
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
	                drop.put(importantInfo[i]);
	                try {
	                    Thread.sleep(random.nextInt(5000));
	                } catch (InterruptedException e) {}
	            }
	            drop.put("DONE");
	        }
	    }
	}

