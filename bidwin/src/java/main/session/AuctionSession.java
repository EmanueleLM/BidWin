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
    
    private Date date;
    
    
    public void save(AuctionDTO auction) {
	Auction newauction = new Auction();
        // cerca object
	em.persist(newauction);
    }

    public List<Auction> getOpenedAuction(){
        date = new Date();
        date.getTime();
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from auction where EndTime > ?1",Auction.class);
        jpqlQuery.setParameter(1, date );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
