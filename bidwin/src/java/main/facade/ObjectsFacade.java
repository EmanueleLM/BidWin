/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import main.Objects;

/**
 *
 * @author Emanuele
 */
@Stateless
public class ObjectsFacade extends AbstractFacade<Objects> {
    @PersistenceContext(unitName = "bidwinrealmPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * abstract facade method for the class objects
     */
    public ObjectsFacade() {
        super(Objects.class);
    }
    
}
