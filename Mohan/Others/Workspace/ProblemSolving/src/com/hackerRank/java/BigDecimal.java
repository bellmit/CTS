package com.hackerRank.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BigDecimal {

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size=0;
		Scanner sc=new Scanner(System.in);
		size=sc.nextInt();
		java.math.BigDecimal[] bdArray=new java.math.BigDecimal[size];
		//List<java.math.BigDecimal> bdList=new ArrayList<java.math.BigDecimal>();
		for(int i=0;i<size;i++)
		{
			//bdList.add(new java.math.BigDecimal(sc.next()));
			bdArray[i]=sc.nextBigDecimal();		
			System.out.println(bdArray[i].precision());
		}
		BigDecimal b=new BigDecimal();
		
		Collections.sort(Arrays.asList(bdArray),b.new Compare());
		System.out.println(Arrays.toString(bdArray));
		sc.close();
	}
	class  Compare implements Comparator<java.math.BigDecimal>
	{

		@Override
		public int compare(java.math.BigDecimal o1,java.math.BigDecimal o2) {
			// TODO Auto-generated method stub
			if(o1.compareTo(o2)>0)
				return -1;
			else
				return 1;
		}
		
	}
	
}
