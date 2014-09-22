package application;

public class NoBidAuctionCloser extends AuctionCloser {

	public NoBidAuctionCloser(Auction auction) {
		super(auction);
	}

	public void onClose() throws Exception {
		PostOffice postOffice = PostOffice.getInstance();
		postOffice.sendEMail(
				auction.getSeller().getUserEmail(),
				"Sorry, your auction for ".concat(auction.getItemName()).concat(" did not have any bidders."));			
	}
}
