package backend;

/**
 *  Types of Donuts can be used
 */
public enum DonutType {
	Yeast(1.39),Cake(1.59),Hole(0.33);
	
	private double price;
	
	/**
	 *  Construct a type with price
	 *  @param price indicating the price of this type
	 */
	DonutType(double price){
		this.price = price;
	}
	
	/**
	 *  Get the price of this type
	 *  @return indicating the price of this type
	 */
	public double getPrice() {
		return price;
	}
}
