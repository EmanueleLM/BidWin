/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import main.Auction;
import main.Objects;
import main.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;

/**
 *
 * @author Mago
 */
public class SearchSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public SearchSessionTest() {
    }
    
    @Before
    public void setUp() {
        
        u1.setUsername("username");
        u1.setName("user");
        o1.setUsername(u1);
        o1.setObjectid(1);
        o1.setObjectName("obj");
        o1.setAuction(a1);
        a1.setAuctionid(1);
        
    }

    /**
     * Test of getUsersByName method, of class SearchSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUsersByName() throws Exception {
        
    }

    /**
     * Test of getHisOpenedAuctions method, of class SearchSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetHisOpenedAuctions() throws Exception {
        
    }

    /**
     * Test of getAuctionsByTag method, of class SearchSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAuctionsByTag() throws Exception {
        
    }

}
