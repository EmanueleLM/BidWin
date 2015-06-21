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
    
    /**
     * function used by the serach engine to retrieve all the users that match in some way the keyword
     * The username is searched with a similarity function (LIKE %initials), while name and surname need to match perfectly
     * @param str the parameter used to perform the serach
     * @return all the users that match in some way the keyword
     */
    public List<Users> getUsersByName(String str){
        try {
        Query jpqlQuery = em.createNativeQuery("Select users.* from users where  (users.Username LIKE  ?1   or  users.Name = ?1  or  users.Surname = ?1) and users.Username <> ?2",Users.class);
        jpqlQuery.setParameter(1, "%" + str + "%" );
        jpqlQuery.setParameter(2, usersession.getPrincipalUsername() );
        List<Users> results = (List<Users>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * get all the open auctions of a user
     * @param username the name of the user used to retrieve the auction's list
     * @return all the open auctions of a user
     */
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

    /**
     * get all the open auctions where the object type matches with the keyword used in the search engine
     * @param str the word used to perform the search
     * @return all the open auctions where the object type matches with the keyword used in the search engine
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
