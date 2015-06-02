/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.main.se2.polimi.java;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ga
 */
@Stateless
public class ObjectsFacade extends AbstractFacade<Objects> {
    @PersistenceContext(unitName = "bidwinRealmPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjectsFacade() {
        super(Objects.class);
    }
    
}
