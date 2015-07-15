package com.cognizant.tool.main;

public class TestThread {
	
	public static void main(String args[])
	{
		Thread t=new Thread();
		t.run();
		ExtendsThread exthread=new ExtendsThread();
		exthread.start();
		
		for(int i=0;i<5;i++)
		{
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


}
