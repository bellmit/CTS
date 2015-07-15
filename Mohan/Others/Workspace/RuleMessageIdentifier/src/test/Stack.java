package test;

import java.util.Arrays;

public class Stack {
	
	int top=-1;
	int elements[];
	
	Stack(int size)
	{
		elements=new int[size];
	}

	Stack()
	{
		elements =new int[10];	
	}
	
	public void push(int num)
	{
		if(top==elements.length-1)
		{
			System.err.println("Stack full.Increasing size...");
			elements = Arrays.copyOf(elements, top*2);
		}
		
		elements[++top]=num;
	}
	
	public int pop()
	{
		if(top==-1)
		{
			System.out.println("Stack is empty");
			return -1;
		}
		return elements[top--];
	}
	
	public int size()
	{
		return top+1;
	}
}
