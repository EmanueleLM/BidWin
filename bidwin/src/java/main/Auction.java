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
 * @author Ga
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
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndTime")
    @Temporal(TemporalType.DATE)
    private Date endTime;
    @JoinColumn(name = "Object_id", referencedColumnName = "Object_id")
    @OneToOne(optional = false)
    private Objects objectid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private Collection<Bid> bidCollection;

    public Auction() {
    }

    public Auction(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Auction(Integer auctionid, Date startTime, Date endTime) {
        this.auctionid = auctionid;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Objects getObjectid() {
        return objectid;
    }

    public void setObjectid(Objects objectid) {
        this.objectid = objectid;
    }

    @XmlTransient
    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

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
        return "src.main.se2.polimi.java.Auction[ auctionid=" + auctionid + " ]";
    }
    
}
