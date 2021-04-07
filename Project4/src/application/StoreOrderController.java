package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import backend.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * The StoreOrderController class defines the operations for StoreOrder view
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class StoreOrderController implements Initializable {
    public static final double fade = 0.5;
    public static final double regularOpacity = 1;
    private MainMenuController mainController;
    
    private StoreOrders storeOrders;
    
    final ObservableList<Order> listOrders=  FXCollections.observableArrayList();

    @FXML
    private ListView<Order> ordersDisplay;

    @FXML
    private Button cancelOrderButton,exportButton;

    @FXML
    private Label numberOfOrder;

    @FXML
    private Label orderDetail;
    

    @FXML
    private Pane warning;
    

	/**
	 * Canceling an order 
	 */
    @FXML
    void cancelOrder() {
    	Order o = ordersDisplay.getSelectionModel().getSelectedItem();
     	if(o != null) {	  
        	warning.setVisible(true);
        	ordersDisplay.setDisable(true);
        	orderDetail.setOpacity(fade);
        	cancelOrderButton.setDisable(true);
        	exportButton.setDisable(true);
     	}
    	
    }
    

	/**
	 * close the canceling order warning 
	 */
    @FXML
    void closeWaring(ActionEvent event) {
    	warning.setVisible(false);
    	ordersDisplay.setDisable(false);
    	orderDetail.setOpacity(regularOpacity);
    	cancelOrderButton.setDisable(false);
    	exportButton.setDisable(false);
    }

	/**
	 * Confirm to cancel the order
	 */
    @FXML
    void confirmCancel(ActionEvent event) {
    	Order target = ordersDisplay.getSelectionModel().getSelectedItem();
    	storeOrders.remove(target);
    	listOrders.remove(target);
    	warning.setVisible(false);
    	ordersDisplay.setDisable(false);
    	orderDetail.setOpacity(regularOpacity);
    	orderDetail.setText("Select order to view detail");
    	if(!listOrders.isEmpty()) {
        	cancelOrderButton.setDisable(false);
    	}
    	exportButton.setDisable(false);
    	
    	numberOfOrder.textProperty().bind(storeOrders.getorderNumberProperty());
    	

    }

    /**
 	 *  Exports a database
 	 *  
 	 *  @param event The triggering event
 	 */
    @FXML
    void exportOrder(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
		try {
			PrintWriter pw = new PrintWriter(targeFile);
			pw.print(storeOrders.toString());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Successfully exporrting file!");
			alert.showAndWait();
			pw.close();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Fail to export file!");
			alert.setHeaderText("Fail to export file!");
			alert.setContentText("Fail to export file!");
			alert.showAndWait();	
		}catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Fail to export file!");
			alert.setHeaderText("Fail to export file!");
			alert.setContentText("Fail to export file!");
			alert.showAndWait();
		}
		
    }
    
	/**
	 * Get the reference of main controller
	 *  
	 * @param controller MainMenuController representing the main controller
	 */
    public void setMainMenuController(MainMenuController controller) {
    	mainController = controller;
    	storeOrders = mainController.getStoreOrders();
    	listOrders.addAll(storeOrders.getOrders());
    	ordersDisplay.setItems(listOrders);
    	
    	ordersDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent event) {  
	        	if(!storeOrders.isEmpty() && 
	        		ordersDisplay.getSelectionModel().getSelectedItem() != null) {	  
		        	Order o = ordersDisplay.getSelectionModel().getSelectedItem();
		        	cancelOrderButton.setDisable(false);
		        	orderDetail.setText(storeOrders.getOrderDetail(o));
	        	}
	        }
	    });
    	
    	ordersDisplay.setCellFactory(parm -> new ListCell<Order>(){
    		
    		@Override
    		protected void updateItem(Order o,boolean empty) {
    			 super.updateItem(o, empty);
    			 if(empty || o == null){
    	                setText("");
    	            }
    	            else{
    	            	String temp = String.format("Order# : %s\t\t\t  $%.2f",o.getOrderNumber(),o.getSubTotal()*(1+Order.taxRate));
    	                setText(temp);
    	            }
    		}
    	});
    	warning.setVisible(false);
    	numberOfOrder.textProperty().bind(storeOrders.getorderNumberProperty());
    	
    }

	/**
	 * Initialization
	 * 
	 * @param arg0 the location of URL
	 * @param arg1 the resources to be bundled
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ordersDisplay.setStyle("-fx-font: 13px \"Georgia\";");
		cancelOrderButton.setDisable(true);
	}

}
