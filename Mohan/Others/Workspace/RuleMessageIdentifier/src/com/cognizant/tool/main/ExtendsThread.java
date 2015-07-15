package com.cognizant.tool.main;

public class ExtendsThread extends Thread {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("ExtendsThread run method");
		for(int i=50;i<60;i++)
		{
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.run();
	}

}
