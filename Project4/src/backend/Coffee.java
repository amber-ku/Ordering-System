package backend;

import java.util.HashMap;

/**
 * The Coffee class defines the common data and operations for all Coffee type.
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class Coffee extends MenuItem implements Customizable{
	private String size;
	private HashMap<String,Integer> addIn = new HashMap<String, Integer>();
	public static final double SHORT = 1.99 ;
	public static final double TALL = 2.49;
	public static final double GRANDE = 2.99;
	public static final double VENTI = 3.49;
	public static final double ADD_IN = 0.20;
	


	
	/**
	 * Creates a Coffee object with the given size and amount.
	 * 
	 * @param size indicating the size of coffee
	 * @param amount indicating the quantity of coffee
	 */
	public Coffee(String size,int amount) {
		super("Coffee",amount);
		this.size = size;
		for(Toppings t:Toppings.values()) {
			addIn.put(t.toString(), 0);
		}
		switch(size) {
		case "Short":setPrice(SHORT);break;
		case "Tall":setPrice(TALL);break;
		case "Grande":setPrice(GRANDE);break;
		case "Venti":setPrice(VENTI);break;
		default:setPrice(SHORT);
		}
	}
	
	/**
	 * Creates a Coffee object with the given size
	 * 
	 * @param size indicating the size of coffee
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	
	/**
	 * Add a new add-in to Coffee.
	 * 
	 * @param obj the add-in to be added.
	 * @return true if add it successfully.
	 */
	@Override
	public boolean add(Object obj) {
		if(obj instanceof Toppings) {
			Toppings temp = (Toppings)obj;
			int currentQty = addIn.get(temp.toString());
			addIn.replace(temp.toString(),currentQty+1);
			return true;
		}else
			return false;
	}

	/**
	 * Remove an add-in from Coffee.
	 * 
	 * @param obj the add-in to be added
	 * @return true if remove it successfully, false otherwise.
	 */
	@Override
	public boolean remove(Object obj) {
		if(obj instanceof Toppings) {
			Toppings temp = (Toppings)obj;
			int currentQty = addIn.get(temp.toString());
			if(currentQty <= 0)
				return false;
			addIn.replace(temp.toString(),currentQty-1);
			return true;
		}else
			return false;
	}
	
	/**
	 * Compute the price of the Coffee
	 */
	public void itemPrice() {
		double retVal = SHORT;

		switch(size) {
		case "Short":retVal = SHORT;break;
		case "Tall":retVal=TALL;break;
		case "Grande":retVal=GRANDE;break;
		case "Venti":retVal=VENTI;break;
		}
		int sum  = 0;
		for(int qty : addIn.values()) {
			sum += qty;
		}
		retVal += sum * ADD_IN;
		
		setPrice(retVal * super.getAmount());
	}
	
	/**
	 * Compare this Coffee object to the given Object for equality
	 * 
	 * @return true if two Coffees' size and add-ins are the same
	 */
	@Override 
	public boolean equals(Object obj){
		if(obj instanceof Coffee) {
			Coffee coffee = (Coffee)obj;
			return coffee.size.equals(size) &&
				   coffee.addIn.equals(this.addIn);
		}
		return false;
	}
	
	/**
	 * Converts this Coffee object to a String
	 * 
	 * @return a string representation of this Coffee
	 */
	@Override
	public String toString() {
		String retVal = String.format("Coffee   (%s)\t\t [%d]\n", size,getAmount());
		
		for(String name : addIn.keySet()) {
			if(addIn.get(name)>0)
				retVal += String.format("\t%s  [%d]\n", name,addIn.get(name));
		}
		
		return retVal;
	}
	
	
	

}
