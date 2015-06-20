/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Emanuele
 */
@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findByAuctionid", query = "SELECT a FROM Auction a WHERE a.auctionid = :auctionid"),
    @NamedQuery(name = "Auction.findByStartTime", query = "SELECT a FROM Auction a WHERE a.startTime = :startTime"),
    @NamedQuery(name = "Auction.findByEndTime", query = "SELECT a FROM Auction a WHERE a.endTime = :endTime")})
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Auction_id")
    private Integer auctionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Notify")
    private int notify;
    @JoinColumn(name = "Object_id", referencedColumnName = "Object_id")
    @OneToOne(optional = false)
    private Objects objectid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private Collection<Bid> bidCollection;

    /**
     * Class constructor
     */
    public Auction() {
    }

    /**
     * Contructor that returns the auction with the specified parameters
     * @param startTime starting time of the auction
     * @param endTime ending time of teh auction
     * @param object the object of the auction
     * @param notify this parameter specifies if the auction is ended or not
     */
    public Auction(Date startTime, Date endTime, Objects object, boolean notify) {
        
        this.startTime     = startTime;
        this.endTime       = endTime;
        this.objectid      = object;
        this.notify        = notify ? 1 : 0;
    }

    /**
     * Contructor that returns the auction with the specified parameters
     * @param auctionid  the auction univoque identifier
     * @param startTime   starting time of the auction
     * @param endTime ending time of teh auction
     * @param notify this parameter specifies if the auction is notified or not
     */
    public Auction(Integer auctionid, Date startTime, Date endTime, boolean notify) {
        this.auctionid = auctionid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notify  = notify ? 1 : 0;
    }

    /**
     * function that returns the auction identifier
     * @return the auction identifier
     */
    public Integer getAuctionid() {
        return auctionid;
    }

    /**
     * set the auction identifier
     * @param auctionid the auction identifier
     */
    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    /**
     * return the start time of an auction
     * @return the start time of the auction
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * set the auction start time
     * @param startTime the auction's start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * return the auction's end time
     * @return the auction's end time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * set the auction's end time
     * @param endTime the auction's end time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * return the object related to the auction
     * @return the object related to the auction
     */
    public Objects getObjectid() {
        return objectid;
    }

    /**
     * set the auction's object id 
     * @param objectid the auction's object id
     */
    public void setObjectid(Objects objectid) {
        this.objectid = objectid;
    }

    /**
     * return the integer which informs whether the auction is ended up or not
     * @return the integer which informs whether the auction is ended up or not
     */
    public int getNotify() {
        return notify;
    }

    /**
     * set whether an auction is ended up or not
     * @param notify the parameter is set to 1 if the auction is ended up, 0 otherwise
     */
    public void setNotify(int notify) {
        this.notify = notify;
    }
    
    /**
     * returns the collection of bids related to a specific auction
     * @return the collection of bids related to a specific auction
     */
    @XmlTransient
    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

    /**
     * set the specific bids related to an auction
     * @param bidCollection the bids that belongs to an auction
     */
    public void setBidCollection(Collection<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionid != null ? auctionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        return !((this.auctionid == null && other.auctionid != null) || (this.auctionid != null && !this.auctionid.equals(other.auctionid)));
    }

    @Override
    public String toString() {
        return "src.Auction[ auctionid=" + auctionid + " ]";
    }

}
