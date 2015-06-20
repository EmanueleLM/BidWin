/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide
 */
@Entity
@Table(name = "user_groups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroups.findAll", query = "SELECT u FROM UserGroups u"),
    @NamedQuery(name = "UserGroups.findByUsername", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.username = :username"),
    @NamedQuery(name = "UserGroups.findByGroupId", query = "SELECT u FROM UserGroups u WHERE u.userGroupsPK.groupId = :groupId"),
    @NamedQuery(name = "UserGroups.findByUriId", query = "SELECT u FROM UserGroups u WHERE u.uriId = :uriId")})
public class UserGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserGroupsPK userGroupsPK;
    @Column(name = "uriId")
    private Integer uriId;

    /**
     * empty contructor for the class
     */
    public UserGroups() {
    }

    /**
     * constructor with parameters
     * @param userGroupsPK the UserGroup manager (manages feature like external keys etc.)
     */
    public UserGroups(UserGroupsPK userGroupsPK) {
        this.userGroupsPK = userGroupsPK;
    }

    /**
     * contructor with parameters
     * @param username the username of the user of a given group
     * @param groupId the id of a username of a given group
     */
    public UserGroups(String username, int groupId) {
        this.userGroupsPK = new UserGroupsPK(username, groupId);
    }

    /**
     * get the UserPK
     * @return the userPK
     */
    public UserGroupsPK getUserGroupsPK() {
        return userGroupsPK;
    }

    /**
     * set the userPK
     * @param userGroupsPK the userPK
     */
    public void setUserGroupsPK(UserGroupsPK userGroupsPK) {
        this.userGroupsPK = userGroupsPK;
    }

    /**
     * function that is used to maintain the properties of the db checked
     * @return an int that is used to maintain the properties of the dbchecked
     */
    public Integer getUriId() {
        return uriId;
    }
    
    /**
     * set an integer that is used to maintain the properties of the db checked
     * @param uriId integer used to maintain the properties of the db checked
     */
    public void setUriId(Integer uriId) {
        this.uriId = uriId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupsPK != null ? userGroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroups)) {
            return false;
        }
        UserGroups other = (UserGroups) object;
        if ((this.userGroupsPK == null && other.userGroupsPK != null) || (this.userGroupsPK != null && !this.userGroupsPK.equals(other.userGroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.UserGroups[ userGroupsPK=" + userGroupsPK + " ]";
    }
    
}
