package application;

public class Bid {

	private String bidderName;
	private int price;
	
	public Bid(String bidderName, int price) {
		this.bidderName = bidderName;
		this.price = price;
	}

	/**
	 * @return the bidderName
	 */
	public String getBidderName() {
		return bidderName;
	}

	public int getPrice() {
		return price;
	}
}
