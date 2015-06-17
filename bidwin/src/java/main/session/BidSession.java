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
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import main.Auction;
import main.Bid;
import main.Chart;
import main.Users;
import main.dto.BidDTO;

/**
 *
 * @author Mago
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
    
    
    public void save(BidDTO bid) {
        
        Users user = usersession.getPrincipalUser();
	Bid newbid = new Bid( user.getUsername(), bid.getAuctionid(), bid.getValue() );
	em.persist(newbid);
        user.setCredits( user.getCredits() - 2 );
        em.merge(user);
    }

    public void notifyTrue(Auction auction) {
        auction.setNotify(1);
        em.merge(auction);
    }

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

    public boolean getWinner(int auctionid, String username) {
        try {
        Query jpqlQuery = em.createNativeQuery("Select username, min(value) ,count(*) as count from bid b where  b.Auction_id= ?1 group by value asc having count(*)=1",Chart.class);
        jpqlQuery.setParameter(1, auctionid );
        Chart chart = (Chart) jpqlQuery.getResultList().get(0);
        System.out.println( chart.getUsername() + " " + usersession.getPrincipalUsername());
        return chart.getUsername().equals( username );
        } catch(NoResultException e) { 
            return false;
        } catch(PersistenceException e) {
            return false;
        }
        
    }

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

    public List<Bid> getMySpecifiedBids(int auctionid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select bid.* from bid where  bid.Auction_id= ?1 order by value asc",Bid.class);
        jpqlQuery.setParameter(1, auctionid );
        List<Bid> results = (List<Bid>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    public List<Auction> auctionsToNotify(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from auction where auction.EndTime > ?1  and  auction.notify = 0",Auction.class);
        jpqlQuery.setParameter(1, date );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

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

}
