package application;


public class TransactionFeeProcessor extends AuctionProcessor {
	
	public TransactionFeeProcessor(Auction auction) {
		super(auction);
	}

	public void process() {
		auction.setTotalPrice(auction.getTotalPrice() * 1.02);
	}
}
