package com.hbmTraining;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;


public class MyFirstHbm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Scanner s=new Scanner(new File("test.txt"));
			s.delimiter();
		}
		catch (IOException e)
		{
			System.out.println("Exception"+e.toString());
		}
	}

}
