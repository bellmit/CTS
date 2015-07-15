package com.ds.binarySearch;

public class FindOccurencesOfKey {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={0,2,4,5,5,6,6,6,6,7,8,9};
		
		FindOccurencesOfKey fc=new FindOccurencesOfKey();
		System.out.println("Count:"+fc.countOccurence(arr, 6));
	}
	
	int countOccurence(int [] arr,int key)
	{
		int left=getLeftOccurence(arr, arr.length, 0, key);
		int right=getRightOccurence(arr, arr.length, 0, key);
		return ((arr[left]==key && arr[right]==key)?(right-left+1):0);
	}
	
	int getRightOccurence(int[] arr,int endPos,int startPos,int key)
	{
			
		while(endPos-startPos>1)
		{
			int mid=startPos+(endPos-startPos)/2;
			if(arr[mid]<=key)
				startPos=mid;
			else
				endPos=mid;
		}
		System.out.println("left Pos"+startPos);
		
			return startPos;
		
	}
	
	int getLeftOccurence(int[] arr,int endPos,int startPos,int key)
	{
			
		while(endPos-startPos>1)
		{
			int mid=startPos+(endPos-startPos)/2;
			if(arr[mid]>=key)
				endPos=mid;
			else
				startPos=mid;
		}
		System.out.println("Right Pos"+endPos);
		
			return endPos;
		
	}

}
