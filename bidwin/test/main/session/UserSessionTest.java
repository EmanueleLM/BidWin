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
import main.dto.UsersDTO;
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
public class UserSessionTest {
    
    
    Users u1;
    Objects o1;
    Auction a1;
    
    @Mock
    ObjectSession os;
    
    @Mock
    UserSession us;
    
    
    public UserSessionTest() {
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
     * Test of save method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        
    }

    /**
     * Test of updatepocket method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdatepocket() throws Exception {
        
    }

    /**
     * Test of updateranking method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateranking() throws Exception {
        
    }

    /**
     * Test of findAll method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testFindAll() throws Exception {
        
    }

    /**
     * Test of usernamecheck method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testUsernamecheck() throws Exception {
        
    }

    /**
     * Test of getPrincipalUser method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrincipalUser() throws Exception {
        
    }

    /**
     * Test of find method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testFind() throws Exception {
        
    }

    /**
     * Test of getPrincipalUsername method, of class UserSession.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrincipalUsername() throws Exception {
        
    }

}
