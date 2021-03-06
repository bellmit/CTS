package com.general.example;

public class TTT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int table[][]={{0,0,1,1},
					   {0,2,1,1},
					   {1,0,0,1},
					   {1,1,2,0}};
		System.out.println(table.length);
		System.out.println(table[0].length);
		System.out.println("Player 0==>"+isTTTWon(table, 0));
		
	}
	
	public static boolean isTTTWon(int[][] table,int player)
	{
		boolean isWon=false;
		isWon=checkHorizontal(table,player);
		System.out.println("h==>"+isWon);
		if(isWon)
			return isWon;
		isWon=checkVertical(table,player);
		System.out.println("v==>"+isWon);
		if(isWon)
			return isWon;
		isWon=checkDiagonal(table,player);
		System.out.println("d==>"+isWon);
		if(isWon)
			return isWon;
		isWon=checkReverseDiagonal(table,player);
		System.out.println("rv==>"+isWon);
		
		
		return isWon;
	}

	private static boolean checkReverseDiagonal(int[][] table, int player) {
		// TODO Auto-generated method stub
		boolean isWon=true;
		for(int i=0;i<table.length;i++)
		{
			int j=table[i].length-1-i;
				if(table[i][j]!=player)
				{
					isWon=false;
					break;
				}
			
		}
		return isWon;
	}

	private static boolean checkDiagonal(int[][] table, int player) {
		// TODO Auto-generated method stub
		boolean isWon=true;
		for(int i=0;i<table.length;i++)
		{
			if(table[i][i]!=player)
			{
				isWon=false;
				break;
			}
			
		}
		return isWon;
	}

	private static boolean checkVertical(int[][] table, int player) {
		// TODO Auto-generated method stub
		boolean isWon=true;
		for(int i=0;i<table.length;i++)
		{
			isWon=true;
			for(int j=0;j<table[i].length;j++)
			{
				if(table[j][i]!=player)
				{
					isWon=false;
					break;
				}
			}
			if(isWon)
				return isWon;
		}
		return false;
	}

	private static boolean checkHorizontal(int[][] table, int player) {
		// TODO Auto-generated method stub
		boolean isWon=true;
		for(int i=0;i<table.length;i++)
		{
			isWon=true;
			for(int j=0;j<table[i].length;j++)
			{
				if(table[i][j]!=player)
				{
					isWon=false;
					break;
				}
			}
			if(isWon)
				return isWon;
		}
		return false;
	}

}
