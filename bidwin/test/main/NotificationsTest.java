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
public class NotificationsTest {
    
    public NotificationsTest() {
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
     * Test of getNotificationid method, of class Notifications.
     */
    @Test
    public void testGetNotificationid() {
        System.out.println("getNotificationid");
        Notifications instance = new Notifications();
        Integer expResult = null;
        Integer result = instance.getNotificationid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotificationid method, of class Notifications.
     */
    @Test
    public void testSetNotificationid() {
        System.out.println("setNotificationid");
        Integer notificationid = null;
        Notifications instance = new Notifications();
        instance.setNotificationid(notificationid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class Notifications.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Notifications instance = new Notifications();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Notifications.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Notifications instance = new Notifications();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuctionId method, of class Notifications.
     */
    @Test
    public void testGetAuctionId() {
        System.out.println("getAuctionId");
        Notifications instance = new Notifications();
        int expResult = 0;
        int result = instance.getAuctionId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuctionId method, of class Notifications.
     */
    @Test
    public void testSetAuctionId() {
        System.out.println("setAuctionId");
        int auctionId = 0;
        Notifications instance = new Notifications();
        instance.setAuctionId(auctionId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNotificationtype method, of class Notifications.
     */
    @Test
    public void testGetNotificationtype() {
        System.out.println("getNotificationtype");
        Notifications instance = new Notifications();
        Integer expResult = null;
        Integer result = instance.getNotificationtype();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotificationtype method, of class Notifications.
     */
    @Test
    public void testSetNotificationtype() {
        System.out.println("setNotificationtype");
        Integer notificationtype = null;
        Notifications instance = new Notifications();
        instance.setNotificationtype(notificationtype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Notifications.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Notifications instance = new Notifications();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Notifications.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Notifications instance = new Notifications();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Notifications.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Notifications instance = new Notifications();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
