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
import main.dto.AuctionDTO;

/**
 *
 * @author Mago
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
    
    
    public void save(AuctionDTO auction) {
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
        Date dateEnd = new Date( System.currentTimeMillis() + (2*60*60*1000) + (auction.getNumberauction()*60*1000) );
        Objects object = objectsession.getObjectFromId(auction.getObjectid());
        
	Auction newauction = new Auction(date, dateEnd, object, false);
	em.persist(newauction);
    }

    public List<Auction> getMyOpenedAuctions(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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

    public List<Auction> getMyClosedAuctions(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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

    public List<Auction> getAllOpenedAuctions(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
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

    public List<Auction> getMyOpenedBids(){
        date = new Date( System.currentTimeMillis() + (2*60*60*1000) );
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,bid where  auction.Auction_id=bid.Auction_id  and  auction.EndTime > ?1  and  bid.Username = ?2",Auction.class);
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
        Query jpqlQuery = em.createNativeQuery("Select distinct(auction.*) from auction,bid where  auction.Auction_id=bid.Auction_id  and  auction.EndTime < ?1  and  bid.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, date );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
