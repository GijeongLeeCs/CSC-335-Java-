/*
 * 
 * Gijeong Lee
 * This version is for drawingView. 
 * Users can click on the screen to make their moves. 
 * When the game is over, the result will be shown. 
 * 
 */

package views_controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import model.IntermediateAI;
import model.OurObserver;
import model.TicTacToeGame;

public class DrawingView extends BorderPane implements OurObserver  { 
	
	private TicTacToeGame theGame;
	private BorderPane pane = new BorderPane();
	private char[][] board;
	double oldX;
	double oldY;
	boolean drawing = false;
	Canvas canvas = new Canvas(230, 230);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Label move = new Label("Click to make a move");
	private boolean check = true;
	Image X = new Image("file:images/X.png", false);
	Image O = new Image("file:images/O.png", false);
	private boolean gameOver = false;
	
	/*
	 * 
	 * This is a constructor.
	 * @param theModel which is an object of TicTacToeGame
	 * It sets computer strategy as intermediateAI
	 * It contains all methods created below.
	 */
	public DrawingView(TicTacToeGame theModel)
	{
		theGame = theModel;
	    Font font = Font.font("Monospace", 20);
	    move.setFont(font);
	    
	    initializePanel();
	   
	}
	
	/*
	 * This is for the layout.
	 */
	private void layoutGUI() {
		pane.setTop(move);
	    pane.setCenter(canvas);
	    drawSquares(gc);

	   
	}

	/*
	 * It draws squares on the canvas
	 * @param gc which is an object of GraphicsContext
	 */
	  private void drawSquares(GraphicsContext gc) {
		  gc.strokeRect(10, 10, 210, 210);
		    
		    // Draw two vertical lines
		    gc.strokeLine(80, 10, 80, 220);
		    gc.strokeLine(150, 10, 150, 220);
		    
		    // Draw two horizontal lines
		    gc.strokeLine(10, 80, 220, 80);
		    gc.strokeLine(10, 150, 220, 150);

	}
	  
	  /*
	   * This method is for updating when users change the view to DrawingView.
	   */
	  public void updateView()
	  {
		  for (int row = 0; row <3 ;row++)
		    {
		    	for (int col = 0; col < 3; col++)
		    	{
		    		
	    			drawXsAndOs(gc);
		    	
		    	}
		    	
		    }
		  
		  check();		
	  }
	  
	  /*
	   * it contains layoutGUI and registerHandlers
	   * It also assigns board as current game's board.
	   */
	public void initializePanel()
	{
		
		layoutGUI();

		registerHandlers();
		board = theGame.getTicTacToeBoard();
		this.setCenter(pane);
	}
	
	
	/*
	 * This method checks the game result.
	 */
	public void check()
	{
		 if(theGame.didWin('X'))
		  {
	    	  move.setText("X win");
			  gameOver = true; // Set the game over flag to true
			  canvas.setDisable(true); // Disable the canvas

	        // Remove the mouse event handlers
			  canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed());
			  
			 
			  
		  }
		  
		  else  if(theGame.didWin('O'))
		  {
			  move.setText("O win");
			  gameOver = true; // Set the game over flag to true
			  canvas.setDisable(true); // Disable the canvas

	        // Remove the mouse event handlers
			  canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed());
			 
		  }
		  
		  else if (theGame.tied())
		  {
			  move.setText("tied");
			  gameOver = true; // Set the game over flag to true
			  canvas.setDisable(true); // Disable the canvas

	        // Remove the mouse event handlers
			  canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed());
		 
		  }
	}
	    	   
		
	 /*
	  * This is for mouse pressing.
	  */
	private void registerHandlers()
	{
		this.setOnMousePressed(new MousePressed());

	}
	

	private class MousePressed implements EventHandler<MouseEvent> {
		


	   /*
	    * 
	    * When the mouse is pressed, it will get its X and Y value.
	    * Then, it finds which square is selected.
	    * When the game is over, the result will be shown, and users 
	    * cannot do something more.
	    * 
	    * @param event which is an object of MouseEvent
	    */
	    public void handle(MouseEvent event) {
	     
	      // Toggle drawing
	   
	    	
	      drawing = !drawing;

	      oldX = event.getSceneX();

	      oldY = event.getSceneY();
	      
	     
	      
	      if (gameOver) {
              return;
	      }
	      
	      if(22.0< oldX && oldX <92.0 && 75.0 < oldY && oldY < 145.0)
			{
				theGame.humanMove(0, 0, false);
				drawXsAndOs(gc);
			}
			
			else if(92.0< oldX && oldX <162.0 && 75.0 < oldY && oldY < 145.0)
			{
				theGame.humanMove(0, 1, false);
				drawXsAndOs(gc);
			}
			
			else if(162.0< oldX && oldX <232.0 && 75.0 < oldY && oldY < 145.0)
			{
				theGame.humanMove(0, 2, false);
				drawXsAndOs(gc);
			}

			else if(22.0< oldX && oldX <92.0 && 145.0 < oldY && oldY < 215.0)
			{
				theGame.humanMove(1, 0, false);
				drawXsAndOs(gc);
			}
			
			else if(92.0< oldX && oldX <162.0 && 145.0 < oldY && oldY < 215.0)
			{
				theGame.humanMove(1, 1, false);
				drawXsAndOs(gc);
			}
			
			else if(162.0< oldX && oldX <232.0 && 145.0 < oldY && oldY < 215.0)
			{
				theGame.humanMove(1, 2, false);
				drawXsAndOs(gc);
			}
			
			else if(22.0< oldX && oldX <92.0 && 215.0 < oldY && oldY < 285.0)
			{
				theGame.humanMove(2, 0, false);
				drawXsAndOs(gc);
			}

			else if(92.0< oldX && oldX <162.0 && 215.0 < oldY && oldY < 285.0)
			{
				theGame.humanMove(2, 1, false);
				drawXsAndOs(gc);
			}
			
			else if(162.0< oldX && oldX <232.0 && 215.0 < oldY && oldY < 285.0)
			{
				theGame.humanMove(2, 2, false);
				drawXsAndOs(gc);
			}
	      
	      
			check();
	     
	    }

	  }
	
	/*
	 * 
	 * It draws 'O' and 'X' on the canvas based on the board.
	 * @param gc which is an object of GraphicsContext
	 */
	  private void drawXsAndOs(GraphicsContext gc) {
	

		    for (int row = 0; row < 3; row++) {
		        for (int col = 0; col < 3; col++) {
		            char symbol = board[row][col];

		            if (symbol == 'X') {
		                gc.drawImage(X, col * 70 + 15, row * 70 + 15, 60, 60);
		            } else if (symbol == 'O') {
		                gc.drawImage(O, col * 70 + 15, row * 70 + 15, 60, 60);
		            }
		        }
		    }
	  }
	  
	
	  /*
	   * This is for printing the current board
	   */
	  public void update(Object observable) {
		    System.out.println("update called from OurObservable TicTacToeGame "); 
		    System.out.println( theGame);
		  }
}

