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
    
    /**
     * save the current notification on the db
     * @param user the user who recieves the notification
     * @param auction the auction related to the notification
     * @param notificationtype the kind of notification 
        1 - user wins the auction
        10 - user wins the auction and has already voted
        2 - user loses the auction, somebody wins
        3 - user loses the auction, nobody wins
        4 - my auction, somebody wins
        5 - my auction, nobody wins
        6 - my auction, no partecipants
        */
    public void save(Users user, Auction auction, Integer notificationtype) {
        
	Notifications newnotifications = new Notifications( user, auction, notificationtype );
	em.persist(newnotifications);
    }

    /**
     * functions used to replicate the object (this function is useful when there's a draw in an auction.In this case 
     * a new object is replicated in order to give the opportunity to the user to create a new auction: Moreover we do not loose 
     * the hisory of the auctions)
     * @param user the user who owns the object
     * @param objectName the name of the object
     * @param objectType the kind of object
     * @param description the description of the object
     * @param imageLink the image link of the object
     */
    public void replicateobject(Users user, String objectName, String objectType, String description, String imageLink) {
        ObjectsDTO object = new ObjectsDTO();
        object.setObjectName(objectName);
        object.setObjectType(objectType);
        object.setDescription(description);
        object.setImageLink(imageLink);
        
	Objects newobject = new Objects(object, user);
	em.persist(newobject);
    }

    /**
     * return the owner of an auction, given the auction
     * @param auction the auction
     * @return the owner of an auction, given the auction
     */
    public Users owner(Auction auction){
        return auction.getObjectid().getUsername();
    }

    /**
     * get the list of notifications of the current user
     * @return the list of notifications of the current user
     */
    public List<Notifications> getMyNotifications(){
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from notifications where notifications.username = ?1",Notifications.class);
        jpqlQuery.setParameter(1, usersession.getPrincipalUsername() );
        List<Notifications> results = (List<Notifications>) jpqlQuery.getResultList();
        Collections.reverse(results);
        return results;
        } catch(NoResultException e) { 
            List<Notifications> results = new ArrayList<>();
            return results;
        }
    }

    /**
     * get the list of the users that needs a vote
     * @return the list of the users that needs a vote
     */
    public List<Users> getUsersToVote(){
        List<Users> users = new ArrayList<>();
        for (Notifications n : getMyNotifications()) {
            Auction a = bidsession.getAuctionFromId( n.getAuctionId() );
            if (n.getNotificationtype() == 1) {
                users.add( owner(a) );
            }
        }
        return users;
    }

    /**
     * set the list of the users that needs a vote
     * @param username username of the user who has to vote
     * @return the list of the users that needs a vote
     */
    public int setUserVoted(String username){
        for (Notifications n : getMyNotifications()) {
            Auction a = bidsession.getAuctionFromId( n.getAuctionId() );
            if ( (n.getNotificationtype() == 1) && (owner(a).getUsername().equals( username )) ) {
                n.setNotificationtype(10);
                em.merge(n);
                return 1;
            }
        }
        return -1;
    }

    /**
     * get the whole list of notifications that will be displayed on the homepage
     * @return the whole list of notifications that will be displayed on the homepage
     */
    public List<String> getStringNotifications(){
        List<String> list = new ArrayList<>();
        for (Notifications n : getMyNotifications()) {
            Auction a = bidsession.getAuctionFromId( n.getAuctionId() );
            String object = a.getObjectid().getObjectName(); 
            String owner = owner(a).getUsername();
            String winner = bidsession.getWinner( n.getAuctionId() );
            
            switch(n.getNotificationtype()) {
                case 1:
                    list.add("/faces/resources/images/winner.png");
                    list.add("You won the auction on the " + object +  " Please vote " + owner + " in Rank Users .");
                break;
                case 2:
                    list.add("/faces/resources/images/loser.png");
                    list.add("You lost the auction on the " + object +  " created by " + owner + ".");
                break;
                case 3:
                    list.add("/faces/resources/images/draw.png");
                    list.add("Nobody won the " + object +  " created by " + owner + ".");
                break;
                case 4:
                    list.add("/faces/resources/images/hint.png");
                    list.add( winner + " won the auction on your " + object + '\n' + "  Remember to send the object.");
                break;
                case 5:
                    list.add("/faces/resources/images/hint.png");
                    list.add("Nobody won your auction on " + object + '\n' + ".Create the auction again.");
                break;
                case 6:
                    list.add("/faces/resources/images/hint.png");
                    list.add("Nobody has participated in your auction on " + object + '\n' + ".Create the auction again.");
                break;
                case 10:
                    list.add("/faces/resources/images/winner.png");
                    list.add("You won the auction on the " + object +  " created by " + owner + ".");
                break;
                default:
                    list.add("/faces/resources/images/cola.png");
                    list.add("Buy a nuke cola.");
                break;    
            }
        }
        list.add("Welcome");
        return list;
    }

}
