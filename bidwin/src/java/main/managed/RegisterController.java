package main.managed;

import main.dto.UsersDTO;
import main.session.UserSession;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Davide
 */
@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterController {
	
    @EJB
    private UserSession usersession;

    private UsersDTO user;


    /**
     * empty constructor for the class
     */
    public RegisterController() {
            user = new UsersDTO();
    }

    /**
     * get the current userDTO
     * @return the current userDTO
     */
    public UsersDTO getUser() {
            return user;
    }

    /**
     * set the current userDTO
     * @param user  the current userDTO
     */
    public void setUser(UsersDTO user) {
            this.user = user;
    }

    /**
     * register a new user to the system and the db
     * @return login page if succeeds, registration page if fails
     */
    public String register() {
            if ( usersession.usernamecheck(user) ) {
                usersession.save(user);
                return "/login?faces-redirect=true";
            } else {
                return "/register?faces-redirect=true&alreadyused=true";
            }
    }

}
