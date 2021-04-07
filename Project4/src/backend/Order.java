package backend;

import java.util.ArrayList;

/**
 * The Order class defines the common data and operations for all order type,
 * Order object contains a list of MenuItems.
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class Order implements Customizable{
	private ArrayList<MenuItem> itemList;
	private String orderNumber;
	public static final double taxRate = 0.06625;
	
	/**
	 * Creates an Order object.
	 */
	public Order() {
		itemList = new ArrayList<MenuItem>();
		orderNumber = "0000";
	}
	
	/**
	 * Creates an Order object with given orderNumber.
	 * 
	 * @param orderNumber the order number to be set
	 */
	public Order(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/**
	 * Add a new MenuItem to the order
	 * 
	 * @param obj the MenuItem to be added.
	 * @return true if add it successfully.
	 */
	@Override
	public boolean add(Object obj) {
		if(obj instanceof MenuItem) {
			itemList.add((MenuItem)obj);
			return true;
		}else
			return false;
	}
	
	/**
	 * Remove a MenuItem from the order
	 * 
	 * @param obj the MenuItem to be removed.
	 * @return true if add it successfully.
	 */
	@Override
	public boolean remove(Object obj) {
		if(obj instanceof Coffee)
			return itemList.remove((Coffee)obj);
		else if(obj instanceof Donut)
			return itemList.remove((Donut)obj);
		else
			return false;
	}
	
	/**
	 * Set the order number.
	 * 
	 * @param orderNumber the order number to be set.
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/**
	 * Get the order subtotal of all items.
	 * 
	 * @return a double number indicating the sum of all items
	 */
	public double getSubTotal() {
		double subTotal = 0;
		for(MenuItem item : itemList) {
			subTotal += item.getPrice();
		}
		return subTotal;
	} 
	
	/**
	 * Get the order number of this order.
	 * 
	 * @return a String indicating the order number of this order.
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * Get the list of this order.
	 * 
	 * @return an arrayList of MenuItem from this order
	 */
	public ArrayList<MenuItem> getList(){
		return itemList;
	}
	
	/**
	 * Convert this order to String.
	 * 
	 * @return an String representing this order.
	 */
	@Override
	public String toString() {
		String retVal = String.format("Order#  : %s\n", orderNumber);
		for(MenuItem i : itemList) {
			retVal+=i.toString();
		}
		retVal += String.format("\n\t\t\t\tSubtotal : $%.2f\n",getSubTotal());
		retVal += String.format("\t\t\t\tTax : $%.2f\n",getSubTotal()*taxRate);
		retVal += String.format("\t\t\t\tTotal : $%.2f\n",getSubTotal()*(1+taxRate));
		
		return retVal;
	}
	
	/**
	 * Compare two orders for equality
	 * 
	 * @return true if two orders' number are the same.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Order) {
			Order o = (Order)obj;
			return o.orderNumber == this.orderNumber;
		}
		return false;
	}
}
