// @author Gijeong Lee


package view;


import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Boggle;
import model.DiceTray;

/*
 * This program does exactly same thing what I did in boggleConsole.
 * But, it actually shows instead of printing results by using javaFX
 * When user press new game button, the random board pops up on the left board
 * Whenever user presses new game button, it will generate a new game
 * Users can guess words by typing on the second board.
 * When users press end game button, the result will be shown on last board.
 */

public class FirstFX extends Application {

	 
  private String attempts = "";
  private String results = "";
 
  
  private Button newGame = new Button("New game"); 
  private Button endGame = new Button("End game");
  private TextArea boardField = new TextArea(); // First board showing random board
  private TextArea outputField = new TextArea(); // Second board users typing
  private TextArea resultField = new TextArea(); // Third board showing the result
  private GridPane borderPane = new GridPane();
  private GridPane gridPane = new GridPane();
  private static DiceTray tray;
  private static Boggle boggle;
  
  public static void main(String[] args) {
 
    launch(args);
    
    }

  /*
   * This function returns randomly chosen alphabets on board
   * @return boardChars which is String
   */
  public static String generate() {
	  tray = new DiceTray();
	  boggle = new Boggle(tray.getBoard());
	 
	
	  String boardChars = "\n";
	  try {
			boggle.readingFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String boardString = boggle.toString();
		for(int i =0; i< boardString.length(); i++)
		{
			
			if(i%4 ==3 && i!=0)
			{
				if(boardString.charAt(i) == '*')
				{
					boardChars+= "Qu";
				}
				
				else
				{
					boardChars+=boardString.charAt(i);
				}

				boardChars+="\n";
						
			}
			
			else {
				if(boardString.charAt(i) == '*')
				{
					boardChars+= "Qu";
					boardChars+=" ";
				}
				else {
					boardChars+=boardString.charAt(i);
					boardChars+="  ";
				}
						
			}
		}
		return boardChars;
  }
  
  /*
   * This function gets words from users on the second board
   * @return words which is String
   */
  public String getting()
  {
	  
	  String words = outputField.getText();
	  
	  return words;
  }

  
  public void start(Stage stage) {

	register();
	setUp();
   
    Scene scene = new Scene(gridPane, 970, 320);
    stage.setScene(scene);
    stage.show();

   
  }
  
  /*
   * This function is basically for setting up FX
   * It makes boards and resize them.
   * Each board has different font.
   */
  public void setUp()
  {
	  borderPane.add(newGame, 1, 1);
	    borderPane.setHgap(10);
	    borderPane.add(endGame, 2, 1);
	    
	    gridPane.setHgap(12);
	    gridPane.setVgap(12);
	    
	    gridPane.add(borderPane, 1, 1);
	    Font fontBoard = Font.font("Courier New", FontWeight.BOLD,30);
	    
	    boardField.setFont(fontBoard);
	    boardField.setMaxSize(250,250);
	    boardField.setMinSize(250,250);
	    boardField.setEditable(false);
	    
	    StackPane.setAlignment(boardField, Pos.CENTER);
	   
	    Font fontOutput = Font.font("Times New Roman", FontWeight.SEMI_BOLD, 18); 
	    Label label1 = new Label("Enter attempts below:");
	    outputField.setFont(fontOutput);
	    outputField.setMaxSize(250, 250);
	    outputField.setMaxSize(250,250);
	    outputField.setMinSize(100,100);
	    outputField.setWrapText(true);
	    
	    Label label2 = new Label("Results:");
	    Font fontResult = Font.font(13); 
	    resultField.setFont(fontResult);
	    resultField.setMaxSize(430,250);
	    resultField.setMinSize(100,100);
	    resultField.setWrapText(true);
	    resultField.setEditable(false);
	    
	    gridPane.add(label1, 2, 1);
	    gridPane.add(label2	, 3, 1);
	    gridPane.add(boardField, 1, 2);
	    gridPane.add(outputField, 2, 2);
	    gridPane.add(resultField, 3, 2);
	    
  }
  /*
   * This function is for two buttons, new Game and end Game.
   */
  public void register() {
	  newGame.setOnAction(new ButtonListener());
	  endGame.setOnAction(new ButtonListener());
	  
  }

  public class ButtonListener implements EventHandler<ActionEvent>
  {
	  /*
	   * This function is  dealing with two cases.
	   * One is for new game button. It generates a new random board
	   * when users press new game button, and it also create a new game
	   * another is for end game button. when users press it,
	   * the result will be shown on the last board.
	   */
	  public void handle(ActionEvent event)
	  {
		if (event.getSource() == newGame)
		{
			outputField.setEditable(true);
			String boardChars =  generate();
			boardField.setText(boardChars);
			outputField.clear();
			resultField.clear();
			
		}
		 
		else if (event.getSource() == endGame)
		{
			String words = "";
			String results = "";
			words += getting();
			boggle.getting(words);
			
			results += "Your score:";
			System.out.print(boggle.score());
			
			boggle.checking();
			int score = boggle.score();
			results+= score;
			results+= "\n";
			results+= "\n";
			
			results += "Words you found:\n";
			results+= boggle.possibleWords();
			results+= "\n";
			results+= "\n";
			
			results += "Incorrect words: \n";
			results+= boggle.impossibleWords();
			results+= "\n";
			results+= "\n";
			
			boggle.printingResult();
			results += "You could have found ";
			results += boggle.restCount();
			results += " more words:\n";
			results += boggle.restWords();
			resultField.setText(results);
			outputField.setEditable(false);
			
		}
	  }
	  

	 
  }
	  
	  
}
	  
  
