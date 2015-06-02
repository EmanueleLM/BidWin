/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.se2.polimi.java;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ga
 */
@Entity
@Table(name = "v_user_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VUserRole.findAll", query = "SELECT v FROM VUserRole v"),
    @NamedQuery(name = "VUserRole.findByUsername", query = "SELECT v FROM VUserRole v WHERE v.username = :username"),
    @NamedQuery(name = "VUserRole.findByPassword", query = "SELECT v FROM VUserRole v WHERE v.password = :password"),
    @NamedQuery(name = "VUserRole.findByGroupName", query = "SELECT v FROM VUserRole v WHERE v.groupName = :groupName")})
public class VUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "username")
    @Id
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "group_name")
    @Id
    private String groupName;

    public VUserRole() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
}
