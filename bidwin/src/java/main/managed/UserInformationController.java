package main.managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import main.session.UserSession;

/**
 * 
 * @author Emanuele
 */

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
    
    public String showSurname() {
        return usersession.getPrincipalUser().getSurname();
    }

    public String showEmail() {
        return usersession.getPrincipalUser().getEmail();
    }
    
    public int showCredits() {
        return usersession.getPrincipalUser().getCredits();
    }
    
    public String showPaymentInfo() {
        return usersession.getPrincipalUser().getPaymentInfo();
    }
    
    public int showRanking() {
        return usersession.getPrincipalUser().getRanking();
    }
    
    public int showAuctionCounter() {
        return usersession.getPrincipalUser().getAuctionCounter();
    }

}