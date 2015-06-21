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
import org.mockito.Mock;

/**
 *
 * @author Mago
 */
public class BidSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public BidSessionTest() {
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
     * Test of save method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        
    }

    /**
     * Test of notifyTrue method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testNotifyTrue() throws Exception {
        
    }

    /**
     * Test of getMyOpenedBids method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyOpenedBids() throws Exception {
        
    }

    /**
     * Test of getMyClosedBids method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyClosedBids() throws Exception {
        
    }

    /**
     * Test of bidcheck method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testBidcheck() throws Exception {
        
    }

    /**
     * Test of getAuctionFromId method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAuctionFromId() throws Exception {
        
    }

    /**
     * Test of getMySpecifiedBids method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMySpecifiedBids() throws Exception {
        
    }

    /**
     * Test of auctionsToNotify method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuctionsToNotify() throws Exception {
        
    }

    /**
     * Test of partecipants method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testPartecipants() throws Exception {
        
    }

    /**
     * Test of isWinner method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsWinner() throws Exception {
        
    }

    /**
     * Test of getWinner method, of class BidSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetWinner() throws Exception {
        
    }

}
