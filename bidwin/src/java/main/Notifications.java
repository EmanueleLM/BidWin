/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide
 */
@Entity
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notifications n"),
    @NamedQuery(name = "Notifications.findByNotificationid", query = "SELECT n FROM Notifications n WHERE n.notificationid = :notificationid"),
    @NamedQuery(name = "Notifications.findByUsername", query = "SELECT n FROM Notifications n WHERE n.username = :username"),
    @NamedQuery(name = "Notifications.findByAuctionId", query = "SELECT n FROM Notifications n WHERE n.auctionId = :auctionId"),
    @NamedQuery(name = "Notifications.findByNotificationtype", query = "SELECT n FROM Notifications n WHERE n.notificationtype = :notificationtype")})
public class Notifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificationid")
    private Integer notificationid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auction_id")
    private int auctionId;
    @Column(name = "notificationtype")
    private Integer notificationtype;

    /**
     *empty contructor of the class
    *1 - user wins the auction
    *2 - user loses the auction, somebody wins
    *3 - user loses the auction, nobody wins
    *4 - my auction, somebody wins
    *5 - my auction, nobody wins
    *6 - my auction, no partecipants
    */
    public Notifications() {
    }

    /**
     * contructor with parameters
     *1 - user wins the auction
     *2 - user loses the auction, somebody wins
     *3 - user loses the auction, nobody wins
     *4 - my auction, somebody wins
     *5 - my auction, nobody wins
     *6 - my auction, no partecipants
     * @param notificationid the unique id of the notifications row
     */
    public Notifications(Integer notificationid) {
        this.notificationid = notificationid;
    }

    /**
     * contructor with fully parameters
     *1 - user wins the auction
     *2 - user loses the auction, somebody wins
     *3 - user loses the auction, nobody wins
     *4 - my auction, somebody wins
     *5 - my auction, nobody wins
     *6 - my auction, no partecipants
     * @param user the user who recieves the notification
     * @param auction the auction related to the notification
     * @param notificationtype the kind of notiifcation
     */
    public Notifications(Users user, Auction auction, Integer notificationtype) {
        
        this.username              = user.getUsername();
        this.auctionId             = auction.getAuctionid();
        this.notificationtype      = notificationtype;
    }

    /**
     * get the unique id of a given notification
     * @return the unique id of a notification
     */
    public Integer getNotificationid() {
        return notificationid;
    }

    /**
     * set the unique id of a given notification
     * @param notificationid the unique id of a given notification
     */
    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    /**
     * get the username of the user who recieved a notification
     * @return the username of the user who recieves the notification
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username of the user who recieved a notification
     * @param username the username of the user who recieved a notification
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the auctionid of the auction related to a notification
     * @return the auctionid of the auction related to a notification
     */
    public int getAuctionId() {
        return auctionId;
    }

    /**
     * set the auctionid of the auction related to a notification
     * @param auctionId the auctionid of the auction related to a notification
     */
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    /**
     * get the kind of notification ( see the legenda or the javadoc of the contructor)
     * @return the kind of notification
     */
    public Integer getNotificationtype() {
        return notificationtype;
    }

    /**
     * set the kind of notification ( see the legenda or the javadoc of the contructor)
     * @param notificationtype the kind of notification ( see the legenda or the javadoc of the contructor)
     */
    public void setNotificationtype(Integer notificationtype) {
        this.notificationtype = notificationtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationid != null ? notificationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notifications)) {
            return false;
        }
        Notifications other = (Notifications) object;
        return !((this.notificationid == null && other.notificationid != null) || (this.notificationid != null && !this.notificationid.equals(other.notificationid)));
    }

    @Override
    public String toString() {
        return "main.Notifications[ notificationid=" + notificationid + " ]";
    }

}
