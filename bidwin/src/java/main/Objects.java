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
 * @author Ga
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

    public Objects() {
    }

    public Objects(ObjectsDTO object, Users username) {
        
        this.objectName    = object.getObjectName();
        this.objectType    = object.getObjectType();
        this.description   = object.getDescription();
        this.imageLink     = object.getImageLink();
        this.username      = username;
    }

    public Objects(Integer objectid, String objectName, String objectType, String description, String imageLink) {
        this.objectid = objectid;
        this.objectName = objectName;
        this.objectType = objectType;
        this.description = description;
        this.imageLink = imageLink;
    }

    public Integer getObjectid() {
        return objectid;
    }

    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Users getUsername() {
        return username;
    }

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
