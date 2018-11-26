/**
 * A class that stores a specific bid.
 * 
 * Complexity: O(1)
 * 
 * @author Alexander Wölfinger
 * @author Richard Martin
 *
 */
public class Bid {
	
	/**
	 *  name Name of bider.
	 *  bid Value of bid.
	 */
	final public String name;
	final public int bid;

	/**
	 * @param name The name of the person who places the bid
	 * @param bid  Value of the bid
	 */
	public Bid(String name, int bid) {
		this.name = name;
		this.bid = bid;
	}

	/*
	 * Specific code for the bid.
	 * 
	 * Complexity: O(1)
	 */
	public int hashCode() {
		return 1 + 23 * bid + 31 * name.hashCode();
	}

	/*
	 * Method that controls if the parameter obj has the same hashcode as this bid.
	 * 
	 * Complexity: O(1)
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Bid))
			return false;
		Bid bid = (Bid) obj;
		return (hashCode() == bid.hashCode());
	}

	/*
	 * Returns the bid as a string.
	 * 
	 * Complexity: O(1)
	 */
	public String toString() {
		return name + " " + bid;
	}
}