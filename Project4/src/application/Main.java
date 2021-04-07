package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
* The main class is a driver class to run the GUI of Orderging System
* 
* @author Chang Li, Hsinghui Ku
*/
public class Main extends Application {
	
	/**
	* Handler for starting application
	* 
	* @param primaryStage the pop up window
	*/
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene scene = new Scene(root,550,550);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Employees Management Application.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Run the application
	* 
	* @param args the command line argument
	*/
	public static void main(String[] args) {
		launch(args);
	}
}
