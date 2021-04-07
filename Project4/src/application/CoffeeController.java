package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * The CoffeeController class defines the operations for coffee view
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class CoffeeController implements Initializable{
	
    private MainMenuController mainController;
    private ObservableList<String> number = FXCollections.observableArrayList("1", "2", "3", "4", "5","6","7","8","9","10");
    private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> mainCart = new ArrayList<MenuItem>();
    private int cream,syrup,whip,milk,caramel;
    private Coffee current;

    @FXML
    private ToggleGroup size;

    @FXML
    private RadioButton shortButton,tallButton,grandeButton,ventiButton;

    @FXML
    private Button creamRemove,creamAdd,whipRemove,whipAdd,caramelAdd;

    @FXML
    private Label creamQty,whipQty,syrupQty,milkQty,caramelQty,numberOfItem,totalLabel;

    @FXML
    private ComboBox<String> coffeeQty;
    
    @FXML
    private ListView<String> orderDisplay;

    @FXML
    private TextArea currentSubTotal,total,currentTax;
    

    @FXML
    private Button addButton;

	/**
	 * Get the reference of main controller
	 *  
	 * @param controller MainMenuController representing the main controller
	 */
    public void setMainMenuController(MainMenuController controller) {
    	mainController = controller;
    	mainCart = mainController.getCart();
    }
    
//	public ArrayList<MenuItem> getCart(){
//		return cart;
//	}
    
	/**
	 * Check the size is selected
	 *  
	 * @param event The triggering event
	 */
    @FXML
    void sizeSelect(ActionEvent event) {
    	if(shortButton.isSelected()) {
    		current.setSize("Short");
    		current.itemPrice();
    	}
    	if(tallButton.isSelected()) {
    		current.setSize("Tall");
    		current.itemPrice();
    	}
    	if(grandeButton.isSelected()) {
    		current.setSize("Grande");
    		current.itemPrice();
    	}
    	if(ventiButton.isSelected()) {
    		current.setSize("Venti");
    		current.itemPrice();
    	}
    }
    
	/**
	 * Check the amount is selected
	 */
    @FXML
    void amountSelect() {
    	int qty = Integer.parseInt(coffeeQty.getValue());
    	current.setAmount(qty);
    	current.itemPrice();
    }
    
    
	/**
	 * Add the coffee to the temporary cart
	 */
    @FXML
    void add() {
    	cart.add(current);
    	orderDisplay.getItems().add(current.toString());
    	calculate();
    	clear();
    	
    }
    
	/**
	 * Calculate the current price and items.
	 */
    void calculate() {
    	double total = 0;
    	for(MenuItem m : cart) {
    		total += m.getPrice()*(1+Order.taxRate);
    	}
    	totalLabel.setText(String.format("$%.2f",total));
    	numberOfItem.setText(String.valueOf(cart.size()));
    }
    
	/**
	 * Remove coffee from temporary cart
	 */
    @FXML
    void remove() {
    	if(orderDisplay.getSelectionModel().getSelectedItem() != null) {
    		int index = orderDisplay.getSelectionModel().getSelectedIndex();

    		cart.remove(index);
    		orderDisplay.getItems().remove(index);
    		calculate();
    	}
    }
    
	/**
	 * Place order and add to main cart.
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
    	
    	mainCart.addAll(cart);
    	cart.clear();
	    Stage stage = (Stage) addButton.getScene().getWindow();
	    stage.close();
    }
    
	/**
	 * Clear selected options
	 */
    @FXML
    void clear() {
    	current = new Coffee("Short",1);
    	currentSubTotal.textProperty().bind(current.getPriceProperty());
       	currentTax.textProperty().bind(current.getTaxProperty());
    	coffeeQty.setValue("1");
    	cream = 0;
    	shortButton.setSelected(true);
    	creamQty.setText(String.format("(%d)", cream));
    	syrup = 0;
    	syrupQty.setText(String.format("(%d)", syrup));
    	milk = 0;
    	milkQty.setText(String.format("(%d)", milk));
    	whip = 0;
    	whipQty.setText(String.format("(%d)", whip));
    	caramel = 0;
    	caramelQty.setText(String.format("(%d)", caramel));
    }
    
	/**
	 * Add cream
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void creamAdd(ActionEvent event) {
    	cream++;
    	creamQty.setText(String.format("(%d)", cream));
    	current.add(Toppings.Cream);
    	current.itemPrice();
    }
    
	/**
	 * Remove cream
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void creamRemove(ActionEvent event) {
    	if(cream>0) {
    		cream--;
    		creamQty.setText(String.format("(%d)", cream));
    		current.remove(Toppings.Cream);
    		current.itemPrice();
    	}
    }
    
	/**
	 * Add caramel
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void caramelAdd(ActionEvent event) {
    	caramel++;
    	caramelQty.setText(String.format("(%d)", caramel));
    	current.add(Toppings.Caramel);
    	current.itemPrice();
    }
    
	/**
	 * Remove caramel
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void caramelRemove(ActionEvent event) {
    	if(caramel>0) {
    		caramel--;
    		creamQty.setText(String.format("(%d)", caramel));
    		current.remove(Toppings.Caramel);
    		current.itemPrice();
    	}
    }
    
	/**
	 * Add syrup
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void syrupAdd(ActionEvent event) {
    	syrup++;
    	syrupQty.setText(String.format("(%d)", syrup));
    	current.add(Toppings.Syrup);
    	current.itemPrice();
    }
    
	/**
	 * Remove syrup
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void syrupRemove(ActionEvent event) {
    	if(syrup>0) {
    		syrup--;
    		syrupQty.setText(String.format("(%d)", syrup));
        	current.remove(Toppings.Syrup);
        	current.itemPrice();
    	}
    }
    
	/**
	 * Add whipped cream
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void whipAdd(ActionEvent event) {
    	whip++;
    	whipQty.setText(String.format("(%d)", whip));
    	current.add(Toppings.WhippedCream);
    	current.itemPrice();
    }
    
	/**
	 * Remove whipped cream
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void whipRemove(ActionEvent event) {
    	if(whip>0) {
    		whip--;
    		whipQty.setText(String.format("(%d)", whip));
        	current.remove(Toppings.WhippedCream);
        	current.itemPrice();
    	}
    }
    
	/**
	 * Add milk
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void milkAdd(ActionEvent event) {
    	milk++;
    	milkQty.setText(String.format("(%d)", milk));
    	current.add(Toppings.Milk);
    	current.itemPrice();
    }
    
	/**
	 * Remove milk
	 * 
	 * @param event The triggering event
	 */
    @FXML
    void milkRemove(ActionEvent event) {
    	if(milk>0) {
    		milk--;
    		milkQty.setText(String.format("(%d)", milk));
        	current.remove(Toppings.Milk);
        	current.itemPrice();
    	}
    }
    
    
	/**
	 * Initialization
	 * 
	 * @param location the location of URL
	 * @param resources the resources to be bundled
	 */
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	coffeeQty.setValue("1");
    	coffeeQty.setItems(number);
    	coffeeQty.setStyle("-fx-font: 13px \"Georgia\";");
       	current = new Coffee("Short",1);
       	currentSubTotal.textProperty().bind(current.getPriceProperty());
       	currentTax.textProperty().bind(current.getTaxProperty());
       	orderDisplay.setStyle("-fx-font: 13px \"Georgia\";");
	}
    


}
