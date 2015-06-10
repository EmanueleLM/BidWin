/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    private UserSession usersession;
    
    
    public void save(BidDTO bid) {
	Bid newobject = new Bid(bid,usersession.getPrincipalUser(), new Auction() );
        
        // cerca auction
	em.persist(newobject);
    }

}
