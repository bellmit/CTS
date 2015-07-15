package selfStudy;


public class FindMaxSumRange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int a[]={-1,0,0,8,5};
		int winSize=1;
		int start=0;
		int startrange=0;
		int endrange=0;
		int maxSum=a[0];
		int sum=a[0];
		for(int i=1;i<a.length;i++)
		{
			if(sum>=0)
			sum=sum+a[i];
			else
				sum=a[i];
			//System.out.println("Sum"+sum);
			//System.out.println(winSize+"sta"+startrange);
			if(sum>maxSum)
			{
				maxSum=sum;
				endrange=i;
			}
			if(sum<0 || i==a.length-1)
			{
				i=startrange+winSize+1;
				if(i<a.length-1)
				{
					startrange=i;
					sum=a[i];
				}
				winSize=1;
			}
			else
			{
				winSize++;
			}
			
			
		}
		
		System.out.println("Maxsum"+maxSum);
		System.out.println("Strt"+startrange+"End"+endrange);
		
	}

}
