package selfStudy;

import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

/*amazon-interview-questions
0
of 0 votes
15
Answers

Write code for finding first duplicate element in given array:
[4,3,1,2,5,9,5,4] */


public class DuplicateFind {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={4,3,1,2,5,9,5,4};
		fistDuplicateElement(a);
		System.out.println(4^5^4);
		
		
	}
	
	public static int fistDuplicate(int[] a)
	{
		
		
		return 0;
	}
	
	
	public static int fistDuplicateElement(int[] a)
	{
		HashSet<Integer> hs=new HashSet<Integer>(a.length);
		for(int i=0;i<a.length;i++)
		{
			if(hs.contains(a[i]))
			{
				System.out.println("First duplicate"+a[i]);
				return a[i];
				
			}
			else
				hs.add(a[i]);
		}
		return 0;
	}
}
