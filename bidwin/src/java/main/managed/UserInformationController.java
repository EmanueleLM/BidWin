package main.managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import main.session.UserSession;


@ManagedBean(name="userInfo")
@RequestScoped
public class UserInformationController {
    
    @EJB
    private UserSession usersession;
    
    
    
    
    public String showUsername() {
        return usersession.getPrincipalUser().getUsername();
    }
    
    public String showName() {
        return usersession.getPrincipalUser().getName();
    }

}