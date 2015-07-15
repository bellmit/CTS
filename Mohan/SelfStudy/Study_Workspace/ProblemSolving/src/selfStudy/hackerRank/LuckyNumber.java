package selfStudy.hackerRank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LuckyNumber {
    static int luckyNumbers(long a,long b) {
      /* Complete this function */
        int[] sqnumbers={0,1,4,9,16,25,36,49,64,81};
        ArrayList<Long> sum1=new ArrayList<Long>();
        ArrayList<Long> sqsum1=new ArrayList<Long>();
		int count=0;
        long sum=0;
        long sqrsum=0;
        int i=0;
        long starttime=new Date().getTime();
        long endTime;
        for(long j=a;j<=b;j++)
        {
            a=j;
            while(a>0)
            {
                sqrsum=sqrsum+sqnumbers[(int) (a%10)];
                sum=sum+a%10;
                a=a/10;
            }
            //System.out.println(sum+">>"+isPrime(sum));
           // System.out.println(sqrsum+">>"+isPrime(sqrsum));
            if(isPrime(sum) && isPrime(sqrsum))
                count++;
            sum1.add(sum);
            sqsum1.add(sqrsum);
            i++;
            sum=0;
            sqrsum=0;
            
        }
        System.out.println("Sum"+sum1.toArray());
        System.out.println("SSum"+sqsum1.toArray());
        endTime=new Date().getTime();
        System.out.println(endTime-starttime);
        return count;
    }

    public static boolean isPrime(long num)
    {
       if(num==1)
			 return false;
		 if(num==2)
			 return true;
		if(num%2==0)return false;
		 for (long i = 3; i <= Math.sqrt(num); i=i+2)
		 {
	            if (num %i  == 0) // you don't need the first condition
	            {
	                return false;
	            }
	     }
		return true;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long res;
        
        int _a_size = Integer.parseInt(in.nextLine());
        long _a,_b;
        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
            String next = in.nextLine();
            String[] next_split = next.split(" ");
            _a=Long.parseLong(next_split[0]);
            _b=Long.parseLong(next_split[1]);
           res = luckyNumbers(_a,_b);
           System.out.println(res);
        }
    }
}