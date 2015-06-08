package main.managed;

import main.dto.UsersDTO;
import main.session.UserSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterController {
	
	@EJB
	private UserSession usersession;

	private UsersDTO user;


	public RegisterController() {
		user = new UsersDTO();
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}
	
	public String register() {
		usersession.save(user);
		return "/login?faces-redirect=true";
	}

}
