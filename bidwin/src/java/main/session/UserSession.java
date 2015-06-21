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
import javax.persistence.PersistenceContext;
import main.UserGroups;
import main.UserGroupsPK;

/**
 *
 * @author Davide
 */
@Stateless
public class UserSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    /**
     * function that save the current user in the db
     * the newly created user will be added to the USER group
     * @param user the user that will be saved on the database
     */
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

    /**
     * function that update the pocket of the user (recharge profile)
     * @param total the new value of his/her pocket (after a recharge)
     */
    public void updatepocket(int total) {
	Users user = getPrincipalUser();
        user.setCredits(total);
        em.merge(user);
    }

    /**
     * function that updates the ranking of the user.
     * The mean is used to evaluate the overall user's rank
     * @param username the username of the user
     * @param vote the new vote that concurs to evaluate the updated mean
     */
    public void updateranking (String username, int vote) {
	Users user = find(username);
        user.setAuctionCounter( user.getAuctionCounter() + 1 );
        user.setRanking( Math.round((user.getRanking() * (user.getAuctionCounter() - 1) + vote ) / user.getAuctionCounter()) );
        em.merge(user);
    }

    /**
     * get all the users
     * @return the list of all the users
     */
    public List<Users> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Users.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * function that checks if a username is still present in the db.If so, this parameter cannot be used again as
     * new registration's username
     * @param user the username used to perform the check
     * @return true if the username can be used for registration (still not present), false otherwise
     */
    public boolean usernamecheck(UsersDTO user) {
        for (Users u : findAll()) {
            if (u.getUsername().equals( user.getUsername() )) {
                return false;
            }
        }
        return true;
    }

    /**
     * function that returns the current logged user
     * @return the current logged user
     */
    public Users getPrincipalUser() {
    	return find(getPrincipalUsername());
    }

    /**
     * find a user by its username
     * @param username the parameter used to performs the search
     * @return the user (if exists)
     */
    public Users find(String username) {
    	return em.find(Users.class, username);
    }

    /**
     * function that returns the username of current logged user
     * @return the username of current logged user
     */
    public String getPrincipalUsername() {
    	return context.getCallerPrincipal().getName();
    }

}
