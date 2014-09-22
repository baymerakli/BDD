package application;

public class AuctionFactory {
	
	public static AuctionCloser getInstance(Auction auction) throws Exception {
		if(auction.getBids().isEmpty()){
			return new NoBidAuctionCloser(auction);
		} else {
			return new WinnerAuctionCloser(auction);
		}
	}
}
