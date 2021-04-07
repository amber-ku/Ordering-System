package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import backend.*;

/**
 * The OrderController class defines the operations for Order view
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class OrderController implements Initializable{
	
    @FXML
    private ListView<String> itemsList;

    @FXML
    private TextField subTotalDisplay;

    @FXML
    private TextField taxDisplay;

    @FXML
    private TextField totalDisplay;
    
    @FXML
    private Button placeOrderButton;
    
    private Order order;
    
    private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
    
    private MainMenuController mainController;
    
    double subTotal,tax,total;

	/**
	 * Place order into store order 
	 */
    @FXML
    void placeOrder() {
    	if(cart.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Empty Cart!");
			alert.setHeaderText("Empty Cart!");
			alert.setContentText("Your shopping cart is empty!");
			alert.showAndWait();	
			return;
    	}
    	
    	order = new Order();
    	for(MenuItem i : cart) {
    		order.add(i);
    	}
    	
    	cart.clear();
    	
    	mainController.getStoreOrders().add(order);
    	calculate();
    	
	    Stage stage = (Stage) placeOrderButton.getScene().getWindow();
	    stage.close();
    }

	/**
	 * Get the reference of main controller
	 *  
	 * @param controller MainMenuController representing the main controller
	 */
    public void setMainMenuController(MainMenuController controller) {
    	mainController = controller;
    	cart = mainController.getCart();
		for(MenuItem item : cart) {
			itemsList.getItems().add(item.toString());
		}
		calculate();
    }
    
    

	/**
	 * Remove an item form the order
	 */
    @FXML
    void remove() {
    	if(itemsList.getSelectionModel().getSelectedItem() != null) {
    		int index = itemsList.getSelectionModel().getSelectedIndex();
    		cart.remove(index);
    		itemsList.getItems().remove(index);
    		calculate();
    	}
    }
    
    
	/**
	 * Calculate the current cart
	 */
    public void calculate() {
    	subTotal = 0;
    	tax = 0;
    	for(MenuItem item : cart) {
    		subTotal += item.getPrice();
    	}
    	
    	tax = subTotal * Order.taxRate;
    	total = tax+subTotal;
    	
    	subTotalDisplay.setText(String.format("$%.2f",subTotal));
    	taxDisplay.setText(String.format("$%.2f",tax));
    	totalDisplay.setText(String.format("$%.2f",total));
    	
    }
    
	/**
	 * Initialization
	 * 
	 * @param arg0 the location of URL
	 * @param arg1 the resources to be bundled
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemsList.setStyle("-fx-font: 13px \"Georgia\";");
	}

}
