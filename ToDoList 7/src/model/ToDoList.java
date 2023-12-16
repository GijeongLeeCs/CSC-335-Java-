/*
 * Gijeong Lee
 * It implements a ToDo list application as an event-driven program 
 * using Java and JavaFX.
 * Users can decide whether they want to save the current list or not.
 * Also users can switch the priority.
 * Users can also add or remove todo list.
 */

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ToDoList extends Application{
	
	
	private VBox pane;
	private HBox paneH;
	private ListView<String> listView;
	private ObservableList<String> observableList;
	private static ArrayList<String> list;
	private TextField text = new TextField();
	private Button button = new Button("Save current list");
	private Button top = new Button("Top");
	private Button bottom = new Button("Bottom");
	private Button raise = new Button("Raise");
	private Button lower = new Button("Lower");
	private Button remove = new Button("Remove");
	private Alert startAlert;
	private Alert endAlert;


	public static void main(String[] args) throws Exception
	{
		
		launch(args);
		
	}
	
	/*
	 * This class contains all functions created below.
	 */
	public void start(Stage stage) throws IOException, ClassNotFoundException
	{
		
		startAlert();
		layoutGUI(); 
		registerHandlers();
		
	    Scene scene = new Scene(pane, 650, 400); 
	    stage.setScene(scene);
	   
	    stage.show();
	    stage.setOnCloseRequest(new Handler());
	    
	    
	    
	}
	
	/*
	 * This class is for stage.setOnCloseRequest
	 * When the user exits the program, endAlert will pop up, and
	 * ask whether the user wants to save the current list or not.
	 */
	private class Handler implements EventHandler<WindowEvent>
	{
		/*
		 * 
		 * @param arg0 which is an object of WindowEvent
		 * This function is for endAlert.
		 * When the user exits the program, endAlert will pop up, and
		 * ask whether the user wants to save the current list or not.
		 * If user presses OK, then it will write a text file to save the list.
		 */
		public void handle(WindowEvent arg0) {
			endAlert = new Alert(AlertType.CONFIRMATION);
			endAlert.setHeaderText("Click cancel to not save any changes");
			endAlert.setContentText("To save the current ToDo list, click OK");
		  	Optional<ButtonType> result = endAlert.showAndWait();
		  	
		  	if (result.get() == ButtonType.OK) 
		  	{
		  		String fileName = "objects.ser";
			    
			    try {
			    	System.out.println("DF");
			    	FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			    	ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			    	
			    	outFile.writeObject(list);
			    }
			    
			    catch (IOException ioe)
			    {
			    	System.out.println("Writing objects failed");
			    }
		  	}
		  	
		  	else 
		  	{
		  		System.out.println("Create an empty List");
		  	}
		}
		
	}
	
	/*
	 * This class is for the alert which is shown when the program runs
	 * It asks the user whether he wants to use a saved list or not.
	 * If the user selects 'OK', it will use the saved list
	 * Otherwise, it generates a new list.
	 */
	public void startAlert() throws IOException, ClassNotFoundException
	{
		startAlert = new Alert(AlertType.CONFIRMATION);
		startAlert.setHeaderText("Click cancel to start with zero todos");
		startAlert.setContentText("Click OK to start with the persistent ToDoList");
	  	Optional<ButtonType> result = startAlert.showAndWait();
	  	
	  	if (result.get() == ButtonType.OK) {
	  		FileInputStream rawBytes = new FileInputStream("objects.ser");
			ObjectInputStream inFile = new ObjectInputStream(rawBytes);
			list = (ArrayList<String>) inFile.readObject();
			inFile.close();
			
	  	} else {
	  		list = new ArrayList<>();
	  	}
	}
	
	
	/*
	 * This class is for the layout.
	 * It adds pretty much all the pane, buttons, and label.
	 */
	private void layoutGUI() {
	  	 
	  	pane = new VBox();
	  	paneH = new HBox();
	  
	  	pane.setPadding(new Insets(10, 10, 10, 10));
	  	pane.setSpacing(10);
	  
	  	paneH.setPadding(new Insets(10, 10, 10, 10));
	  	paneH.setSpacing(10);
	  
	
	  	pane.getChildren().add(new Label("Enter a new ToDo"));
	  	pane.getChildren().add(text);
	  	pane.getChildren().add(button);
	  
	  	top.setPrefWidth(100);
	  	bottom.setPrefWidth(100);
	  	raise.setPrefWidth(100);
	  	lower.setPrefWidth(100);
	  	remove.setPrefWidth(100);
	  
	  	observableList = FXCollections.observableArrayList(list);
	  
	  	listView = new ListView<>();
	  	listView.setItems(observableList);
	  
	  	pane.getChildren().add(listView);
	  	paneH.getChildren().add(top);
	  	paneH.getChildren().add(bottom);
	  	paneH.getChildren().add(raise);
	  	paneH.getChildren().add(lower);
	  	paneH.getChildren().add(remove);
	  	pane.getChildren().add(paneH);
		 
	  }
  
  /*
   * This class is for updating.
   */
  private void update()
  {
	  observableList = FXCollections.observableArrayList(list);
	  listView.setItems(observableList);
  }
  
  /*
   * This is for the event. Each button will have different events
   */
  private void registerHandlers() {
	  button.setOnAction(new CelHanlder());
	  top.setOnAction(new CelHanlder());
	  bottom.setOnAction(new CelHanlder());
	  raise.setOnAction(new CelHanlder());
	  lower.setOnAction(new CelHanlder());
	  remove.setOnAction(new CelHanlder());
	  
	  
  }

  /*
   * This is for buttons
   */
  private class CelHanlder implements EventHandler<ActionEvent>
  {	
	  /*
	   * It gets which todo element is selected, then
	   * when the user presses any button, the selected todo element
	   * will be changed based on the selected button.
	   */
	  public void handle(ActionEvent event)
	  {
		  int index = listView.getSelectionModel().getSelectedIndex();
		  listView.getSelectionModel().select(index);
		  
		  if (event.getSource() == button)
		  {
			  String toDo = text.getText();
			  list.add(toDo);
			  
		  }
		  
		  else if(event.getSource() == top) {
			  list.add(0, list.get(index));
			  list.remove(list.size()-1);
			  
		  }
		  
		  else if(event.getSource() == bottom) {
			  list.add(list.size(), list.get(index));
			  list.remove(index);	
		  }

		  else if(event.getSource() == raise) {
			  list.add(index-1, list.get(index));
			  list.remove(index+1);
		  }
		  
		  else if(event.getSource() == lower) {
			  list.add(index+2, list.get(index));
			  list.remove(index);
		  }
		  
		  else if(event.getSource() == remove) {
			  list.remove(index);
		  }

		  update();
	  }
  }
}
