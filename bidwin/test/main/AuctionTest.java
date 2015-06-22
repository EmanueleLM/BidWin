/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mago
 */
public class AuctionTest {
    
    public AuctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAuctionid method, of class Auction.
     */
    @Test
    public void testGetAuctionid() {
        System.out.println("getAuctionid");
        Auction instance = new Auction();
        Integer expResult = null;
        Integer result = instance.getAuctionid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuctionid method, of class Auction.
     */
    @Test
    public void testSetAuctionid() {
        System.out.println("setAuctionid");
        Integer auctionid = null;
        Auction instance = new Auction();
        instance.setAuctionid(auctionid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartTime method, of class Auction.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        Auction instance = new Auction();
        Date expResult = null;
        Date result = instance.getStartTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartTime method, of class Auction.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        Date startTime = null;
        Auction instance = new Auction();
        instance.setStartTime(startTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndTime method, of class Auction.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        Auction instance = new Auction();
        Date expResult = null;
        Date result = instance.getEndTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndTime method, of class Auction.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        Date endTime = null;
        Auction instance = new Auction();
        instance.setEndTime(endTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectid method, of class Auction.
     */
    @Test
    public void testGetObjectid() {
        System.out.println("getObjectid");
        Auction instance = new Auction();
        Objects expResult = null;
        Objects result = instance.getObjectid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setObjectid method, of class Auction.
     */
    @Test
    public void testSetObjectid() {
        System.out.println("setObjectid");
        Objects objectid = null;
        Auction instance = new Auction();
        instance.setObjectid(objectid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotify method, of class Auction.
     */
    @Test
    public void testGetNotify() {
        System.out.println("getNotify");
        Auction instance = new Auction();
        int expResult = 0;
        int result = instance.getNotify();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotify method, of class Auction.
     */
    @Test
    public void testSetNotify() {
        System.out.println("setNotify");
        int notify = 0;
        Auction instance = new Auction();
        instance.setNotify(notify);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBidCollection method, of class Auction.
     */
    @Test
    public void testGetBidCollection() {
        System.out.println("getBidCollection");
        Auction instance = new Auction();
        Collection<Bid> expResult = null;
        Collection<Bid> result = instance.getBidCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBidCollection method, of class Auction.
     */
    @Test
    public void testSetBidCollection() {
        System.out.println("setBidCollection");
        Collection<Bid> bidCollection = null;
        Auction instance = new Auction();
        instance.setBidCollection(bidCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Auction.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Auction instance = new Auction();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Auction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Auction instance = new Auction();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Auction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Auction instance = new Auction();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
