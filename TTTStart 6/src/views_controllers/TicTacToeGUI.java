package views_controllers;


/**
 * Play TicTacToe the computer that can have different AIs to beat you. 
 * Select the Options menus to begin a new game, switch strategies for 
 * the computer player (BOT or AI), and to switch between the two views.
 * 
 * This class represents an event-driven program with a graphical user 
 * interface as a controller between the view and the model. It has 
 * event handlers to mediate between the view and the model.
 * 
 * This controller employs the Observer design pattern that updates two 
 * views every time the state of the Tic Tac Toe game changes:
 * 
 *  1) whenever you make a move by clicking a button or an area of either view
 *  2) whenever the computer AI makes a move
 *  3) whenever there is a win or a tie
 *    
 * You can also select two different strategies to play against from the menus
 * 
 * @author Rick Mercer and Gijeong Lee
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.IntermediateAI;
import model.OurObserver;
import model.RandomAI;
import model.TicTacToeGame;

public class TicTacToeGUI extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private TicTacToeGame theGame;

  private OurObserver currentView;
  private TextAreaView textAreaView;
  private ButtonView buttonView;
  private DrawingView drawingView;
  private OurObserver current;
  private char check = 'z';
  
  private Menu options = new Menu("Options");
  private MenuBar menuBar = new MenuBar();
  private MenuItem newGame = new MenuItem("New Game");
  private Menu strategies = new Menu("Strategies");
  private Menu views = new Menu("Views");
  
  private MenuItem randomAI = new MenuItem("RandomAI");
  private MenuItem intermediate = new MenuItem("Intermediate");
  
  private MenuItem button = new MenuItem("Button");
  private MenuItem textArea = new MenuItem("TextArea");
  private MenuItem drawing = new MenuItem("Drawing");
  
  private char[][] board;
  private Button[][] buttons;
  
  
  // TBA:
  // private OurObserver buttonView;
  // private OurObserver drawingView;

  private BorderPane window;
  
  public static final int width = 254;
  public static final int height = 360;

  /*
   * This method assigns windown to new BorderPane object, and
   * sets up scene. Then, it contains method created below.
   */
  public void start(Stage stage) {
	 
	
    stage.setTitle("Tic Tac Toe");
    window = new BorderPane();
    
    Scene scene = new Scene(window, width, height);
    
    register();
    initializeGameForTheFirstTime();
    
    
    menuItem();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Set the game to the default of an empty board and the random AI.
   * Assign textAreaView, drawingView, and buttonView.
   * The view starts with textAreaView at first.
   */
  public void initializeGameForTheFirstTime() {
    theGame = new TicTacToeGame();
    // This event driven program will always have
    // a computer player who takes the second turn
    theGame.setComputerPlayerStrategy(new RandomAI());
    
    textAreaView = new TextAreaView(theGame);
	drawingView = new DrawingView(theGame);
	buttonView = new ButtonView(theGame);
   
    setViewTo(textAreaView);
  }
  
  /*
   * This method is for when users press New Game
   * It creates a new game based on current view.
   */
  public void initializeGame() {
	  theGame = new TicTacToeGame();
	  theGame.setComputerPlayerStrategy(new RandomAI());
	  textAreaView = new TextAreaView(theGame);
	  drawingView = new DrawingView(theGame);
	  buttonView = new ButtonView(theGame);
	  
	  if(check == 'b')
	  {
		  current = new ButtonView(theGame);
	  }
	  
	  else if (check == 'd')
	  {
		  current = new DrawingView(theGame);
	  }
	  
	  else if (check == 't')
	  {
		  current = new TextAreaView(theGame);
	  }
	
	  setViewTo(current);
  }
  
  /*
   * This is for setting up menuBar, menus, and menuItems.
   */
  public void menuItem()
  {
	  menuBar.getMenus().addAll(options);
	  options.getItems().addAll(newGame, views, strategies);
	  views.getItems().addAll(textArea, button, drawing);
	  strategies.getItems().addAll(randomAI, intermediate);
	  window.setTop(menuBar);
  }

  /*
   * This method changes view based on newView
   * @param newView which is an object of OurObserver
   */
  private void setViewTo(OurObserver newView) {
    
    currentView = newView;
    window.setCenter((Node) currentView);
  }

  
  /*
   * This is for menuItems' event.
   */
  public void register() {

	  newGame.setOnAction(new MenuListener());
	  
	  randomAI.setOnAction(new MenuListener());
	  intermediate.setOnAction(new MenuListener());
	  
	  button.setOnAction(new MenuListener());
	  textArea.setOnAction(new MenuListener());
	  drawing.setOnAction(new MenuListener());
	  
  }
  
  public class MenuListener implements EventHandler<ActionEvent>
  {
	/*
	 * This method deals with all menuItems.
	 * Users can create a new game, change computer player strategy, change the view.
	 */
	  public void handle(ActionEvent event)
	  {
		  if ((MenuItem) event.getSource() == newGame)
		  {

			  initializeGame();
			 
			  
		  }
		  
		  else if ((MenuItem) event.getSource() == randomAI)
		  {
			  theGame.setComputerPlayerStrategy(new RandomAI());
			  
		  }
		  
		  
		  else if ((MenuItem) event.getSource() == intermediate)
		  {
			  theGame.setComputerPlayerStrategy(new IntermediateAI());
		  }
		  
		  
		  else if ((MenuItem) event.getSource() == button)
		  {
			  check = 'b';
			  board = buttonView.board();
			  buttons = buttonView.buttons();
			  buttonView.updateView();
			  setViewTo(buttonView);
			  
		  }
		  
		  
		  else if ((MenuItem) event.getSource() == textArea)
		  {
			  check = 't';
			  textAreaView.updateView();
			  setViewTo(textAreaView);
			  
		  }
		  
		  else if ((MenuItem) event.getSource() == drawing)
		  {
			  check = 'd';
			  drawingView.updateView();
			  setViewTo(drawingView);

		  }

	  }
  }
}