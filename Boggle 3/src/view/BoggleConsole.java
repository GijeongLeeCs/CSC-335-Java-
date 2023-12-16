// Gijeong Lee
/*
 * This file is for actually running Boggle class
 * It is like a main function for Boggle class.
 * It creates a random generated board and get inputs from users until
 * the user type ZZ.
 * Then, it prints the result.
 * It basically calls all the functions in Boggle class.
 */

package view;

import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Boggle;
import model.DiceTray;


public class BoggleConsole {
	public static void main(String[] args) throws FileNotFoundException
	{
		DiceTray tray = new DiceTray();
		Boggle boggle = new Boggle(tray.getBoard());
		
		System.out.println(boggle);
		boggle.printingBoard();
		boggle.readingFile();
		
		
		boolean check = true;
		while (check)
		{
			
			Scanner scanner = new Scanner(System.in);
			
			String userInput = scanner.nextLine();
			
			if(userInput.equals("ZZ"))
			{
				check = false;
				break;
			}
			
			
			boggle.getting(userInput);
				
		}
		
		boggle.checking();
		boggle.printingResult();
	}
}
