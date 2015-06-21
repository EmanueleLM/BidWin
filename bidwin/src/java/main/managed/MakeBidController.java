package main.managed;

import main.dto.BidDTO;
import main.session.BidSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Davide
 */
@ManagedBean(name="newbidBean")
@RequestScoped
public class MakeBidController {

	@EJB
	private BidSession bidsession;

	private BidDTO bid;
        
    /**
     * empty constructor for the class
     */
    public MakeBidController() {
		bid = new BidDTO();
	}

    /**
     * get the bid for the bidDTO
     * @return the bid for the bidDTO
     */
    public BidDTO getBid() {
		return bid;
	}

    /**
     * set the bid for the bidDTO
     * @param bid the bid for the bidDTO
     */
    public void setBid(BidDTO bid) {
            this.bid = bid;
    }

    /**
     * make a bid persistent
     * @param auctionid the auction where the bid is made
     * @param numberbid the number used to see who wins the auction
     * @return the homepage if the metho succeds, the current page otherwise
     */
    public String makebid(int auctionid, float numberbid) {
            bid.setValue( (int)(numberbid*100) );
            bid.setAuctionid(auctionid);
            if ( bidsession.bidcheck(bid) ) {
                bidsession.save(bid);
                return "/user/welcome?faces-redirect=true";
            } else {
                return "/user/makebid?faces-redirect=true&alreadybid=true";
            }
    }

}