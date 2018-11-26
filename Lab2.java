import java.io.*;
import java.util.*;

/**
 * A class that takes in a file of bids and creates a priority queue for sell
 * and one for buy. Buys and sells and prints what it does.
 * 
 * Complexity: O(n)
 * 
 * @author Alexander Wölfinger
 * @author Richard Martin
 *
 */
public class Lab2 {

	/**
	 * Creates the two priority queues and makes sell and buy orders and creates a string with them
	 * and the remaining orders that have not been executed.
	 * 
	 * Complexity: O(n)
	 * 
	 * @param commands the data it takes in, name, order type and bid value.
	 * @return a string with the information of the orders.
	 */
	public static String pureMain(String[] commands) {
		Comparator<Bid> buyC = new BuyComparator();
		Comparator<Bid> sellC = new SellComparator();
		PriorityQueue<Bid> buy_pq = new PriorityQueue<Bid>(buyC);
		PriorityQueue<Bid> sell_pq = new PriorityQueue<Bid>(sellC);
		StringBuilder sb = new StringBuilder();

		for (int line_no = 0; line_no < commands.length; line_no++) {
			String line = commands[line_no];
			if (line.equals(""))
				continue;

			String[] parts = line.split("\\s+");
			if (parts.length != 3 && parts.length != 4)
				throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
			String name = parts[0];
			if (name.charAt(0) == '\0')
				throw new RuntimeException("line " + line_no + ": invalid name");
			String action = parts[1];
			int price;
			try {
				price = Integer.parseInt(parts[2]);
			} catch (NumberFormatException e) {
				throw new RuntimeException("line " + line_no + ": invalid price");
			}

			if (action.equals("K")) {
				Bid currentBid = new Bid(name, price);
				buy_pq.add(currentBid);
			} else if (action.equals("S")) {
				Bid currentBid = new Bid(name, price);
				sell_pq.add(currentBid);
			} else if (action.equals("NK")) {
				Bid oldBid = new Bid(name, price);
				Bid newBid = new Bid(name, Integer.parseInt(parts[3]));
				buy_pq.update(oldBid, newBid);
			} else if (action.equals("NS")) {
				Bid oldBid = new Bid(name, price);
				Bid newBid = new Bid(name, Integer.parseInt(parts[3]));
				sell_pq.update(oldBid, newBid);
			} else {
				throw new RuntimeException("line " + line_no + ": invalid action");
			}

			if (sell_pq.size() == 0 || buy_pq.size() == 0)
				continue;

			if (buy_pq.minimum().bid >= sell_pq.minimum().bid) {
				System.out.println(buy_pq.minimum().name + " buys a share from " + sell_pq.minimum().name + " for "
						+ buy_pq.minimum().bid + "kr");
				buy_pq.deleteMinimum();
				sell_pq.deleteMinimum();
			}
		}

		sb.append("Order book:\n");
		sb.append("Sellers: ");
		while (sell_pq.size() != 0) {
			sb.append(sell_pq.minimum().toString());
			if (sell_pq.size() != 1)
				sb.append(", ");
			sell_pq.deleteMinimum();
		}
		sb.append("\n");
		sb.append("Buyers: ");

		while (buy_pq.size() != 0) {
			sb.append(buy_pq.minimum().toString());
			if (buy_pq.size() != 1)
				sb.append(", ");
			buy_pq.deleteMinimum();
		}
		return sb.toString();
	}

	/**
	 * Reads in the file or arguments in terminal.
	 * 
	 * Complexity: O(n)
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if (args.length != 1) {
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while (true) {
			String line = actions.readLine();
			if (line == null)
				break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}