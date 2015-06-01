/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ga
 */
@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"),
    @NamedQuery(name = "Bid.findByUsername", query = "SELECT b FROM Bid b WHERE b.bidPK.username = :username"),
    @NamedQuery(name = "Bid.findByAuctionid", query = "SELECT b FROM Bid b WHERE b.bidPK.auctionid = :auctionid"),
    @NamedQuery(name = "Bid.findByValue", query = "SELECT b FROM Bid b WHERE b.value = :value")})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BidPK bidPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Value")
    private int value;
    @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "Auction_id", referencedColumnName = "Auction_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auction auction;

    public Bid() {
    }

    public Bid(BidPK bidPK) {
        this.bidPK = bidPK;
    }

    public Bid(BidPK bidPK, int value) {
        this.bidPK = bidPK;
        this.value = value;
    }

    public Bid(String username, int auctionid) {
        this.bidPK = new BidPK(username, auctionid);
    }

    public BidPK getBidPK() {
        return bidPK;
    }

    public void setBidPK(BidPK bidPK) {
        this.bidPK = bidPK;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bidPK != null ? bidPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.bidPK == null && other.bidPK != null) || (this.bidPK != null && !this.bidPK.equals(other.bidPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Bid[ bidPK=" + bidPK + " ]";
    }
    
}
