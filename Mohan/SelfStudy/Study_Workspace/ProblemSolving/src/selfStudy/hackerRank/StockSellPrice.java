package selfStudy.hackerRank;

import java.util.Scanner;

public class StockSellPrice {

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
			a[0]=sc.nextInt();
			prnum=a[0];
			for(int j=1;j<noofDays;)
			{
				a[j]=sc.nextInt();
				
				if(prnum>a[j])
				{
					sum=sum+findstock(a,startindex,j-1);
					startindex=j;
				}
				prnum=a[j];
				j++;
			}
			sum=sum+findstock(a, startindex, a.length-1);
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
		return sum;
	}

}
