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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Notifications.findByNotificationtype", query = "SELECT n FROM Notifications n WHERE n.notificationtype = :notificationtype")})
public class Notifications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "notificationid")
    private Integer notificationid;
    @Column(name = "notificationtype")
    private Integer notificationtype;
    @JoinColumn(name = "username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Users username;
    @JoinColumn(name = "auction_id", referencedColumnName = "Auction_id")
    @ManyToOne(optional = false)
    private Auction auctionId;

    public Notifications() {
    }

    public Notifications(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public Integer getNotificationtype() {
        return notificationtype;
    }

    public void setNotificationtype(Integer notificationtype) {
        this.notificationtype = notificationtype;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public Auction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Auction auctionId) {
        this.auctionId = auctionId;
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
