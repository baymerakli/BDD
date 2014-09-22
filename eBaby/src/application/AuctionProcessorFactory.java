package application;

public class AuctionProcessorFactory {
	
	private AuctionProcessorFactory() {}
	
	public static AuctionProcessorFactory getInstance() {
		return new AuctionProcessorFactory();
	}
	
	public AuctionProcessor[] getFeeAdders(Auction auction) {
		AuctionProcessor[] auctionProcessor = {new TransactionFeeProcessor(auction),
				new ShippingFeeProcessor(auction),
				new LuxuryCarFeeProcessor(auction),
				new LogCarSaleProcessor(auction),
				new Log10KSalesProcessor(auction),
				new LogOffHoursSalesProcessor(auction)};
		return auctionProcessor;
	}
}
