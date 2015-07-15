package selfStudy.hackerRank;

import java.util.Arrays;
import java.util.Scanner;

/*You are given two integer arrays, A and B, each containing N integers. The size of the array is <= 1000. You are free to permute the order of the elements in the arrays. 

Now for the real question - is there an arrangement of the arrays such that Ai+Bi>=K for all i where Ai denotes the ith element in the array A.

*/
/*2
3 10
2 1 3
7 8 9
4 5
1 2 2 1
3 3 3 4
*/

public class TwoArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int nooftest=sc.nextInt();
		int a[],b[];
		
		for(int i=0;i<nooftest;i++)
		{
			boolean isTwoArrEql=true;
			int one=Integer.parseInt(sc.next());
			int findSum=Integer.parseInt(sc.next());
			
			a=new int[one];
			b=new int[one];
			for (int j = 0; j < one; j++) {
				a[j]=Integer.parseInt(sc.next());
			}
			for (int j = 0; j < one; j++) {
				b[j]=Integer.parseInt(sc.next());
			}
			//System.out.println(Arrays.toString(a));
			Arrays.sort(a);
			//System.out.println(Arrays.toString(b));
			Arrays.sort(b);
			for(int j=0;j<one-1;j++)
			{
				if(a[j]+b[one-1]>=findSum)
				{
					isTwoArrEql=false;
					break;
				}one--;
			}
			if(isTwoArrEql)
				System.out.println("Yes");
			else
				System.out.println("No");
			//System.out.println(i);
		}
	}

}
