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
 * @author Davide
 */
@Embeddable
public class UserGroupsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "group_id")
    private int groupId;

     /**
     *empty contructor of the class
     */
    public UserGroupsPK() {
    }

    /**
     * contructor with parameters
     * @param username the username of the user
     * @param groupId the group of the userPK
     */
    public UserGroupsPK(String username, int groupId) {
        this.username = username;
        this.groupId = groupId;
    }

    /**
     * get the username from the userPK
     * @return  the username from the userPK
     */
    public String getUsername() {
        return username;
    }

    /**
     * set  the username from the userPK
     * @param username  the username from the userPK
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the group id from the userPK
     * @return the group id from the userPK
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * set the group id from the userPK
     * @param groupId the group id from the userPK
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (int) groupId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroupsPK)) {
            return false;
        }
        UserGroupsPK other = (UserGroupsPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if (this.groupId != other.groupId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.UserGroupsPK[ username=" + username + ", groupId=" + groupId + " ]";
    }
    
}
