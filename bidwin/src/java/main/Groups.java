/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Emanuele
 */
@Entity
@Table(name = "groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGroupId", query = "SELECT g FROM Groups g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "Groups.findByGroupName", query = "SELECT g FROM Groups g WHERE g.groupName = :groupName"),
    @NamedQuery(name = "Groups.findByGroupDesc", query = "SELECT g FROM Groups g WHERE g.groupDesc = :groupDesc")})
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private Integer groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "group_name")
    private String groupName;
    @Size(max = 200)
    @Column(name = "group_desc")
    private String groupDesc;
    @JoinTable(name = "user_groups", joinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "group_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Username", referencedColumnName = "Username")})
    @ManyToMany
    private Collection<Users> usersCollection;

    /**
     *contructor (empty) of the class
     */
    public Groups() {
    }

    /**
     * constructor with the parameter groupid
     * @param groupId the groupId which identifies the system population w.r.t the permissions on specific url (and functionalities)
     */
    public Groups(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * constructor with the parameter groupid
     * @param groupId the groupId which identifies the system population w.r.t the permissions on specific url (and functionalities)
     * @param groupName the specific name of a group (which allows specific permissions)
     */
    public Groups(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    /**
     * get the groupId of a specific group
     * @return the groupid of a specific group
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * set the groupId of a specific group
     * @param groupId the groupId of a specific group
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * get the groupName of a specific group
     * @return the groupName of a specific group
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * set the groupName of a specific group
     * @param groupName the groupName of a specific group
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * get the group descritpion
     * @return the group description
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * set the group descritpion
     * @param groupDesc  the group description
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    /**
     * get the users who belong to a group
     * @return the users who belong to a group
     */
    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    /**
     * set the users who belong to a group
     * @param usersCollection the users who belong to a group
     */
    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.Groups[ groupId=" + groupId + " ]";
    }
    
}
