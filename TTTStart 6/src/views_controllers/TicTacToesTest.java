package views_controllers;

import java.util.*;
import model.TicTacToeGame;

public class TicTacToesTest {

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		game.startNewGame();
		
		while(game.stillRunning()) 
		{
			System.out.println("");
			Scanner input = new Scanner(System.in);
			System.out.print("Enter row and column: ");
			String rowCol = input.nextLine();
			
			char chrRow = rowCol.charAt(0);
			char chrCol = rowCol.charAt(2);
			
			int row = Character.getNumericValue(chrRow);
			int col = Character.getNumericValue(chrCol);
			
			if(game.available(row, col))
			{
				game.humanMove(row, col, false);
			}
			
			else {
				System.out.println("Square taken, try again.");
			}
			
			game.computerMove(row, col);
			
			System.out.println(game);
		}
		
		if(game.didWin('O'))
		{
			System.out.println("O wins");
		}
		
		else if(game.didWin('X'))
		{
			System.out.println("X wins");
		}
		
		else
		{
			System.out.println("Ties");
		}
	}

}
