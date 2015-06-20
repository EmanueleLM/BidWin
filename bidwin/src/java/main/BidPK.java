/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Emanuele
 */
@Embeddable
public class BidPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Auction_id")
    private int auctionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Value")
    private int value;

    /**
     *empty contructor of teh class
     */
    public BidPK() {
    }

    /**
     * contructor of BidPK with parameters
     * @param username the username of the user  who made the bid
     * @param auctionid the auction related to teh bid
     * @param value the value of teh bid
     */
    public BidPK(String username, int auctionid, int value) {
        this.username = username;
        this.auctionid = auctionid;
        this.value = value;
    }

    /**
     * get the username of the user who made the bid
     * @return the username of the user who made teh bid
     */
    public String getUsername() {
        return username;
    }

    /**
     *set the username of the user who made the bid
     * @param username the name of the username who made the bid
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the auctionid related to the bid
     * @return teh auctionid of the auction related to the bid
     */
    public int getAuctionid() {
        return auctionid;
    }

    /**
     * set the auctionid of the bid
     * @param auctionid teh auctionid of the auction related to teh bid
     */
    public void setAuctionid(int auctionid) {
        this.auctionid = auctionid;
    }

    /**
     * get the value of the bid
     * @return the value of this specific bid
     */
    public int getValue() {
        return value;
    }

    /**
     * set the value of the bid
     * @param value the value of the bid
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (int) auctionid;
        hash += (int) value;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BidPK)) {
            return false;
        }
        BidPK other = (BidPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if (this.auctionid != other.auctionid) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.BidPK[ username=" + username + ", auctionid=" + auctionid + ", value=" + value + " ]";
    }
    
}
