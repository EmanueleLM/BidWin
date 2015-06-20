/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.dto.UsersDTO;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author Emanuele
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
    @Column(name = "Password")
    private String password;
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Groups> groupsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Bid> bidCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Objects> objectsCollection;

     /**
     *empty contructor of the class
     */
    public Users() {
    }

    /**
     * contructor with parameters from the DTO (input in registration phase)
     * @param user the userDTO used to generate the user in the registration phase
     */
    public Users(UsersDTO user){
        
        this.username         = user.getUsername();
        this.name             = user.getName();
        this.surname          = user.getSurname();
        this.email            = user.getEmail();
        this.ranking          = 0;
        this.address          = user.getAddress();
        this.paymentInfo      = user.getPaymentInfo();
        this.auctionCounter   = 0;
        this.birthdate        = user.getBirthdate();
        this.credits          = 100;
        this.password         = DigestUtils.sha256Hex( user.getPassword() );
    }

    /**
     * contructor with fully parameters
     * @param username the username of the user
     * @param name the name of the user
     * @param surname the surname of the user
     * @param email the email of the user
     * @param ranking the ranking of the user
     * @param address the address of the user
     * @param paymentInfo the paymentinfo of the user
     * @param auctionCounter the number of auction of a user
     * @param birthdate the birthdate of the user
     * @param credits  the nuember of credits of the user
     * @param password the password of the user
     */
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
        this.password = DigestUtils.sha256Hex( password );
    }
    
    /**
     * get the username from the user
     * @return  the username from the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username for the user
     * @param username the username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

        /**
     * get the name from the user
     * @return  the name from the user
     */
    public String getName() {
        return name;
    }

    /**
     * set the name for the user
     * @param name the name for the user
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * get the surname from the user
     * @return  the surname from the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * set the surname for the user
     * @param surname the surname for the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    /**
     * get the email from the user
     * @return  the email from the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the email for the user
     * @param email the email for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * get the ranking from the user
     * @return  the ranking from the user
     */
    public int getRanking() {
        return ranking;
    }

    /**
     * set the ranking for the user
     * @param ranking the ranking for the user
     */
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    /**
     * get the address from the user
     * @return  the address from the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * set the address for the user
     * @param address the address for the user
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * get the payment info from the user
     * @return  the payment info from the user
     */
    public String getPaymentInfo() {
        return paymentInfo;
    }

    /**
     * set the paymentinfo for the user
     * @param paymentInfo  the payment info for the user
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
    
    /**
     * get the auctioncounter from the user
     * @return  the auctioncounter from the user
     */
    public int getAuctionCounter() {
        return auctionCounter;
    }

    /**
     * set the auction counter for the user
     * @param auctionCounter  the auction counter for the user
     */
    public void setAuctionCounter(int auctionCounter) {
        this.auctionCounter = auctionCounter;
    }
    
    /**
     * get the birthdate from the user
     * @return  the birthdate from the user
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * set the birthdate for the user
     * @param birthdate   the birthdate for the user
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    /**
     * get the credits from the user
     * @return  the credits from the user
     */
    public int getCredits() {
        return credits;
    }

    /**
     * set the credits for the user
     * @param credits the credits for the user
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    /**
     * get the password from the user
     * @return  the password from the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the password for the user
     * @param password the password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * get the collection of the groups from the user
     * @return  the collection of the groups from the user
     */    
    @XmlTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    /**
     * set the collection of groups for the user
     * @param groupsCollection collection of groups for the user
     */
    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }
    
    /**
     * get the collection of bids from the user
     * @return  the collection of bids from the user
     */
    @XmlTransient
    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

    /**
     * set the collection of bids for the user
     * @param bidCollection  collection of bids for the user
     */
    public void setBidCollection(Collection<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

        
    /**
     * get the collection of objects from the user
     * @return  the collection of objects from the user
     */
    @XmlTransient
    public Collection<Objects> getObjectsCollection() {
        return objectsCollection;
    }

    /**
     * set the collection of objects for the user
     * @param objectsCollection  collection of objects for the user
     */
    public void setObjectsCollection(Collection<Objects> objectsCollection) {
        this.objectsCollection = objectsCollection;
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
        return !((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public String toString() {
        return "src.Users[ username=" + username + " ]";
    }

}
