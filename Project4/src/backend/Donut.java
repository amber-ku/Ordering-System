package backend;

/**
 * The Donut class defines the common data and operations for all Donut type.
 * 
 * @author Chang Li, Hsinghui Ku
 */
public class Donut extends MenuItem{
	private DonutType type;
	private String flavor;
	
	
	/**
	 * Creates a Donut object with the given type, flavor and amount.
	 * 
	 * @param type a DonutType indicating the type
	 * @param flavor a String indicating the flavor 
	 * @param amount indicating the quantity of coffee
	 */
	public Donut(DonutType type, String flavor, int amount) {
		super("Donut",amount);
		this.type = type;
		this.flavor = flavor;
	}
	
	
	/**
	 * Get the type of this Donut
	 * 
	 * @return DonutType a DonutType indicating the type
	 */
	public DonutType getType() {
		return type;
	}
	
	/**
	 * Compute the price of the Donut.
	 */
	public void itemPrice() {
		setPrice(type.getPrice()* super.getAmount());
	}
	
	/**
	 * Converts this Donut object to a String
	 * 
	 * @return a string representation of this Donut
	 */
	public String toString() {
		return String.format("%s %s [%d]\n",flavor,type.toString(),getAmount());
	}
	
	/**
	 * Compare this Donut object to the given Object for equality
	 * 
	 * @return true if two Donuts' type and flavor are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut donut = (Donut)obj;
			return donut.type.equals(type) && donut.flavor.equals(flavor);
		}
		return false;
	}
}
