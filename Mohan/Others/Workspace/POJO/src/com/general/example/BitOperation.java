package com.general.example;

public class BitOperation {

	
	/**
     * All possible chars for representing a number as a String
     */
    final static char[] digits = {
	'0' , '1' , '2' , '3' , '4' , '5' ,
	'6' , '7' , '8' , '9' , 'a' , 'b' ,
	'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
	'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
	'o' , 'p' , 'q' , 'r' , 's' , 't' ,
	'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

	
	public static void main(String args[])
	{
		int A=1;
		System.out.println("A::>"+(A |= 1 << 4));
		byte b=(byte) A;
		
		System.out.println(b|1);
		Integer.toBinaryString(A);
		int i=60;
		while(i>0)
		{
			System.out.println("shift"+(i>>>=2));
		}
		//toUnsignedString(A,1);
		System.out.println(Integer.MAX_VALUE);
	}
	
	
	private static String toUnsignedString(int i, int shift) {
		char[] buf = new char[32];
		int charPos = 32;
		int radix = 1 << shift;
		int mask = radix - 1;
		System.out.println("mask"+mask);
		do {
		    buf[--charPos] = digits[i & mask];
		    i >>>= shift;
		System.out.println("i"+i);
		} while (i != 0);
		System.out.println("Buf"+String.valueOf(buf));
		return new String(buf, charPos, (32 - charPos));
	    }
}
