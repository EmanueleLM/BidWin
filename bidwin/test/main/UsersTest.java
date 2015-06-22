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
public class UsersTest {
    
    public UsersTest() {
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
     * Test of getUsername method, of class Users.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Users.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Users instance = new Users();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Users.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Users.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Users instance = new Users();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSurname method, of class Users.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getSurname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSurname method, of class Users.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        String surname = "";
        Users instance = new Users();
        instance.setSurname(surname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Users.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Users.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Users instance = new Users();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRanking method, of class Users.
     */
    @Test
    public void testGetRanking() {
        System.out.println("getRanking");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getRanking();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRanking method, of class Users.
     */
    @Test
    public void testSetRanking() {
        System.out.println("setRanking");
        int ranking = 0;
        Users instance = new Users();
        instance.setRanking(ranking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class Users.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class Users.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        Users instance = new Users();
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentInfo method, of class Users.
     */
    @Test
    public void testGetPaymentInfo() {
        System.out.println("getPaymentInfo");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getPaymentInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentInfo method, of class Users.
     */
    @Test
    public void testSetPaymentInfo() {
        System.out.println("setPaymentInfo");
        String paymentInfo = "";
        Users instance = new Users();
        instance.setPaymentInfo(paymentInfo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuctionCounter method, of class Users.
     */
    @Test
    public void testGetAuctionCounter() {
        System.out.println("getAuctionCounter");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getAuctionCounter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuctionCounter method, of class Users.
     */
    @Test
    public void testSetAuctionCounter() {
        System.out.println("setAuctionCounter");
        int auctionCounter = 0;
        Users instance = new Users();
        instance.setAuctionCounter(auctionCounter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBirthdate method, of class Users.
     */
    @Test
    public void testGetBirthdate() {
        System.out.println("getBirthdate");
        Users instance = new Users();
        Date expResult = null;
        Date result = instance.getBirthdate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBirthdate method, of class Users.
     */
    @Test
    public void testSetBirthdate() {
        System.out.println("setBirthdate");
        Date birthdate = null;
        Users instance = new Users();
        instance.setBirthdate(birthdate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCredits method, of class Users.
     */
    @Test
    public void testGetCredits() {
        System.out.println("getCredits");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.getCredits();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCredits method, of class Users.
     */
    @Test
    public void testSetCredits() {
        System.out.println("setCredits");
        int credits = 0;
        Users instance = new Users();
        instance.setCredits(credits);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Users.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Users instance = new Users();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class Users.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Users instance = new Users();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupsCollection method, of class Users.
     */
    @Test
    public void testGetGroupsCollection() {
        System.out.println("getGroupsCollection");
        Users instance = new Users();
        Collection<Groups> expResult = null;
        Collection<Groups> result = instance.getGroupsCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupsCollection method, of class Users.
     */
    @Test
    public void testSetGroupsCollection() {
        System.out.println("setGroupsCollection");
        Collection<Groups> groupsCollection = null;
        Users instance = new Users();
        instance.setGroupsCollection(groupsCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBidCollection method, of class Users.
     */
    @Test
    public void testGetBidCollection() {
        System.out.println("getBidCollection");
        Users instance = new Users();
        Collection<Bid> expResult = null;
        Collection<Bid> result = instance.getBidCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBidCollection method, of class Users.
     */
    @Test
    public void testSetBidCollection() {
        System.out.println("setBidCollection");
        Collection<Bid> bidCollection = null;
        Users instance = new Users();
        instance.setBidCollection(bidCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectsCollection method, of class Users.
     */
    @Test
    public void testGetObjectsCollection() {
        System.out.println("getObjectsCollection");
        Users instance = new Users();
        Collection<Objects> expResult = null;
        Collection<Objects> result = instance.getObjectsCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setObjectsCollection method, of class Users.
     */
    @Test
    public void testSetObjectsCollection() {
        System.out.println("setObjectsCollection");
        Collection<Objects> objectsCollection = null;
        Users instance = new Users();
        instance.setObjectsCollection(objectsCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Users.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Users instance = new Users();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Users.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Users instance = new Users();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Users.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Users instance = new Users();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
