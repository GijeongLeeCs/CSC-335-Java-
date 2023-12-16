/*
 * 
 * Gijeong Lee
 * This version is for ButtonView.
 * There are nine buttons on the screen.
 * Each button means each position for the game.
 * When the users click a certain button, the computer's choice
 * is automatically decided based on intermediateAI.
 * When the game is over, the result will be shown on the screen.
 */


package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.IntermediateAI;
import model.OurObserver;
import model.TicTacToeGame;
import views_controllers.TextAreaView.ButtonListener;

public class ButtonView extends BorderPane implements OurObserver  {

	
	private TicTacToeGame theGame;
	private char[][] board;
	
	
	private TextArea buttonArea = new TextArea();
	private BorderPane firstBoard = new BorderPane();
	private BorderPane secondBoard = new BorderPane();
	private GridPane secondPane = new GridPane();
	private GridPane thirdPane = new GridPane();
	private Button zeroZero = new Button("_");
	private Button zeroOne = new Button("_");
	private Button zeroTwo = new Button("_");
	private Button oneZero = new Button("_");
	private Button oneOne = new Button("_");
	private Button oneTwo = new Button("_");
	private Button twoZero = new Button("_");
	private Button twoOne = new Button("_");
	private Button twoTwo = new Button("_");
	private Button[][] buttons = new Button[3][3];
	private Label move = new Label("Click to make a move");

	
	/*
	 * This is a constructor.
	 * @param theModel which is an object of TicTacToeGame
	 * It sets theGame as theModel.
	 * It also sets computer strategy as IntermediateAI
	 * It contains methods created below.
	 */
	public ButtonView(TicTacToeGame theModel) {
	    theGame = theModel;
	    initializePanel();
	   
	    register();
	  }
	
	
	/*
	 * 
	 * @return board which is char[][] representing the board
	 */
	public char[][] board()
	{
		return board;
	}
	
	/*
	 * @return buttons which is Button[][] 2d array containing Button objects.
	 */
	public Button[][] buttons()
	{
		
		return buttons;
	}
	
	  /*
	   * This method is for updating when users change the view to ButtonView.
	   */
	public void updateView()
	{
		for (int row = 0; row <3 ;row++)
	    {
	    	for (int col = 0; col < 3; col++)
	    	{
	    		if(board[row][col] == 'X')
	    		{
	    			buttons[row][col].setText("X");
	    		}
	    		
	    		else if(board[row][col] =='O')
	    		{
	    			buttons[row][col].setText("O");
	    		}

	    	}
	    	
	    	
	    }
		
		check();
	}
	
	/*
	 * It is mainly for setting nine buttons up.
	 * All button objects are added to button 2d array. 
	 */
	public void initializePanel()
	{
		
		Font font = Font.font("Courier", 30);
		Font font1 = Font.font("Monospace", 15);
		move.setFont(font1);
		
		zeroZero.setFont(font);
		zeroOne.setFont(font);
		zeroTwo.setFont(font);
		oneZero.setFont(font);
		oneOne.setFont(font);
		oneTwo.setFont(font);
		twoZero.setFont(font);
		twoOne.setFont(font);
		twoTwo.setFont(font);
	    
		
		buttons[0][0] = zeroZero;
		buttons[0][1] = zeroOne;
		buttons[0][2] = zeroTwo;
		buttons[1][0] = oneZero;
		buttons[1][1] = oneOne;
		buttons[1][2] = oneTwo;
		buttons[2][0] = twoZero;
		buttons[2][1] = twoOne;
		buttons[2][2] = twoTwo;
		 
		zeroZero.setPrefWidth(70);
        zeroZero.setPrefHeight(70);
        zeroOne.setPrefWidth(70);
        zeroOne.setPrefHeight(70);
        zeroTwo.setPrefWidth(70);
        zeroTwo.setPrefHeight(70);
        oneZero.setPrefWidth(70);
        oneZero.setPrefHeight(70);
        oneOne.setPrefWidth(70);
        oneOne.setPrefHeight(70);
        oneTwo.setPrefWidth(70);
        oneTwo.setPrefHeight(70);
        twoZero.setPrefWidth(70);
        twoZero.setPrefHeight(70);
        twoOne.setPrefWidth(70);
        twoOne.setPrefHeight(70);
        twoTwo.setPrefWidth(70);
        twoTwo.setPrefHeight(70);
        
		secondPane.add(zeroZero, 1, 1);
		secondPane.add(zeroOne, 2, 1);
		secondPane.add(zeroTwo, 3, 1);
		secondPane.add(oneZero, 1, 2);
		secondPane.add(oneOne, 2, 2);
		secondPane.add(oneTwo, 3, 2);
		secondPane.add(twoZero, 1, 3);
		secondPane.add(twoOne, 2, 3);
		secondPane.add(twoTwo, 3, 3);
	
		
		secondPane.setHgap(10);
	    secondPane.setVgap(10);
	   
	    
	    VBox vBox = new VBox(10); // Spacing between elements
	    vBox.getChildren().addAll(secondPane, move);
	    
	    firstBoard.setCenter(vBox);
	    
	   
		board = theGame.getTicTacToeBoard();
	    this.setCenter(firstBoard);
	   
	    
	}
	
	/*
	 * This method checks the game result.
	 */
	public void check()
	{
	    if(theGame.didWin('X'))
		  {
			  move.setText("X win");
			  firstBoard.setDisable(true);
			  
		  }
		  
		  else  if(theGame.didWin('O'))
		  {
			  move.setText("O win");
			  firstBoard.setDisable(true);
		  }
		  
		  else if (theGame.tied())
		  {
			  move.setText("tied");
			  firstBoard.setDisable(true);
			 
	    	   
		  }
	}
	
	/*
	 * This is for all buttons' actions.
	 */
	 public void register() {
		 zeroZero.setOnAction(new ButtonListener());
		 zeroOne.setOnAction(new ButtonListener());
		 zeroTwo.setOnAction(new ButtonListener());
		 oneZero.setOnAction(new ButtonListener());
		 oneOne.setOnAction(new ButtonListener());
		 oneTwo.setOnAction(new ButtonListener());
		 twoZero.setOnAction(new ButtonListener());
		 twoOne.setOnAction(new ButtonListener());
		 twoTwo.setOnAction(new ButtonListener());
		  
	  }
	 
	 public class ButtonListener implements EventHandler<ActionEvent>
	 {
		 /*
		  * We know which button is selected by using getSource()
		  * The selected button will be changed to 'X'
		  * After human move, the computer move will be updated as well.
		  * When the game is over, the result will be shown on the screen,
		  * and users cannot do something more.
		  */
		 public void handle(ActionEvent arg0) {
			    Button buttonClicked = (Button) arg0.getSource();
			        
			    	
			    for (int row = 0; row <3 ;row++)
			    {
			    	for (int col = 0; col < 3; col++)
			    	{
			    		 if (buttons[row][col] == buttonClicked)
					       {
					    	  buttons[row][col].setText("X");
					    	  theGame.humanMove(row, col, false);
					    	  for(int i = 0; i < 3; i++)
					    	  {
					    		  for(int k = 0; k < 3; k++)
						    	  {
					    			  if(board[i][k] == 'O')
							    	  {
					   
							    		  buttons[i][k].setText("O");
							    	  }
						    	  }
					    	  }
					    	  
					       }
			    	}
			    	
			    }
			    
			    check();
		 	}
	 }
	
	/*
	 * This is for printing current board.
	 */
	public void update(Object observable) {
	    System.out.println("update called from OurObservable TicTacToeGame " + theGame); 
	  }

}

