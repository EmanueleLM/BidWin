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
    
    
    public void save(AuctionDTO object) {
	Auction newauction = new Auction();
        
        // cerca object
	em.persist(newauction);
    }

}
