package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.DiceTray;

class DiceTrayTest{
	
	  private char[][] longWords = {
	  { 'A', 'B', 'S', 'E' },
	  { 'I', 'Q', 'T', 'N' },
	  { 'N', 'D', 'E', 'D' },  
	  { 'S', 'S', 'E', 'N' } };

	  private DiceTray tray = new DiceTray(longWords);
	  private DiceTray randomTray = new DiceTray();
	@Test
	void test() {
		char[][] board = tray.getBoard();
		
 		assertFalse(tray.found("")); // empty
	    assertFalse(tray.found("AB")); // word length < 3
		assertTrue(tray.found("SSEN")); 
	    assertTrue(tray.found("AQU")); //QU
	    assertFalse(tray.found("TS"));  
	    assertTrue(tray.found("TND"));
	    assertTrue(tray.found("aBs")); 
	    assertTrue(tray.found("AbS"));
	    assertFalse(tray.found("FSFSF"));
	    assertTrue(tray.found( "sent"));
	    assertTrue(tray.found( "SENT"));
	    assertTrue(tray.found( "ness"));
	    assertTrue(tray.found( "set"));
	    assertTrue(tray.found( "ndte"));
	    assertTrue(tray.found( "qutNDNESS")); 
	    assertTrue(tray.found( "edneSBAIn"));
	}

}
