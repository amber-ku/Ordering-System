package backend;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The StoreOrders class defines the common data and operations for all StoreOrders type,
 * StoreOrders object contains a list of Order.
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class StoreOrders implements Customizable{
	private ArrayList<Order> orderList;
	private String currentOrderNumber;
	private SimpleStringProperty orderNumberProperty = new SimpleStringProperty();
	
	/**
	 * Creates an Store Order object.
	 */
	public StoreOrders() {
		orderList = new ArrayList<Order>();
		currentOrderNumber = "0001";
		
	}
	
	/**
	 * check if store orders is empty
	 * 
	 * @return true if thelist is empty. 
	 */
	public boolean isEmpty() {
		return orderList.isEmpty();
	}
	

	/**
	 * Add an order to the store order list
	 * 
	 * @param obj the order to be added.
	 * @return true if add it successfully.
	 */
	@Override
	public boolean add(Object obj) {
		if(obj instanceof Order) {
			Order order = (Order)obj;
			order.setOrderNumber(currentOrderNumber);
			orderList.add(order);
			int temp = Integer.parseInt(currentOrderNumber);
			temp++;
			currentOrderNumber = String.format("%04d", temp);
		}
		return false;
	}
	
	/**
	 * Remove an order from the store order list
	 * 
	 * @param obj the order to be removed.
	 * @return true if remove it successfully.
	 */
	@Override
	public boolean remove(Object obj) {
		if(obj instanceof Order) {
			Order order = (Order)obj;
			return orderList.remove(order);
		}
		return false;
	}
	
	
	/**
	 * Get the detail of the given order
	 * 
	 * @param target the order being requested
	 * @return a String representing the detail of the order 
	 */
	public String getOrderDetail(Order target) {
		String retVal = "";
		
		for(Order o : orderList) {
			if(o.equals(target)) {
				retVal = o.toString();
				break;
			}
		}
		return retVal;
	}

	
	/**
	 * Get the list of the all orders
	 *
	 * @return an ArrsyList of all orders. 
	 */
	public ArrayList<Order> getOrders() {
		return orderList;
	}
	
	/**
	 * Get the property of the number of orders in this store
	 *
	 * @return a String property representing the number of store orders
	 */
	public StringProperty getorderNumberProperty() {
		orderNumberProperty.setValue(String.valueOf(orderList.size()));
		return orderNumberProperty;
	}


	/**
	 * Compare two StoreOrders for equality
	 * 
	 * @return true if two StoreOrders are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof StoreOrders) {
			StoreOrders s = (StoreOrders)obj;
			return s.currentOrderNumber == this.currentOrderNumber
				&& s.orderList.equals(this.orderList);
		}
		return false;
	}
	
	/**
	 * Convert this StoreOrders to String.
	 * 
	 * @return an String representing this StoreOrders.
	 */
	@Override
	public String toString() {
		String retVal = "";
		
		for(Order o : orderList) {
			retVal += o.toString();
			retVal += "----------------------------------------------------\n";
		}
		return retVal;
	}
	
	
	
	
}
