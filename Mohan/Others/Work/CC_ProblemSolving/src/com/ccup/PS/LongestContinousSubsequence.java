package com.ccup.PS;

import java.util.Arrays;

public class LongestContinousSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input={2,-1,-5,6,4,-3,-1,0,2,-1,-1};
		int sum=3;
		findContinousSubsequence(input,sum);
	}
	
	private static void findContinousSubsequence(int[] input,int fSum)
	{
		int sqStart,sqEnd;
		int maxWdwSize=0;
		int csqStart=0,csqEnd=0;
		for(int i=0;i<input.length;i++)
		{
			sqStart=i;
			int sum=input[i];
			for(int j=i+1;j<input.length;j++)
			{
				sqEnd=j;
				sum=sum+input[j];
				if(sum==fSum)
				{
					if(maxWdwSize<(sqEnd-sqStart))
					{
						csqStart=sqStart;
						csqEnd=sqEnd;
						maxWdwSize=csqEnd-csqStart;
					}
				}
			}
			
			if(maxWdwSize+1 >(input.length-i))
			{
				break;
			}
		}
		
		System.out.println("Output>>>>>");
		System.out.println("Longest sequence"+(maxWdwSize+1));
		System.out.println("Start"+csqStart+" End"+csqEnd);
		System.out.println("Sequence"+Arrays.toString(Arrays.copyOfRange(input, csqStart, csqEnd+1)));
	}

}
