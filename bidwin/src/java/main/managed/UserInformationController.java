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
    
    /**
     * get the principal username of the user
     * @return the principal username of the user
     */
    public String showUsername() {
        return usersession.getPrincipalUser().getUsername();
    }
    
    /**
     * get the principal name of the user
     * @return the principal name of the user
     */    
    public String showName() {
        return usersession.getPrincipalUser().getName();
    }
    
    /**
     * get the principal surname of the user
     * @return the principal surname of the user
     */     
    public String showSurname() {
        return usersession.getPrincipalUser().getSurname();
    }
    
    /**
     * get the principal mail of the user
     * @return the principal mail of the user
     */  
    public String showEmail() {
        return usersession.getPrincipalUser().getEmail();
    }
    
    /**
     * get the credits of the user
     * @return the credits of the user
     */      
    public int showCredits() {
        return usersession.getPrincipalUser().getCredits();
    }
    
    /**
     * get the paymentinfo of the user
     * @return the paymentinfo of the user
     */     
    public String showPaymentInfo() {
        return usersession.getPrincipalUser().getPaymentInfo();
    }
    
    /**
     * get the rank of the user
     * @return the rank of the user
     */ 
    public int showRanking() {
        return usersession.getPrincipalUser().getRanking();
    }
    
    /**
     * get the auction counter of the user
     * @return the auction counter of the user
     */   
    public int showAuctionCounter() {
        return usersession.getPrincipalUser().getAuctionCounter();
    }

}