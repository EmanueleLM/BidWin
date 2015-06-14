/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.List;
import main.Users;
import main.dto.UsersDTO;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import main.UserGroups;
import main.UserGroupsPK;

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
        UserGroups usergroup = new UserGroups();
        UserGroupsPK usergroupPK = new UserGroupsPK();
        usergroupPK.setGroupId(1);
        usergroupPK.setUsername(user.getUsername());
        usergroup.setUserGroupsPK(usergroupPK);
	em.persist(newuser);
        em.persist(usergroup);
    }

    public void updatepocket(int total) {
	Users user = getPrincipalUser();
        user.setCredits(total);
        em.merge(user);
    }

    public List<Users> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Users.class));
        return em.createQuery(cq).getResultList();
    }

    public boolean usernamecheck(UsersDTO user) {
        for (Users u : findAll()) {
            if (u.getUsername().equals( user.getUsername() )) {
                return false;
            }
        }
        return true;
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

    public List<Users> getUsersByName(String str){
        try {
        Query jpqlQuery = em.createNativeQuery("Select users.* from users where  users.Username LIKE % ?1 %  or  users.Name = ?1  or  users.Surname = ?1",Users.class);
        jpqlQuery.setParameter(1, str );
        List<Users> results = (List<Users>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
