/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
public class BidTest {
    
    public BidTest() {
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
     * Test of getBidPK method, of class Bid.
     */
    @Test
    public void testGetBidPK() {
        System.out.println("getBidPK");
        Bid instance = new Bid();
        BidPK expResult = null;
        BidPK result = instance.getBidPK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBidPK method, of class Bid.
     */
    @Test
    public void testSetBidPK() {
        System.out.println("setBidPK");
        BidPK bidPK = null;
        Bid instance = new Bid();
        instance.setBidPK(bidPK);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Bid.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Bid instance = new Bid();
        Users expResult = null;
        Users result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Bid.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Users users = null;
        Bid instance = new Bid();
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuction method, of class Bid.
     */
    @Test
    public void testGetAuction() {
        System.out.println("getAuction");
        Bid instance = new Bid();
        Auction expResult = null;
        Auction result = instance.getAuction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuction method, of class Bid.
     */
    @Test
    public void testSetAuction() {
        System.out.println("setAuction");
        Auction auction = null;
        Bid instance = new Bid();
        instance.setAuction(auction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Bid.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Bid instance = new Bid();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Bid.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Bid instance = new Bid();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Bid.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bid instance = new Bid();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
