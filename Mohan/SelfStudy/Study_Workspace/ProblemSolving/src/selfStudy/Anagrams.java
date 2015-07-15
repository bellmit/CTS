package selfStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Anagrams {

	public static void main(String args[])
	{
		String s="mohan";
		String sortStr;
		char c[]=s.toCharArray();
		Hashtable<String, List<String>> hs=new Hashtable<String, List<String>>();
		Arrays.sort(c);
		sortStr=String.valueOf(c);
		System.out.println(String.valueOf(c));
		if(hs.get(sortStr)!=null)
		{
			hs.get(sortStr).add(s);
		}
		else
		{
			System.out.println("Not Found");
			List a=new ArrayList<String>();
			a.add(s);
			hs.put(sortStr,a);
		}
		System.out.println(hs.get(sortStr));
		
		
		
	}
	
}
