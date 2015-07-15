package test;

public class DS_Stack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Stack st=new Stack(5);
		for(int i=0;i<=5;i++)
			st.push(i);
		System.out.println("stack size==>"+st.size());
		for(int i=0;i<=6;i++)
			System.out.println("element==>"+st.pop());
		System.out.println("stack size==>"+st.size());*/
		
		callTwoStackImplement();
	}

	/*How do you implement 2 stacks using only one array.
	 *Your stack routines should not indicate an overflow
	 *unless every slot in the array is used? 
	 */
	public static void callTwoStackImplement()
	{
		TwoStack st=new TwoStack(6);
		for(int i=0;i<=3;i++)
			st.push(i, 1);
		for(int i=0;i<=3;i++)
			st.push(i, 2);
		for(int i=0;i<=4;i++)
			st.pop(1);
		for(int i=0;i<=2;i++)
			st.pop(2);
	}
	
	
}
