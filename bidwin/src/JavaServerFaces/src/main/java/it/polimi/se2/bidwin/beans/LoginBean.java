package it.polimi.se2.bidwin.beans;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ga
 */
@ManagedBean
@SessionScoped

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
        
	private String name;
        
        public LoginBean(String name) {
            this.name = name;
        }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
}
