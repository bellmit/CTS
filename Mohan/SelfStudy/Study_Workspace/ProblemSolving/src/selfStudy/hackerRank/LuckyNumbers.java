package selfStudy.hackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class LuckyNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int noofTest=sc.nextInt();
		long a,b;
		for(int i=0;i<noofTest;i++)
		{
			a=Long.parseLong(sc.next());
			b=Long.parseLong(sc.next());
			System.out.println(getCountofLuckyNumber(a,b));
		}
	}

	private static long getCountofLuckyNumber(long a, long b) {
		// TODO Auto-generated method stub
		long count=0;
		int sqrnos[]={0,1,4,9,16,25,36,49,64,81};
		int oddoccur[]={1,3,5,7,9,11,13,15,17,0};
		long startnum=a;
		long sum=0;
		long sqrsum=0;
		long sqrinc=0;
		ArrayList<Long> sum1 =new ArrayList<Long>(); 
        ArrayList<Long> sqsum1=new ArrayList<Long>();
        ArrayList<Long> sqrinc1=new ArrayList<Long>();
        int k=0;
        long starttime=new Date().getTime();
        long endTime;
		while(startnum>0)
		{
			sum=sum+startnum%10;
			int j=(int) (startnum%10);
			sqrsum=sqrsum+sqrnos[j];
			startnum=startnum/10;
		}
		sqrinc=oddoccur[(int)a%10];
		sum1.add(sum);
		sqsum1.add(sqrsum);
		sqrinc1.add(sqrinc);
		
		for(long i=a+1;i<=b;i++)
		{
			//System.out.println(i);
			if(isPrime(sum) && isPrime(sqrsum))
			{
				//System.out.println("isPrime"+sum+" "+sqrsum);
				count++;
			}
			//System.out.println("i>"+(i-1)+">Sum"+sum);
			//System.out.println("SqrSum"+sqrsum);
			if(i%10==0)
			{
				sum=0;
				sqrsum=0;
				startnum=i;
				while(startnum>0)
				{
					sum=sum+startnum%10;
					int j=(int) (startnum%10);
					sqrsum=sqrsum+sqrnos[j];
					startnum=startnum/10;
				}
				sqrinc=1;
			}
			else
			{
				sum=sum+1;
				
				sqrsum=sqrsum+sqrinc;
				sqrinc=sqrinc+2;
			}
			
			sum1.add(sum);
            sqsum1.add(sqrsum);
            sqrinc1.add(sqrinc);
			k++;
		}
		//System.out.println("Sum"+sum1);
      //  System.out.println("SSum"+sqsum1);
       // System.out.println("inc"+sqrinc1);
		endTime=new Date().getTime();
        System.out.println(endTime-starttime);
		return count;
	}

	private static boolean isPrime(long sqrsum) {
		// TODO Auto-generated method stub
		 if(sqrsum==1)
			 return false;
		 if(sqrsum==2)
			 return true;
		if(sqrsum%2==0)return false;
		 for (long i = 3; i <= Math.sqrt(sqrsum); i=i+2)
		 {
	            if (sqrsum %i  == 0) // you don't need the first condition
	            {
	                return false;
	            }
	     }
	      
	       
		
		return true;
	}
	
}
