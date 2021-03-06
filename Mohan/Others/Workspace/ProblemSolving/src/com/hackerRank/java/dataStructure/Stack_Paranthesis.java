package com.hackerRank.java.dataStructure;

import java.util.Scanner;
import java.util.Stack;

/*A string containing only parentheses is balanced if the following is true:

if it is an empty string
if A and B are correct, AB is correct,
if A is correct, (A) and {A} and [A] are also correct.
Examples of some correctly balanced strings are: "{}()", "[{()}]", "({()})" 

Examples of some unbalanced strings are: "{}(", "({)}", "[[", "}{" etc.

Given a string, determine if it is balanced or not.*/


public class Stack_Paranthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String sample="{{[[(()]]}}";
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext())
		{
			char[] sChar=sc.nextLine().toCharArray();
					//sample.toCharArray();
			boolean status=true;
			Stack<Character> s=new Stack<>();
			for(int i=0;i<sChar.length;i++)
			{
				if(sChar[i]=='{' || sChar[i]=='[' || sChar[i] =='(')
				{
					s.push(sChar[i]);
				}
				else if(s.size()==0)
				{
					status=false;
							
				}
				else
				{
					switch(sChar[i])
					{
					case '}':
						if((char)s.peek()=='{')
						{
							s.pop();
						}else
						{
							status =false;
							break;
						}
						break;
					case ']':
						if((char)s.peek()=='[')
						{
							s.pop();
						}else
						{
							status =false;
							break;
						}
						break;
					case ')':
						if((char)s.peek()=='(')
						{
							s.pop();
						}else
						{
							status =false;
							break;
						}
						break;
					}
					
				}
				
			}
			System.out.println("Status"+status);
		}
		sc.close();
		
	}

}
