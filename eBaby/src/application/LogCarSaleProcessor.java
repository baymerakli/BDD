package application;

public class LogCarSaleProcessor extends AuctionProcessor {

	public LogCarSaleProcessor(Auction auction) {
		super(auction);
	}

	public void process() {
		if (auction.getItemCategory().equals("car")) {
			AuctionLogger logger = AuctionLogger.getInstance();
			logger.log("/tmp/carSales", "Auction ".concat(auction.getItemName()).concat(" sold a car"));
		}
	}
}
