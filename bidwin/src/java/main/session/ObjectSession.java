/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

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
import main.Objects;
import main.dto.AuctionDTO;
import main.dto.ObjectsDTO;

/**
 *
 * @author Emanuele
 */
@Stateless
public class ObjectSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    /**
     * save the object on the db
     * @param object the object that will be saved on the db
     */
    public void save(ObjectsDTO object) {
	Objects newobject = new Objects(object,usersession.getPrincipalUser());
	em.persist(newobject);
    }

    /**
     * get the list of objects of the current user
     * @return the list of objects of the current user
     */
    public List<Objects> getMyObjects(){
        try {
        Query jpqlQuery = em.createNativeQuery("select * from objects o where o.Object_id  =  (select max(o1.Object_id) as object_id from objects o1 where ( o.Username = ?1  and  o.Username = o1.Username  and  o.ObjectName = o1.ObjectName  and o.ObjectType = o1.ObjectType  and  o.Description = o1.Description  and  o.ImageLink = o1.ImageLink) )",Objects.class);
        jpqlQuery.setParameter(1, usersession.getPrincipalUsername() );
        List<Objects> results = (List<Objects>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     *
     * @param auction
     * @return
     */
    public boolean auctioncheck(AuctionDTO auction) {
        for (Auction a : this.getAllAuctions()) {
            if ( auction.getObjectid().equals( a.getObjectid().getObjectid() )) {
                return false;
            }
        }
        return true;
    }

    /**
     * get the object from the object id
     * @param objectid the object id used to retrieve the object
     * @return the object from the object id
     */
    public Objects getObjectFromId(int objectid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from objects where Object_id = ?1",Objects.class);
        jpqlQuery.setParameter(1, objectid );
        Objects result = (Objects) jpqlQuery.getSingleResult();
        return result;
        } catch(NoResultException e) { 
            return null;
        }
    }

    /**
     * get all the auctions
     * @return a list that contains all the auctions (or null if it's empty)
     */
    public List<Auction> getAllAuctions(){
        try {
        Query jpqlQuery = em.createNativeQuery("Select auction.* from auction",Auction.class);
        List<Auction> results = (List<Auction>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
