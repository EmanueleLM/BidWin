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
        return usersession.getPrincipalUser().getUsername().toString();
    }
    
    public String showName() {
        return usersession.getPrincipalUser().getName().toString();
    }
    
    public String showSurname() {
        return usersession.getPrincipalUser().getSurname().toString();
    }

    public String showEmail() {
        return usersession.getPrincipalUser().getEmail().toString();
    }
    
    public int showCredits() {
        return usersession.getPrincipalUser().getCredits();
    }
    
    public String showPaymentInfo() {
        return usersession.getPrincipalUser().getPaymentInfo().toString();
    }
}