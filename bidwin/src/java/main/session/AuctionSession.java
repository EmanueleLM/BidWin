/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import main.Objects;
import main.Auction;
import main.Bid;
import main.dto.AuctionDTO;

/**
 *
 * @author Davide
 */
@Stateless
public class AuctionSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private ObjectSession objectsession;
    
    @EJB
    private UserSession usersession;
    
    private Date date;
    
    /**
     * save the current auction on the db
     * @param auction the current auction
     */
    public void save(AuctionDTO auction) {
        date = new Date(System.currentTimeMillis());
        Date dateEnd = new Date( System.currentTimeMillis() + (auction.getNumberauction()*60*1000) );
        Objects object = objectsession.getObjectFromId(auction.getObjectid());
        
	Auction newauction = new Auction(date, dateEnd, object, false);
	em.persist(newauction);
        
        Auction a = getMyOpenedAuctions().get( getMyOpenedAuctions().size() - 1 );
        Bid newbid = new Bid("user", a.getAuctionid(), 300002 );
	em.persist(newbid);
    }

    /**
     * return the results of the query that looks for the open auctions of the current user
     * @return the open auctions of the current user
     */
    public List<Auction> getMyOpenedAuctions(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,objects where  auction.Object_id=objects.Object_id  and  auction.EndTime > ?1  and  objects.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * return the results of the query that looks for the closed auctions of the current user
     * @return the closed auctions of the current user
     */
    public List<Auction> getMyClosedAuctions(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,objects where  auction.Object_id=objects.Object_id  and  auction.EndTime < ?1  and  objects.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * return the results of the query that looks for all the open auctions 
     * @return all the open auctions 
     */
    public List<Auction> getAllOpenedAuctions(){
        date = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,objects where  auction.Object_id=objects.Object_id  and  auction.EndTime > ?1  and  objects.Username <> ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }
    
    /**
     * function that returns all the bids from an auction, given the specified auction id
     * @param auctionid the identifier of the auction
     * @return the list of bids
     */
    public List<Bid> getBidsFromId(int auctionid) {
        try {
        Query jpqlQuery = em.createNativeQuery("Select bid.* from bid where bid.Auction_id= ?1 ",Bid.class);
        jpqlQuery.setParameter(1, auctionid );
        List<Bid> results = (List<Bid>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }
    
    /**
     * function that returns all the auctions filterd by object type
     * @param str the object type
     * @return all the auctions that match the object type
     */
    public List<Auction> getAuctionsByTag(String str){
        Date d = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,objects where  auction.Object_id=objects.Object_id  and  objects.ObjectType = ?1  and  auction.EndTime > ?2  and  objects.Username <> ?3",Auction.class);
        jpqlQuery.setParameter(1, str );
        jpqlQuery.setParameter(2, d );
        jpqlQuery.setParameter(3, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
