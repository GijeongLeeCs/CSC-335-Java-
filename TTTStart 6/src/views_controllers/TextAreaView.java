/*
 * Gijeong Lee
 * This file is for creating required buttons, textAreas, and GridPanes
 * This program doensn't have options yet but you can run the game
 * the computer uses intermediateAI.
 */

package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 * This is the beginning of one view of a Tic Tac Toe game using
 * two TextField objects and one TextArea. The other two views
 * of ButtonView and DrawingView follow the same structure as this.
 * 
 * @author Rick Mercer and Gijeong Lee 
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.OurObserver;
import model.TicTacToeGame;
import model.IntermediateAI;

public class TextAreaView extends BorderPane implements OurObserver {

  private TicTacToeGame theGame;
  private TextArea rowArea = new TextArea();
  private TextArea colArea = new TextArea();
  private GridPane gridPane = new GridPane();
  private TextArea text = new TextArea();
  private GridPane firstPane = new GridPane();
  private Menu options = new Menu("Options");


  private Button move = new Button("Make Move");
  
  /*
   * This is a constructor. It set theGame as theModel and,
   * set the computer strategy as IntermediateAI
   * @param theModel which is an object of TicTacToeGame
   */
  public TextAreaView(TicTacToeGame theModel) {
    theGame = theModel;
    theGame.setComputerPlayerStrategy(new IntermediateAI());
    initializePanel();
  }

  /*
   * This function is for setting up all the Panes and textAreas.
   * This function determines the size, font, and positions of them 
   */
  private void initializePanel() {
	 
	gridPane.add(text, 0, 0);
	Font fontBoard = Font.font("Courier New", FontWeight.BOLD,38);
	text.setFont(fontBoard);
	text.setText(theGame.toString());
    TextArea message = new TextArea();
    message.setStyle("-fx-border-color: blue; border-width: 10;");
    Font font = new Font("Arial", 22);
    message.setFont(font);
    message.setText("\nTextAreaView\nUnder\nConstruction");
    this.setCenter(message);
    
   
    rowArea.setMaxSize(80,30);
    rowArea.setMinSize(80,20);
    
    colArea.setMaxSize(80,30);
    colArea.setMinSize(80,20);
    
    rowArea.setPrefWidth(3);
    firstPane.setHgap(12);
    firstPane.setVgap(12);
    
    firstPane.add(rowArea, 5, 2);
    firstPane.add(colArea, 5, 3);
    firstPane.add(move, 5, 4);
    
    firstPane.add(new Label("row"), 6, 2);
    firstPane.add(new Label("column"), 6, 3);
    
    
    this.setCenter(firstPane);
    this.generate();
    
    this.setBottom(gridPane);
    this.register();
  }
 
  /*
   * @return theGame.toString() which is String showing the board
   */
  public String generate()
  {
	  
	  return theGame.toString();
  }
  
  /*
   * This function is for move button.
   * When users type row and column and press this button,
   * it will check whether the user has valid inputs.
   * If it is invalid, it will return invalid message.
   * Otherwise, the game will run properly.
   * When the game is over, the result will be shown.
   */
  private void move()
  {
	  
	  	String row = rowArea.getText();
		String col = colArea.getText();
		
		int rowInt = Integer.parseInt(row);
		int colInt = Integer.parseInt(col);
		
		if(rowInt > 2 || colInt > 2)
		{
			move.setText("Invalid choice");
			
		}
		
		else if(rowInt < 0 || colInt < 0)
		{
			move.setText("Invalid choice");
			
		}
		
		else if(!theGame.available(rowInt, colInt))
		{
			move.setText("Invalid choice");
		}
		
		else {
			move.setText("Make Move");
			theGame.humanMove(rowInt, colInt, false);
			String tic =  generate();
			text.setText(tic);
		}
		
		
		check();
		
  }
  
  /*
   * This method is for updating when users change the view to TextAreaView.
   */
  public void updateView()
  {
	  for (int row = 0; row <3 ;row++)
	    {
	    	for (int col = 0; col < 3; col++)
	    	{
	    		
	    		String tic =  generate();
				text.setText(tic);
	    	
	    	}
	    	
	    }
	  check();
	  
  }
  
	/*
	 * This method checks the game result.
	 */
  public void check()
  {
	  if(theGame.didWin('X'))
	  {
		  move.setText("X win");
		  rowArea.setEditable(false);
		  colArea.setEditable(false);
	  }
	  
	  else  if(theGame.didWin('O'))
	  {
		  move.setText("O win");
		  rowArea.setEditable(false);
		  colArea.setEditable(false);
	  }
	  
	  else if (theGame.tied())
	  {
		  move.setText("tied");
		  rowArea.setEditable(false);
		  colArea.setEditable(false);
	  }
  }
  
  /*
   * This function is for the move button.
   */
  public void register() {
	  move.setOnAction(new ButtonListener());
	  
  }

  public class ButtonListener implements EventHandler<ActionEvent>
  {
	  /*
	   * It just calls the move function above.
	   */
	  public void handle(ActionEvent event)
	  {
			move();
			

	  }
  }
		
  // This method is called by Observable's notifyObservers()
  @Override
  public void update(Object observable) {
    System.out.println("update called from OurObservable TicTacToeGame " + theGame); 
  }
  

  
  }
