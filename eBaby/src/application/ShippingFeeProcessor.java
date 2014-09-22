package application;

public class ShippingFeeProcessor extends AuctionProcessor {
	
	public ShippingFeeProcessor(Auction auction) {
		super(auction);
	}

	public void process() {
		if (auction.getItemCategory().equals("car")) {
			auction.setTotalPrice(auction.getTotalPrice() + 1000);
		} else if(!auction.getItemCategory().equals("Downloadable Software")){
			auction.setTotalPrice(auction.getTotalPrice() + 10);
		}
	}
}
