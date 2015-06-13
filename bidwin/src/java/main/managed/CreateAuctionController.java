package main.managed;

import main.dto.AuctionDTO;
import main.session.AuctionSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mago
 */
@ManagedBean(name="createauctionBean")
@RequestScoped
public class CreateAuctionController {

	@EJB
	private AuctionSession auctionsession;

	private AuctionDTO auction;


        public CreateAuctionController() {
		auction = new AuctionDTO();
	}

	public AuctionDTO getAuction() {
		return auction;
	}

	public void setAuction(AuctionDTO auction) {
		this.auction = auction;
	}

        public String register(int objectid, int numberauction) {
                auction.setObjectid(objectid);
                auction.setNumberauction(numberauction);
                auctionsession.save(auction);
                return "/user/welcome?faces-redirect=true"; 
	}

}
