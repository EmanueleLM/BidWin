/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.ArrayList;
import java.util.List;
import main.Users;
import main.Groups;
import main.dto.UsersDTO;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mago
 */
@Stateless
public class UserSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    
    public void save(UsersDTO user) {
	Users newuser = new Users(user);
        
        // Lele devi farlo qua
        
	em.persist(newuser);
    }



    // Ritorna l'utente loggato come oggetto
    public Users getPrincipalUser() {
    	return find(getPrincipalUsername());
    }

    public Users find(String username) {
    	return em.find(Users.class, username);
    }

    public String getPrincipalUsername() {
    	return context.getCallerPrincipal().getName();
    }

}
