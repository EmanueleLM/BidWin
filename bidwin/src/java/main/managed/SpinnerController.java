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
 * @author Emanuele
 */

@ManagedBean(name="spinnerView") 
@RequestScoped
public class SpinnerController {
     
    private int number;
    private int numberauction;
    private float numberbid;
    private float numbervote;
    private int total;
    
    @EJB
    private UserSession usersession;

    /**
     * empty constructor for the current class, used to initialize the spinner
     */   
    public SpinnerController() {
        number = 0;
        numberauction = 5;
        total = number;
        numberbid = 0;
        numbervote = 1;
    }
 
    /**
     *  function used to keep track of the pocket of the user, and recharge it
     */
    public void rechargePocket() {
        usersession.updatepocket(this.number + usersession.getPrincipalUser().getCredits());
        setNumber(0);
    }

    /**
     * get the number visualized by the spinner
     * @return the number visualized by the spinner
     */
    public int getNumber() {
        return number;
    }

    /**
     * set the number visualized by the spinner
     * @param number  the number visualized by the spinner
     */    
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * get the total (number + parameter) visualized by the spinner
     * @return the total (number + parameter) number visualized by the spinner
     */  
    public int getTotal() {
        total = this.number + usersession.getPrincipalUser().getCredits();
        return total;
    }

    /**
     * set the total (number + parameter) visualized by the spinner
     * @param total the total (number + parameter) number visualized by the spinner
     */  
    public void setTotal(int total) {
        this.total = usersession.getPrincipalUser().getCredits();
    }

    /**
     * get the numberauction visualized by the spinner
     * @return the numberauction visualized by the spinner
     */ 
    public int getNumberauction() {
        return numberauction;
    }

    /**
     * set the numberauction visualized by the spinner
     * @param numberauction visualized by the spinner
     */ 
    public void setNumberauction(int numberauction) {
        this.numberauction = numberauction;
    }

    /**
     * get the numberbid visualized by the spinner
     * @return the numberbid visualized by the spinner
     */ 
    public float getNumberbid() {
        return numberbid;
    }

    /**
     * set the numberbid visualized by the spinner
     * @param numberbid  visualized by the spinner
     */ 
    public void setNumberbid(float numberbid) {
        this.numberbid = numberbid;
    }

    /**
     * get the numbervote visualized by the spinner
     * @return the numbervote visualized by the spinner
     */ 
    public float getNumbervote() {
        return numbervote;
    }

    /**
     * set the numbervote visualized by the spinner
     * @param numbervote visualized by the spinner
     */ 
    public void setNumbervote(float numbervote) {
        this.numbervote = numbervote;
    }

}