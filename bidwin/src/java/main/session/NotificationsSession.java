/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.Collections;
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
import main.Notifications;
import main.Objects;
import main.Users;
import main.dto.ObjectsDTO;

/**
 *
 * @author Mago
 */
@Stateless
public class NotificationsSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    
    public void save(Users user, Auction auction, Integer notificationtype) {
        
	Notifications newnotifications = new Notifications( user, auction, notificationtype );
	em.persist(newnotifications);
    }

    public void replicateobject(Users user, String objectName, String objectType, String description, String imageLink) {
        ObjectsDTO object = new ObjectsDTO();
        object.setObjectName(objectName);
        object.setObjectType(objectType);
        object.setDescription(description);
        object.setImageLink(imageLink);
        
	Objects newobject = new Objects(object, user);
	em.persist(newobject);
    }

    public Users owner(Auction auction){
        return auction.getObjectid().getUsername();
    }

    public List<Notifications> getMyNotifications(){
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from notifications where notifications.username = ?1",Notifications.class);
        jpqlQuery.setParameter(1, usersession.getPrincipalUsername() );
        List<Notifications> results = (List<Notifications>) jpqlQuery.getResultList();
        Collections.reverse(results);
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
