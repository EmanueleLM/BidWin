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
import main.dto.AuctionDTO;
import main.dto.ObjectsDTO;
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
public class ObjectSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public ObjectSessionTest() {
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
     * Test of save method, of class ObjectSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        
    }

    /**
     * Test of getMyObjects method, of class ObjectSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyObjects() throws Exception {
        
    }

    /**
     * Test of auctioncheck method, of class ObjectSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuctioncheck() throws Exception {
        
    }

    /**
     * Test of getObjectFromId method, of class ObjectSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetObjectFromId() throws Exception {
        
    }

    /**
     * Test of getAllAuctions method, of class ObjectSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllAuctions() throws Exception {
        
    }

}
