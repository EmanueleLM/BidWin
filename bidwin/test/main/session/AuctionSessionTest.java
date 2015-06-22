/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import main.Auction;
import main.Bid;
import main.Objects;
import main.Users;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Mago
 */
public class AuctionSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public AuctionSessionTest() {
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
     * Test of save method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        
    }

    /**
     * Test of getMyOpenedAuctions method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyOpenedAuctions() throws Exception {
       
    }

    /**
     * Test of getMyClosedAuctions method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyClosedAuctions() throws Exception {
       
    }

    /**
     * Test of getAllOpenedAuctions method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllOpenedAuctions() throws Exception {
        
    }

    /**
     * Test of getBidsFromId method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetBidsFromId() throws Exception {
        
    }

    /**
     * Test of getAuctionsByTag method, of class AuctionSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAuctionsByTag() throws Exception {
        
    }

}
