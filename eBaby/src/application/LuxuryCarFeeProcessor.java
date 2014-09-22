package application;

public class LuxuryCarFeeProcessor extends AuctionProcessor {

	public LuxuryCarFeeProcessor(Auction auction) {
		super(auction);
	}

	public void process() {
		int bidPrice = auction.getBids().getLast().getPrice();
		if (bidPrice >= 50000) {
			auction.setTotalPrice(auction.getTotalPrice() + bidPrice * .04);
		}
	}
}
