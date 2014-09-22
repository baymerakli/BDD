package application;

public class WinnerAuctionCloser extends AuctionCloser {
	
	public WinnerAuctionCloser(Auction auction) {
		super(auction);
	}

	public void onClose() throws Exception {
		PostOffice postOffice = PostOffice.getInstance();
		Users users = new Users();
		Bid highBid = auction.getBids().getLast();
		String bidderEmail = users.findByUserName(highBid.getBidderName()).getUserEmail();
		String sellerEmail = auction.getSeller().getUserEmail();
		
		postOffice.sendEMail(sellerEmail,
				"Your ".concat(auction.getItemName()).concat(" auction sold to bidder ").concat(bidderEmail).concat(" for ") + highBid.getPrice() + ".");
		postOffice.sendEMail(bidderEmail,
				"Congratulations! You won an auction for a ".concat(auction.getItemName()).concat(" from ").concat(sellerEmail).concat(" for ") + highBid.getPrice() + ".");
		
		int bidPrice = auction.getBids().getLast().getPrice();
		auction.setTotalPrice(bidPrice);
		
		AuctionProcessor[] auctionProcessors = AuctionProcessorFactory.getInstance().getFeeAdders(auction);
		
		for (AuctionProcessor auctionProcessor : auctionProcessors) {
			auctionProcessor.process();
		}		
	}
}
