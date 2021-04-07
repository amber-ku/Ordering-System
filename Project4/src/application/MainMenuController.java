package application;




import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The MainMenuController class defines the operations for main menu view
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class MainMenuController {
	
	private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
	
	private StoreOrders allOrders = new StoreOrders();
	
	@FXML
    private Button orderdonutsView,ordercoffeeView,orderView,storeorderView;
	
    @FXML
    private Label title;
    
	
    /**
     * Open the store order view window.
     */
	@FXML
	void viewStoreOrder() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrderView.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setTitle("Store Order");	
			stage.setScene(scene);
			
			stage.setX(600);
			stage.setY(200);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			
			StoreOrderController storeOrderController = loader.getController();
			storeOrderController.setMainMenuController(this);
			stage.show();
			
		}catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Waringing");
			alert.setHeaderText("Something went wrong!");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		}
	}
	
	
    /**
     * Open the ordering coffee view window.
     */
	@FXML
	void orderCoffee() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CoffeeView.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setTitle("Ordering Coffee");	
			stage.setScene(scene);
			
			
			stage.setX(600);
			stage.setY(200);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			
			CoffeeController coffeeController = loader.getController();
			coffeeController.setMainMenuController(this);
			stage.show();
		
			
		}catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Waringing");
			alert.setHeaderText("Something went wrong!");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		}
	}
	
    /**
     * Open the current order view window.
     */
	@FXML
	void viewCurrentOrder() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderView.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setTitle("Shopping Cart");	
			stage.setScene(scene);
			
			stage.setX(600);
			stage.setY(200);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			
			OrderController orderController = loader.getController();
			orderController.setMainMenuController(this);
			stage.show();
			
		}catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Waringing");
			alert.setHeaderText("Something went wrong!");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		}
	}
	
    /**
     * Open the ordering donut view window.
     */
	@FXML
	void orderDonut() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DonutView.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setTitle("Ordering Donut");	
			stage.setScene(scene);
			
			stage.setX(600);
			stage.setY(200);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			
			DonutController donutController = loader.getController();
			donutController.setMainMenuController(this);
			stage.show();
		
		}catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Waringing");
			alert.setHeaderText("Something went wrong!");
			alert.setContentText("Please try again!");
			alert.showAndWait();
		}		
	}

    /**
     * Get the store order
     * 
     * @return A StoreOrders object
     */
	public StoreOrders getStoreOrders() {
		return allOrders;
	}
    /**
     * Get main cart
     * 
     * @return An ArrayList representing current cart.
     */
	public ArrayList<MenuItem> getCart(){
		return cart;
	}
	
	/**
	 * Initialization
	 * 
	 * @param location the location of URL
	 * @param resources the resources to be bundled
	 */ 
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}

