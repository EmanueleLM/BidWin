/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import main.Auction;
import main.Bid;
import main.Chart;
import main.Notifications;
import main.Users;
import main.dto.BidDTO;

/**
 *
 * @author Davide
 */
@Stateless
public class BidSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    private Date date;
    
     
    /**
     * save the current bid on the db
     * @param bid  the current bid
     */   
    public void save(BidDTO bid) {
        
        Users user = usersession.getPrincipalUser();
	Bid newbid = new Bid( user.getUsername(), bid.getAuctionid(), bid.getValue() );
	em.persist(newbid);
        user.setCredits( user.getCredits() - 2 );
        em.merge(user);
    }

    /**
     * set the notify field on the auction table
     * @param auction the auction where the notify filed will be set
     */
    public void notifyTrue(Auction auction) {
        auction.setNotify(1);
        em.merge(auction);
    }

    /**
     * return the results of the query that looks for the open auctions where the current user made at least a bid
     * @return the open auctions where the current user made at least a bid
     */
    public List<Auction> getMyOpenedBids(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select distinct(auction.Auction_id), auction.Object_id, auction.StartTime, auction.EndTime from auction,bid where  auction.Auction_id=bid.Auction_id  and  auction.EndTime > ?1  and  bid.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * return the results of the query that looks for the closed auctions where the current user made at least a bid
     * @return the closed auctions where the current user made at least a bid
     */
    public List<Auction> getMyClosedBids(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select distinct(auction.Auction_id), auction.Object_id, auction.StartTime, auction.EndTime from auction,bid where  auction.Auction_id=bid.Auction_id  and  auction.EndTime < ?1  and  bid.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    public boolean bidcheck(BidDTO bid) {
        Auction auction = getAuctionFromId( bid.getAuctionid() );
        date = new Date(System.currentTimeMillis());
        return (auction.getEndTime().after( date ));
    }

    /**
     * get the specified auction from the id
     * @param auctionid the id used to find the auction
     * @return the auction that matches with the id
     */
    public Auction getAuctionFromId(int auctionid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from auction where Auction_id = ?1",Auction.class);
        jpqlQuery.setParameter(1, auctionid );
        Auction result = (Auction) jpqlQuery.getSingleResult();
        return result;
        } catch(NoResultException e) {
            return null;
        }
    }


    /**
     * get the specified bids from an auction, given the auction id
     * @param auctionid the id used to find the auction
     * @return the bids that matches with the auction id
     */
    public List<Bid> getMySpecifiedBids(int auctionid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select bid.* from bid where bid.Username <> 'user'  and  bid.Auction_id= ?1 order by value asc",Bid.class);
        jpqlQuery.setParameter(1, auctionid );
        List<Bid> results = (List<Bid>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * get the list of auction that need to be notified (notify field)
     * @return the list of auction that need to be notified (notify field)
     */
    public List<Auction> auctionsToNotify(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from auction where auction.EndTime < ?1  and  auction.notify = 0",Auction.class);
        jpqlQuery.setParameter(1, date );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * get the partecipants to a given auction
     * @param auctionid the field used to retrieve the specified auction
     * @return the partecipants to a given auction
     */
    public List<Users> partecipants(int auctionid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select distinct(users.Username), users.Name, users.Surname, users.Email, users.Ranking, users.Address, users.PaymentInfo, users.AuctionCounter, users.Birthdate, users.Credits, users.Password  from users, bid  where  users.Username = bid.Username  and  bid.Auction_id = ?1",Users.class);
        jpqlQuery.setParameter(1, auctionid );
        List<Users> results = (List<Users>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * return true if a user is the actual winner of the open auction
     * @param auctionid the auction where the check is performed
     * @param username the username used for the check
     * @return a boolean that says whether the user is the winner or not
     */
    public boolean isWinner(int auctionid, String username) {
        try {
        Query jpqlQuery = em.createNativeQuery("Select username, min(value) ,count(*) as count from bid b where  b.Auction_id= ?1 group by value asc having count(*)=1",Chart.class);
        jpqlQuery.setParameter(1, auctionid );
        Chart chart = (Chart) jpqlQuery.getResultList().get(0);
        return chart.getUsername().equals( username );
        } catch(TransactionRolledbackLocalException | ArrayIndexOutOfBoundsException e) { 
            return false;
        } catch(NullPointerException | PersistenceException e) {
            return false;
        }
    }

    /**
     * returns the actual winner of an auction, given the auction id
     * @param auctionid the id of the auction
     * @return the winner ( if present, a messagge otherwise)
     */
    public String getWinner(int auctionid) {
        try {
        Query jpqlQuery = em.createNativeQuery("Select notifications.* from notifications where notifications.auction_id = ?1 and (notifications.notificationtype = 1 or notifications.notificationtype = 10)",Notifications.class);
        jpqlQuery.setParameter(1, auctionid );
        Notifications results = (Notifications) jpqlQuery.getSingleResult();
        return results.getUsername();
        } catch(NoResultException e) { 
            return "Nobody won";
        }
    }

}
