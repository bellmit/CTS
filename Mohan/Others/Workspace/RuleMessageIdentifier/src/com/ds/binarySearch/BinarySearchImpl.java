package com.ds.binarySearch;

public class BinarySearchImpl {

	
	public static int binarySearch_Recursive(int[] arr,int endPos,int startPos,int key)
	{
		System.out.println("range="+endPos+" length"+startPos);
		if(startPos>=endPos)
			return -1;
		int mid=startPos+((endPos-startPos)/2);
		if(arr[mid]== key)
		{
			return mid;
		}
		else if(key < arr[mid])
		{
			return binarySearch_Recursive(arr, mid-1, startPos, key);
		}
		else
		{
			return binarySearch_Recursive(arr, endPos, mid+1, key);
		}
	}
	
	public static int binarySearch_Iterative(int[] arr,int endPos,int startPos,int key)
	{
		
		while(endPos-startPos>1)
		{
			int mid=startPos+(endPos-startPos)/2;
			if(arr[mid]<=key)
				startPos=mid;
			else
				endPos=mid;
		}
		if(arr[startPos]==key)
			return startPos;
		
			return -1;
	}
	
	public static int binarySearch_Floor(int[] arr,int endPos,int startPos,int key)
	{
		
		while(endPos-startPos>1)
		{
			int mid=startPos+(endPos-startPos)/2;
			if(arr[mid]<=key)
				startPos=mid;
			else
				endPos=mid;
		}
		if(arr[startPos]==key)
			return startPos;
		
			return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={2,3,4,5,7};
		int n=2;
		switch(n)
		{
			case 1:
				//Recursive
				int pos=binarySearch_Recursive(a, a.length, 0, 7);
				System.out.println("element="+a[pos]);
				break;
				
			case 2:
				//Recursive
				pos=binarySearch_Recursive(a, a.length, 0, 2);
				System.out.println("element="+a[pos]);
				break;
				
			case 3:
				//Recursive
				pos=binarySearch_Recursive(a, a.length, 0, 4);
				System.out.println("element="+a[pos]);
				break;
		}
		//System.out.println("BinarY Search=>"+a[binarySearch_Recursive(a, a.length, 0, 9)]);
		System.out.println("Binary Search=>"+a[binarySearch_Iterative(a, a.length, 0, 7)]);
	}

}
