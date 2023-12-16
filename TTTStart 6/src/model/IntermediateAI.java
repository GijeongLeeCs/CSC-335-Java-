/*
 * @authors Gijeong Lee 
 * This class is for intermediateAI.
 * IntermediateAI is a better version of AI than randomAI
 * the IntermediateAI first check to stop a win of the opponent, 
 * then look for its own win. If neither is found, select any other open
 */
package model;

import java.util.Random;

public class IntermediateAI  implements TicTacToeStrategy {

	private char player = 'O';
	
  @Override
  /*
   * @param theGame which is an object of TicTacToeGame
   * @return move which is an object of Ourpoint.
   * It creates random which is an object of RandomAI
   * and board which is the board of theGame.
   * It checks the player order and returns random desiredMove
   * when move is null
   */
  public OurPoint desiredMove(TicTacToeGame theGame) {
	  RandomAI random = new RandomAI();
	  char[][] board = theGame.getTicTacToeBoard();
	  
	  
	  if(theGame.maxMovesRemaining() == 9)
	  {
		  player = 'X';
	  }
	 
	  OurPoint move = find(board, player, theGame);
	  
	 if(move != null)
	 {
		 return move;
		 
	 }
	 
	 
	 return random.desiredMove(theGame);
	 
  }
  
  
  /*
   * @param board which is 2d char array
   * @param player which is a character
   * @param theGame which is an object of TicTacToeGame
   * This method finds the OurPoint for IntermediateAI
   * It checks the character at first and return OurPoint by 
   * looping through.
   */
  public OurPoint find(char[][] board, char player, TicTacToeGame theGame)
  {
	  char player2;
	  
	  if (player =='X')
	  {
		  player2 = 'O';
	  }
	  
	  else {
		  player2 = 'X';
	  }
	  
	  
	  for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (theGame.available(row, col)) {

					board[row][col] = player;

					if (theGame.didWin(player)) {
						board[row][col] = '_';
						return new OurPoint(row, col);
					}

					board[row][col] = '_';
				}
			}
		}
	  
	  
	  for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (theGame.available(row, col)) {
					board[row][col] = player2;
					
					if (theGame.didWin(player2))
					{
						
						board[row][col] = '_';
						return new OurPoint(row, col);
					}

					board[row][col] = '_';
				}
			}
		}
	  
	  return null;
  }
 	 
}

	