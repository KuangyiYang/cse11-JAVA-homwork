/** 
 *  Name: Kuangyi Yang
 *  Email: kuy006@ucsd.edu
 *  ID: A53083212
**/

import java.util.Scanner;
import java.util.Random;

public class RPS 
{
	public static void main(String[] args) 
	{ 
		int seedVal = 0;
		int player1Num = 0;
		int player2Num = 0;

		String player1Choice = " ";
		String player2Choice = " ";
		
		final int rock = 1;  // define rock, paper and scissors as constants  
		final int paper = 2;
		final int scissors = 3;
  

		Random ranGen = new Random();  // New random number generator
		Scanner scnr = new Scanner(System.in);  // Setup to scan seed from System.in
		System.out.print("Enter Seed : ");  // Input seed by user
		seedVal = scnr.nextInt();  // Read next integer from scanner
		ranGen.setSeed(seedVal);

		player1Num = ranGen.nextInt(3)+1;  // Generate four random numbers between 1, 2, 3
		player2Num = ranGen.nextInt(3)+1;
		
		//Convert randomly generated inter to Rock, Paper, and Scissors
		if ( player1Num == rock )
		{
			player1Choice = "rock";
		}
		else if ( player1Num == paper )
		{
			player1Choice = "paper";
		}
		else if ( player1Num == scissors )
		{
			player1Choice = "scissors";
		}
			
		if ( player2Num == rock )
		{
			player2Choice = "rock";
		}
		else if ( player2Num == paper )
		{
			player2Choice = "paper";
		}
		else if ( player2Num == scissors )
		{
			player2Choice = "scissors";
		}

		System.out.println("Player 1 : " + player1Choice);
		System.out.println("Player 2 : " + player2Choice);

		// List all the outcomes in this game
		if( player1Choice.equals(player2Choice) )
		{
			System.out.println("Nobody Wins");
		}
		else if( player1Choice.equals("rock") )
		{  
			if( player2Choice.equals("scissors") )
			{ 
				System.out.println("Player 1 Wins");
			}
			else
			{
				System.out.println("Player 2 Wins");
			}
		}
		else if( player1Choice.equals("paper") )
		{  
			if( player2Choice.equals("rock"))
			{ 
				System.out.println("Player 1 Wins");
			}
			else
			{
				System.out.println("Player 2 Wins");
			}
 		}
		else if( player1Choice.equals("scissors") )
		{  
			if( player2Choice.equals("paper"))
			{ 
				System.out.println("Player 1 Wins");
			}
			else
			{
				System.out.println("Player 2 Wins");
			}
		}
 
		return;
	}
}

