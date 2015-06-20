/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emanuele
 */
@Entity
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"),
    @NamedQuery(name = "Bid.findByUsername", query = "SELECT b FROM Bid b WHERE b.bidPK.username = :username"),
    @NamedQuery(name = "Bid.findByAuctionid", query = "SELECT b FROM Bid b WHERE b.bidPK.auctionid = :auctionid"),
    @NamedQuery(name = "Bid.findByValue", query = "SELECT b FROM Bid b WHERE b.bidPK.value = :value")})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BidPK bidPK;
    @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "Auction_id", referencedColumnName = "Auction_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auction auction;

    /**
     * constructor (empty) of the class
     */
    public Bid() {
    }

    /**
     * contructor whit parameters
     * @param username the user who made the bid
     * @param auctionid the auction related to the bid
     * @param value the value of the bid
     */
    public Bid(String username, int auctionid, int value) {
        this.bidPK = new BidPK(username, auctionid, value);
    }

    /**
     * get the bidPK (delegates some functionalities as managing external keys etc.)
     * @return the bidPK
     */
    public BidPK getBidPK() {
        return bidPK;
    }

    /**
     * set the bidPK(delegates some functionalities as managing external keys etc.)
     * @param bidPK the bidPK
     */
    public void setBidPK(BidPK bidPK) {
        this.bidPK = bidPK;
    }

    /**
     * get the user who made teh bid
     * @return the user who made the bid
     */
    public Users getUsers() {
        return users;
    }

    /**
     * set the user who made the bid
     * @param users who made the bid
     */
    public void setUsers(Users users) {
        this.users = users;
    }

    /**
     * the auction related to the bid
     * @return the auction related to the bid
     */
    public Auction getAuction() {
        return auction;
    }

    /**
     * set the auction related to the bid
     * @param auction the auction related to teh bid
     */
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
        return !((this.bidPK == null && other.bidPK != null) || (this.bidPK != null && !this.bidPK.equals(other.bidPK)));
    }

    @Override
    public String toString() {
        return "src.Bid[ bidPK=" + bidPK + " ]";
    }
    
}
