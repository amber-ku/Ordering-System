package backend;


/**
 * This is an interface for Customizable object.
 */
public interface Customizable {
	
	/**
	 * This method will be used to add the given object.
	 * @param obj an object to be added
     * @return true if success
     */
	boolean add(Object obj);
	
	/**
	 * This method will be used to remove the given object.
	 * @param obj an object to be removed
     * @return true if success
     */
	boolean remove(Object obj);
}
