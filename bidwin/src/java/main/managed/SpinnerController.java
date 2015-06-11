/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.managed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import main.session.UserSession;
/**
 *
 * @author Ga
 */

@ManagedBean(name="spinnerView") 
@RequestScoped
public class SpinnerController {
     
    private int number;
    private int total;
    
    @EJB
    private UserSession usersession;

 
    public int getNumber() {
        return number;
    }
 
    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotal() {
        total = this.number + usersession.getPrincipalUser().getCredits();
        return total;
    }

    public void setTotal(int total) {
        this.total = usersession.getPrincipalUser().getCredits();
    }
    
    
}