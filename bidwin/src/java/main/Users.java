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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ga
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByRanking", query = "SELECT u FROM Users u WHERE u.ranking = :ranking"),
    @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address"),
    @NamedQuery(name = "Users.findByPaymentInfo", query = "SELECT u FROM Users u WHERE u.paymentInfo = :paymentInfo"),
    @NamedQuery(name = "Users.findByAuctionCounter", query = "SELECT u FROM Users u WHERE u.auctionCounter = :auctionCounter"),
    @NamedQuery(name = "Users.findByBirthdate", query = "SELECT u FROM Users u WHERE u.birthdate = :birthdate"),
    @NamedQuery(name = "Users.findByCredits", query = "SELECT u FROM Users u WHERE u.credits = :credits"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ranking")
    private int ranking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PaymentInfo")
    private String paymentInfo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AuctionCounter")
    private int auctionCounter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Credits")
    private int credits;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Bid> bidCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "username")
    private Objects objects;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String name, String surname, String email, int ranking, String address, String paymentInfo, int auctionCounter, Date birthdate, int credits, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ranking = ranking;
        this.address = address;
        this.paymentInfo = paymentInfo;
        this.auctionCounter = auctionCounter;
        this.birthdate = birthdate;
        this.credits = credits;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public int getAuctionCounter() {
        return auctionCounter;
    }

    public void setAuctionCounter(int auctionCounter) {
        this.auctionCounter = auctionCounter;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(Collection<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    public Objects getObjects() {
        return objects;
    }

    public void setObjects(Objects objects) {
        this.objects = objects;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.username != null && !this.username.equals(other.username)) || (null != other.username && this.username == null));
    }

    @Override
    public String toString() {
        return "main.Users[ username=" + username + " ]";
    }
    
}
