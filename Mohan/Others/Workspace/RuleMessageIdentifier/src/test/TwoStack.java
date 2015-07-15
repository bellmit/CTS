package test;

import java.util.Arrays;

public class TwoStack {
	
	int top1=-1;
	int top2=-1;
	int elements[];
	
	TwoStack(int size)
	{
		elements=new int[size];
		top2=size;
	}

	TwoStack()
	{
		elements =new int[10];	
		top2=10;
	}
	
	public void push(int num,int stackno)
	{
		
		if(top1==elements.length-1 || top2==0 || top1+1>=top2)
		{
			System.err.println("Stack full.Increasing size...");
			
		}
		else
		{
			if(stackno==1)
			{
				elements[++top1]=num;
			}
			else if(stackno==2)
			{
				elements[--top2]=num;
			}
			else
			{
				System.out.println("Invalid Stack");
			}
				
		}
		System.out.println("stack pos"+top1+"<==>"+top2);
	}
	
	public int pop(int stackno)
	{
		int element=-1;
		if(stackno==1)
		{
			if(top1==-1)
			{
				System.out.println("Stack1 is empty");
				element= -1;
			}
			else
				element= elements[top1--];
		}
		else if(stackno==2)
		{
			if(top2==elements.length)
			{
				System.out.println("Stack2 is empty");
				element= -1;
			}
			else
				element= elements[top2++];
		}
		else
		{
			System.out.println("Invalid Stack");
		}
		
		return element;
	}
	
	public int size(int stackno)
	{
		int size=0;
		if(stackno==1)
			size= top1+1;
		else if(stackno==2)
			size= elements.length-top2;
		return size;
	}
}
