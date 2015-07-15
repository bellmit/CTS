package selfStudy;

public class StringtoIntConversion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		String number="15650";
		int num;
		num=convertStringtoNumber(number);
		
		
	}
	
	public static int convertStringtoNumber(String snum)
	{
		char[] schar=new char[snum.length()];
		schar=snum.toCharArray();
		int num=0;
		int pow=0;
		for(int i=schar.length-1;i>=0;i--)
		{
			
			num=(int) (num+Character.getNumericValue(schar[i])*Math.pow(10, pow));
			pow++;
		}
		System.out.println("NUMBER"+num);
		return num;
	}

}
