package application;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author niralt
 *
 */
public class Auction {

	private User seller; 
	private String state;
	private LinkedList<Bid> bids;
	private String itemName;
	private double totalPrice;
	private String itemCategory;
		
	protected Auction(String sellerName, String itemName, String itemCategory) throws Exception {
		Users users = new Users();
		User seller = users.findByUserName(sellerName);
		if (!seller.getAuthenticated()) {
			throw new Exception("User not logged in");
		}
		
		this.seller = seller;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		totalPrice = 0.0;
		state = "CREATED";
		bids = new LinkedList<Bid>();
	}

	/**
	 * @return the seller
	 */
	public User getSeller() {
		return seller;
	}

	public void onStart() {
		state = "STARTED";
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	public void onClose() throws Exception {
		state = "CLOSED";
		AuctionFactory.getInstance(this).onClose();
	}

	public void bid(User bidder, int price) throws Exception {
		if (!bidder.getAuthenticated()) {
			throw new Exception("User not logged in");
		}
		
		if (!state.equals("STARTED")) {
			throw new Exception("Auction NOT Started or Closed");
		}
		
		try{
			if (price < bids.getLast().getPrice()) {
				throw new Exception("Bid price must be higher then the current price");
			}			
		} catch(NoSuchElementException e){
			//Do nothing...
		}
		
		Bid bid = new Bid(bidder.getUserName(), price);
		bids.add(bid);
	}

	/**
	 * @return the bids
	 */
	public LinkedList<Bid> getBids() {
		return bids;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the itemCategory
	 */
	public String getItemCategory() {
		return itemCategory;
	}
}
