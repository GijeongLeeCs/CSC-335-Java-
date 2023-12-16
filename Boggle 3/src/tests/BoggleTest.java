// Gijeong Lee
/*
 * This is junit test file for testing Boggle class.
 *  It tests all the cases and functions in Boggle class.
 *  It uses a fixed board instead of randomly changed board
 */

///
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import model.Boggle;

class BoggleTest {
	
	
	private char[][] longWords = {
			  { 'A', 'B', 'S', 'E' },
			  { 'I', 'K', 'T', 'N' },
			  { 'N', 'D', 'E', 'D' },  
			  { 'S', 'S', 'E', 'N' } };
	
	
	Boggle boggle = new Boggle(longWords);
	
	
	@Test
	void test() throws FileNotFoundException {
		
		String check = "ABSEIKTNNDEDSSEN";
		String getWords = "deed bidet indeed tend end kkkk absented indents";
		String possibleWords = "tend indents bidet end indeed absented deed ";
		String impossibleWords = "kkkk ";
		int score;
		int restWords;
		
		assertTrue(boggle.toString().equals(check)); // checking characters on the board
		 
		boggle.printingBoard();
		boggle.readingFile();
		
		
		boggle.getting(getWords);
		boggle.checking();
		
		score = boggle.score();
		
		
		assertTrue(boggle.possibleWords().equals(possibleWords)); // checking founded words
		assertTrue(boggle.impossibleWords().equals(impossibleWords)); // checking incorrect words
		assertEquals(score,24); // checking score
		
		
		
		boggle.printingResult();
		restWords = boggle.restCount();
		assertEquals(restWords, 99); // checking unfounded words
		 
	}
	
	
	

	
}
