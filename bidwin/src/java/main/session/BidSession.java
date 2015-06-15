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
import main.Auction;
import main.Bid;
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
    private ObjectSession bidsession;
    
    @EJB
    private UserSession usersession;
    
    private Date date;
    
    
    public void save(BidDTO bid) {
        
        
	Bid newobject = new Bid();
	em.persist(newobject);
    }
    
    public List<Auction> getMyOpenBids(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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
        
        
    public List<Auction> getMyClosedBids(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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
        
        public boolean auctioncheck(BidDTO bid) {
        Auction auction = getAuctionFromId( bid.getAuctionid() );
        Date date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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

}
