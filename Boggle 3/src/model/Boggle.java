// Gijeong Lee

/*
 * This program does pretty much the same thing as DiceTray program does
 * but instead of having fixed board, it generates a random board.
 * 
 * 
 * 
 */
package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;


public class Boggle{
	
	/* boardWords are possible words from the board
	 *  possible words are words that are founded on the board and google doc.
	 * impossible words are words that are not founded on the board or google doc
	 * docWords are words from google doc.
	 * allPossibleWords is a string containing possible words
	 * allImpossibleWords is a string containing impossible words
	 * restCount is an integer showing how many unfounded words are
	 * score is an integer showing user's score.
	*/ 
	private DiceTray tray;
	private static char[][] theBoard;
	private static HashSet<String>  boardWords = new HashSet<>();
	private static HashSet<String>  possibleWords = new HashSet<>();
	private static HashSet<String> impossibleWords = new HashSet<>();
	private static HashSet<String> docWords = new HashSet<>();
	private static ArrayList<String> userwords = new ArrayList<>();
	private static String allPossibleWords = "";
	private static String allImpossibleWords = "";
	private static String restWords = "";
	private int restCount;
	private int score;
	
	
	
	
	private ArrayList<String> userWords = new ArrayList<>();
	

	// @return restCount which is an integer showing the number of unfounded words
	public int restCount()
	{
		return restCount;
	}
	
	// @return score which is an integer.
	public int score()
	{
		return score;
	}
	
	// @return allPossibleWords which is a sentence.
	public String possibleWords()
	{
		for(String word : possibleWords)
		{
			
			allPossibleWords+= word;
			allPossibleWords+= " ";
		
		
		}
		
		return allPossibleWords;
	}
	
	// @return allImpossibleWords which is a sentence.
	public String impossibleWords()
	{
		for(String word : impossibleWords)
		{
			
			allImpossibleWords+= word;
			allImpossibleWords+= " ";
		
		
		}
		
		return allImpossibleWords;
	}
	
	public String restWords()
	{
		for(String word : boardWords)
		{
			if(!possibleWords.contains(word))
			{
				restWords+=word;
				restWords+= " ";
				
			}
			
		}
		
		return restWords;
	}
	
	// Constructor. @param board which is char 2d arrayList
	public Boggle(char[][] board)
	{
		tray = new DiceTray(board);
		theBoard = tray.getBoard();
	}
	
	// This function prints board.
	public static void printingBoard()
	{
	
		System.out.println("Play one game of Boggle"); 
		System.out.println("");

		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				System.out.print(theBoard[i][j]+ " ");
						
			}
			System.out.println("");	
			
		}
		System.out.println("");	
		System.out.println("Enter words or ZZ to quit:");
		
		
	}
	// This function gets input and store words in to userWords.
	// @param userInput which is String containing words from users.
	public void getting(String userInput)
	{
		
		String[] words = userInput.split(" ");
		
		for(String word:words)
		{
			userWords.add(word.toLowerCase());

		}
			
		
	
	}
	
	// This function checks whether guessed word is valid or not 
	// by looking up the board by calling found function and checking 
	// docWords. If both are true, the word is added to possibleWords
	// and score is updated. Otherwise, the word is added to impossibleWords
	public void checking()
	{
		
		for(String word: userWords)
		{
		
			
			if(tray.found(word) && docWords.contains(word))
			{
				if(!possibleWords.contains(word))
				{
					possibleWords.add(word);
					score(word);
				}
				
			}
			
			
			else {
				impossibleWords.add(word);
				
			}
		}
	}
	
	//This function reads the google doc file and store all the words
	// that are founded on the board to docWords
	public void readingFile() throws FileNotFoundException
	{
		ArrayList<String> actualWords = new ArrayList<>();
			
		File myObj = new File("/Users/lkj1769/Downloads/BoggleWords.txt");
	
		Scanner reading = new Scanner(myObj);
		
		while (reading.hasNextLine()) 
		{
			String word = reading.nextLine();
			docWords.add(word);
			if(tray.found(word))
			{
				boardWords.add(word);

			}
			
		}
		
		
	}
	

	//This function prints all the results including score, founded words,
	// incorect words, and unfounded words.
	public void printingResult()
	{
		System.out.println("Your score: " + score);
		
		System.out.println("Words you found:");
		System.out.println("=================");
		System.out.println("");
		for(String word : possibleWords)
		{
			System.out.print(word + " ");
		}
		System.out.println("");
		System.out.println("");
		
		System.out.println("Incorrect words:");
		System.out.println("=================");
		System.out.println("");
		
		for(String word : impossibleWords)
		{
			System.out.print(word + " ");
		}
		
		System.out.println("");
		System.out.println("");
		
		for(String word : boardWords)
		{
			if(!possibleWords.contains(word))
			{
				restCount+=1;
			}
			
		}
	
		System.out.println("You could have found these " + restCount + " more words:" );
		System.out.println("======================================");
		System.out.println("");
		
		for(String word : boardWords)
		{
			if(!possibleWords.contains(word))
			{
				System.out.print(word + " ");
			}
			
		}
		
		System.out.println("");
		
	}
	
	// This function is for scoring based on founded word's length.
	// @param words which is String.
	public void score(String words)
	{
		int legnth = words.length();
		if(legnth ==3 || legnth ==4)
		{
			score+=1;
		}
		else if(legnth ==5)
		{
			score+=2;
		}
		else if(legnth ==6)
		{
			score+=3;
		}
		else if(legnth ==7)
		{
			score+=5;
		}
		else if(legnth >=8)
		{
			score+=11;
		}
	}
	
	
	// This is overwriting toString function for testing
	// @return sentence which is String showing Board characters.
	public String toString()
	{
		String sentence = "";
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				sentence += theBoard[i][j];
						
			}
			
		}		
		
		return sentence;
	}
	

	
	
}


