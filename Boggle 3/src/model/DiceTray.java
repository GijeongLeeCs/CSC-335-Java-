package model;

import java.util.Random;

/**
 * This class is for the game called Boogle.
 * There are random characters on the 4x4 board.
 * This game is just for making just strings.
 * @author Gijeong Lee
 * 
 */
public class DiceTray {
	// instance variable theBoard
	private char[][] theBoard;
	
	// constructor for creating a Board
	public DiceTray(char[][] newBoard)
	{
		theBoard = newBoard;
		
	}
	
	public DiceTray()
	{
		theBoard = new char[4][4];
		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				Random r = new Random();
				char randomChar = (char) (r.nextInt(26) +'A');
				theBoard[i][j] = randomChar;
			}
			
			
		}
		
	}

	// @param attempt which is a guessed word
	// if attempt is less than three characters, it returns false
	// Q represents Qu, so Q changes to a special character '*'
	// Also if attempt contains qu it changes to a special character '*'
	// attempt changes to uppercase.
	// If attempt is founded on the board, it returns true.
	public boolean found(String attempt)
	{
		String newString = "";
		
		attempt = attempt.toUpperCase();
		int wordLength = attempt.length();
		
		if(wordLength < 3)
		{
			return false;
			
		}
		
		for (int k =0; k< attempt.length(); k++)
		{
			if(attempt.charAt(k) == 'Q' && attempt.charAt(k+1) == 'U')
			{
				newString+='*';
				k++;
			}
			
			else
			{
				newString+=attempt.charAt(k);
			}
		}
		
		
		for (int row=0; row< 4; row++) {
			for(int col=0; col< 4; col++)
			{
				if (theBoard[row][col] == 'Q')
				{
					theBoard[row][col] = '*'; 
					
				}
			
				if (finding(newString, 0, row, col))
				{
					return true;
				}
			}
		}
		
		
		return false;
		
	}
	
	public char[][] getBoard()
	{
		return theBoard;
	}
	//@ param word which is a word will be founded on board
	//@ param count which is an index in word
	//@ param row which is a row on board
	//@ param col which is a col on board
	// if count equals to word's length, then it means it founds the word
	// so it will return true
	// it will check all the possible surrounded character from the base character
	// if row or col are out of bounds, it returns false
	// it uses recursive backtracking algorithm
	public boolean finding(String word, int count, int row, int col)
	{
		if (count == word.length())
		{
			return true;
		}
		
		if (row < 0 || row >= theBoard.length || col < 0 || col >= theBoard.length)
		{
			return false;
		}
		
		
		if (theBoard[row][col] == word.charAt(count))
		{
			char oldChar = theBoard[row][col];
			theBoard[row][col] = '*';
			
			boolean found =
					finding(word, count + 1, row-1, col) || // up
					finding(word, count + 1, row+1, col) || // down 
					finding(word, count + 1, row, col-1) ||	// left
					finding(word, count + 1, row, col+1) || // right
					finding(word, count + 1, row-1, col-1) || // up left
					finding(word, count + 1, row-1, col+1) || // up right
					finding(word, count + 1, row+1, col-1) || // down left
					finding(word, count + 1, row+1, col+1); // down right
					
			
			theBoard[row][col] = oldChar;
			return found;
			
		}
		
		return false;
	}
}

