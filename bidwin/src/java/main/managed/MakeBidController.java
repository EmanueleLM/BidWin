package main.managed;

import main.dto.BidDTO;
import main.session.BidSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Mago
 */
@ManagedBean(name="newbidBean")
@RequestScoped
public class MakeBidController {

	@EJB
	private BidSession bidsession;

	private BidDTO bid;


        public MakeBidController() {
		bid = new BidDTO();
	}

	public BidDTO getBid() {
		return bid;
	}

	public void setBid(BidDTO bid) {
		this.bid = bid;
	}

        public String makeBid() {
                bidsession.save(bid);
                return "/welcome?faces-redirect=true"; 
	}

}