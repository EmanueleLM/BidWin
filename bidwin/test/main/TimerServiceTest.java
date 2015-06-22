/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.ejb.embeddable.EJBContainer;
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
public class TimerServiceTest {
    
    public TimerServiceTest() {
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
     * Test of doWork method, of class TimerService.
     */
    @Test
    public void testDoWork() throws Exception {
        System.out.println("doWork");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TimerService instance = (TimerService)container.getContext().lookup("java:global/classes/TimerService");
        instance.doWork();
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyUsers method, of class TimerService.
     */
    @Test
    public void testNotifyUsers() throws Exception {
        System.out.println("notifyUsers");
        Auction a = null;
        boolean win = false;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        TimerService instance = (TimerService)container.getContext().lookup("java:global/classes/TimerService");
        instance.notifyUsers(a, win);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
