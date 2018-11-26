/**
 * Comparator that compares sell bids.
 * 
 * Complexity: O(1)
 * 
 * @author Alexander Wölfinger
 * @author Richard Martin
 *
 */
public class SellComparator implements Comparator<Bid> {

	/*
	 * Returns -1 if bid a is smaller then bid b, 1 if bid a is bigger then bid b
	 * and 0 if they are equal.
	 * 
	 * Complexity: O(1)
	 */
	@Override
	public int compare(Bid a, Bid b) {
		if (a.bid < b.bid) {
			return -1;
		} else if (a.bid > b.bid) {
			return 1;
		} else {
			return 0;
		}
	}
}