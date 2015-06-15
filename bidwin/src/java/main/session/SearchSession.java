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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import main.Auction;
import main.Users;


/**
 *
 * @author Mago
 */
@Stateless
public class SearchSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    
    public List<Users> getUsersByName(String str){
        try {
        Query jpqlQuery = em.createNativeQuery("Select users.* from users where  (users.Username LIKE % ?1 %  or  users.Name = ?1  or  users.Surname = ?1) and users.Username <> ?2",Users.class);
        jpqlQuery.setParameter(1, str );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Users> results = (List<Users>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    public List<Auction> getHisOpenedAuctions(String username){
        Date d = new Date(System.currentTimeMillis());
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction,objects where  auction.Object_id=objects.Object_id  and  auction.EndTime > ?1  and  objects.Username = ?2",Auction.class);
        jpqlQuery.setParameter(1, d );
        jpqlQuery.setParameter(2, username );
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

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
