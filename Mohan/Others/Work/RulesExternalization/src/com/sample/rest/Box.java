package com.sample.rest;

public class Box<T> {

	  private T t;

	  public void add(T t) {
	    this.t = t;
	  }

	  public T get() {
	    return t;
	  }

	  public static void main(String[] args) {
	     Box<Integer> integerBox = new Box<Integer>();
	     Box<String> stringBox = new Box<String>();
	     Box<Integer> integerBox1 = new Box<Integer>();
	    
	     integerBox.add(new Integer(10));
	     integerBox1.add(new Integer(10));
	     Integer i1 = (Integer)integerBox.get();
	     Integer i2 = (Integer)integerBox1.get();
	    // int i = i1.intValue()+i2.intValue();
	     int i=integerBox.get();
	     System.out.printf("addition23423423423",i);
	     stringBox.add(new String("Hello World"));
	     
	     

	     //System.out.printf("Integer Value :%d\n\n", integerBox.get());
	    // System.out.printf("String Value :%s\n", stringBox.get());
	  }
	}
