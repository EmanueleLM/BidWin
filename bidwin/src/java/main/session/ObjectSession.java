/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Query jpqlQuery = em.createNativeQuery("Select * from objects where username = ?1",Objects.class);
        jpqlQuery.setParameter(1, usersession.getPrincipalUsername() );
        List<Objects> results = (List<Objects>) jpqlQuery.getResultList();
        return results;
        } catch(NoResultException e) { 
            return null;
        }
    }
    
    public Objects getObjectFromId(int objectid){
        try {
        Query jpqlQuery = em.createNativeQuery("Select * from objects where Object_id = ?1",Objects.class);
        jpqlQuery.setParameter(1, objectid );
        Objects result = (Objects) jpqlQuery.getSingleResult();
        return result;
        } catch(NoResultException e) { 
            return null;
        }
    }

}
