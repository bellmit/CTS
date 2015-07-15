package com.HR.Algo;

import java.io.InputStreamReader;
import java.util.Scanner;

public class HolloweenParty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc =new Scanner(new InputStreamReader(System.in));
		int testcase=sc.nextInt();
		long k;
		for (int i = 0; i < testcase; i++) {
			k=sc.nextLong();
			System.out.println(Math.abs(k/2)*Math.abs(k-k/2));
			
		}
	}

}
