package it.polimi.se2.bidwin.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

@ManagedBean (name = "helloBean")
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

        public HelloBean() {
        }  
        
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}