package com.ds.binarySearch;

public class MinimumInRotatedArray {

	public static void main(String args[])
	{
		int arr[] ={5,6,7,8,4};
		System.out.println("pos:"+binarySearchIndexofMin(arr, 0, arr.length-1));
	}
	
	
	static int binarySearchIndexofMin(int[] arr,int startPos,int endPos)
	{
		int m=0;
		if(arr[startPos]<= arr[endPos])
			return startPos;
		
		while(startPos<=endPos)
		{
			if(startPos== endPos)
				return startPos+1;
			m=startPos+(endPos-startPos)/2;
			if(arr[startPos] <arr[m])
				startPos=m;
			else
				endPos=m;
		}
		return -1;
		
	}
}
