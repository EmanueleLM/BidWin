/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.ArrayList;
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
import main.Bid;
import main.Notifications;
import main.Objects;
import main.Users;
import main.dto.ObjectsDTO;

/**
 *
 * @author Davide
 */
@Stateless
public class NotificationsSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    @EJB
    private BidSession bidsession;
    
    
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

    public Bid getUserBid(int auctionid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select bid.* from bid where bid.Username = 'user'  and  bid.Auction_id= ?1",Bid.class);
        jpqlQuery.setParameter(1, auctionid );
        Bid results = (Bid) jpqlQuery.getSingleResult();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
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

    public List<Users> getUsersToVote(){
        List<Users> users = new ArrayList<>();
        for (Notifications n : getMyNotifications()) {
            Auction a = bidsession.getAuctionFromId( n.getAuctionId() );
            if ( (n.getNotificationtype() == 1) && (getUserBid(n.getAuctionId()).getBidPK().getValue() == 300002) ) {
                users.add( owner(a) );
            }
        }
        return users;
    }

    public List<String> getStringNotifications(){
        List<String> list = new ArrayList<>();
        for (Notifications n : getMyNotifications()) {
            Auction a = bidsession.getAuctionFromId( n.getAuctionId() );
            a.getObjectid().getObjectName(); // nome oggetto e altre informazioni
            owner(a); // proprietario dell'asta (se l'hai vinta devi votarlo)
            bidsession.getWinner( n.getAuctionId() ); // vincitore dell'asta
            switch(n.getNotificationtype()) {
                case 1:
                    list.add("/faces/resources/images/winner.png");
                    list.add("You won the auction on the " + a.getObjectid().getObjectName() +  " Please vote " + owner(a).getUsername() + ".");
                break;
                case 2:
                    list.add("/faces/resources/images/loser.png");
                    list.add("You lost the auction on the " + a.getObjectid().getObjectName() +".");
                break;
                case 3:
                    list.add("/faces/resources/images/draw.png");
                    list.add("Nobody won the " + a.getObjectid().getObjectName() + ".Check the auction again.");
                break;
                case 4:
                    list.add("/faces/resources/images/hint.png");
                    list.add( bidsession.getWinner( n.getAuctionId() ) + " won the auction on your " + a.getObjectid().getObjectName() + '\n' + "  Remember to send the object.");
                break;
                case 5:
                    list.add("/faces/resources/images/hint.png");
                    list.add("Nobody won your auction on " + a.getObjectid().getObjectName() + ".Create the auction again.");
                break;
                default:
                    list.add("/faces/resources/images/cola.png");
                    list.add("Buy a nuke cola.");
                break;    
            }
            // informazioni (nei casi in cui non ovvie)
            // senza usare altre query !!!

            
            // componi e poi fai  list.add( qua la stringa );
        }
        return list;
    }

}
