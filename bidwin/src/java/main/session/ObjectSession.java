/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import main.Objects;
import main.dto.ObjectsDTO;

/**
 *
 * @author Mago
 */
@Stateless
public class ObjectSession {
    @PersistenceContext(unitName = "bidwinrealmPU")
    protected EntityManager em;
    
    @Resource
    protected EJBContext context;
    
    @EJB
    private UserSession usersession;
    
    
    public void save(ObjectsDTO object) {
	Objects newobject = new Objects(object,usersession.getPrincipalUser());
	em.persist(newobject);
    }

    public List<Objects> getMyObjects(){
        try {
            List<Objects> myobjects = (ArrayList<Objects>)em.createQuery("SELECT o.* FROM objects o WHERE o.Username=:us").setParameter("us", usersession.getPrincipalUsername() ).getResultList();
            return myobjects;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
