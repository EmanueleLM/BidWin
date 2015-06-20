/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import main.dto.ObjectsDTO;

/**
 *
 * @author Emanuele
 */
@Entity
@Table(name = "objects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objects.findAll", query = "SELECT o FROM Objects o"),
    @NamedQuery(name = "Objects.findAllByUsername", query = "SELECT o FROM Objects o WHERE o.username = :username"),
    @NamedQuery(name = "Objects.findByObjectid", query = "SELECT o FROM Objects o WHERE o.objectid = :objectid"),
    @NamedQuery(name = "Objects.findByObjectName", query = "SELECT o FROM Objects o WHERE o.objectName = :objectName"),
    @NamedQuery(name = "Objects.findByObjectType", query = "SELECT o FROM Objects o WHERE o.objectType = :objectType"),
    @NamedQuery(name = "Objects.findByDescription", query = "SELECT o FROM Objects o WHERE o.description = :description"),
    @NamedQuery(name = "Objects.findByImageLink", query = "SELECT o FROM Objects o WHERE o.imageLink = :imageLink")})
public class Objects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Object_id")
    private Integer objectid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ObjectName")
    private String objectName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ObjectType")
    private String objectType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ImageLink")
    private String imageLink;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "objectid")
    private Auction auction;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne(optional = false)
    private Users username;

    /**
     *empty contructor of the class
     */
    public Objects() {
    }
    
    /**
     * contructor with parameters used when an object is uploaded by a user
     * @param object the objectDTO used to get info from the object when newly created
     * @param username the username of the user who creates the object
     */
    public Objects(ObjectsDTO object, Users username) {
        
        this.objectName    = object.getObjectName();
        this.objectType    = object.getObjectType();
        this.description   = object.getDescription();
        this.imageLink     = object.getImageLink();
        this.username      = username;
    }

    /**
     * contructor with parameters
     * @param objectid the objectid of the object
     * @param objectName the object name
     * @param objectType the object type (used by the serach engine)
     * @param description a short description of the object
     * @param imageLink link to image
     */
    public Objects(Integer objectid, String objectName, String objectType, String description, String imageLink) {
        this.objectid = objectid;
        this.objectName = objectName;
        this.objectType = objectType;
        this.description = description;
        this.imageLink = imageLink;
    }

    /**
     * get the objectid from a given object
     * @return the objectid from a given object
     */
    public Integer getObjectid() {
        return objectid;
    }

    /**
     * set the objectid from a given object
     * @param objectid the objectid from a given object
     */ 
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    /**
     * get the objectname from a given object
     * @return the objectname from a given object
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * set the objectname from a given object
     * @param objectName the objectname from a given object
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    /**
     * get the objecttype from a given object
     * @return the objecttype from a given object
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * set the objecttype from a given object
     * @param objectType the objecttype from a given object
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    /**
     * get the object descritpion from a given object
     * @return the object descritpion from a given object
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the object descritpion from a given object
     * @param description the object descritpion from a given object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get the image link from a given object
     * @return the image link from a given object
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * set the image link from a given object
     * @param imageLink the image link from a given object
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     * get the auction from a given object
     * @return the auction from a given object
     */
    public Auction getAuction() {
        return auction;
    }

    /**
     * set the auction from a given object
     * @param auction the auction from a given object
     */
    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    /**
     * get the username of the user who owns this specific object
     * @return the username of the user who owns this specific object
     */
    public Users getUsername() {
        return username;
    }

    /**
     * set the username of the user who owns this specific object
     * @param username the username of the user who owns this specific object
     */
    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objectid != null ? objectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objects)) {
            return false;
        }
        Objects other = (Objects) object;
        return !((this.objectid == null && other.objectid != null) || (this.objectid != null && !this.objectid.equals(other.objectid)));
    }

    @Override
    public String toString() {
        return this.objectName;
    }

}
