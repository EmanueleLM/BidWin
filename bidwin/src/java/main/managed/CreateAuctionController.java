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

        public String register() {
                auctionsession.save(auction);
                return "/welcome?faces-redirect=true"; 
	}

}
