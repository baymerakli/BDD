package application;

public abstract class AuctionCloser {
	protected Auction auction;

	public AuctionCloser(Auction auction) {
		this.auction = auction;
	}
	
	public void onClose() throws Exception {}
}
