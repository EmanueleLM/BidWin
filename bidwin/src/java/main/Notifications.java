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
 * @author Mago
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

    public Notifications() {
    }

    public Notifications(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public Notifications(Users user, Auction auction, Integer notificationtype) {
        
        this.username              = user.getUsername();
        this.auctionId             = auction.getAuctionid();
        this.notificationtype      = notificationtype;
    }

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getNotificationtype() {
        return notificationtype;
    }

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