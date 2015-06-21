package main.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Emanuele
 */

@ManagedBean(name="logoutBean")
@RequestScoped
public class LogoutController {
    
    /**
     * performs the logout from the system
     * @return the login page 
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/user/welcome?faces-redirect=true";
    }

}
