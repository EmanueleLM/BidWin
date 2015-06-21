package main.managed;

import main.dto.AuctionDTO;
import main.session.AuctionSession;
import main.session.ObjectSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Davide
 */
@ManagedBean(name="createauctionBean")
@RequestScoped
public class CreateAuctionController {

	@EJB
	private AuctionSession auctionsession;
        
        @EJB
	private ObjectSession objectsession;

	private AuctionDTO auction;

    /**
     * empty constructor of the class
     */
    public CreateAuctionController() {
		auction = new AuctionDTO();
	}

    /**
     * get the auctionDTO of the current auctioncontroller
     * @return the auctionDTO of the current auctioncontroller
     */
    public AuctionDTO getAuction() {
            return auction;
    }

    /**
     * set the auctionDTO of the current auctioncontroller
     * @param  auction the auctionDTO of the current auctioncontroller
     */
    public void setAuction(AuctionDTO auction) {
            this.auction = auction;
    }

    /**
     * function that registers an auction and make it persistent on the database
     * @param objectid the objectid of the object which will be registered
     * @param numberauction the progressive number of the object (used in case of draw in the auctions)
     * @return the homepage if succeds, the current page otherwise
     */
    public String register(int objectid, int numberauction) {
            auction.setObjectid(objectid);
            auction.setNumberauction(numberauction);
            if ( objectsession.auctioncheck(auction) ) {
                auctionsession.save(auction);
                return "/user/welcome?faces-redirect=true"; 
            } else {
                return "/user/createauction?faces-redirect=true&alreadyex=true";
            }
    }

}
