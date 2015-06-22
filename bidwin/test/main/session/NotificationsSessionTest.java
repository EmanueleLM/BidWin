/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.session;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import main.Auction;
import main.Notifications;
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
public class NotificationsSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public NotificationsSessionTest() {
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
     * Test of save method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        
    }

    /**
     * Test of replicateobject method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testReplicateobject() throws Exception {
        
    }

    /**
     * Test of owner method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testOwner() throws Exception {
        
    }

    /**
     * Test of getMyNotifications method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMyNotifications() throws Exception {
        
    }

    /**
     * Test of getUsersToVote method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUsersToVote() throws Exception {
        
    }

    /**
     * Test of setUserVoted method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetUserVoted() throws Exception {
        
    }

    /**
     * Test of getStringNotifications method, of class NotificationsSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetStringNotifications() throws Exception {
        
    }

}
