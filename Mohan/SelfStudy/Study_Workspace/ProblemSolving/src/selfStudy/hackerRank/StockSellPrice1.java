package selfStudy.hackerRank;

import java.util.Scanner;
import java.util.Stack;

public class StockSellPrice1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int noofTest=sc.nextInt();
		
		for(int i=0;i<noofTest;i++)
		{
			int prnum=0;
			int startindex=0;
			int noofDays=sc.nextInt();
			int a[]=new int[noofDays];
			int sum=0;
			prnum=a[0];
			int max=0;
			int maxin=0;
			Stack<Integer> stack=new Stack<Integer>();
			for(int j=0;j<noofDays;j++)
			{
				a[j]=sc.nextInt();
			}
			
			for(int j=noofDays-1;j>0;j--)
			{
				if(stack.isEmpty())
					stack.push(j);
				else if(a[j]>=a[stack.peek()])
				{
					stack.add(j);
				}
					
			}
			while(!stack.isEmpty())
			{
				int j=stack.pop();
				//System.out.println("sum"+sum+"si"+startindex+"jj"+j);
				sum=sum+findstock(a, startindex, j);
				startindex=j+1;
			}			
			System.out.println(sum);
		}
	}

	private static int findstock(int[] a, int startindex, int j) {
		// TODO Auto-generated method stub
		int sum=0;
		
		for(int i=startindex;i<=j;i++)
		{
			sum=sum+(a[j]-a[i]);
		}
		System.out.println("sum"+sum+"si"+startindex+"jj"+j);
		return sum>0?sum:0;
	}

}
