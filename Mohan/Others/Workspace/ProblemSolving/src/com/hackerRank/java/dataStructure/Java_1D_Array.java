package com.hackerRank.java.dataStructure;

import java.util.Scanner;

public class Java_1D_Array {

	public static void main(String args[])
	{
		//int[] _arr={0,1,0};
		Scanner sc=new Scanner(System.in);
		int noofTC=sc.nextInt();
		int m=0;
		int n=0;
		for(int j=0;j<noofTC;j++)
		{
			n=sc.nextInt();
			m=sc.nextInt();
			int[] _arr=new int[n];
			
			for (int k=0;k<n;k++) {
				_arr[k]=sc.nextInt();
							
			}
			int i=0;
		while(i<_arr.length-1)
		{
			if(_arr[i+1]==0)
			{
				i+=1;
			}
			else if(i+m>_arr.length || _arr[i+m]==0)
			{
				i+=m;
			}
			else if(i>0 && _arr[i-1]==0)
			{
				_arr[i]=9;
				i-=1;
				
			}
			else if(i<=0)
			{
				break;
			}
			else
			{
				i++;
			}
		}
		if(i>=_arr.length-1)
		{
			System.out.println("YES");
		}
		else
			System.out.println("NO");
	}
	}
}
