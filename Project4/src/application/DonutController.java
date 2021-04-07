package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


import java.lang.String;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import backend.*;

/**
 * The DonutsController class defines the operations for donut view
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class DonutController implements Initializable{
	public static final int YEASTTYPE = 4;
	public static final int CAKETYPE = 8;
	public static final int HOLETYPE = 12;
	public static final double fade = 0.5;
	public static final double regularOpacity = 1;
	
	private ObservableList<String> list1 = FXCollections.observableArrayList("Yeast donut", "Cake donut", "Donut holes");	    
    private ObservableList<String> list2 = FXCollections.observableArrayList("1", "2", "3", "4", "5","6","7","8","9","10");
  	    
  
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    private ArrayList<ImageView> imgList = new ArrayList<ImageView>(); 
    private ArrayList<String> flavors = new ArrayList<String>();
    private ArrayList<MenuItem> cart = new ArrayList<MenuItem>();
    private ArrayList<MenuItem> mainCart = new ArrayList<MenuItem>();
    private MainMenuController mainController;
    
    @FXML
    private ComboBox<String> typePicker;

    @FXML
    private ComboBox<String> amountPicker;
    
    @FXML
    private Pane yeastType,holeType,cakeType,cover,glazedBox;
    

    @FXML
    private CheckBox yeastMintCheck,yeastStrawberryCheck,yeastChocolateCheck,yeastVanillaCheck;

    @FXML
    private CheckBox cakeCoffeeCheck,cakeBerryCheck,cakeClassicCheck,cakeCinnamonCheck;

    @FXML
    private CheckBox holeGlazedCheck,holePeanutCheck,holeLemonCheck,holeBlueberryCheck;

    @FXML
    private ImageView yeastMintImg,yeastStrawberryImg,yeastChocolateImg,yeastVanillaImg;

    @FXML
    private ImageView cakeCoffeeImg,cakeBerryImg,cakeClassicImg,cakeCinnamonImg;
    
    @FXML
    private ImageView holeGlazedImg,holePeanutImg,holeLemonImg,holeBlueberryImg;
    
    @FXML
    private Button addButton, removeButton, addOrderButton,selectAll;

    @FXML
    private ListView<String> cartView;
    
    @FXML
    private TextArea subtotal;   
    
	/**
	 * Adds a donut to the temporary cart. 
	 *  
	 * @param event The triggering event
	 */
    @FXML
    void add(ActionEvent event) {
    	if(typePicker.getValue() != "Select Type"){
    		int qty = Integer.parseInt(amountPicker.getValue());
    		ArrayList<Donut> temp = new ArrayList<Donut>();
    		
    		for(int i = 0; i < flavors.size();i++) {
        		if(checkBoxes.get(i).isSelected()) {
        			Donut d;
        			
					if(i < YEASTTYPE)
        				d = new Donut(DonutType.Yeast,flavors.get(i),qty);
					else if(i < CAKETYPE)
						d = new Donut(DonutType.Cake,flavors.get(i),qty);
					else
						d = new Donut(DonutType.Hole,flavors.get(i),qty);
					
					temp.add(d);
        			imgList.get(i).setOpacity(fade);
        			checkBoxes.get(i).setSelected(false);
        			checkBoxes.get(i).setVisible(false);
        		}
    		}
    		
    		for(Donut d : temp) {
    			cartView.getItems().add(d.toString());
    			cart.add(d);
    		}
    		subtotal.setText(String.format("$%.2f", getSubtotal()));
    		
    	}
    			
    }

	/**
	 * Adds all donuts in the temporary cart to the main cart. 
	 *  
	 * @param event The triggering event
	 */
    @FXML
    void addOrder(ActionEvent event) {
    	if(cart.isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Empty Cart!");
			alert.setHeaderText("Empty Cart!");
			alert.setContentText("Your shopping cart is empty!\nSelect donuts to add to cart!");
			alert.showAndWait();	
			return;
    	}
    	mainCart.addAll(cart);
    	cart.clear();
	    Stage stage = (Stage) addOrderButton.getScene().getWindow();
	    stage.close();
	    
    }
    
	/**
	 * Remove a donut from the temporary cart. 
	 *  
	 * @param event The triggering event
	 */
    @FXML
    void remove(ActionEvent event) {
    	if(cartView.getSelectionModel().getSelectedItem() != null) {
    		String str = cartView.getSelectionModel().getSelectedItem();
    		int index = cartView.getSelectionModel().getSelectedIndex();
    		StringTokenizer st = new StringTokenizer(str, " ");
    		String flavor = st.nextToken();
    		int flavorIndex = 0;
    		
    		while(!flavor.equals(flavors.get(flavorIndex))) {
    			flavorIndex++;
    		}
    		imgList.get(flavorIndex).setOpacity(regularOpacity);
			checkBoxes.get(flavorIndex).setSelected(false);
			checkBoxes.get(flavorIndex).setVisible(true);

    		cart.remove(index);
    		cartView.getItems().remove(index);
    		subtotal.setText(String.format("$%.2f", getSubtotal()));
    		

    	}
    }
    
	/**
	 * Clear the current selected 
	 */
    @FXML
    void clearSelected() {
    	for(CheckBox c : checkBoxes) {
    		if(c.isSelected()) {
    			c.setSelected(false);
    		}  		
    	}
    }
    
    
	/**
	 * Check what types are selected  
	 *  
	 * @param event The triggering event
	 */
    @FXML
    void selectDonut(ActionEvent event) {
    	if(typePicker.getValue()=="Yeast donut") {
    		yeastType.setVisible(true);
    		holeType.setVisible(false);
    		cakeType.setVisible(false);
    		cover.setVisible(false);
    	}else if(typePicker.getValue()=="Cake donut"){
    		yeastType.setVisible(false);
    		holeType.setVisible(false);
    		cakeType.setVisible(true);
    		cover.setVisible(false);
    	}else if(typePicker.getValue()=="Donut holes"){
    		yeastType.setVisible(false);
    		holeType.setVisible(true);
    		cakeType.setVisible(false);
    		cover.setVisible(false);
    	}else{
    		System.out.println("somthing wrong");
    	}
    }
    
	/**
	 * Get the reference of main controller
	 *  
	 * @param controller MainMenuController representing the main controller
	 */
    public void setMainMenuController(MainMenuController controller) {
    	mainController = controller;
    	mainCart = mainController.getCart();
    }
    
	/**
	 * Get the sub-total of reference cart
	 * 
	 *  @return a double representing the subtotal
	 */
    public double getSubtotal() {
    	double retVal = 0;
    	for(MenuItem item : cart) {
    		((Donut)item).itemPrice();
    		retVal += item.getPrice();
    	}
    	return retVal;
    }



	/**
	 * Initialization
	 * 
	 * @param arg0 the location of URL
	 * @param arg1 the resources to be bundled
	 */ 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		checkBoxes.add(yeastChocolateCheck);
		checkBoxes.add(yeastMintCheck);
		checkBoxes.add(yeastStrawberryCheck);
		checkBoxes.add(yeastVanillaCheck);
		checkBoxes.add(cakeClassicCheck);
		checkBoxes.add(cakeCoffeeCheck);
		checkBoxes.add(cakeBerryCheck);
		checkBoxes.add(cakeCinnamonCheck);
		checkBoxes.add(holeLemonCheck);
		checkBoxes.add(holeGlazedCheck);
		checkBoxes.add(holePeanutCheck);
		checkBoxes.add(holeBlueberryCheck);
		
		imgList.add(yeastChocolateImg); 
		imgList.add(yeastMintImg);
		imgList.add(yeastStrawberryImg);
		imgList.add(yeastVanillaImg);
		imgList.add(cakeClassicImg);
		imgList.add(cakeCoffeeImg);
		imgList.add(cakeBerryImg);
		imgList.add(cakeCinnamonImg);
		imgList.add(holeLemonImg);
		imgList.add(holeGlazedImg);
		imgList.add(holePeanutImg);
		imgList.add(holeBlueberryImg);
		
		String[] str = {"Chocolate","Mint","Strawberry","Vanilla","Classic","Coffee"
					   ,"Berry","Cinnamon","Lemon","Glazed","Peanut","Blueberry"};
		flavors.addAll(Arrays.asList(str));
		
		typePicker.setValue("Select Type");
		typePicker.setItems(list1);
		typePicker.setStyle("-fx-font: 13px \"Georgia\";");
		amountPicker.setValue("1");
		amountPicker.setItems(list2);
		subtotal.setText("$0.00");
		cartView.setStyle("-fx-font: 13px \"Georgia\";");

	}
	
}