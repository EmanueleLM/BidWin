package it.polimi.se2.bidwin.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean (name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

        public UserBean() {
        }  
        
        private String userid;
	private String name;
        private String surname;
        private String password;
        private String email;
        private String address;

    public String getUserid() {
        return this.userid;
    }

    private void setUserid(String userid) {
        this.userid = (name+(surname.substring(0,3)));
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
          

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
}