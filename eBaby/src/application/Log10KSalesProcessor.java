package application;

public class Log10KSalesProcessor extends AuctionProcessor {

	public Log10KSalesProcessor(Auction auction) {
		super(auction);
	}

	public void process() {
		if (auction.getBids().getLast().getPrice() >= 10000) {
			AuctionLogger logger = AuctionLogger.getInstance();
			logger.log("/tmp/salesOver10k", "Auction ".concat(auction.getItemName()).concat(" sold over 10k"));
		}
	}
}
