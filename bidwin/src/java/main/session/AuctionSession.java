/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
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
    
    private Date date;
    
    
    public void save(AuctionDTO object) {
	Auction newauction = new Auction();
        
        // cerca object
	em.persist(newauction);
    }

    public List<Auction> getOpenedAuction(){
        date = new Date();
        date.getTime();
        try {
            List<Auction> auctions = (ArrayList<Auction>)em.createQuery("SELECT a.* FROM auction a WHERE a.EndTime>:end").setParameter("end", date ).getResultList();
            return auctions;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
