package application;

public abstract class AuctionProcessor {
	Auction auction;
	
	public AuctionProcessor(Auction auction) {
		this.auction = auction;
	}

	public void process() {}
}
