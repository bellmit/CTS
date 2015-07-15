package selfStudy;

public class Reverse {

	public static void main(String args[])
	{
		String str="THIS is a TEST";
		StringBuilder st=new StringBuilder();
		String reverse;
		//System.out.println(st.append(str).reverse());
		reverse=reverse(str);
		System.out.println(reverse(str));
		int n=str.length();
		for(int i=0;i<n;i++)
		{
			if(reverse.charAt(i)==' ')
			{
				//System.out.println(reverse(reverse.substring(st.length(),i)));
				st.append(reverse(reverse.substring(st.length(),i))+" ");
			}
		}
		System.out.println(st.append(reverse(reverse.substring(st.length()))));
		
	}
	public static String reverse(String toreverse)
	{
		char ch[]=toreverse.toCharArray();
		int n=ch.length-1;
		for(int i=0;i<n;i++)
		{
			char t=ch[i];
			ch[i]=ch[n];
			ch[n]=t;
			n--;
		}
		return String.valueOf(ch);
	}
}
